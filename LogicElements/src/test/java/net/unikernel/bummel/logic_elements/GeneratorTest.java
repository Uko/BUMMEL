package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.Generator.Generator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess0_1()
	{
		System.out.println("gen_process0_1");
		instance.setState(1);
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess1_0()
	{
		System.out.println("gen_process1_0");
		instance.setState(0);
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess1_1()
	{
		System.out.println("gen_process1_1");
		instance.setState(1);
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
