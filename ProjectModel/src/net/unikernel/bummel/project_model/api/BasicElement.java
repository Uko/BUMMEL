package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author uko
 */
public abstract class BasicElement implements Element
{	
	String label;
	int state;
	Point coords;
	HashSet<String> availablePorts;

	public BasicElement(String[] availablePorts)
	{
		this.availablePorts = new HashSet<String>(Arrays.asList(availablePorts));
	}
	
	@Override
	public String getLabel()
	{
		return label;
	}
	@Override
	public void setLabel(String label)
	{
		this.label = label;
	}
	@Override
	public int getState()
	{
		return state;
	}
	@Override
	public void setState(int state)
	{
		this.state = state;
	}
	@Override
	public Point getCoords()
	{
		return coords;
	}
	@Override
	public void setCoords(Point point)
	{
		this.coords = point;
	}
}