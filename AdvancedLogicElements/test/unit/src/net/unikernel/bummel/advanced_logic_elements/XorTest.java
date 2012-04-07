package net.unikernel.bummel.advanced_logic_elements;

import java.util.Map;
import java.util.TreeMap;
import net.unikernel.bummel.advanced_logic_elements.Xor.Xor;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Roma
 */
public class XorTest {
    
    public XorTest() 
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
    
    Xor instance;
    @Before
    public void setUp() 
    {
        instance = new Xor();
    }
    
    @After
    public void tearDown() 
    {
        instance = null;
    }
    @Test
    public void testProcess000()
    {
        System.out.println("xor_process 000");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess001()
    {
        System.out.println("xor_process 001");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
	assertEquals(expResult, result);
    }
    @Test
    public void testProcess010()
    {
        System.out.println("xor_process 010");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess011()
    {
        System.out.println("xor_process 011");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess100()
    {
        System.out.println("xor_process 100");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess101()
    {
        System.out.println("xor_process 101");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess110()
    {
        System.out.println("xor_process 110");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess111()
    {
        System.out.println("xor_process 111");
        Map<String, Double> valuesOnPorts = new TreeMap<String, Double>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<String, Double>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
}
