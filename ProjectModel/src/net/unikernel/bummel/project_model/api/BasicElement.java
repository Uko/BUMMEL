package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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