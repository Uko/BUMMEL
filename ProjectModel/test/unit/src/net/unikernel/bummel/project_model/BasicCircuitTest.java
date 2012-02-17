package net.unikernel.bummel.project_model;

import org.junit.After;
import org.junit.Before;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class BasicCircuitTest
{
	public BasicCircuitTest()
	{
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception
	{
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception
	{
	}
	
	BasicCircuit instance;
	@Before
	public void setUp()
	{
		instance = new BasicCircuit();
	}
	@After
	public void tearDown()
	{
		instance = null;
	}
	
	/**
	 * Test of addElement method, of class BasicCircuit.
	 */
	@Test
	public void testAddElement()
	{
		System.out.println("addElement");
		Element element = new BasicElementImpl();
		instance.addElement(element);
		assertTrue(instance.getElements().contains(element));
	}
	
	/**
	 * Test of removeElement method, of class BasicCircuit.
	 */
	@Test
	public void testRemoveElement()
	{
		System.out.println("removeElement");
		Element element = new BasicElementImpl();
		instance.addElement(element);
		instance.removeElement(element);
		assertFalse(instance.getElements().contains(element));
	}
	
	/**
	 * Test of connectElements method, of class BasicCircuit.
	 */
	@Test
	public void testConnectElements()
	{
		System.out.println("connectElements");
		Element firstElement = new BasicElementImpl();
		String firstElementPort = "in";
		Element secondElement = new BasicElementImpl();
		String secondElementPort = "out";
		instance.connectElements(firstElement, firstElementPort, secondElement, secondElementPort);
	}
	
	/**
	 * Test of disconectElements method, of class BasicCircuit.
	 */
	@Test
	public void testDisconectElements()
	{
		System.out.println("connectElements");
		Element firstElement = new BasicElementImpl();
		String firstElementPort = "in";
		Element secondElement = new BasicElementImpl();
		String secondElementPort = "out";
		instance.addElement(firstElement);
		instance.addElement(secondElement);
		instance.connectElements(firstElement, firstElementPort, secondElement, secondElementPort);
		instance.disconectElements(firstElement, firstElementPort, secondElement, secondElementPort);
	}
	
	/**
	 * Test of step method, of class BasicCircuit.
	 */
	@Test
	public void testStep()
	{
		System.out.println("step");
		Element firstElement = new BasicElementImpl();
		String firstElementPort = "in";
		Element secondElement = new BasicElementImpl();
		String secondElementPort = "out";
		instance.addElement(firstElement);
		instance.addElement(secondElement);
		instance.connectElements(firstElement, firstElementPort, secondElement, secondElementPort);
		instance.step();
	}
	
	@Test
	public void testStep2()
	{
		System.out.println("step2");
		Element firstElement = new BasicElementImpl();
		instance.addElement(firstElement);
		instance.step();
	}
	
	@Test
	public void testStep3()
	{
		System.out.println("step3");
		Element elem1 = new BasicElementImpl();
		Element elem2 = new BasicElementImpl();
		Element elem3 = new BasicElementImpl();
		instance.addElement(elem1);
		instance.addElement(elem2);
		instance.addElement(elem3);
		instance.connectElements(elem1, elem1.getPorts().get(1), elem2, elem2.getPorts().get(0));
		instance.step();
	}
	
	/**
	 * Test of getLabel method, of class BasicCircuit.
	 */
	@Test
	public void testGetLabel()
	{
		System.out.println("getLabel");
		String expResult = "";
		String result = instance.getLabel();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of setLabel method, of class BasicCircuit.
	 */
	@Test
	public void testSetLabel()
	{
		System.out.println("setLabel");
		String label = "hello";
		instance.setLabel(label);
		assertEquals(label, instance.getLabel());
	}
	
	/**
	 * Test of getState method, of class BasicCircuit.
	 */
	@Test
	public void testGetState()
	{
		System.out.println("getState");
		assertEquals(1, instance.getState());
	}
	
	/**
	 * Test of setState method, of class BasicCircuit.
	 */
	@Test
	public void testSetState()
	{
		System.out.println("setState");
		instance.setState(0);
	}
	
	/**
	 * Test of getCoords method, of class BasicCircuit.
	 */
	@Test
	public void testGetCoords()
	{
		System.out.println("getCoords");
		Point expResult = new Point(0, 0);
		Point result = instance.getCoords();
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of setCoords method, of class BasicCircuit.
	 */
	@Test
	public void testSetCoords()
	{
		System.out.println("setCoords");
		Point point = new Point(13, 25);
		instance.setCoords(point);
		Point result = instance.getCoords();
		assertEquals(point, result);
	}
	
	/**
	 * Test of process method, of class BasicCircuit.
	 */
	@Test
	public void testProcess()
	{
		System.out.println("process");
		assertEquals(null, instance.process(null));
	}

	/**
	 * Some element for testing purposes
	 */
	public class BasicElementImpl extends BasicElement
	{
		public BasicElementImpl()
		{
			super(new String[]{"in","out"});
			setLabel("BasicElementImpl");
			setState(1);
			setCoords(new Point(1, 1));
		}

		@Override
		public Map<String, Double> process(Map<String, Double> valuesOnPorts)
		{
			//evil code to crash scheme ]:P
			HashMap<String, Double> map = new HashMap<>();
			map.put("in", 0.);
			map.put("out", 1.);
			map.put("this way", 0.);
			map.put("that way", 1.);
			map.put("up", 0.);
			return map;
		}
	}
	
}