package net.unikernel.bummel.project_model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author mcangel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
	net.unikernel.bummel.project_model.BasicCircuitTest.class,
  net.unikernel.bummel.project_model.LogicCircuitTest.class,
	net.unikernel.bummel.project_model.api.ApiSuite.class
})
public class Project_modelSuite
{

	@BeforeClass
	public static void setUpClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
}
