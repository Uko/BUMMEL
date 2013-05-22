package net.unikernel.bummel.project_model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Connection;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=Circuit.class)
@Messages("LC_DisplayName=Logic Circuit")
public class LogicCircuit extends BasicCircuit
{
  private FutureEventsQueue feq = new FutureEventsQueue();

  private Map<Element, Map<String, Double>> elementPortValue = new HashMap<>();

  @Override public void step()
  {
    System.out.println("step() -- start");
    Set<BasicElement> activeElements = new HashSet<>();
    Map<BasicElement, Map<String, Double>> activeElementsRegisteredSignals = new HashMap<>();
    while(!feq.isEmpty())
    {
      Map.Entry<Integer, HashMap<BasicElement, HashMap<String, Double>>> head = feq.remove();
      Integer currentDelay = head.getKey();
      System.out.println("Delay: " + currentDelay);
      // for each event (element) in the queue for the current delay
      for (Map.Entry<BasicElement, HashMap<String, Double>> entry : head.getValue().entrySet())
      {
        BasicElement bEl = entry.getKey();
        HashMap<String, Double> elSignals = entry.getValue();

        // [First walkthrough]
        // for each of the element's ports and corresponding values
        for (Map.Entry<String, Double> portValue : elSignals.entrySet())
        {
          String port = portValue.getKey();
          Double registeredValue = portValue.getValue();

          //@TODO: optimize to check all connections or all registrations for the current element at once
          //       now there is check and processing of element for each registration
          //       not needed? hard to implement? - connected elements are recalculated

          // if it's event indeed - feq value and current value differs
          if(registeredValue != elementPortValue.get(bEl).get(port))
          {
            // get connected element
            Connection conn = elementPortConnection.get(bEl).get(port);
            BasicElement connBEl = (BasicElement)conn.getSecondElement();
            // register it and it's new signal values for the processing in the second walkthrough
            if(!activeElementsRegisteredSignals.containsKey(connBEl))
            {
              activeElementsRegisteredSignals.put(connBEl, new HashMap<String, Double>());
            }
            activeElementsRegisteredSignals.get(connBEl).put(conn.getSecondElementPort(), registeredValue);
          }

          // [Second walkthrough]
          // active elements modelling
          for(Map.Entry<BasicElement, Map<String, Double>> aEl : activeElementsRegisteredSignals.entrySet())
          {
            // outgoing port check - store current element signals for the further comparison
            Map<String, Double> elLastSignals = new HashMap<>();
            Map<String, Double> aElSignals = elementPortValue.get(aEl.getKey());
            for(Map.Entry<String, Double> signal : aElSignals.entrySet())
            {
              elLastSignals.put(signal.getKey(), signal.getValue().doubleValue());
            }
            // change element signals with registered in the first walkthrough ones
            aElSignals.putAll(aEl.getValue());
            // process active element
            Map<String, Double> result = aEl.getKey().process(aElSignals);
            // outgoing port check - compare previous signals and process result ones
            // and register this element in the queue if they differ
            for(Map.Entry<String, Double> signal : result.entrySet())
            {
              if(Math.abs(signal.getValue() - elLastSignals.get(signal.getKey())) > 0.0001)
              {
                aElSignals.put(signal.getKey(), signal.getValue());
                feq.addEvent(aEl.getKey().getDelay(), aEl.getKey(), signal.getKey(), signal.getValue());
              }
            }
          }
        }
        activeElements.clear();
        activeElementsRegisteredSignals.clear();
      }
    }

    System.out.println("step() -- end");
  }

  @Override
  public String displayName()
  {
    return NbBundle.getMessage(LogicCircuit.class, "LC_DisplayName");
  }

  @Override
  public boolean connectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
  {
    if(super.connectElements(firstElement, firstElementPort, secondElement, secondElementPort))
    {
      BasicElement fbe = (BasicElement)firstElement;
      BasicElement sbe = (BasicElement)secondElement;
      feq.addEvent(fbe.getDelay(), sbe, secondElementPort, elementPortValue.get(firstElement).get(firstElementPort));
      feq.addEvent(sbe.getDelay(), fbe, firstElementPort, elementPortValue.get(secondElement).get(secondElementPort));
      return true;
    }
    return false;
  }

  @Override
  public void addElement(Element element)
  {
    super.addElement(element);
    elementPortValue.put(element, new HashMap<String, Double>());
    Map<String, Double> elementSignals = elementPortValue.get(element);
    for(String port : element.getPorts())
    {
      elementSignals.put(port, defaultValue());
    }
    elementSignals.putAll(element.process(elementSignals));
  }

  @Override
  public boolean removeElement(Element element)
  {
    super.removeElement(element);
    elementPortValue.remove(element);
    //@TODO: remove element from queue?
    return true;
  }

  private Double defaultValue()
  {
    return .0;
  }
}
