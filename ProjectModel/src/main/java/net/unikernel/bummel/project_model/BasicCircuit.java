package net.unikernel.bummel.project_model;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.util.*;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Connection;
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
	private Map<Element, Map<String, Connection>> elementPortConnection;
	
	public BasicCircuit()
	{
		label = "";
		coords = new Point(0, 0);
		elements = new HashSet<>();
		connections = new HashMap<>();
		elementPortConnection = new HashMap<>();
	}
	
	@Override
	public void addElement(Element element)
	{
		this.elements.add(element);
	}
	@Override
	public boolean removeElement(Element element)
	{
		if(elementPortConnection.containsKey(element))
		{
			for(Object con : elementPortConnection.get(element).values().toArray())
			{
				BasicConnection i = (BasicConnection)con;
				this.disconnectElements(i.getFirstElement(), i.getFirstElementPort(),
						i.getSecondElement(), i.getSecondElementPort());
			}
		}
		return this.elements.remove(element);
	}
	@Override
	public boolean connectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
	{
		//if the specified elements are in the circuit
		if(elements.contains(firstElement) && elements.contains(secondElement))
		{
			//make a connection
			BasicConnection conn = new BasicConnection(firstElement, firstElementPort, secondElement, secondElementPort);
			
			//check if none of elements ports are already used
			if(elementPortConnection.containsKey(firstElement) && elementPortConnection.get(firstElement).containsKey(firstElementPort)
					|| elementPortConnection.containsKey(secondElement) && elementPortConnection.get(secondElement).containsKey(secondElementPort))
			{//if some of them is - cancel current connection
				return false;
			}

			//put it in the connections
			connections.put(conn, 0.);

			//put it in the map of element->port->connection for each element+port pair
			if(!elementPortConnection.containsKey(firstElement))
			{
				elementPortConnection.put(firstElement, new HashMap<String, Connection>());
			}
			if(!elementPortConnection.containsKey(secondElement))
			{
				elementPortConnection.put(secondElement, new HashMap<String, Connection>());
			}
			elementPortConnection.get(firstElement).put(firstElementPort, conn);
			elementPortConnection.get(secondElement).put(secondElementPort, conn);
			return true;
		}
		return false;
	}
	
	@Override
	public void disconnectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
	{
		if (connections.remove(new BasicConnection(firstElement, firstElementPort, 
				secondElement, secondElementPort))!=null)
		{
			elementPortConnection.get(firstElement).remove(firstElementPort);
			elementPortConnection.get(secondElement).remove(secondElementPort);
			if(elementPortConnection.get(firstElement).isEmpty())
			{
				elementPortConnection.remove(firstElement);
			}
			if(elementPortConnection.containsKey(secondElement) && elementPortConnection.get(secondElement).isEmpty())
			{
				elementPortConnection.remove(secondElement);
			}
		}
	}
	@Override
	public void step()
	{
		Map<Connection,ArrayList<Double>> tempoMap = new HashMap<>();
		for (Connection i : connections.keySet())
		{
			tempoMap.put(i, new ArrayList<Double>());
		}
		for (Element i: elements)
		{
			//check whether element is at least connected to something
			if(elementPortConnection.containsKey(i))
			{
				Map<String, Double> portsMap = new TreeMap<>();
				//copy values from connections to the current element ports
				for (Map.Entry<String, Connection> j : elementPortConnection.get(i).entrySet())
				{
					portsMap.put(j.getKey(), connections.get(j.getValue()));
				}
				try
				{
					portsMap = i.process(portsMap);
				} //ignore elements crashes
				catch (NullPointerException ex)
				{
					//put 0s on the used ports
					for (String j : i.getPorts())
					{
						//"used" means connected to something
						if (elementPortConnection.get(i).containsKey(j))
						{
							portsMap.put(j, 0.);
						}
					}
				}
				for (Map.Entry<String, Double> j : portsMap.entrySet())
				{
					//take output values only from present and used ports
					if (elementPortConnection.get(i).containsKey(j.getKey()))
					{
						tempoMap.get(elementPortConnection.get(i).get(j.getKey())).add(j.getValue());
					}
				}
			}
			else
			{//if not - touch it with zeros
				Map<String, Double> portsMap = new TreeMap<>();
				for (String str : i.getPorts())
				{
					portsMap.put(str, .0);
				}
				i.process(portsMap);
			}
		}
		//sets values on the connections as a middle of the edges values
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
	public Map<String, Double> process(Map<String, Double> valuesOnPorts)
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
	public List<String> getPorts()
	{
		return new ArrayList<>();	//Merry Christmas
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Set<Connection> getConnections()
	{
		return Collections.unmodifiableSet(connections.keySet());
	}
	@Override
	public String getPort(int port)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override
	public Map<String, Double> nullFreePortsOf(Map<String, Double> valuesOnPorts)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	private class BasicConnection implements Connection
	{
		private Element firstElement;
		private String firstElementPort;
		private Element secondElement;
		private String secondElementPort;
		
		public BasicConnection(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
		{
			this.firstElement = firstElement;
			this.firstElementPort = firstElementPort;
			this.secondElement = secondElement;
			this.secondElementPort = secondElementPort;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if(o.getClass().equals(BasicConnection.class))
			{
				BasicConnection temp = (BasicConnection)o;
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
		@Override
		public Element getFirstElement()
		{
			return firstElement;
		}
		/**
		 * @return the firstElementPort
		 */
		@Override
		public String getFirstElementPort()
		{
			return firstElementPort;
		}
		/**
		 * @return the secondElement
		 */
		@Override
		public Element getSecondElement()
		{
			return secondElement;
		}
		/**
		 * @return the secondElementPort
		 */
		@Override
		public String getSecondElementPort()
		{
			return secondElementPort;
		}	
	}
}