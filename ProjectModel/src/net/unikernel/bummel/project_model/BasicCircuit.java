package net.unikernel.bummel.project_model;

import java.util.HashMap;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Connection;
import net.unikernel.bummel.project_model.api.BasicElement;
/**
 *
 * @author uko
 */
public class BasicCircuit implements Circuit
{
	private HashMap<Integer,BasicElement> elements;
	private HashMap<Integer,Connection> connections;
	private int elementCounter=0;
	private int connectionCounter=0;
	
	@Override
	public int addElement(BasicElement element)
	{
		elements.put(++elementCounter, element);
		return elementCounter;
	}
	@Override
	public BasicElement removeElement(int elementId)
	{
		return elements.remove(elementId);
	}
	@Override
	public int connectElements(int first, int firstPort, int second, int secondPort)
	{
		Connection connection = new BasicConnection(elements.get(first), elements.get(second));
		elements.get(first).plug(connection, firstPort);
		elements.get(second).plug(connection, secondPort);
		connections.put(++connectionCounter, connection);
		return elementCounter;
	}
	@Override
	public Connection disconect(int connectionId)
	{
		Connection connection = connections.get(connectionId);
		connection.getFirstElement().unplug(connection);
		connection.getSecondElement().unplug(connection);
		return connections.remove(connectionId);
	}
	@Override
	public void step()
	{
		//TODO: engine implementation
	}
	
}
