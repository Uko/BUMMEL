package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.logic_elements.Not.Not;
import net.unikernel.bummel.project_model.api.Circuit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openide.util.Lookup;
import static org.junit.Assert.*;

public class SchemeTest
{
	Circuit instance;
	@Before
	public void setUp()
	{
		instance = Lookup.getDefault().lookup(Circuit.class);
	}
	@After
	public void tearDown()
	{
		instance = null;
	}
	
	/**
	 * an = gen 
	 */
	@Test
	public void testSchemeN1()
	{
		System.out.println("scheme_processN1");
		Generator gen = new Generator();
		Analyzer an = new Analyzer();
		instance.addElement(gen);
		instance.addElement(an);
		instance.connectElements(gen, 0, an, 0);
		gen.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
	}
	
	/**
	 * an = not(gen) 
	 */
	@Test
	public void testSchemeN2()
	{
		System.out.println("scheme_processN2");
		Generator gen = new Generator();
		Not no = new Not();
		Analyzer an = new Analyzer();
		instance.addElement(gen);
		instance.addElement(no);
		instance.addElement(an);
		instance.connectElements(gen, 0, no, 0);
		instance.connectElements(no, 1, an, 0);
		gen.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
		gen.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
	}
	
	/**
	 * an = and(gen1, gen2)
	 */
	@Test
	public void testSchemeN3()
	{
		System.out.println("scheme_processN3");
		Generator gen1 = new Generator();
		Generator gen2 = new Generator();
		And and = new And();
		Analyzer an = new Analyzer();
		instance.addElement(gen1);
		instance.addElement(gen2);
		instance.addElement(and);
		instance.addElement(an);
		instance.connectElements(gen1, 0, and, 0);
		instance.connectElements(gen2, 0, and, 1);
		instance.connectElements(and, 2, an, 0);
		gen1.setState(1);
		gen2.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
		gen2.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen1.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
	}
	
	/**
	 * an = or(gen1, gen2)
	 */
	@Test
	public void testSchemeN4()
	{
		System.out.println("scheme_processN4");
		Generator gen1 = new Generator();
		Generator gen2 = new Generator();
		Or or = new Or();
		Analyzer an = new Analyzer();
		instance.addElement(gen1);
		instance.addElement(gen2);
		instance.addElement(or);
		instance.addElement(an);
		instance.connectElements(gen1, 0, or, 0);
		instance.connectElements(gen2, 0, or, 1);
		instance.connectElements(or, 2, an, 0);
		gen1.setState(1);
		gen2.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen2.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen1.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
	}
	
	/**
	 * an = and(or(gen1, gen2), gen3)
	 */
	@Test
	public void testSchemeN5()
	{
		System.out.println("scheme_processN5");
		Generator gen1 = new Generator();
		Generator gen2 = new Generator();
		Generator gen3 = new Generator();
		Or or = new Or();
		And and = new And();
		Analyzer an = new Analyzer();
		instance.addElement(gen1);
		instance.addElement(gen2);
		instance.addElement(gen3);
		instance.addElement(or);
		instance.addElement(and);
		instance.addElement(an);
		instance.connectElements(gen1, 0, or, 0);
		instance.connectElements(gen2, 0, or, 1);
		instance.connectElements(gen3, 0, and, 0);
		instance.connectElements(or, 2, and, 1);
		instance.connectElements(and, 2, an, 0);
		gen1.setState(1);
		gen2.setState(1);
		gen3.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen2.setState(0);
		gen3.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
		gen1.setState(0);
		gen3.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
		gen2.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
	}
	
	/**
	 * an = or(and(gen1, gen2), gen3)
	 */
	@Test
	public void testSchemeN6()
	{
		System.out.println("scheme_processN6");
		Generator gen1 = new Generator();
		Generator gen2 = new Generator();
		Generator gen3 = new Generator();
		Or or = new Or();
		And and = new And();
		Analyzer an = new Analyzer();
		instance.addElement(gen1);
		instance.addElement(gen2);
		instance.addElement(gen3);
		instance.addElement(or);
		instance.addElement(and);
		instance.addElement(an);
		instance.connectElements(gen1, 0, and, 0);
		instance.connectElements(gen2, 0, and, 1);
		instance.connectElements(gen3, 0, or, 0);
		instance.connectElements(and, 2, or, 1);
		instance.connectElements(or, 2, an, 0);
		gen1.setState(1);
		gen2.setState(1);
		gen3.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen2.setState(0);
		gen3.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
		gen1.setState(0);
		gen3.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen2.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
	}
	
	/**
	 * A test without the NOT element added into the scheme
	 * (was the source of NullPointerException).
	 * XOR:
	 * an = and2(	or(spl1-gen1, spl2-gen2),
	 *				not(and1(spl1-gen1, spl2-gen2)))
	 */
	@Test
	public void testSchemeN7CrashTest()
	{
		System.out.println("scheme_processN7_CrashTest");
		Generator gen1 = new Generator();
		Generator gen2 = new Generator();
		Not not = new Not();
		Or or = new Or();
		And and1 = new And();
		And and2 = new And();
		Split spl1 = new Split();
		Split spl2 = new Split();
		Analyzer an = new Analyzer();
		instance.addElement(gen1);
		instance.addElement(gen2);
		instance.addElement(or);
		instance.addElement(and1);
		instance.addElement(and2);
		instance.addElement(spl1);
		instance.addElement(spl2);
		instance.addElement(an);
		instance.connectElements(gen1, 0, spl1, 0);
		instance.connectElements(gen2, 0, spl2, 0);
		instance.connectElements(spl1, 1, or, 0);
		instance.connectElements(spl2, 1, or, 1);
		instance.connectElements(spl1, 2, and1, 0);
		instance.connectElements(spl2, 2, and1, 1);
		instance.connectElements(and1, 2, not, 0);
		instance.connectElements(or, 2, and2, 0);
		instance.connectElements(not, 1, and2, 1);
		instance.connectElements(and2, 2, an, 0);
		gen1.setState(1);
		gen2.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		gen2.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		gen1.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		gen2.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
	}
	
	/**
	 * XOR:
	 * an = and2(	or(spl1-gen1, spl2-gen2),
	 *				not(and1(spl1-gen1, spl2-gen2)))
	 */
	@Test
	public void testSchemeN8()
	{
		System.out.println("scheme_processN8");
		Generator gen1 = new Generator();
		Generator gen2 = new Generator();
		Not not = new Not();
		Or or = new Or();
		And and1 = new And();
		And and2 = new And();
		Split spl1 = new Split();
		Split spl2 = new Split();
		Analyzer an = new Analyzer();
		instance.addElement(gen1);
		instance.addElement(gen2);
		instance.addElement(or);
		instance.addElement(not);
		instance.addElement(and1);
		instance.addElement(and2);
		instance.addElement(spl1);
		instance.addElement(spl2);
		instance.addElement(an);
		instance.connectElements(gen1, 0, spl1, 0);
		instance.connectElements(gen2, 0, spl2, 0);
		instance.connectElements(spl1, 1, or, 0);
		instance.connectElements(spl2, 1, or, 1);
		instance.connectElements(spl1, 2, and1, 0);
		instance.connectElements(spl2, 2, and1, 1);
		instance.connectElements(and1, 2, not, 0);
		instance.connectElements(or, 2, and2, 0);
		instance.connectElements(not, 1, and2, 1);
		instance.connectElements(and2, 2, an, 0);
		gen1.setState(1);
		gen2.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen2.setState(1);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
		gen1.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		gen2.setState(0);
		for (int i = 0; i < 10; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
	}
}
