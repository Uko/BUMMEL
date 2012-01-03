/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.project_model;

import java.util.ArrayList;
import net.unikernel.bummel.project_model.api.BasicElement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mcangel
 */
public class ProjectModelTest
{
	public ProjectModelTest()
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
	
	@Before
	public void setUp()
	{
	}
	
	@After
	public void tearDown()
	{
	}

	/**
	 * Test of getFilePath method, of class ProjectModel.
	 */
	@Test
	public void testGetFilePath()
	{
		System.out.println("getFilePath");
		ProjectModel instance = new ProjectModel();
		String expResult = "";
		String result = instance.getFilePath();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getName method, of class ProjectModel.
	 */
	@Test
	public void testGetName()
	{
		System.out.println("getName");
		ProjectModel instance = new ProjectModel();
		String expResult = "";
		String result = instance.getName();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setFilePath method, of class ProjectModel.
	 */
	@Test
	public void testSetFilePath()
	{
		System.out.println("setFilePath");
		String filePath = "";
		ProjectModel instance = new ProjectModel();
		instance.setFilePath(filePath);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setName method, of class ProjectModel.
	 */
	@Test
	public void testSetName()
	{
		System.out.println("setName");
		String name = "";
		ProjectModel instance = new ProjectModel();
		instance.setName(name);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getModel method, of class ProjectModel.
	 */
	@Test
	public void testGetModel()
	{
		System.out.println("getModel");
		ProjectModel instance = new ProjectModel();
		ArrayList expResult = null;
		ArrayList result = instance.getModel();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setModel method, of class ProjectModel.
	 */
	@Test
	public void testSetModel()
	{
		System.out.println("setModel");
		ArrayList<BasicElement> model = null;
		ProjectModel instance = new ProjectModel();
		instance.setModel(model);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
