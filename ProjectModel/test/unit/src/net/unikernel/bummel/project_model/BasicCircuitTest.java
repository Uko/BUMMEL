package net.unikernel.bummel.project_model;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mcangel
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
	
	@Before
	public void setUp()
	{
	}
	
	@After
	public void tearDown()
	{
	}

	/**
	 * Test of addElement method, of class BasicCircuit.
	 */
	@Test
	public void testAddElement()
	{
		System.out.println("addElement");
		BasicElement element = null;
		BasicCircuit instance = new BasicCircuit();
		int expResult = 0;
		int result = instance.addElement(element);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of removeElement method, of class BasicCircuit.
	 */
	@Test
	public void testRemoveElement()
	{
		System.out.println("removeElement");
		int elementId = 0;
		BasicCircuit instance = new BasicCircuit();
		BasicElement expResult = null;
		BasicElement result = instance.removeElement(elementId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of connectElements method, of class BasicCircuit.
	 */
	@Test
	public void testConnectElements()
	{
		System.out.println("connectElements");
		int first = 0;
		int second = 0;
		BasicCircuit instance = new BasicCircuit();
		int expResult = 0;
		int result;
		try
		{
			result = instance.connectElements(first, "", second, "");
			assertEquals(expResult, result);
		} catch (Exception ex)
		{
			Logger.getLogger(BasicCircuitTest.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of disconect method, of class BasicCircuit.
	 */
	@Test
	public void testDisconect()
	{
		System.out.println("disconect");
		int connectionId = 0;
		BasicCircuit instance = new BasicCircuit();
		Connection expResult = null;
		Connection result = instance.disconect(connectionId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of step method, of class BasicCircuit.
	 */
	@Test
	public void testStep()
	{
		System.out.println("step");
		BasicCircuit instance = new BasicCircuit();
		instance.step();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
