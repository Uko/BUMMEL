package net.unikernel.bummel.latches.RS;

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
public class RS extends BasicElement
{
    public RS()
    {
        super(new String[]{"reset", "set", "q", "notq"});
        setState(0);
    }
    // getPorts().get(0) - R;
    // getPorts().get(1) - S;
    // getPorts().get(2) - Q;
    // getPorts().get(3) - Qnext;
    
    //S |  R | Qnext |  Action       | Q | Qnext | S | R
    
    //0 |  0 |   Q   | hold state    | 0 |   0   | 0 | X
    //0 |  1 |   0   |  reset        | 0 |   1   | 1 | 0
    //1 |  0 |   1   |   set         | 1 |   0   | 0 | 1
    //1 |  1 |   X   |not allowed    | 1 |   1   | X | 0
    @Override
    public Map<String, Double> process(Map<String, Double> valuesOnPorts) 
    {
        if(valuesOnPorts.get(getPorts().get(0)).compareTo(new Double(0)) != 0 &&
           valuesOnPorts.get(getPorts().get(1)).compareTo(new Double(0)) == 0)
        {
            valuesOnPorts.put(getPorts().get(2), 0.);
            valuesOnPorts.put(getPorts().get(3), 0.);
            setState(valuesOnPorts.get(getPorts().get(2)).intValue());
        }
        else
            if(valuesOnPorts.get(getPorts().get(0)).compareTo(new Double(0)) == 0 &&
               valuesOnPorts.get(getPorts().get(1)).compareTo(new Double(0)) != 0)
            {
                valuesOnPorts.put(getPorts().get(2), 0.);
                valuesOnPorts.put(getPorts().get(3), 1.);
                setState(valuesOnPorts.get(getPorts().get(2)).intValue());
            }
            else
                if(valuesOnPorts.get(getPorts().get(0)).compareTo(new Double(0)) == 0 &&
                   valuesOnPorts.get(getPorts().get(1)).compareTo(new Double(0)) == 0)
                {
                    valuesOnPorts.put(getPorts().get(2), 0.);
                    valuesOnPorts.put(getPorts().get(3), new Double(getState()));
                }
        
        return valuesOnPorts;
    }
}
