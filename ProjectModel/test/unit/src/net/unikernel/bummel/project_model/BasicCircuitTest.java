/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.project_model;

import java.awt.Point;
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
	
	/**
	 * Test of addElement method, of class BasicCircuit.
	 */
	@Test
	public void testAddElement()
	{
		System.out.println("addElement");
		Element element = new BasicElementImpl();
		BasicCircuit instance = new BasicCircuit();
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
		BasicCircuit instance = new BasicCircuit();
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
		Integer firstElementPort = 0;
		Element secondElement = new BasicElementImpl();
		Integer secondElementPort = 1;
		BasicCircuit instance = new BasicCircuit();
		instance.connectElements(firstElement, firstElementPort, secondElement, secondElementPort);
		//O_o
		//We can connect elements that are not in the circuit.
	}
	
	/**
	 * Test of disconectElements method, of class BasicCircuit.
	 */
	@Test
	public void testDisconectElements()
	{
		System.out.println("connectElements");
		Element firstElement = new BasicElementImpl();
		Integer firstElementPort = 0;
		Element secondElement = new BasicElementImpl();
		Integer secondElementPort = 1;
		BasicCircuit instance = new BasicCircuit();
		instance.addElement(firstElement);
		instance.addElement(secondElement);
		instance.connectElements(firstElement, firstElementPort, secondElement, secondElementPort);
		instance.disconectElements(firstElement, firstElementPort, secondElement, secondElementPort);
		//o_O
		//We can connect elements that are not in the circuit.
	}
	
	/**
	 * Test of step method, of class BasicCircuit.
	 */
	@Test
	public void testStep()
	{
		System.out.println("step");
		Element firstElement = new BasicElementImpl();
		Integer firstElementPort = 0;
		Element secondElement = new BasicElementImpl();
		Integer secondElementPort = 1;
		BasicCircuit instance = new BasicCircuit();
		instance.addElement(firstElement);
		instance.addElement(secondElement);
		instance.connectElements(firstElement, firstElementPort, secondElement, secondElementPort);
		instance.step();
	}
	
	/**
	 * Test of getLabel method, of class BasicCircuit.
	 */
	@Test
	public void testGetLabel()
	{
		System.out.println("getLabel");
		BasicCircuit instance = new BasicCircuit();
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
		BasicCircuit instance = new BasicCircuit();
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
		BasicCircuit instance = new BasicCircuit();
		int expResult = 0;
		int result = instance.getState();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
	/**
	 * Test of setState method, of class BasicCircuit.
	 */
	@Test
	public void testSetState()
	{
		System.out.println("setState");
		int state = 0;
		BasicCircuit instance = new BasicCircuit();
		instance.setState(state);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
	/**
	 * Test of getCoords method, of class BasicCircuit.
	 */
	@Test
	public void testGetCoords()
	{
		System.out.println("getCoords");
		BasicCircuit instance = new BasicCircuit();
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
		BasicCircuit instance = new BasicCircuit();
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
		Map<Integer, Double> valuesOnPorts = null;
		BasicCircuit instance = new BasicCircuit();
		Map expResult = null;
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Some element for testing purposes
	 */
	public class BasicElementImpl extends BasicElement
	{
		public BasicElementImpl()
		{
			super(new String[]{"",""});
			setLabel("BasicElementImpl");
			setState(1);
			setCoords(new Point(1, 1));
		}

		@Override
		public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
		{
			return valuesOnPorts;
		}
	}
	
}