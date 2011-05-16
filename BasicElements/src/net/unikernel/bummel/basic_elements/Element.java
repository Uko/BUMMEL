package net.unikernel.bummel.basic_elements;

/**
 * Interface of an element. All elements should have a function that a user can use to trigger them.
 * @author mcangel
 */
public interface Element
{
	/**
	 * This method should receive status if all pins and calculate what will happen.
	 * @param acdc array of all pins.
	 */
	public void touch(double[] acdc);
}
