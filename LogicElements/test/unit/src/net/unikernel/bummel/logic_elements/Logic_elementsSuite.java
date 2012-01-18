/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.logic_elements;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author uko
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
	net.unikernel.bummel.logic_elements.GeneratorTest.class, net.unikernel.bummel.logic_elements.AnalyzerTest.class, net.unikernel.bummel.logic_elements.AndTest.class, net.unikernel.bummel.logic_elements.OrTest.class, net.unikernel.bummel.logic_elements.NotTest.class, net.unikernel.bummel.logic_elements.SplitTest.class
})
public class Logic_elementsSuite
{
	@BeforeClass
	public static void setUpClass() throws Exception
	{
	}
	@AfterClass
	public static void tearDownClass() throws Exception
	{
	}
	
}
