/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

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
	private Integer[] availablePorts = {0};
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
	/**
	 * Test of process method, of class Generator.
	 */
	@Test
	public void testProcess0_0()
	{
		System.out.println("process");
		Generator instance = new Generator(availablePorts);
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
		System.out.println("process");
		Generator instance = new Generator(availablePorts);
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
		System.out.println("process");
		Generator instance = new Generator(availablePorts);
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
		System.out.println("process");
		Generator instance = new Generator(availablePorts);
		instance.setState(1);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
