/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.latches.T;

import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=BasicElement.class)
@Element.ElementData(dataFile="element_info.xml")
public class T extends BasicElement
{
    public int state;
    private String t;
    private String q;
    private String notq;
    public T()
    {
        super(new String[]{"t","q","notq"});
        t = getPorts().get(0);
        q = getPorts().get(1);
        notq=getPorts().get(2);
        state = 0;
    }
    @Override
    public Map<String, Double> process(Map<String, Double> valuesOnPorts) 
    {
       if(valuesOnPorts.get(t).compareTo(new Double(0)) != 0)
       {
           switch(state)
           {
               case 0:
                   valuesOnPorts.put(q, 1.);
                   valuesOnPorts.put(notq, 0.);
                   state = 1;
                   break;
               case 1:
                   valuesOnPorts.put(q, 0.);
                   valuesOnPorts.put(notq, 1.);
                   state = 0;
                   break;
           }
       }
       else
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
       valuesOnPorts.put(t, 0.);
       return valuesOnPorts;
    }
    
}
