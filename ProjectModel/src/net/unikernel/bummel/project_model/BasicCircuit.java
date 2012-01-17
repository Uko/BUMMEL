package net.unikernel.bummel.project_model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Element;
/**
 *
 * @author uko
 */
public class BasicCircuit implements Circuit, Element
{
	private String label;
	private Point coords;
	private Set<Element> elements;
	private Map<Conection, Double> conections;
	private Map<Element, Map<Integer, Conection>> conectionsByElementAndPort;
	
	public BasicCircuit()
	{
		label = "";
		coords = new Point(0, 0);
		elements = new HashSet<Element>();
		conections = new HashMap<Conection, Double>();
		conectionsByElementAndPort = new HashMap<Element, Map<Integer, Conection>>();
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
		//make a conection
		Conection temp = new Conection(firstElement, firstElementPort, secondElement, secondElementPort);
		conections.put(temp, 0.);
		if(!conectionsByElementAndPort.containsKey(firstElement))
		{
			conectionsByElementAndPort.put(firstElement, new HashMap<Integer, Conection>());
		}
		if(!conectionsByElementAndPort.containsKey(secondElement))
		{
			conectionsByElementAndPort.put(secondElement, new HashMap<Integer, Conection>());
		}
		conectionsByElementAndPort.get(firstElement).put(firstElementPort, temp);
		conectionsByElementAndPort.get(secondElement).put(secondElementPort, temp);
	}
	@Override
	public void disconectElements(Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort)
	{
		if (conections.remove(new Conection(firstElement, firstElementPort, secondElement, secondElementPort))==null)
		{
			conectionsByElementAndPort.get(firstElement).remove(firstElementPort);
			conectionsByElementAndPort.get(secondElement).remove(secondElementPort);
			if(conectionsByElementAndPort.get(secondElement).isEmpty())
			{
				conectionsByElementAndPort.remove(firstElement);
			}
			if(conectionsByElementAndPort.get(firstElement).isEmpty())
			{
				conectionsByElementAndPort.remove(secondElement);
			}
		}
	}
	@Override
	public void step()
	{
		Map<Conection,ArrayList<Double>> tempoMap = new HashMap<Conection,ArrayList<Double>>();
		for (Conection i : conections.keySet())
		{
			tempoMap.put(i, new ArrayList<Double>());
		}
		for (Element i: elements)
		{
			Map<Integer, Double> portsMap = new TreeMap<Integer, Double>();
			for (Map.Entry<Integer,Conection> j : conectionsByElementAndPort.get(i).entrySet())
			{
				portsMap.put(j.getKey(), conections.get(j.getValue()));
			}
			portsMap = i.process(portsMap);
			for (Map.Entry<Integer,Double> j : portsMap.entrySet())
			{
				tempoMap.get(conectionsByElementAndPort.get(i).get(j.getKey())).add(j.getValue());
			}
		}
		for (Map.Entry<Conection,ArrayList<Double>> i : tempoMap.entrySet())
		{
			conections.put(i.getKey(), (i.getValue().get(0) +i.getValue().get(1))/2);
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
	
	private class Conection
	{
		private Element firstElement;
		private Integer firstElementPort;
		private Element secondElement;
		private Integer secondElementPort;
		
		public Conection(Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort)
		{
			this.firstElement = firstElement;
			this.firstElementPort = firstElementPort;
			this.secondElement = secondElement;
			this.secondElementPort = secondElementPort;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if(o.getClass().equals(Conection.class))
			{
				Conection temp = (Conection)o;
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
