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
 * <li>Output</li>
 * <li>Output</li>
 * </ol>
 * @author uko
 */
public class Split extends BasicElement
{
	@Override
	protected double[] process(double[] acdc)
	{
		double[] result = {0,acdc[0],acdc[0]};
		return result;
	}
}
