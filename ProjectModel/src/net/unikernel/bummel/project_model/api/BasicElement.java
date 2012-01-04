package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	HashMap<String, Connection> connections;
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
	@Override
	public void plug(Connection connection, String port) throws Exception
	{
		if(availablePorts.contains(port))
		{
			this.connections.put(label, connection);
		}
		else
		{//or replace this with "return false;"
			throw new Exception("No such port: '"+port+"' among available ports");
		}
	}
	
	@Override
	public Connection unplug(String port)
	{
		return this.connections.remove(port);
	}
	@Override
	public boolean unplug(Connection connection)
	{
		return this.connections.values().remove(connection);
	}
	
	public abstract ArrayList<Double> process(ArrayList<Double> connectionsValues);
	
	@Override
	public ArrayList<Double> step()
	{
		ArrayList<Double> connectionsValues = new ArrayList<Double>();
		for(Connection connection : connections.values())
		{
			connectionsValues.add(new Double(connection.getValue()));
		}
		return process(connectionsValues);
	}
	
}