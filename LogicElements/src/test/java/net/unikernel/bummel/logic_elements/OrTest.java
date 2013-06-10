package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.Or.Or;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author uko
 */
public class OrTest
{
	Or instance;
	@BeforeMethod
	public void setUp()
	{
		instance = new Or();
	}
	@AfterMethod
	public void tearDown()
	{
		instance = null;
	}
	/**
	 * Test of process method, of class Or.
	 */
	@Test
	public void testProcess000()
	{
		System.out.println("or_process000");
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
		System.out.println("or_process001");
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
		System.out.println("or_process010");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 0.);
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
	public void testProcess011()
	{
		System.out.println("or_process011");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 0.);
		valuesOnPorts.put("input2", 1.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess100()
	{
		System.out.println("or_process100");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess101()
	{
		System.out.println("or_process101");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input1", 1.);
		valuesOnPorts.put("input2", 0.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input1", 0.);
		expResult.put("input2", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess110()
	{
		System.out.println("or_process110");
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
		System.out.println("or_process111");
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