package net.unikernel.bummel.latches;

import java.util.Map;
import java.util.TreeMap;
import org.junit.*;
import static org.junit.Assert.*;
import net.unikernel.bummel.latches.RS.RS;

/**
 *
 * @author Roma
 */
public class RSTest
{
   
    public RSTest() 
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
    RS instance;
    @Before
    public void setUp()
    {
         instance = new RS();
    }
    
    @After
    public void tearDown()
    {
         instance = null;
    }
    @Test
    public void RSTest()
    {
        System.out.println("Set and Reset ports are both 0:");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
        System.out.println("Set port is 1:");
        valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
       
        expResult.clear();
        valuesOnPorts.clear();
        System.out.println("Set port is 0:");
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
        expResult.clear();
        valuesOnPorts.clear();
        System.out.println("Reset port is 1:");
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
        expResult.clear();
        valuesOnPorts.clear();
        System.out.println("Reset port is 0:");
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
        expResult.clear();
        valuesOnPorts.clear();
        System.out.println("Set port is again 1:");
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
        expResult.clear();
        valuesOnPorts.clear();
        System.out.println("Set and Reset are both 1:");
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 0.);
        result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
                expResult.clear();
        valuesOnPorts.clear();
        System.out.println("Set and Reset are both 0 again:");
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
}
