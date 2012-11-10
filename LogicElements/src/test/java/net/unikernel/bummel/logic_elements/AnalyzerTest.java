package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.Analyzer.Analyzer;
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
public class AnalyzerTest
{	
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
	
	Analyzer instance;
	@Before
	public void setUp()
	{
		instance = new Analyzer();
	}
	@After
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
