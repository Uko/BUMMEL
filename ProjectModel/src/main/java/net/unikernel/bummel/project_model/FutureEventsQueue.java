package net.unikernel.bummel.project_model;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import net.unikernel.bummel.project_model.api.BasicElement;

public class FutureEventsQueue
{
  private PriorityQueue<Integer> delays = new PriorityQueue<>();
  private HashMap<Integer, HashMap<BasicElement, HashMap<String, Double>>> delayElementPortValue = new HashMap<>();

  public void addEvent(Integer delay, BasicElement element, String port, double value)
  {
    //@TODO: ?how Integer and Double values are copied
    //@TODO: ?when delay is 0

    if(!delayElementPortValue.containsKey(delay))
    {
      delayElementPortValue.put(delay, new HashMap<BasicElement, HashMap<String, Double>>());
    }
    HashMap<BasicElement, HashMap<String, Double>> delayEvents = delayElementPortValue.get(delay);
    if(!delayEvents.containsKey(element))
    {
      delayEvents.put(element, new HashMap<String, Double>());
    }
    // overwrites previous value if there was one
    //@TODO:
    //  Is this correct?
    //  When such situation can appear? - Element has multiple inputs? - Check for existance and handle as IncorrectCircuitException?
    delayEvents.get(element).put(port, value);
    if(!delays.contains(delay))
    {
      delays.add(delay);
    }
  }

  public void removeEvent(Integer delay, BasicElement bEl)
  {
    Set<Map.Entry<Integer, HashMap<BasicElement, HashMap<String, Double>>>> entrySet = delayElementPortValue.entrySet();
    delayElementPortValue.get(delay).remove(bEl);
    if(delayElementPortValue.get(delay).isEmpty())
    {
      delayElementPortValue.remove(delay);
      delays.remove(delay);
    }
  }

  public Map.Entry<Integer, HashMap<BasicElement, HashMap<String, Double>>> remove()
  {
    Map.Entry<Integer, HashMap<BasicElement, HashMap<String, Double>>> head
            = new AbstractMap.SimpleEntry<>(delays.remove(), null);
    head.setValue(delayElementPortValue.remove(head.getKey()));
    return head;
  }

  public HashMap<BasicElement, HashMap<String, Double>> getEvents(Integer delay)
  {
    return delayElementPortValue.get(delay);
  }

  public boolean isEmpty()
  {
    return delays.isEmpty();
  }

  /**
   * Returns a string representation of this FEQ.
   * Simply reproduces string representation of the internal
   * delay -> element -> port -> value map.
   *
   * @return a string representation of this FEQ
   */
  @Override
  public String toString()
  {
    return delayElementPortValue.toString();
  }
}
