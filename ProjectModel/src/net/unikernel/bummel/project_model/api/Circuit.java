/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.project_model.api;

/**
 *
 * @author uko
 */
public interface Circuit
{
	public int addElement (BasicElement element);
	public BasicElement removeElement (int elementId);
	public int connectElements (int first, int firstPort, int second, int secondPort);
	public Connection disconect (int connectionId);
	public void step();
}
