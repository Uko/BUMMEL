/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.latches;

import java.util.Map;
import static org.junit.Assert.*;
import java.util.TreeMap;
import net.unikernel.bummel.latches.D.D;
import org.junit.*;

/**
 *
 * @author Roma
 */
public class DTest 
{
    public DTest()
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
    D instance;
    @Before
    public void setUp()
    {
        instance = new D();
    }
    
    @After
    public void tearDown()
    {
        instance = null;
    }
    @Test
    public void DProcess00()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("C and D both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("d",0.);
        valuesOnPorts.put("c",0.);
        valuesOnPorts.put("t",0.);
        expResult.put("d", 0.);
	expResult.put("c", 0.);
	expResult.put("t", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("T: " + result.get("t"));
        System.out.println("-----");
    }
    @Test
    public void DProcess01()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("C and D both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("d",0.);
        valuesOnPorts.put("c",0.);
        valuesOnPorts.put("t",0.);
        expResult.put("d", 0.);
	expResult.put("c", 0.);
	expResult.put("t", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("T: " + result.get("t"));
        System.out.println("-----");
    }
    @Test
    public void DProcess10()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        System.out.println("C 1 D 0:");
        valuesOnPorts.put("d",0.);
        valuesOnPorts.put("c",1.);
        valuesOnPorts.put("t",0.);
        expResult.put("d", 0.);
	expResult.put("c", 0.);
	expResult.put("t", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("T: " + result.get("t"));
        System.out.println("-----");
    }
    @Test
    public void DProcess11()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        System.out.println("C 1 D 1:");
        valuesOnPorts.put("d",1.);
        valuesOnPorts.put("c",1.);
        valuesOnPorts.put("t",0.);
        expResult.put("d", 0.);
	expResult.put("c", 0.);
	expResult.put("t", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("T: " + result.get("t"));
        System.out.println("-----");
    }
}
