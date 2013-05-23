package net.unikernel.bummel.project_model;

import java.util.HashMap;
import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogicCircuitTest
{
  @BeforeClass
  public static void setUpClass() throws Exception
  {
    System.out.println("LogicCircuitTest -- start");
  }

  @AfterClass
  public static void tearDownClass() throws Exception
  {
    System.out.println("LogicCircuitTest -- end");
  }

  LogicCircuit instance;

  @Before
  public void setUp()
  {
    instance = new LogicCircuit();
  }

  @After
  public void tearDown()
  {
    instance = null;
  }

  /**
   * Test of addElement method, of class LogicCircuit.
   */
  @Test
  public void testAddElement()
  {
    System.out.println("addElement");
    Element element = new BasicCircuitTest.BasicElementImpl();
    instance.addElement(element);
    assertTrue(instance.getElements().contains(element));
  }

  /**
   * Test of connectElements method, of class LogicCircuit.
   */
  @Test
  public void testConnectElements1()
  {
    System.out.println("connectElements1");
    Element firstElement = new BasicCircuitTest.BasicElementImpl();
    Element secondElement = new BasicCircuitTest.BasicElementImpl();
    instance.addElement(firstElement);
    instance.addElement(secondElement);
    assertTrue(instance.connectElements(firstElement, firstElement.getPorts().get(0), secondElement, secondElement.getPorts().get(1)));
  }
  @Test
  public void testConnectElements2()
  {
    System.out.println("connectElements2");
    // generator
    Element firstElement = new BasicElement(new String[]{"output"})
    {
      @Override
      public Map<String, Double> process(Map<String, Double> valuesOnPorts)
      {
        valuesOnPorts.put(getPort(0), Double.valueOf(1));
        return valuesOnPorts;
      }
    };
    // analyzer
    Element secondElement = new BasicElement(new String[]{"input"})
    {
      @Override
      public Map<String, Double> process(Map<String, Double> valuesOnPorts)
      {
        valuesOnPorts = nullFreePortsOf(valuesOnPorts);
        if (valuesOnPorts.get(getPort(0)).compareTo(Double.valueOf(0.d)) != 0)
        {
          setState(1); //light it
        } else
        {
          setState(0); //douse it
        }
        valuesOnPorts.put("input", 0.d);
        return valuesOnPorts;
      }
    };
    // not
    Element thirdElement = new BasicElement(new String[]{"input", "output"})
    {
      @Override
      public Map<String, Double> process(Map<String, Double> valuesOnPorts)
      {
        valuesOnPorts = nullFreePortsOf(valuesOnPorts);
        if (valuesOnPorts.get(getPort(0)).compareTo(Double.valueOf(0.d)) == 0)
        {
          valuesOnPorts.put(getPort(1), Double.valueOf(1.d));
        } else
        {
          valuesOnPorts.put(getPort(1), Double.valueOf(0.d));
        }
        valuesOnPorts.put(getPort(0), 0.d);
        return valuesOnPorts;
      }
    };
    instance.addElement(firstElement);
    instance.addElement(secondElement);
    instance.addElement(thirdElement);
    assertTrue(instance.connectElements(firstElement, firstElement.getPort(0),
            thirdElement, thirdElement.getPort(0)));
  }

  /**
   * Test of LogicCircuit connectElements and step methods.
   */
  @Test
  public void testConnectElementsAndStep1()
  {
    System.out.println("connectElementsAndStep1");
    testConnectElements1();
    for(int i = 0; i< 5; i++) instance.step();
  }
  @Test
  public void testConnectElementsAndStep2()
  {
    System.out.println("connectElementsAndStep2");
    testConnectElements2();
    for(int i = 0; i< 5; i++) instance.step();
  }
}
