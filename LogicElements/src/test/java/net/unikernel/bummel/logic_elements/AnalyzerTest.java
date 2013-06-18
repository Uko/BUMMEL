package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.Analyzer.Analyzer;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author uko
 */
public class AnalyzerTest
{
	Analyzer instance;
	@BeforeMethod
	public void setUp()
	{
		instance = new Analyzer();
	}
	@AfterMethod
	public void tearDown()
	{
		instance = null;
	}
	/**
	 * Test of process method, of class Analyzer.
	 */
	@Test
	public void testProcess0()
	{
		System.out.println("an_process0");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input", 0.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(0, instance.getState());
	}
	
	@Test
	public void testProcess1()
	{
		System.out.println("an_process1");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(1, instance.getState());
	}
}
