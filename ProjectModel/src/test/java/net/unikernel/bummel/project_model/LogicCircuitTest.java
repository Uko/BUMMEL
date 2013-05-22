package net.unikernel.bummel.project_model;

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
}
