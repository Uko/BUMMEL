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
public class GeneratorTest
{
	public GeneratorTest()
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
	
	Generator instance;
	@Before
	public void setUp()
	{
		instance = new Generator();
	}
	@After
	public void tearDown()
	{
		instance = null;
	}
	/**
	 * Test of process method, of class Generator.
	 */
	@Test
	public void testProcess0_0()
	{
		System.out.println("gen_process0_0");
		instance.setState(0);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 0.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess0_1()
	{
		System.out.println("gen_process0_1");
		instance.setState(1);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 0.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess1_0()
	{
		System.out.println("gen_process1_0");
		instance.setState(0);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess1_1()
	{
		System.out.println("gen_process1_1");
		instance.setState(1);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
