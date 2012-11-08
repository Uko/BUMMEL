package net.unikernel.bummel.advanced_logic_elements;

import java.util.Map;
import java.util.TreeMap;
import org.junit.*;
import static org.junit.Assert.*;
import net.unikernel.bummel.advanced_logic_elements.Xnor.Xnor;
/**
 *
 * @author Roma
 */
public class XnorTest 
{
    
    public XnorTest() 
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
    
    Xnor instance;
    @Before
    public void setUp() 
    {
        instance = new Xnor();
    }
    
    @After
    public void tearDown() 
    {
        instance = null;
    }
    @Test
    public void testProcess000()
    {
        System.out.println("xnor_process 000");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess001()
    {
        System.out.println("xnor_process 001");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
	assertEquals(expResult, result);
    }
    @Test
    public void testProcess010()
    {
        System.out.println("xnor_process 010");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess011()
    {
        System.out.println("xnor_process 011");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",0.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess100()
    {
        System.out.println("xnor_process 100");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess101()
    {
        System.out.println("xnor_process 101");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",0.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 0.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess110()
    {
        System.out.println("xnor_process 110");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",0.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
    @Test
    public void testProcess111()
    {
        System.out.println("xnor_process 111");
        Map<String, Double> valuesOnPorts = new TreeMap<>();
        valuesOnPorts.put("input1",1.);
        valuesOnPorts.put("input2",1.);
        valuesOnPorts.put("output",1.);
        Map<String, Double> expResult= new TreeMap<>();
	expResult.put("input1", 0.);
	expResult.put("input2", 0.);
	expResult.put("output", 1.);
        Map result = instance.process(valuesOnPorts);
	assertEquals(expResult, result);
        System.out.println("Result output values: " + result.get("output"));
        System.out.println("-------------------------");
    }
}
