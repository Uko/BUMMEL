/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol>
 * <li>Input</li>
 * <li>Input</li>
 * <li>Output</li>
 * </ol>
 * @author uko
 */
public class Or extends BasicElement
{
	/**
	 * Calculates the logical "or" value
	 * <b>Pinout:</b>
	 * <ol>
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
		double[] result =
		{
			0,0,0
		};
		for (int i =0; i<3; i++)
		{
			if ((result[3] = ((acdc[i] == 0) ? 0 : 1)) == 1)
			{
				return result;
			}
		}
		return result;
	}
}
