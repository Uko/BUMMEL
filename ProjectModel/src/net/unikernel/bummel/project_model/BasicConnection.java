/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.project_model;

import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Connection;

/**
 *
 * @author uko
 */
public class BasicConnection implements Connection
{
	private String label;
	private double valule;
	private BasicElement first;
	private BasicElement second;
	
	public BasicConnection(BasicElement first, BasicElement second)
	{
		label = "";
		valule = 0;
		this.first = first;
		this.second = second;
	}
	
	@Override
	public BasicElement getOtherElement(BasicElement thisElement)
	{
		return first == thisElement ? second : first;
	}
	@Override
	public BasicElement getFirstElement()
	{
		return first;
	}
	@Override
	public BasicElement getSecondElement()
	{
		return second;
	}
	@Override
	public String getLabel()
	{
		return label;
	}
	@Override
	public void setLabel(String label)
	{
		this.label = label;
	}
	@Override
	public double getValue()
	{
		return valule;
	}
}
