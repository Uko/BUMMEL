package net.unikernel.bummel.project_model;

import java.util.HashMap;
import java.util.PriorityQueue;
import net.unikernel.bummel.project_model.api.BasicElement;

/**
 *
 * @author imy
 */
public class FutureEventsQueue extends PriorityQueue<Integer>
{
  private HashMap<Integer, HashMap<BasicElement, HashMap<String, Double>>> delayElementPortValue = new HashMap<>();

  public void addEvent(Integer delay, BasicElement element, String port, double value)
  {
    if(!delayElementPortValue.containsKey(delay))
    {
      HashMap<BasicElement, HashMap<String, Double>> elEvent = new HashMap<>();
      HashMap<String, Double> portEvent = new HashMap<>();
      elEvent.put(element, portEvent);
      delayElementPortValue.put(delay, elEvent);
    }
    if(!delayElementPortValue.get(delay).containsKey(element))
    {
      HashMap<String, Double> portEvent = new HashMap<>();
      delayElementPortValue.get(delay).put(element, portEvent);
    }
    // overwrites previous value if there was one
    //@TODO:
    //  Is this correct?
    //  When such situation can appear? - Element has multiple inputs? - Check for existance and handle as IncorrectCircuitException?
    delayElementPortValue.get(delay).get(element).put(port, value);
    add(delay);
  }
  
  public HashMap<BasicElement, HashMap<String, Double>> getEvents(Integer delay)
  {
    return delayElementPortValue.get(delay);
  }
}
