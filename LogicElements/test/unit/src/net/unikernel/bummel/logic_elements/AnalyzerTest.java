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
		System.out.println("an_process");
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
		System.out.println("an_process");
		Map<Integer, Double> valuesOnPorts = new TreeMap<Integer, Double>();
		valuesOnPorts.put(0, 1.);
		Map expResult = new TreeMap<Integer, Double>();
		expResult.put(0, 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
		assertEquals(1, instance.getState());
	}
}
