/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uko
 */
public class AnalyzerTest
{
	private Integer[] availablePorts = {0};
	public AnalyzerTest()
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
	 * Test of process method, of class Analyzer.
	 */
	@Test
	public void testProcess0()
	{
		System.out.println("process");
		Analyzer instance = new Analyzer(availablePorts);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 0.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(0, instance.getState());
	}
	
	@Test
	public void testProcess1()
	{
		System.out.println("process");
		Analyzer instance = new Analyzer(availablePorts);
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(1, instance.getState());
	}
}
