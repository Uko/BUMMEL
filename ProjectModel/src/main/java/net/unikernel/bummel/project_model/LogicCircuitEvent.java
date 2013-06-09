package net.unikernel.bummel.project_model;

import java.util.HashMap;
import java.util.Objects;
import net.unikernel.bummel.project_model.api.BasicElement;

public class LogicCircuitEvent extends HashMap<String, Double>// implements Comparable<LogicCircuitEvent>
{
//  public int delay;
  public BasicElement element;

  public LogicCircuitEvent(/*int delay, */BasicElement element)
  {
//    this.delay = delay;
    this.element = element;
  }

  @Override
  public boolean equals(Object o)
  {
    if (getClass() != o.getClass())
    {
      return false;
    }
    LogicCircuitEvent lce = (LogicCircuitEvent)o;
//    if(delay != lce.delay)
//    {
      return element.equals(lce.element);
//    }
//    return true;
  }

  @Override
  public int hashCode()
  {
    int hash = 7;
    hash = 97 * hash/* + this.delay*/ + Objects.hashCode(this.element);
    return hash;
  }

//  @Override
//  public int compareTo(LogicCircuitEvent o)
//  {
//    return this.delay - ((LogicCircuitEvent) o).delay;
//  }
}
