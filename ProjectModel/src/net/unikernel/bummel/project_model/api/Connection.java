/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.project_model.api;

/**
 *
 * @author uko
 */
public interface Connection
{
	public Element getOtherElement(Element thisElement);
	public Element getFirstElement();
	public Element getSecondElement();
	public String getLabel();
	public void setLabel(String label);
	public double getValue ();
}
