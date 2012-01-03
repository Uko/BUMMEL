package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author uko
 */
public abstract class BasicElement implements Element
{
	String label;
	int state;
	Point coords;
	ArrayList<Connection> connections;
	
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
	public void plug(Connection connection, int port)
	{
		this.connections.set(port, connection);
	}
	@Override
	public Connection unplug(int port)
	{
		return this.connections.remove(port);
	}
	@Override
	public boolean unplug(Connection connection)
	{
		return this.connections.remove(connection);
	}
	
	public abstract ArrayList<Double> process(ArrayList<Double> connectionsValues);
	
	@Override
	public ArrayList<Double> step()
	{
		ArrayList<Double> connectionsValues = new ArrayList<Double>();
		for(Connection connection : connections)
		{
			connectionsValues.add(new Double(connection.getValue()));
		}
		return process(connectionsValues);
	}
	
}
