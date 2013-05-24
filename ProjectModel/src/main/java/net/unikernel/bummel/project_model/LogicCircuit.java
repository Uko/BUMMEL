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
        // skip "free" elements
        if(!elementPortConnection.containsKey(bEl))
        {
          continue;
        }
        HashMap<String, Double> elSignals = entry.getValue();

        // [First walkthrough]
        // for each of the element's ports and corresponding values
        for (Map.Entry<String, Double> portValue : elSignals.entrySet())
        {
          String port = portValue.getKey();
          // skip "free" ports
          if(!elementPortConnection.get(bEl).containsKey(port))
          {
            continue;
          }
          Double registeredValue = portValue.getValue();

          //@TODO: optimize to check all connections or all registrations for the current element at once
          //       now there is check and processing of element for each registration
          //       not needed? hard to implement? - connected elements are recalculated

          // if it's event indeed - feq value and current value differs
          if(registeredValue.compareTo(elementPortValue.get(bEl).get(port)) != 0)
          {
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

        // [Second walkthrough]
        // active elements modelling
        for (Map.Entry<BasicElement, Map<String, Double>> aEl : activeElementsRegisteredSignals.entrySet())
        {
          processElement(aEl.getKey(), aEl.getValue());
        }

        activeElementsRegisteredSignals.clear();
      }
    }

    System.out.println("step() -- end");
  }

  /**
   * Processes element with provided data and checks if the resulting values
   * differ from the initial ones if they do - then registers this element
   * in the FEQ.
   */
  private void processElement(BasicElement element, Map<String, Double> signals, boolean changeCircuitValues)
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
  }

  private void processElement(BasicElement element, Map<String, Double> signals)
  {
    processElement(element, signals, true);
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
//      feq.addEvent(fbe.getDelay(), sbe, secondElementPort, elementPortValue.get(firstElement).get(firstElementPort));
//      feq.addEvent(sbe.getDelay(), fbe, firstElementPort, elementPortValue.get(secondElement).get(secondElementPort));

//      feq.addEvent(fbe.getDelay(), fbe, firstElementPort, elementPortValue.get(firstElement).get(firstElementPort));
//      feq.addEvent(sbe.getDelay(), sbe, secondElementPort, elementPortValue.get(secondElement).get(secondElementPort));

      for(Map.Entry<String, Connection> portConn : elementPortConnection.get(firstElement).entrySet())
      {
        Connection conn = portConn.getValue();
        Element other = conn.getOther(firstElement);
        String otherElementPort = conn.getElementPort(other);

        elementPortValue.get(firstElement).put(portConn.getKey(), elementPortValue.get(other).get(otherElementPort));
      }
//      elementPortValue.get(fbe).put(firstElementPort, elementPortValue.get(sbe).get(secondElementPort));
      processElement(fbe, elementPortValue.get(fbe));
      for(Map.Entry<String, Connection> portConn : elementPortConnection.get(secondElement).entrySet())
      {
        Connection conn = portConn.getValue();
        Element other = conn.getOther(secondElement);
        String otherElementPort = conn.getElementPort(other);

        elementPortValue.get(secondElement).put(portConn.getKey(), elementPortValue.get(other).get(otherElementPort));
      }
//      elementPortValue.get(sbe).put(secondElementPort, elementPortValue.get(fbe).get(firstElementPort));
      processElement(sbe, elementPortValue.get(sbe));
      return true;
    }
    return false;
  }

  @Override
  public void disconnectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
  {
//    double firstElementValue = elementPortValue.get(firstElement).get(firstElementPort);
//    elementPortValue.get(firstElement).put(firstElementPort, elementPortValue.get(secondElement).get(secondElementPort));
////    Map<String, Double> fForPr = new HashMap<>();
////    fForPr.putAll(elementPortValue.get(firstElement));
////    fForPr.put(firstElementPort, defaultValue());
////    processElement((BasicElement)firstElement, fForPr);
//
//    elementPortValue.get(secondElement).put(secondElementPort, firstElementValue);
////    Map<String, Double> sForPr = new HashMap<>();
////    sForPr.putAll(elementPortValue.get(firstElement));
////    processElement((BasicElement)secondElement, sForPr);
//    feq.addEvent(0, (BasicElement)firstElement, firstElementPort, defaultValue());
//    feq.addEvent(0, (BasicElement)secondElement, secondElementPort, defaultValue());
    elementPortValue.get(firstElement).put(firstElementPort, defaultValue());
//    firstElement.process(elementPortValue.get(firstElement));
    BasicElement bE = (BasicElement)firstElement;
    processElement(bE, elementPortValue.get(bE), false);
//    for(String port : elementPortConnection.get(bE).keySet())
//    {
//      feq.addEvent(bE.getDelay(), bE, port, elementPortValue.get(bE).get(port));
//    }

    elementPortValue.get(secondElement).put(secondElementPort, defaultValue());
//    secondElement.process(elementPortValue.get(secondElement));
    bE = (BasicElement)secondElement;
    processElement(bE, elementPortValue.get(bE), false);
//    for(String port : elementPortConnection.get(bE).keySet())
//    {
//      feq.addEvent(bE.getDelay(), bE, port, elementPortValue.get(bE).get(port));
//    }

    super.disconnectElements(firstElement, firstElementPort, secondElement, secondElementPort);
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

  Map<String, Double> getElementSignals(BasicElement element)
  {
    return elementPortValue.get(element);
  }
}
