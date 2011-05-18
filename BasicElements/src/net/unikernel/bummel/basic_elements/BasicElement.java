/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.basic_elements;

/**
 * 
 * @author uko
 */
public abstract  class BasicElement implements Element
{
	private double[] onPins;
	
	/**
	 * This method should be overridden in every child class to ensure their functionality. The method itself makes all calculations.
	 * @param acdc state of pins to process
	 * @return result of pins after calculation
	 */
	protected abstract double[] process(double[] acdc);
	/**
	 * This method is final and it's made to ensure that the result will be written to the pins array.
	 * @param acdc state of pins to process
	 */
	@Override
	public final void touch(double[] acdc)
	{
		onPins = process(acdc);
	}
	/**
	 * @return the onPins
	 */
	public final double[] getOnPins()
	{
		if(onPins == null)
			return null;
		double[] arr = new double[onPins.length];
		System.arraycopy(onPins, 0, arr, 0, onPins.length);
		return arr;
	}
	
	/**
	 * This method may be overridden in every child class to correct it's graphics representation of current work state.
	 * @return state The state that represents element look.
	 */
	public int getState()
	{
		return 0;
	}
}
