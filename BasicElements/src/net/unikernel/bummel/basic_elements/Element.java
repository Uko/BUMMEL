package net.unikernel.bummel.basic_elements;

import java.io.Serializable;

/**
 * Interface of an element. All elements should have a function that a user can use to trigger them.
 * @author mcangel
 */
public interface Element extends Serializable
{
	/**
	 * This method should receive status if all pins and calculate what will happen.
	 * @param acdc array of all pins.
	 */
	public void touch(double[] acdc);
}
