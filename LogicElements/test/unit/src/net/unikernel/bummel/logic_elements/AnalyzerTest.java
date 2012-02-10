package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.logic_elements.Analyzer.Analyzer;
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
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input", 0.);
		Map<String, Double> expResult= new TreeMap<String, Double>();
		expResult.put("input", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(0, instance.getState());
	}
	
	@Test
	public void testProcess1()
	{
		System.out.println("an_process1");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input", 1.);
		Map<String, Double> expResult= new TreeMap<String, Double>();
		expResult.put("input", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(1, instance.getState());
	}
}
