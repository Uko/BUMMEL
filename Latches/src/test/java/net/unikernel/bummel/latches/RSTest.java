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
    //RSProcess(R port value)(S port value) (State value)
    @Test
    public void RSProcess001()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("Set and Reset ports are Both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess002()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 2;
        System.out.println("Set and Reset ports are Both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
        
    }
    @Test
    public void RSProcess003()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 3;
        System.out.println("Set and Reset ports are both 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess011()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("Reset port is 1 Set port is 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess012()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 2;
        System.out.println("Reset port is 1 Set port is 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess013()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 3;
        System.out.println("Reset port is 1 Set port is 0:");
        System.out.println("Previous state: " + instance.state);
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess101()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("Set port is 1 Reset port is 0:");
        System.out.println("Previous state: " + instance.state);
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
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
       
    }
    @Test
    public void RSProcess102()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 2;
        System.out.println("Set port is 1 Reset port is 0:");
        System.out.println("Previous state: " + instance.state);
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
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
       
    }
    @Test
    public void RSProcess103()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 3;
        System.out.println("Set port is 1 Reset port is 0:");
        System.out.println("Previous state: " + instance.state);
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
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
       
    }
    @Test
    public void RSProcess111()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 1;
        System.out.println("Set and Reset are both 1:");
        System.out.println("Previos state: " + instance.state);
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess112()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 2;
        System.out.println("Set and Reset are both 1:");
        System.out.println("Previos state: " + instance.state);
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
    @Test
    public void RSProcess113()
    {
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        Map<String, Double> expResult = new TreeMap<>();
        instance.state = 3;
        System.out.println("Set and Reset are both 1:");
        System.out.println("Previos state: " + instance.state);
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
        assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q")); 
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-----");
    }
}
