/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.latches;

import java.util.Map;
import static org.junit.Assert.*;
import java.util.TreeMap;
import net.unikernel.bummel.latches.T.T;
import org.junit.*;

public class TTest 
{
    public TTest()
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
    T instance;
    @Before
    public void setUp()
    {
        instance = new T();
    }
    
    @After
    public void tearDown()
    {
        instance = null;
    }
    @Test
    public void TProcess00()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("T port is 0: ");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("t",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("t", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void TProcess01()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("T port is 0: ");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("t",0.);
        valuesOnPorts.put("q",1.);
        valuesOnPorts.put("notq",0.);
        expResult.put("t", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void TProcess10()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("T port is 1: ");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("t",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("t", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void TProcess11()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("T port is 1: ");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("t",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("t", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
}
