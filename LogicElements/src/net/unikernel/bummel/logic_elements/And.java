package net.unikernel.bummel.logic_elements;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * <li>Input</li>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
public class And extends BasicElement
{

	public And(Integer[] availablePorts)
	{
		super(new Integer[]{0,1,2}); //input1,inpu2,output
	}
	
	 /**
	 * Calculates the logical "and" value
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * <li>Input</li>
	 * <li>Output</li>
	 * </ol>
	 */
	@Override
	public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
	{
		valuesOnPorts.put(getPorts().get(2), 
				new Double(valuesOnPorts.get(getPorts().get(0)).doubleValue()
				* valuesOnPorts.get(getPorts().get(1)).doubleValue()));
		return valuesOnPorts;
	}
}