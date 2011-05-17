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
	private int counter=0;
	@Override
	public void run()
	{
            counter=0;
            while(true)
            {
                counter++;
                //this try catch statement ensures thar thread will stop when something will try to interrupt it.
                //if you need to interupt thred without sleep statements you need to perform check inside method: t.interrupted() or t.isinterrupted()
                try{t.sleep(1);}
                catch (InterruptedException e) {return;}
                t.interrupted();
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
           //tells thread to interrupt
           t.interrupt();
           //interruption kills thread, so we need to create it again to avoid null reference exception when we start it again.
           t = new Thread(this);
           System.out.println("Thread work interrupted on "+counter + " tick");
	}
}
