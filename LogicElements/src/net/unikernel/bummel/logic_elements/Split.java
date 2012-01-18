package net.unikernel.bummel.logic_elements;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * <li>Output</li>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
public class Split extends BasicElement
{

	public Split()
	{
		super(new Integer[]{0,1,2});
	}
	
	/**
	 * Duplicates input port value to the output ports.
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * <li>Output</li>
	 * <li>Output</li>
	 * </ol>
	 */
	@Override
	public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
	{
		valuesOnPorts.put(getPorts().get(1), valuesOnPorts.get(getPorts().get(0)));
		valuesOnPorts.put(getPorts().get(2), valuesOnPorts.get(getPorts().get(0)));
		valuesOnPorts.put(0, 0.);
		return valuesOnPorts;
	}
}
