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
public class AndTest
{
	public AndTest()
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
	
	And instance;
	@Before
	public void setUp()
	{
		instance = new And();
	}
	@After
	public void tearDown()
	{
		instance = null;
	}
	/**
	 * Test of process method, of class And.
	 */
	@Test
	public void testProcess000()
	{
		System.out.println("and_process000");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 0.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess001()
	{
		System.out.println("and_process001");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 1.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess010()
	{
		System.out.println("and_process010");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 0.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess011()
	{
		System.out.println("and_process011");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 1.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess100()
	{
		System.out.println("and_process100");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 0.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess101()
	{
		System.out.println("and_process101");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 1.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess110()
	{
		System.out.println("and_process110");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 0.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess111()
	{
		System.out.println("and_process111");
		Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 1.);
		Map expResult = new TreeMap<String, Double>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
