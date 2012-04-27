package net.unikernel.bummel.latches.D;

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
public class D extends BasicElement
{
    private String d;
    private String c;
    private String t;
    public int state;
    public D()
    {
        super(new String[]{"d", "c", "t"});
        d = getPorts().get(0);
        c = getPorts().get(1);
        t = getPorts().get(2);
        state = 0;
    }
    
    @Override
    public Map<String, Double> process(Map<String, Double> valuesOnPorts) 
    {
        if(valuesOnPorts.get(c).compareTo(new Double(0)) == 0)
        {
            switch(state)
            {
                case 0:
                    valuesOnPorts.put(t, 0.);
                    break;
                case 1:
                    valuesOnPorts.put(t, 1.);
                    break;
            }
        }
        else
            if(valuesOnPorts.get(c).compareTo(new Double(0)) != 0)
            {
                if(valuesOnPorts.get(d).compareTo(new Double(0)) == 0)
                {
                    valuesOnPorts.put(t, 0.);
                    state = 0;
                }
                else
                    if(valuesOnPorts.get(d).compareTo(new Double(0)) != 0)
                    {
                        valuesOnPorts.put(t, 1.);
                        state = 1;
                    }
            }
        valuesOnPorts.put(d, 0.);
        valuesOnPorts.put(c, 0.);
        return valuesOnPorts;
    }
    
}