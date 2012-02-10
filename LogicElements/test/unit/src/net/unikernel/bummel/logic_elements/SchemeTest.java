package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.logic_elements.Split.Split;
import net.unikernel.bummel.logic_elements.Generator.Generator;
import net.unikernel.bummel.logic_elements.Or.Or;
import net.unikernel.bummel.logic_elements.And.And;
import net.unikernel.bummel.logic_elements.Analyzer.Analyzer;
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
		instance.connectElements(gen, "output", an, "input");
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
		instance.connectElements(gen, "output", no, "input");
		instance.connectElements(no, "output", an, "input");
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
		instance.connectElements(gen1, "output", and, "input1");
		instance.connectElements(gen2, "output", and, "input2");
		instance.connectElements(and, "output", an, "input");
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
		instance.connectElements(gen1, "output", or, "input1");
		instance.connectElements(gen2, "output", or, "input2");
		instance.connectElements(or, "output", an, "input");
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
		instance.connectElements(gen1, "output", or, "input1");
		instance.connectElements(gen2, "output", or, "input2");
		instance.connectElements(gen3, "output", and, "input1");
		instance.connectElements(or, "output", and, "input2");
		instance.connectElements(and, "output", an, "input");
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
		instance.connectElements(gen1, "output", and, "input1");
		instance.connectElements(gen2, "output", and, "input2");
		instance.connectElements(gen3, "output", or, "input1");
		instance.connectElements(and, "output", or, "input2");
		instance.connectElements(or, "output", an, "input");
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
		instance.connectElements(gen1, "output", spl1, "input");
		instance.connectElements(gen2, "output", spl2, "input");
		instance.connectElements(spl1, "output1", or, "input1");
		instance.connectElements(spl2, "output1", or, "input2");
		instance.connectElements(spl1, "output2", and1, "input1");
		instance.connectElements(spl2, "output2", and1, "input2");
		instance.connectElements(and1, "output", not, "input");
		instance.connectElements(or, "output", and2, "input1");
		instance.connectElements(not, "output", and2, "input2");
		instance.connectElements(and2, "output", an, "input");
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
	public void testSchemeN7()
	{
		System.out.println("scheme_processN7");
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
		instance.connectElements(gen1, "output", spl1, "input");
		instance.connectElements(gen2, "output", spl2, "input");
		instance.connectElements(spl1, "output1", or, "input1");
		instance.connectElements(spl2, "output1", or, "input2");
		instance.connectElements(spl1, "output2", and1, "input1");
		instance.connectElements(spl2, "output2", and1, "input2");
		instance.connectElements(and1, "output", not, "input");
		instance.connectElements(or, "output", and2, "input1");
		instance.connectElements(not, "output", and2, "input2");
		instance.connectElements(and2, "output", an, "input");
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
