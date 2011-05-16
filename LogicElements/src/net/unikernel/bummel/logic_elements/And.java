/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicElement;
import net.unikernel.bummel.basic_elements.Element;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * <li>Input</li>
 * <li>Output</li>
 * </ol>
 * @author uko
 */
public class And extends BasicElement
{
	 /**
	 * Calculates the logical "and" value
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * <li>Input</li>
	 * <li>Output</li>
	 * </ol>
	 * @param acdc
	 * @return
	 */
	@Override
	public double[] process(double[] acdc)
	{
		double[] result = {0,0,acdc[0]*acdc[1]};
		return result;
	}
}
