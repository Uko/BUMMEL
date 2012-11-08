package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 *
 * @author uko, mcangel
 */
public abstract class BasicElement implements Element
{
	public static final String PROP_STATE = "state";
	public static final String PROP_LBL = "label";
	public static final String PROP_COORDS = "coordinates";
	
	private String label;
	private int state;
	private Point coords;
	private ArrayList<String> availablePorts;
	private PropertyChangeSupport pcs;

	public BasicElement(String[] availablePorts)
	{
		this.availablePorts = new ArrayList<>(Arrays.asList(availablePorts));
		this.pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public String getLabel()
	{
		return label;
	}
	@Override
	public void setLabel(String label)
	{
		String old = this.label;
		this.label = label;
		this.pcs.firePropertyChange(PROP_LBL, old, this.label);
	}
	@Override
	public int getState()
	{
		return state;
	}
	@Override
	public void setState(int state)
	{
		int old = this.state;
		this.state = state;
		this.pcs.firePropertyChange(PROP_STATE, old, this.state);
	}
	@Override
	public Point getCoords()
	{
		return coords;
	}
	@Override
	public void setCoords(Point point)
	{
		Point old = this.coords;
		this.coords = point;
		this.pcs.firePropertyChange(PROP_COORDS, old, this.coords);
	}
	@Override
	public List<String> getPorts()
	{
		return Collections.unmodifiableList(availablePorts);
	}
	/**
	 * Return the port's name by port position   
	 * @param port port's position in the list
	 * @return port's name
	 */
	@Override
	public String getPort(int port)
	{
		return getPorts().get(port);
	}
	
	/**
	 * Changes all null values on the port to 0s
	 * @param valuesOnPorts map of port -> value
	 * @return map of port -> value free of null values e.i. will all available ports.
	 */
	@Override
	public Map<String, Double> nullFreePortsOf(Map<String, Double> valuesOnPorts)
	{
		for(String port : getPorts())
			if(valuesOnPorts.get(port) == null) valuesOnPorts.put(port, new Double(0));
		return valuesOnPorts;
	}
	
	@Override		
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
	{
		this.pcs.addPropertyChangeListener(listener);
	}
	@Override
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
	{
		this.pcs.removePropertyChangeListener(listener);
	}
}