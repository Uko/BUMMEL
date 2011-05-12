/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.basic_elements.BasicEnderElement;

/**
 *
 * @author uko
 */
public class Analyzer implements BasicEnderElement
{
	private double state;
	@Override
	public void touch(double[] acdc)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	public Analyzer()
	{
		state=0;
	}
	
}
