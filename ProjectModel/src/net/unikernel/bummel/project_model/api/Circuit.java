package net.unikernel.bummel.project_model.api;

/**
 *
 * @author uko
 */
public interface Circuit
{
	public void addElement (Element element);
	public boolean removeElement (Element element);
	public void connectElements (Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort);
	public void disconectElements (Element firstElement, Integer firstElementPort, Element secondElement, Integer secondElementPort);
	//public void removeConnection (int connectionId);
	public void step();
}