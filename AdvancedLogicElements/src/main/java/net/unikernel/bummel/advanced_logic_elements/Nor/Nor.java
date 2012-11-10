package net.unikernel.bummel.advanced_logic_elements.Nor;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Roma
 */
@ServiceProvider(service=BasicElement.class)
@Element.ElementData(dataFile="element_info.xml")
public class Nor extends BasicElement 
{
	private static final long serialVersionUID = 1L;
	
    public Nor()
    {
        super(new String[]{"input1", "input2", "output"});
    }

    @Override
    public Map<String, Double> process(Map<String, Double> valuesOnPorts) 
    {
        if(valuesOnPorts.get(getPorts().get(0)).compareTo(Double.valueOf(0.d)) == 0 &&
		   valuesOnPorts.get(getPorts().get(1)).compareTo(Double.valueOf(0.d)) == 0)
        {
            valuesOnPorts.put(getPorts().get(2), Double.valueOf(1.d));
        }
        else
        {
            valuesOnPorts.put(getPorts().get(2), Double.valueOf(0.d));
        }
        valuesOnPorts.put(getPorts().get(0), Double.valueOf(0));
        valuesOnPorts.put(getPorts().get(1), Double.valueOf(0));
        return valuesOnPorts;
    }
}
