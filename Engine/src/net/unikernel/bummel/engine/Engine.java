/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.engine;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.view.mxGraph;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import net.unikernel.bummel.basic_elements.BasicElement;
import net.unikernel.bummel.jgraph.ElementModel;
import net.unikernel.bummel.jgraph.ElementPort;

/**
 *
 * @author mcangel
 */
public class Engine implements PropertyChangeListener, Runnable
{
	protected mxIGraphModel model;
	protected Thread t;
	
	public static final String CIRCLE_DONE = "circleDone";

	public Engine()
	{
		this((mxIGraphModel) null);
	}

	public Engine(mxIGraphModel model)
	{
		this.model = model;
		t = new Thread(this);
	}

	public Engine(mxGraph graph)
	{
		this(graph.getModel());
	}

	public Engine(mxGraphComponent graphComponent)
	{
		this(graphComponent.getGraph().getModel());
	}

	public void setModel(mxIGraphModel model)
	{
		stop();
		this.model = model;
		t.start();
	}

	public mxIGraphModel getModel()
	{
		return model;
	}

	@Override
	public void run()
	{
		while (true)
		{
			boolean wereChanges = false;
			mxCell trueRoot = (mxCell) ((mxCell) model.getRoot()).getChildAt(0);
			int amountOfElements = trueRoot.getChildCount();

			for (int i = 0; i < amountOfElements; i++)
			{//цикл обрахунку кожного окремого елемента
				ElementModel elem;
				double[] _acdc;
				try
				{
					elem = (ElementModel) model.getChildAt(trueRoot, i);
					_acdc = ((BasicElement)elem.getValue()).getOnPins();
				}
				catch (Exception e)
				{
					continue;
				}

				//обрахування значень на входах
				double[] acdc = new double[elem.getChildCount()];
				for (int j = 0, m = elem.getChildCount(); j < m; j++)
				{
					ElementPort port;
					try
					{
						port = (ElementPort) elem.getChildAt(j);
					}
					catch (Exception e)
					{
						continue;
					}
					
					//take only first edge
					mxICell edge = (port.getEdgeCount() > 0) ? port.getEdgeAt(0) : null;
					if(edge == null || edge.getValue() == null || edge.getValue().toString().equals(""))
					{
						acdc[j] = 0;
					}
					else
					{
						acdc[j] = Double.valueOf(edge.getValue().toString());
					}
				}
				
				//обрахунок елемента
				((BasicElement)elem.getValue()).touch(acdc);
				elem.setShape(((BasicElement)elem.getValue()).getState());	//set appropriate shape
				acdc = ((BasicElement)elem.getValue()).getOnPins();
				if(_acdc != null && acdc != null && _acdc.length == acdc.length)
				{
					for(int k = 0; k < acdc.length; k++)
					{
						if(_acdc[k] != acdc[k])
						{
							wereChanges = true;
							break;
						}
					}
				}
				else
				{
					wereChanges = true;
				}
			}

			for (int i = 0; i < amountOfElements; i++)
			{//цикл обрахунку кожного зв'язка
				mxCell edge;
				try
				{
					edge = (mxCell) model.getChildAt(trueRoot, i);
				}
				catch (Exception e)
				{
					continue;
				}
				if(edge.isEdge() && edge.getSource() != null && edge.getTarget() != null)
				{
					double[] onPins;
					onPins = ((BasicElement)edge.getSource().getParent().getValue()).getOnPins();
					double a = onPins[edge.getSource().getParent().getIndex(edge.getSource())];
					onPins = ((BasicElement)edge.getTarget().getParent().getValue()).getOnPins();
					double b = onPins[edge.getTarget().getParent().getIndex(edge.getTarget())];
					edge.setValue(new Double((a+b == 0) ? 0 : 1));	//one of them is supposed to be 0
				}
			}

			if(!wereChanges)
				break;
			//this try catch statement ensures that thread will stop when something will try to interrupt it.
			try
			{
				t.sleep(1);
			}
			catch (InterruptedException e)
			{
				break;
			}
			//if you need to interupt thread without sleep statements you need to perform check inside method: t.interrupted() or t.isinterrupted()
			if(t.isInterrupted())
				break;
		}
		//((mxGraphModel)model).fireEvent(new mxEventObject(mxEvent.REPAINT, "region", ((mxCell)model.getRoot()).getGeometry()));
		((mxGraphModel)model).fireEvent(new mxEventObject(Engine.CIRCLE_DONE));
		stop();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void start()
	{
		if(!t.isAlive())
			t.start();
	}

	public void stop()
	{
		if(t.isAlive())
		{
			//tells thread to interrupt
			t.interrupt();			
		}
		//interruption kills thread, so we need to create it again to avoid null reference exception when we start it again.
		t = new Thread(this);
		System.out.println("Thread work interrupted");
	}
}
