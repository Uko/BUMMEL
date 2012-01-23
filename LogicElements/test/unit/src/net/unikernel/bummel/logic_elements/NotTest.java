package net.unikernel.bummel.logic_elements;

import org.junit.After;
import org.junit.Before;
import java.util.TreeMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class NotTest
{
	public NotTest()
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
	
	Not instance;
	@Before
	public void setUp()
	{
		instance = new Not();
	}
	@After
	public void tearDown()
	{
		instance = null;
	}
	/**
	 * Test of process method, of class Not.
	 */
	@Test
	public void testProcess00()
	{
		System.out.println("not_process00");
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 0.);
		valuesOnPorts.put(1, 0.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		expResult.put(1, 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess01()
	{
		System.out.println("not_process01");
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 0.);
		valuesOnPorts.put(1, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		expResult.put(1, 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess10()
	{
		System.out.println("not_process10");
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		valuesOnPorts.put(1, 0.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		expResult.put(1, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess11()
	{
		System.out.println("not_process11");
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		valuesOnPorts.put(1, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		expResult.put(1, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
