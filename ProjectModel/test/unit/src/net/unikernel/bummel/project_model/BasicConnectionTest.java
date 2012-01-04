package net.unikernel.bummel.project_model;

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
public class BasicConnectionTest
{
	BasicConnection instance;
	
	public BasicConnectionTest()
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
		instance = new BasicConnection(null, null);
	}
	
	@After
	public void tearDown()
	{
		instance = null;
	}

	/**
	 * Test of getOtherElement method, of class BasicConnection.
	 */
	@Test
	public void testGetOtherElement()
	{
		System.out.println("getOtherElement");
		assertEquals(null, instance.getOtherElement(null));
	}

	/**
	 * Test of getFirstElement method, of class BasicConnection.
	 */
	@Test
	public void testGetFirstElement()
	{
		System.out.println("getFirstElement");
		assertEquals(null, instance.getFirstElement());
	}

	/**
	 * Test of getSecondElement method, of class BasicConnection.
	 */
	@Test
	public void testGetSecondElement()
	{
		System.out.println("getSecondElement");
		assertEquals(null, instance.getSecondElement());
	}

	/**
	 * Test of getLabel method, of class BasicConnection.
	 */
	@Test
	public void testGetLabel()
	{
		System.out.println("getLabel");
		assertEquals("", instance.getLabel());
	}

	/**
	 * Test of setLabel method, of class BasicConnection.
	 */
	@Test
	public void testSetLabel()
	{
		System.out.println("setLabel");
		String label = "Connected";
		instance.setLabel(label);
		assertTrue(label.equals(instance.getLabel()));
	}

	/**
	 * Test of getValue method, of class BasicConnection.
	 */
	@Test
	public void testGetValue()
	{
		System.out.println("getValue");
		assertEquals(0, instance.getValue(), 0.0);
	}
}
