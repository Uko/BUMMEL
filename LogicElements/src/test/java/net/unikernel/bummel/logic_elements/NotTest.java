package net.unikernel.bummel.logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.logic_elements.Not.Not;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author uko
 */
public class NotTest
{
	Not instance;
	@BeforeMethod
	public void setUp()
	{
		instance = new Not();
	}
	@AfterMethod
	public void tearDown()
	{
		instance = null;
	}
	/**
	 * Test of process method, of class Not.
	 */
	@Test
	public void testProcess00()
	{
		System.out.println("not_process00");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input", 0.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess01()
	{
		System.out.println("not_process01");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input", 0.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input", 0.);
		expResult.put("output", 1.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess10()
	{
		System.out.println("not_process10");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input", 1.);
		valuesOnPorts.put("output", 0.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testProcess11()
	{
		System.out.println("not_process11");
		Map<String, Double> valuesOnPorts = new TreeMap<>();
		valuesOnPorts.put("input", 1.);
		valuesOnPorts.put("output", 1.);
		Map<String, Double> expResult= new TreeMap<>();
		expResult.put("input", 0.);
		expResult.put("output", 0.);
		Map result = instance.process(valuesOnPorts);
		assertEquals(expResult, result);
	}
}
