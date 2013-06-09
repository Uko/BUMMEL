package net.unikernel.bummel.project_model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.Element;
import static org.testng.Assert.*;


import org.openide.util.Lookup;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogicCircuitTest
{
  @DataProvider(name = "circuits")
  public Object[][] availableCircuits()
  {
    Object[] toArray = Lookup.getDefault().lookupAll(Circuit.class).toArray();
    Object[][] cont = new Object[toArray.length][];
    for (int i = 0; i < cont.length; i++)
    {
      cont[i] = new Object[]
      {
        toArray[i]
      };
    }
    return cont;
  }

  @DataProvider(name = "logicCircuits")
  public Object[][] availableLogicCircuits()
  {
    List<Circuit> circuits = new LinkedList<>();
    for(Circuit c : Lookup.getDefault().lookupAll(Circuit.class))
    {
      if(c instanceof LogicCircuit || c instanceof LogicCircuitEventualDoubleWalkthrough)
      {
        circuits.add(c);
      }
    }
    Object[][] cont = new Object[circuits.size()][];
    for (int i = 0; i < cont.length; i++)
    {
      cont[i] = new Object[]
      {
        circuits.get(i)
      };
    }
    return cont;
  }

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

  BasicElement generator;
  BasicElement analyzer;
  BasicElement not;

  @BeforeMethod
  public void setUp()
  {
    generator = new BasicElement(new String[]{"output"})
    {
      @Override
      public Map<String, Double> process(Map<String, Double> valuesOnPorts)
      {
        valuesOnPorts.put(getPort(0), Double.valueOf(1));
        return valuesOnPorts;
      }

      @Override
      public String toString()
      {
        return "\"Generator("+getState()+")\"";
      }
    };
    analyzer = new BasicElement(new String[]{"input"})
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

      @Override
      public String toString()
      {
        return "\"Analyzer("+getState()+")\"";
      }
    };
    not = new BasicElement(new String[]{"input", "output"})
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
      @Override
      public String toString()
      {
        return "\"Not("+getState()+")\"";
      }
    };
  }

  @AfterMethod
  public void tearDown()
  {
    generator = null;
    analyzer = null;
    not = null;
  }

  /**
   * Test of addElement method, of class LogicCircuit.
   */
  @Test(dataProvider = "circuits")
  public void testAddElement(Circuit instance)
  {
    System.out.println("addElement");
    Element element = new BasicCircuitTest.BasicElementImpl();
    instance.addElement(element);
    assertTrue(instance.getElements().contains(element));
  }

  /**
   * Test of connectElements method, of class LogicCircuit.
   */
  @Test(dataProvider = "circuits")
  public void testConnectElements1(Circuit instance)
  {
    System.out.println("connectElements1");
    Element firstElement = new BasicCircuitTest.BasicElementImpl();
    Element secondElement = new BasicCircuitTest.BasicElementImpl();
    instance.addElement(firstElement);
    instance.addElement(secondElement);
    assertTrue(instance.connectElements(firstElement, firstElement.getPorts().get(0), secondElement, secondElement.getPorts().get(1)));
  }
  @Test(dataProvider = "circuits")
  public void testConnectElements2(Circuit instance)
  {
    System.out.println("connectElements2");
    instance.addElement(generator);
    instance.addElement(analyzer);
    instance.addElement(not);
    assertTrue(instance.connectElements(generator, generator.getPort(0), not, not.getPort(0)));
  }

  /**
   * Test of LogicCircuit connectElements and step methods.
   */
  @Test(dataProvider = "circuits", dependsOnMethods={"testConnectElements1"})
  public void testConnectElementsAndStep1(Circuit instance)
  {
    System.out.println("connectElementsAndStep1");
    instance.step();
  }
  @Test(dataProvider = "circuits")
  public void testConnectElementsAndStep2(Circuit instance)
  {
    System.out.println("connectElementsAndStep2");
    testConnectElements2(instance);
    instance.step();
    assertTrue(instance.getElementSignals(not).get("output") == .0);
    assertTrue(analyzer.getState() == 0);

    assertTrue(instance.connectElements(not, not.getPort(1), analyzer, analyzer.getPort(0)));
    instance.step();
    assertTrue(instance.getElementSignals(generator).get("output") != .0);
    assertTrue(instance.getElementSignals(not).get("output") == .0);
    assertTrue(analyzer.getState() == 0);
  }
  /**
   * Test of LogicCircuit connectElements, step and disconnectElements methods.
   */
  @Test(dataProvider = "logicCircuits")
  public void testElementsInteraction3(Circuit instance)
  {
    System.out.println("testElementsInteraction3");
    testConnectElementsAndStep2(instance);
    instance.disconnectElements(generator, generator.getPort(0), not, not.getPort(0));
    instance.step();
    assertTrue(instance.getElementSignals(generator).get("output") == 1.);
    assertTrue(instance.getElementSignals(not).get("input") == 0.);
    assertTrue(instance.getElementSignals(not).get("output") == 1.);
    assertTrue(analyzer.getState() == 1);
    assertTrue(instance.connectElements(generator, generator.getPort(0), not, not.getPort(0)));
    instance.step();
    assertTrue(analyzer.getState() == 0);
    instance.disconnectElements(analyzer, analyzer.getPort(0), not, not.getPort(1));
    instance.step();
    assertTrue(analyzer.getState() == 0);
    instance.disconnectElements(generator, generator.getPort(0), not, not.getPort(0));
    assertTrue(instance.connectElements(generator, generator.getPort(0), analyzer, analyzer.getPort(0)));
    instance.step();
    assertTrue(analyzer.getState() == 1);
  }

  @Test
  public void testElementsInteraction4()
  {
    LogicCircuit instance = new LogicCircuit();
    System.out.println("testElementsInteraction4");
    testConnectElementsAndStep2(instance);
    instance.disconnectElements(analyzer, "input", not, "output");
    instance.step();
    assertTrue(instance.getElementSignals(not).get("output") == 0);
    assertTrue(analyzer.getState() == 0);

    assertTrue(instance.connectElements(not, "output", analyzer, "input"));
    instance.step();
    assertTrue(analyzer.getState() == 0);
  }
}
