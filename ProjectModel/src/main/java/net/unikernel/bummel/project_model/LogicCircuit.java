package net.unikernel.bummel.project_model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Connection;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=Circuit.class)
@Messages("LC_DisplayName=Logic Circuit")
public class LogicCircuit extends BasicCircuit implements PropertyChangeListener
{
  private FutureEventsQueue feq = new FutureEventsQueue();

  private Map<Element, Map<String, Double>> elementPortValue = new HashMap<>();

  @Override public void step()
  {
    while(!feq.isEmpty())
    {
      Map.Entry<Integer, HashMap<BasicElement, HashMap<String, Double>>> head = feq.remove();
      Integer currentDelay = head.getKey();
      System.out.println("Delay: " + currentDelay);
      // process each event (element) in the queue for the current delay
      for (Map.Entry<BasicElement, HashMap<String, Double>> entry : head.getValue().entrySet())
      {
        BasicElement bEl = entry.getKey();
        HashMap<String, Double> elSignals = entry.getValue();
        HashMap<String, Double> signalsToProcess = new HashMap<>();
        for(String port : bEl.getPorts())
        {
          Double val = retreiveVal(bEl, port);
          signalsToProcess.put(port, val);
        }
        signalsToProcess.putAll(elSignals);
        Map<String, Double> processedSignals = bEl.process(signalsToProcess);
        for(Map.Entry<String, Double> ps : processedSignals.entrySet())
        {
          String port = ps.getKey();
          Double val = ps.getValue();
          // if it's event indeed
          if(val.compareTo(elementPortValue.get(bEl).get(port)) != 0)
          {
            // change circuit value
            elementPortValue.get(bEl).put(port, val);
            if (elementPortConnection.containsKey(bEl)
                    && elementPortConnection.get(bEl).containsKey(port))
            {
              Connection conn = elementPortConnection.get(bEl).get(port);
              // connection existance check
              if (conn != null)
              {
                BasicElement connEl = (BasicElement) conn.getOther(bEl);
                // register connected elements in the FEQ for the further processing
                feq.addEvent(bEl.getDelay(), connEl, conn.getElementPort(connEl), val);
              }
            }
          }
        }
      }
    }
  }

  /**
   * Calculates port value as average of two values on the connection ends.
   * If it's port is not connected - simply returns its value.
   * @param el - element
   * @param port - element's port
   * @return - calculated value for the circuit processing
   */
  private Double retreiveVal(BasicElement el, String port)
  {
    Double val = elementPortValue.get(el).get(port);
    if (elementPortConnection.containsKey(el)
            && elementPortConnection.get(el).containsKey(port))
    {
      Connection conn = elementPortConnection.get(el).get(port);
      // connection existance check
      if (conn != null)
      {
        BasicElement connEl = (BasicElement) conn.getOther(el);
        val = (val + elementPortValue.get(connEl).get(conn.getElementPort(connEl))) / 2;
      }
    }
    return val;
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
    element.addPropertyChangeListener(this);

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
    element.removePropertyChangeListener(this);
    elementPortValue.remove(element);
    return true;
  }

  private Double defaultValue()
  {
    return .0;
  }

  @Override
  public Map<String, Double> getElementSignals(Element element)
  {
    return Collections.unmodifiableMap(elementPortValue.get(element));
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals(BasicElement.PROP_STATE))
    {
      BasicElement bEl = (BasicElement)evt.getSource();
      feq.addEvent(bEl.getDelay(), bEl, bEl.getPort(0), retreiveVal(bEl, bEl.getPort(0)));
    }
  }
}
