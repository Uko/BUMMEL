/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.engine;

import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author mcangel
 */
public class Engine implements PropertyChangeListener, Runnable
{
	protected mxIGraphModel model;
	protected Thread t;
	
	public Engine()
	{
		this((mxIGraphModel)null);
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
		while(true)
		{
			System.out.println("run");
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public void start()
	{
		t.start();
	}
	
	public void stop()
	{
		Thread tmpT = t;
        t = null;
        if (tmpT != null)
		{
           tmpT.interrupt();
        }
		t = new Thread(this);
	}
}
