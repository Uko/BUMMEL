package net.unikernel.bummel.logic_elements.Generator;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.ElementData;
import org.openide.util.lookup.ServiceProvider;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
@ServiceProvider(service=BasicElement.class)
@ElementData(dataFile="element_info.xml")
public class Generator extends BasicElement
{

	public Generator()
	{
		super(new String[]{"output"});//output
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
	public Map<String, Double> process(Map<String, Double> valuesOnPorts)
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