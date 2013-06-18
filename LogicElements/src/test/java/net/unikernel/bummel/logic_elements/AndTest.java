package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.And.And;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author uko
 */
public class AndTest
{
	And instance;
	@BeforeMethod
	public void setUp()
	{
		instance = new And();
	}
	@AfterMethod
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
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
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
