package net.unikernel.bummel.project_model.api;

/**
 *
 * @author uko
 */
public interface Circuit
{
	public int addElement (Element element);
	public Element removeElement (int elementId);
	public int connectElements (int firstElementId, int firstElementPort, int secondElementId, int secondElementPort);
	public void disconectElements (int firstElementId, int firstElementPort, int secondElementId, int secondElementPort);
	public void removeConnection (int connectionId);
	public void step();
}