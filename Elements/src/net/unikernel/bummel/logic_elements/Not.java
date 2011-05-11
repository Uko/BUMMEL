/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicElement;

/**
 *
 * @author uko
 */
public class Not implements BasicElement
{
	@Override
	public double[] touch(double[] acdc)
	{
		double[] result =
		{
			((acdc[0] == 0) ? 1 : 0)
		};
		return result;
	}
}
