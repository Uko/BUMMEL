/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicStarterElement;

/**
 *
 * @author uko
 */
public class Generator implements BasicStarterElement
{
	private double state;
	@Override
	public double[] touch()
	{
		double[] result = {state};
		return result;
	}
	/**
	 * @return the state
	 */
	public double getState()
	{
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(double state)
	{
		this.state = state;
	}
	public Generator()
	{
		state=0;
	}
	public Generator(double state)
	{
		this.state = state;
	}
}
