package net.unikernel.bummel.logic_elements.Or;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element.ElementData;
import org.openide.util.lookup.ServiceProvider;

/**
 * <b>Pinout:</b>
 * <ol start='0'>
 * <li>Input</li>
 * <li>Input</li>
 * <li>Output</li>
 * </ol>
 * @author mcangel
 */
@ServiceProvider(service=BasicElement.class)
@ElementData(dataFile="element_info.xml")
public class Or extends BasicElement
{
	private static final long serialVersionUID = 1L;

	public Or()
	{
		super(new String[]{"input1","input2","output"});//input,input,output
	}
	
	/**
	 * Calculates the logical "or" value
	 * <b>Pinout:</b>
	 * <ol start='0'>
	 * <li>Input</li>
	 * <li>Input</li>
	 * <li>Output</li>
	 * </ol>
	 */
	@Override
	public Map<String, Double> process(Map<String, Double> valuesOnPorts)
	{
		valuesOnPorts = nullFreePortsOf(valuesOnPorts);
		if(valuesOnPorts.get(getPorts().get(0)).compareTo(Double.valueOf(0.d)) != 0 || valuesOnPorts.get(getPorts().get(1)).compareTo(Double.valueOf(0.d)) != 0)
		{
			valuesOnPorts.put(getPorts().get(2), Double.valueOf(1.d));
		}
		else
		{
			valuesOnPorts.put(getPorts().get(2), Double.valueOf(0.d));
		}
		valuesOnPorts.put(getPorts().get(0), 0.d);
		valuesOnPorts.put(getPorts().get(1), 0.d);
		return valuesOnPorts;
	}
}
