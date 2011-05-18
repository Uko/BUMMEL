/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Output</li>
 * </ol>
 * @author uko
 */
public class Generator extends BasicElement
{
	private double state;
	
	/**
	 * Returns the current state of generator
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Output</li>
	 * </ol>
	 * @param acdc
	 * @return
	 */
	@Override
	public double[] process(double[] acdc)
	{
		double[] result = {state};
		return result;
	}
	/**
	 * @return the state
	 */
	@Override
	public int getState()
	{
		return (int)state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(double state)
	{
		this.state = state;
	}
	/**
	 * Creates generator with state 0
	 */
	public Generator()
	{
		state = 1;
	}
	/**
	 * Creates generator with passed state
	 * @param state state to set
	 */
	public Generator(double state)
	{
		this.state = state;
	}
}
