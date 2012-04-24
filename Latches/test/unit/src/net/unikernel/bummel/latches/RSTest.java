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
    RS instance;
    public RSTest() 
    {
          instance = new RS();
    }

    @BeforeClass
    public static void setUpClass() throws Exception 
    {
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception 
    {
    }
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    @Test
    public void A()
    {
        System.out.println("A");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-------------------------");
    }
    @Test
    public void B()
    {
        System.out.println("B");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-------------------------");
    } 
    @Test
    public void C()
    {
        System.out.println("C");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-------------------------");
    }
    @Test
    public void D()
    {
        System.out.println("D");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",0.);
        valuesOnPorts.put("set",1.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 1.);
        expResult.put("notq", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-------------------------");
    }
    @Test
    public void E()
    {
        System.out.println("E");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("reset",1.);
        valuesOnPorts.put("set",0.);
        valuesOnPorts.put("q",0.);
        valuesOnPorts.put("notq",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("reset", 0.);
	expResult.put("set", 0.);
	expResult.put("q", 0.);
        expResult.put("notq", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Q: " + result.get("q"));
        System.out.println("NotQ: " + result.get("notq"));
        System.out.println("-------------------------");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
