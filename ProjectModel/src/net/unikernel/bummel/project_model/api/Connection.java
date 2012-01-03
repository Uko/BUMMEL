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
	public BasicElement getOtherElement(BasicElement thisElement);
	public BasicElement getFirstElement();
	public BasicElement getSecondElement();
	public String getLabel();
	public void setLabel(String label);
	public double getValue ();
}
