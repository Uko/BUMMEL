package net.unikernel.bummel.logic_elements;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
public class Not extends BasicElement
{

	public Not()
	{
		super(new Integer[]{0,1});//input, output
	}
	
	/**
	 * Calculates the logical "not" value
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * <li>Output</li>
	 * </ol>
	 */
	@Override
	public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
	{
		if(valuesOnPorts.get(getPorts().get(0)).compareTo(new Double(0)) == 0)
		{
			valuesOnPorts.put(getPorts().get(1), new Double(1));
		}
		else
		{
			valuesOnPorts.put(getPorts().get(1), new Double(0));
		}
		valuesOnPorts.put(0, 0.);
		return valuesOnPorts;
	}
}
