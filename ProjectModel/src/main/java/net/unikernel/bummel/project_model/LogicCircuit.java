package net.unikernel.bummel.project_model;

import java.util.Collections;
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
        HashMap<String, Double> signalsToProcess = new HashMap<>();
        for(String port : bEl.getPorts())
        {
          Double val = elementPortValue.get(bEl).get(port);
          if(elementPortConnection.containsKey(bEl)
                  && elementPortConnection.get(bEl).containsKey(port))
          {
            Connection conn = elementPortConnection.get(bEl).get(port);
            // connection existance check
            if (conn != null)
            {
              BasicElement connEl = (BasicElement) conn.getOther(bEl);
              val = (val + elementPortValue.get(connEl).get(conn.getElementPort(connEl))) / 2;
            }
          }
          signalsToProcess.put(port, val);
        }
//        signalsToProcess.putAll(elementPortValue.get(bEl));
        signalsToProcess.putAll(elSignals);
        Map<String, Double> processedSignals = bEl.process(signalsToProcess);
        for(Map.Entry<String, Double> ps : processedSignals.entrySet())
        {
          String port = ps.getKey();
          Double val = ps.getValue();
          if(val.compareTo(elementPortValue.get(bEl).get(port)) != 0)
          {
            // change circuit value
            elementPortValue.get(bEl).put(port, val);
            Connection conn = elementPortConnection.get(bEl).get(port);
            // connection existance check
            if(conn != null)
            {
              BasicElement connEl = (BasicElement) conn.getOther(bEl);
              // register connected elements in the FEQ for the further processing
              feq.addEvent(bEl.getDelay(), connEl, conn.getElementPort(connEl), val);
            }
          }
        }
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

      feq.addEvent(sbe.getDelay(), fbe, firstElementPort, elementPortValue.get(sbe).get(secondElementPort));
      feq.addEvent(fbe.getDelay(), sbe, secondElementPort, elementPortValue.get(fbe).get(firstElementPort));

      return true;
    }
    return false;
  }

  @Override
  public void disconnectElements(Element firstElement, String firstElementPort, Element secondElement, String secondElementPort)
  {
    BasicElement fbe = (BasicElement) firstElement;
    BasicElement sbe = (BasicElement) secondElement;

    feq.addEvent(0, fbe, firstElementPort, defaultValue());
    feq.addEvent(0, sbe, secondElementPort, defaultValue());

    super.disconnectElements(firstElement, firstElementPort, secondElement, secondElementPort);
  }

  @Override
  public void addElement(Element element)
  {
    super.addElement(element);

    Map<String, Double> elementSignals = new HashMap<>();
    for(String port : element.getPorts())
    {
      elementSignals.put(port, defaultValue());
    }
    elementPortValue.put(element, element.process(elementSignals));
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
    return Collections.unmodifiableMap(elementPortValue.get(element));
  }
}
