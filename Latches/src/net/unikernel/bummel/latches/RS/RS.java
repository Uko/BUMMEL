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
    private int state;
    private String R, S, Q, notQ;
    public RS()
    {
        super(new String[]{"reset", "set", "q", "notq"});
        R    = getPorts().get(0);
        S    = getPorts().get(1);
        Q    = getPorts().get(2);
        notQ = getPorts().get(3);
        state = 1;
    }
    @Override
    public Map<String, Double> process(Map<String, Double> valuesOnPorts) 
    {
        if(valuesOnPorts.get(R).compareTo(new Double(0)) == 0 && 
           valuesOnPorts.get(S).compareTo(new Double(0)) != 0)
        {
            valuesOnPorts.put(Q, 1.);
            valuesOnPorts.put(notQ, 0.);
            state = 2;
        }
        else
            if(valuesOnPorts.get(R).compareTo(new Double(0)) != 0 &&
               valuesOnPorts.get(S).compareTo(new Double(0)) == 0)
            {
                valuesOnPorts.put(Q, 0.);
                valuesOnPorts.put(notQ, 1.);
                state = 3;
            }
            else
                if(valuesOnPorts.get(R).compareTo(new Double(0)) == 0 &&
                   valuesOnPorts.get(S).compareTo(new Double(0)) == 0)
                {
                    switch(state)
                    {
                        case 1:
                            valuesOnPorts.put(Q, 0.);
                            valuesOnPorts.put(notQ, 0.);
                            break;
                        case 2:
                            valuesOnPorts.put(Q, 1.);
                            break;
                        case 3:
                            valuesOnPorts.put(notQ, 1.);
                            break;    
                    }
                }  
                else
                    if(valuesOnPorts.get(R).compareTo(new Double(0)) != 0 &&
                       valuesOnPorts.get(S).compareTo(new Double(0)) != 0)
                    {
                        valuesOnPorts.put(Q, 0.);
                        valuesOnPorts.put(notQ, 0.);
                    }
        valuesOnPorts.put(R, 0.);
        valuesOnPorts.put(S, 0.);
        return valuesOnPorts;
    }
}
