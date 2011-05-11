/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.electric_element;

/**
 *
 * @author mcangel
 */
public class LogicElements
{
	public class Not implements ElectricElement
	{
		@Override
		public double[] touch(double[] acdc)
		{
			double[] result = {((acdc[0] == 0) ? 1 : 0)};
			return result;
		}
	}
	
	public class And implements ElectricElement
	{
		@Override
		public double[] touch(double[] acdc)
		{
			double[] result = {0};
			for (double pin : acdc)
			{
				result[0] *= ((pin == 0) ? 1 : 0);
			}
			return result;
		}
	}
	
	public class Or implements ElectricElement
	{
		@Override
		public double[] touch(double[] acdc)
		{
			double[] result = {0};
			for (double pin : acdc)
			{
				if((result[0] = ((pin == 0) ? 0 : 1)) == 1)
					return result;
			}
			return result;
		}
	}
}
