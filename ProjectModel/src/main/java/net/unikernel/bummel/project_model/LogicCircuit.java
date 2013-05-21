package net.unikernel.bummel.project_model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Connection;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author imy
 */
@ServiceProvider(service=Circuit.class)
@Messages("LC_DisplayName=Logic Circuit")
public class LogicCircuit extends BasicCircuit
{
  private FutureEventsQueue feq;

  private class Event
  {
    public BasicElement element;
    public String port;
    public double value;
  }

  private Map<Element, Map<String, Double>> elementPortValue;

  @Override public void step()
  {
//    while(!feq.isEmpty())
//    {
//      Set<BasicElement> activeElements = new HashSet<>();
//      System.out.println("Delay: " + feq.peek());
//      Integer currentDelay = feq.peek();
//      for (Map.Entry<BasicElement, HashMap<String, Double>> entry : feq.getEvents(currentDelay).entrySet())
//      {
//        BasicElement basicElement = entry.getKey();
//        HashMap<String, Double> hashMap = entry.getValue();
//        for (Map.Entry<String, Double> portValue : hashMap.entrySet())
//        {
//          String port = portValue.getKey();
//          Double registeredValue = portValue.getValue();
//          if(registeredValue != elementPortValue.get(basicElement).get(port))
//          {//it's event indeed - feq value and current value differs
//            elementPortValue.get(basicElement).put(port, registeredValue);
//            //fetch all connected elements
//            Connection con = elementPortConnection.get(basicElement).get(port);
//            Map<String, Double> portsMap = new TreeMap<>();
//            //copy values from connections to the current element ports
//            for (Map.Entry<String, Connection> j : elementPortConnection.get(con.getSecondElement()).entrySet())
//            {
//              portsMap.put(j.getKey(), connections.get(j.getValue()));
//            }
//            portsMap.put(con.getSecondElementPort(), registeredValue);
//            con.getSecondElement().process(portsMap);
//            for (Map.Entry<String, Double> j : portsMap.entrySet())
//            {
//              //take output values only from present and used ports
//              if (elementPortConnection.get(el).containsKey(j.getKey()))
//              {
//                feq.addEvent(((BasicElement)con.getSecondElement()).getDelay(), con.getSecondElement(), port, registeredValue);
//                tempoMap.get(elementPortConnection.get(el).get(j.getKey())).add(j.getValue());
//              }
//            }
//          }
//        }
//        
//      }
//    }

//		Map<Connection,ArrayList<Double>> tempoMap = new HashMap<>();
//		for (Connection i : connections.keySet())
//		{
//			tempoMap.put(i, new ArrayList<Double>());
//		}
//		for (Element el: elements)
//		{
//			//check whether element is at least connected to something
//			if(elementPortConnection.containsKey(el))
//			{
//				Map<String, Double> portsMap = new TreeMap<>();
//        Map<String, Double> newPortsMap;
//				//copy values from connections to the current element ports
//				for (Map.Entry<String, Connection> j : elementPortConnection.get(el).entrySet())
//				{
//					portsMap.put(j.getKey(), connections.get(j.getValue()));
//				}
//				try
//				{
////					portsMap = el.process(portsMap);
//          newPortsMap = el.process(portsMap);
//          boolean valuesChanged = false;
//          for (String port : portsMap.keySet())
//          {
//            if(newPortsMap.get(port) != portsMap.get(port))
//            {
//              valuesChanged = true;
//              break;
//            }
//          }
//          if(valuesChanged)
//          {
//            //add to queue
//          }
//				} //ignore elements crashes
//				catch (NullPointerException ex)
//				{
//					//put 0s on the used ports
//					for (String j : el.getPorts())
//					{
//						//"used" means connected to something
//						if (elementPortConnection.get(el).containsKey(j))
//						{
//							portsMap.put(j, 0.);
//						}
//					}
//				}
//				for (Map.Entry<String, Double> j : portsMap.entrySet())
//				{
//					//take output values only from present and used ports
//					if (elementPortConnection.get(el).containsKey(j.getKey()))
//					{
//						tempoMap.get(elementPortConnection.get(el).get(j.getKey())).add(j.getValue());
//					}
//				}
//			}
//			else
//			{//if not - touch it with zeros
//				Map<String, Double> portsMap = new TreeMap<>();
//				for (String str : el.getPorts())
//				{
//					portsMap.put(str, .0);
//				}
//				el.process(portsMap);
//			}
//		}
//		//sets values on the connections as a middle of the edges values
//		for (Map.Entry<Connection,ArrayList<Double>> i : tempoMap.entrySet())
//		{
//			connections.put(i.getKey(), (i.getValue().get(0) +i.getValue().get(1))/2);
//		}
//		
	}

  @Override
  public String displayName()
  {
    return NbBundle.getMessage(LogicCircuit.class, "LC_DisplayName");
  }
}
