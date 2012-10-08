package net.unikernel.bummel.latches.JK;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=BasicElement.class)
@Element.ElementData(dataFile="element_info.xml")
public class JK extends BasicElement
{
    public int state;
    private String j, k, q, notq;
    public JK()
    {
        super(new String[]{"j", "k", "q", "notq"});
        state = 0;
        j    = getPorts().get(0);
        k    = getPorts().get(1);
        q    = getPorts().get(2);
        notq = getPorts().get(3);
    }

    @Override
    public Map<String, Double> process(Map<String, Double> valuesOnPorts) 
    {
        if(valuesOnPorts.get(j).compareTo(new Double(0)) == 0 &&
           valuesOnPorts.get(k).compareTo(new Double(0)) == 0)
        {
            switch(state)
            {
                case 0:
                    valuesOnPorts.put(q, 0.);
                    valuesOnPorts.put(notq, 1.);
                    break;
                case 1:
                    valuesOnPorts.put(q, 1.);
                    valuesOnPorts.put(notq, 0.);
                    break;
            }
        }
        else
            if(valuesOnPorts.get(j).compareTo(new Double(0)) == 0 && 
               valuesOnPorts.get(k).compareTo(new Double(0)) != 0)
            {
                valuesOnPorts.put(q, 0.);
                valuesOnPorts.put(notq, 1.);
                state = 0;
            }
            else
                if (valuesOnPorts.get(j).compareTo(new Double(0)) != 0 && 
                    valuesOnPorts.get(k).compareTo(new Double(0)) == 0)
                {
                    valuesOnPorts.put(q, 1.);
                    valuesOnPorts.put(notq, 0.);
                    state = 1;
                }
                else
                    if (valuesOnPorts.get(j).compareTo(new Double(0)) != 0 && 
                        valuesOnPorts.get(k).compareTo(new Double(0)) != 0)
                    {
                        if(valuesOnPorts.get(q).compareTo(new Double(0)) == 0)
                        {
                            valuesOnPorts.put(q, 1.);
                            valuesOnPorts.put(notq, 0.);
                        }
                        else
                        {
                            valuesOnPorts.put(q, 0.);
                            valuesOnPorts.put(notq, 1.); 
                        }
                        
                    }
        valuesOnPorts.put(j, 0.);
        valuesOnPorts.put(k, 0.);
        return valuesOnPorts;
    }
    
}
