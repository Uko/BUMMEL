/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * </ol>
 * @author uko
 */
public class Analyzer extends BasicElement
{
	private double state;
	/**Sets analyzers state same as input
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * </ol>
	 * @param acdc
	 * @return
	 */
	@Override
	public double[] process(double[] acdc)
	{
		state = acdc[0];
		double[] result = {0};
		return result;
	}
	/**
	 * Creates analyzer with state 0
	 */
	public Analyzer()
	{
		state = 0;
	}
	
	@Override
	public int getState()
	{
		return (int)state;
	}
}
