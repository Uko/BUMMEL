package net.unikernel.bummel.logic_elements.Not;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element.ElementData;
import org.openide.util.lookup.ServiceProvider;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
@ServiceProvider(service=BasicElement.class)
@ElementData(dataFile="element_info.xml")
public class Not extends BasicElement
{
	private static final long serialVersionUID = 1L;

	public Not()
	{
		super(new String[]{"input","output"});//input, output
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
	public Map<String, Double> process(Map<String, Double> valuesOnPorts)
	{
		valuesOnPorts = nullFreePortsOf(valuesOnPorts);
		if(valuesOnPorts.get(getPorts().get(0)).compareTo(Double.valueOf(0.d)) == 0)
		{
			valuesOnPorts.put(getPorts().get(1), Double.valueOf(1.d));
		}
		else
		{
			valuesOnPorts.put(getPorts().get(1), Double.valueOf(0.d));
		}
		valuesOnPorts.put(getPorts().get(0), 0.d);
		return valuesOnPorts;
	}
}
