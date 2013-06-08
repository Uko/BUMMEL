package net.unikernel.bummel.project_model.api;

import java.util.Set;

/**
 *
 * @author uko
 */
public interface Circuit
{
	public void addElement (Element element);
	public boolean removeElement (Element element);
	public boolean connectElements (Element firstElement, String firstElementPort, Element secondElement, String secondElementPort);
	
	/**
	 * Gives away all the elements of the circuit.
	 * @return set of elements
	 */
	public Set<Element> getElements();
	/**
	 * Gives away all the connections of the circuit.
	 * @return set of connections (preferably unmodifiable)
	 */
	public Set<Connection> getConnections();
	public void disconnectElements (Element firstElement, String firstElementPort, Element secondElement, String secondElementPort);
	//public void removeConnection (int connectionId);
	public void step();

  public String displayName();
}