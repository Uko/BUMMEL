package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.Generator.Generator;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author uko
 */
public class GeneratorTest
{
	Generator instance;
	@BeforeMethod
	public void setUp()
	{
		instance = new Generator();
	}
	@AfterMethod
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
