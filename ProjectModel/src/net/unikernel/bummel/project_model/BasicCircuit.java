package net.unikernel.bummel.project_model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.lookup.ServiceProvider;
/**
 *
 * @author uko
 */
@ServiceProvider(service=Circuit.class)
public class BasicCircuit implements Circuit, Element
{
	private String label;
	private Point coords;
	private Set<Element> elements;
	private Map<Connection, Double> connections;
	private Map<Element, Map<Integer, Connection>> connectionsByElementAndPort;
	
	public BasicCircuit()
	{
		label = "";
		coords = new Point(0, 0);
		elements = new HashSet<Element>();
		connections = new HashMap<Connection, Double>();
		connectionsByElementAndPort = new HashMap<Element, Map<Integer, Connection>>();
	}
	
	@Override
	public void addElement(Element element)
	{
		this.elements.add(element);
	}
	@Override
	public boolean removeElement(Element element)
	{
		return this.elements.remove(element);
	}
	@Override
	public void connectElements(Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort)
	{
		//if the specified elements are in the circuit
		if(elements.contains(firstElement) && elements.contains(secondElement))
		{
			//make a connection
			Connection temp = new Connection(firstElement, firstElementPort, secondElement, secondElementPort);

			//put it in the connections
			connections.put(temp, 0.);

			//put it in the map of element->port->connection for each element+port pair
			if(!connectionsByElementAndPort.containsKey(firstElement))
			{
				connectionsByElementAndPort.put(firstElement, new HashMap<Integer, Connection>());
			}
			if(!connectionsByElementAndPort.containsKey(secondElement))
			{
				connectionsByElementAndPort.put(secondElement, new HashMap<Integer, Connection>());
			}
			connectionsByElementAndPort.get(firstElement).put(firstElementPort, temp);
			connectionsByElementAndPort.get(secondElement).put(secondElementPort, temp);
		}
	}
	
	@Override
	public void disconectElements(Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort)
	{
		if (connections.remove(new Connection(firstElement, firstElementPort, secondElement, secondElementPort))==null)
		{
			connectionsByElementAndPort.get(firstElement).remove(firstElementPort);
			connectionsByElementAndPort.get(secondElement).remove(secondElementPort);
			if(connectionsByElementAndPort.get(secondElement).isEmpty())
			{
				connectionsByElementAndPort.remove(firstElement);
			}
			if(connectionsByElementAndPort.get(firstElement).isEmpty())
			{
				connectionsByElementAndPort.remove(secondElement);
			}
		}
	}
	@Override
	public void step()
	{
		Map<Connection,ArrayList<Double>> tempoMap = new HashMap<Connection,ArrayList<Double>>();
		for (Connection i : connections.keySet())
		{
			tempoMap.put(i, new ArrayList<Double>());
		}
		for (Element i: elements)
		{
			Map<Integer, Double> portsMap = new TreeMap<Integer, Double>();
			for (Map.Entry<Integer,Connection> j : connectionsByElementAndPort.get(i).entrySet())
			{
				portsMap.put(j.getKey(), connections.get(j.getValue()));
			}
			portsMap = i.process(portsMap);
			for (Map.Entry<Integer,Double> j : portsMap.entrySet())
			{
				tempoMap.get(connectionsByElementAndPort.get(i).get(j.getKey())).add(j.getValue());
			}
		}
		for (Map.Entry<Connection,ArrayList<Double>> i : tempoMap.entrySet())
		{
			connections.put(i.getKey(), (i.getValue().get(0) +i.getValue().get(1))/2);
		}
		
	}
	@Override
	public String getLabel()
	{
		return this.label;
	}
	@Override
	public void setLabel(String label)
	{
		this.label = label;
	}
	@Override
	public int getState()
	{
		return 1;
	}
	@Override
	public void setState(int state)
	{
		//should we set some state?
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
	public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
	{
		this.step();
		return null;
	}
	@Override
	public Set<Element> getElements()
	{
		return Collections.unmodifiableSet(this.elements);
	}

	@Override
	public List<Integer> getPorts()
	{
		return new ArrayList<Integer>();	//Merry Christmas
	}
	
	private class Connection
	{
		private Element firstElement;
		private Integer firstElementPort;
		private Element secondElement;
		private Integer secondElementPort;
		
		public Connection(Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort)
		{
			this.firstElement = firstElement;
			this.firstElementPort = firstElementPort;
			this.secondElement = secondElement;
			this.secondElementPort = secondElementPort;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if(o.getClass().equals(Connection.class))
			{
				Connection temp = (Connection)o;
				if(this.firstElement.equals(temp.firstElement) && this.firstElementPort.equals(temp.firstElementPort) && this.secondElement.equals(temp.secondElement) && this.secondElementPort.equals(temp.secondElementPort))
				{
					return true;
				}
			}
			return false;
		}
		
		@Override
		public int hashCode()
		{
			int hash = 5;
			hash = 67 * hash + (this.firstElement != null ? this.firstElement.hashCode() : 0);
			hash = 67 * hash + (this.firstElementPort != null ? this.firstElementPort.hashCode() : 0);
			hash = 67 * hash + (this.secondElement != null ? this.secondElement.hashCode() : 0);
			hash = 67 * hash + (this.secondElementPort != null ? this.secondElementPort.hashCode() : 0);
			return hash;
		}
		
		/**
		 * @return the firstElement
		 */
		public Element getFirstElement()
		{
			return firstElement;
		}
		/**
		 * @return the firstElementPort
		 */
		public Integer getFirstElementPort()
		{
			return firstElementPort;
		}
		/**
		 * @return the secondElement
		 */
		public Element getSecondElement()
		{
			return secondElement;
		}
		/**
		 * @return the secondElementPort
		 */
		public Integer getSecondElementPort()
		{
			return secondElementPort;
		}	
	}
}