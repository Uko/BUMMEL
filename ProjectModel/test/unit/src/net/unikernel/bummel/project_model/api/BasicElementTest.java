package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unikernel.bummel.project_model.BasicConnection;
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
public class BasicElementTest
{
	BasicElement instance;
	Integer globalPort = -100500;
			
	public BasicElementTest()
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
		instance = new BasicElementImpl();
	}
	
	@After
	public void tearDown()
	{
		instance = null;
	}

	/**
	 * Test of getLabel method, of class BasicElement.
	 */
	@Test
	public void testGetLabel()
	{
		System.out.println("getLabel");
		String result = instance.getLabel();
		assertEquals("BasicElementImpl", result);
	}

	/**
	 * Test of setLabel method, of class BasicElement.
	 */
	@Test
	public void testSetLabel()
	{
		System.out.println("setLabel");
		String label = "Turned BasicElementImpl";
		instance.setLabel(label);
		assertTrue(label.equals(instance.getLabel()));
	}

	/**
	 * Test of getState method, of class BasicElement.
	 */
	@Test
	public void testGetState()
	{
		System.out.println("getState");
		int result = instance.getState();
		assertEquals(1, result);
	}

	/**
	 * Test of setState method, of class BasicElement.
	 */
	@Test
	public void testSetState()
	{
		System.out.println("setState");
		int state = 0;
		instance.setState(state);
		assertEquals(instance.getState(), 0);
	}

	/**
	 * Test of getCoords method, of class BasicElement.
	 */
	@Test
	public void testGetCoords()
	{
		System.out.println("getCoords");
		assertEquals(1, instance.getCoords().x);
		assertEquals(1, instance.getCoords().y);
	}
	@Test
	public void testSetCoords()
	{
		System.out.println("setCoords");
		instance.setCoords(new Point(2, 3));
		assertEquals(2, instance.getCoords().x);
		assertEquals(3, instance.getCoords().y);
	}

	
	/**
	 * Some element for testing purposes
	 */
	public class BasicElementImpl extends BasicElement
	{
		public BasicElementImpl()
		{
			super(new Integer[]{globalPort});
			setLabel("BasicElementImpl");
			setState(1);
			setCoords(new Point(1, 1));
		}

		@Override
		public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts)
		{
			return null;
		}
	}
}
