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
 * <li>Output</li>
 * </ol>
 * @author uko
 */
public class Not extends BasicElement
{
	/**
	 * Calculates the logical "not" value
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * <li>Output</li>
	 * </ol>
	 * @param acdc
	 * @return
	 */
	@Override
	public double[] process(double[] acdc)
	{
		double[] result =
		{
			0,((acdc[0] == 0) ? 1 : 0)
		};
		return result;
	}
}
