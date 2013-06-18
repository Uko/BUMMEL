package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.logic_elements.Analyzer.Analyzer;
import net.unikernel.bummel.logic_elements.Generator.Generator;
import net.unikernel.bummel.project_model.api.Circuit;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.openide.util.Lookup;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class UserInteractionTest
{
	Circuit instance;

	@BeforeMethod
	public void setUp()
	{
		instance = Lookup.getDefault().lookup(Circuit.class);
	}

	@AfterMethod
	public void tearDown()
	{
		instance = null;
	}

	@Test
	public void testDisconnect()
	{
		System.out.println("testDisconnect");
		Generator gen = new Generator();
		Analyzer an = new Analyzer();
		instance.addElement(gen);
		instance.addElement(an);
		instance.connectElements(gen, "output", an, "input");
		gen.setState(1);
		for (int i = 0, n = instance.getElements().size(); i < n; i++)
		{
			instance.step();
		}
		assertEquals(1, an.getState());
		instance.disconnectElements(gen, "output", an, "input");
		for (int i = 0, n = instance.getElements().size(); i < n; i++)
		{
			instance.step();
		}
		assertEquals(0, an.getState());
	}
}