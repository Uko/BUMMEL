package net.unikernel.bummel.logic_elements;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
public class Generator extends BasicElement
{

	public Generator()
	{
		super(new Integer[]{0});//output
		setState(1);
	}
	/**
	 * Returns the current state of generator
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Output</li>
	 * </ol>
	 */
	@Override
	public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
	{
		if(getState() == 0)
		{
			valuesOnPorts.put(getPorts().get(0), new Double(0));
		}
		else
		{
			valuesOnPorts.put(getPorts().get(0), new Double(1));
		}
		return valuesOnPorts;
	}
}