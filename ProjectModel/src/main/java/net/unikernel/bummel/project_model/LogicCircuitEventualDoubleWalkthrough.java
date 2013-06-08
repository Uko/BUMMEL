package net.unikernel.bummel.project_model;

import java.util.HashMap;
import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Connection;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=Circuit.class)
public class LogicCircuitEventualDoubleWalkthrough extends BasicCircuit
{
  private FutureEventsQueue feq = new FutureEventsQueue();

  private Map<Element, Map<String, Double>> elementPortValue = new HashMap<>();

  @Override public void step()
  {
    Map<BasicElement, Map<String, Double>> activeElementsRegisteredSignals = new HashMap<>();
    while(!feq.isEmpty())
    {
      Map.Entry<Integer, HashMap<BasicElement, HashMap<String, Double>>> head = feq.remove();
      Integer currentDelay = head.getKey();
      // delay simulation
      System.out.println("Delay: " + currentDelay);
      // [First walkthrough]
      // for each event (element) in the queue for the current delay
      for (Map.Entry<BasicElement, HashMap<String, Double>> entry : head.getValue().entrySet())
      {
        BasicElement bEl = entry.getKey();
        
        HashMap<String, Double> elSignals = entry.getValue();

        // for each of the element's ports and corresponding values
        for (Map.Entry<String, Double> portValue : elSignals.entrySet())
        {
          String port = portValue.getKey();
          Double registeredValue = portValue.getValue();

          //@TODO: optimize to check all connections or all registrations for the current element at once
          //       now there is check and processing of element for each registration
          //       not needed? hard to implement? - connected elements are recalculated

          // if it's event indeed - feq value and current value differs
          if(registeredValue.compareTo(elementPortValue.get(bEl).get(port)) != 0)
          {
            elementPortValue.get(bEl).put(port, registeredValue);
            // skip "free" elements or their ports
            if (!elementPortConnection.containsKey(bEl)
                    || !elementPortConnection.get(bEl).containsKey(port))
            {
              continue;
            }
            // get connected element
            Connection conn = elementPortConnection.get(bEl).get(port);
            BasicElement connBEl = (BasicElement)conn.getOther(bEl);
            // register it and it's new signal values for the processing in the second walkthrough
            if(!activeElementsRegisteredSignals.containsKey(connBEl))
            {
              activeElementsRegisteredSignals.put(connBEl, new HashMap<String, Double>());
            }
            activeElementsRegisteredSignals.get(connBEl).put(conn.getSecondElementPort(), registeredValue);
          }
        }
      }
      // [Second walkthrough]
      // active elements modelling
      for (Map.Entry<BasicElement, Map<String, Double>> aEl : activeElementsRegisteredSignals.entrySet())
      {
        processElement(aEl.getKey(), aEl.getValue(), false);
      }
      activeElementsRegisteredSignals.clear();
    }
  }

  /**
   * Processes element with provided data and checks if the resulting values
   * differ from the initial ones if they do - then registers this element
   * in the FEQ.
   */
  private Map<String, Double> processElement(BasicElement element, Map<String, Double> signals, boolean changeCircuitValues)
  {
    // outgoing port check - store current element signals for the further comparison
    Map<String, Double> signalsToProcess = new HashMap<>();
    Map<String, Double> aElSignals = elementPortValue.get(element);
    for (Map.Entry<String, Double> signal : aElSignals.entrySet())
    {
      signalsToProcess.put(signal.getKey(), signal.getValue().doubleValue());
    }
    // change signals with registered in the first walkthrough ones
    signalsToProcess.putAll(signals);
    // process active element
    Map<String, Double> result = element.process(signalsToProcess);
    // outgoing port check - compare previous signals and process result ones
    // and register this element in the queue if they differ
    for (Map.Entry<String, Double> signal : result.entrySet())
    {
      if (signal.getValue().compareTo(aElSignals.get(signal.getKey())) != 0)
      {
        if(changeCircuitValues)
        {
          aElSignals.put(signal.getKey(), signal.getValue());
        }
        feq.addEvent(element.getDelay(), element, signal.getKey(), signal.getValue());
      }
    }
    return result;
  }

  @Override
  public String displayName()
  {
    return "Eventual Double Walkthrough Logic Circuit";
  }

  @Override
  public boolean connectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
  {
    if(super.connectElements(firstElement, firstElementPort, secondElement, secondElementPort))
    {
      BasicElement fbe = (BasicElement)firstElement;
      BasicElement sbe = (BasicElement)secondElement;

      feq.addEvent(fbe.getDelay(), fbe, firstElementPort, elementPortValue.get(firstElement).get(firstElementPort));
      feq.addEvent(sbe.getDelay(), sbe, secondElementPort, elementPortValue.get(secondElement).get(secondElementPort));
      elementPortValue.get(firstElement).put(firstElementPort, defaultValue());
      elementPortValue.get(secondElement).put(secondElementPort, defaultValue());

      return true;
    }
    return false;
  }

  private void processDisconnectedElement(BasicElement element, Map<String, Double> signals, String disconnectedPort)
  {
    elementPortValue.get(element).put(disconnectedPort,
            processElement(element, signals, false).get(disconnectedPort));
  }

  @Override
  public void disconnectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
  {
    super.disconnectElements(firstElement, firstElementPort, secondElement, secondElementPort);

    elementPortValue.get(firstElement).put(firstElementPort, defaultValue());
    BasicElement bE = (BasicElement)firstElement;
    processDisconnectedElement(bE, elementPortValue.get(bE), firstElementPort);

    elementPortValue.get(secondElement).put(secondElementPort, defaultValue());
    bE = (BasicElement)secondElement;
    processDisconnectedElement(bE, elementPortValue.get(bE), secondElementPort);
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
    return true;
  }

  private Double defaultValue()
  {
    return .0;
  }

  Map<String, Double> getElementSignals(BasicElement element)
  {
    return elementPortValue.get(element);
  }
}
