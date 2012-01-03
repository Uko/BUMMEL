package net.unikernel.bummel.project_model.api;

/**
 *
 * @author uko
 */
public interface Circuit
{
	public int addElement (BasicElement element);
	public BasicElement removeElement (int elementId);
	public int connectElements (int first, String firstPort, int second, String secondPort)
			throws Exception;
	public Connection disconect (int connectionId);
	public void step();
}
