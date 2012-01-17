package net.unikernel.bummel.project_model.api;

import java.awt.Point;
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
	String label;
	int state;
	Point coords;
	ArrayList<Integer> availablePorts;

	public BasicElement(Integer[] availablePorts)
	{
		this.availablePorts = new ArrayList<Integer>(Arrays.asList(availablePorts));
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
	@Override
	public List<Integer> getPorts()
	{
		return Collections.unmodifiableList(availablePorts);
	}
}