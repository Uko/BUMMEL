import org.netbeans.junit.*;
import net.unikernel.bummel.logic_elements.And;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author uko
 */
public class LogicElementsTest extends NbTestCase
{
	public LogicElementsTest(String name)
	{
		super(name);
	}
	
	public void testAnd()
	{
		And and = new And();
		double[] result = {0,0,0};
		double[] val = {1,1,0};
		//assertEquals("Should be equal", result, and.getOnPins());
		and.touch(val);
		double[] result2 = {1,1,1};
		assertEquals("Should be equal2", result2[2], and.getOnPins()[2]);
	}
}
