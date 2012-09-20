package net.unikernel.bummel.latches;

import java.util.Map;
import java.util.TreeMap;
import org.junit.*;
import net.unikernel.bummel.latches.JK.JK;
import static org.junit.Assert.*;

/**
 *
 * @author Roma
 */
public class JKTest 
{
    
    public JKTest() 
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
    JK instance;
    @Before
    public void setUp() 
    {
        instance = new JK();
    }
    
    @After
    public void tearDown() 
    {
        instance = null;
    }
    @Test
    public void JKProcess000()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("J and K ports are Both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",0.);
        valuesOnPorts.put("k",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess001()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("J and K ports are Both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",0.);
        valuesOnPorts.put("k",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess010()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("J port is 1 K port is 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",1.);
        valuesOnPorts.put("k",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess011()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("J port is 1 K port is 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",1.);
        valuesOnPorts.put("k",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess100()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("J port is 0 K port is 1:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",0.);
        valuesOnPorts.put("k",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess101()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("J port is 0 K port is 1:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",0.);
        valuesOnPorts.put("k",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess110()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 0;
        System.out.println("J and K ports are Both 1:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",1.);
        valuesOnPorts.put("k",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void JKProcess111()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("J and K ports are Both 1:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("j",1.);
        valuesOnPorts.put("k",1.);
        valuesOnPorts.put("q",1.);
        valuesOnPorts.put("notq",0.);
        expResult.put("j", 0.);
	expResult.put("k", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
}
