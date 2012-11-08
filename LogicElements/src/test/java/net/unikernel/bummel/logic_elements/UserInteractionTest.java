package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.logic_elements.Analyzer.Analyzer;
import net.unikernel.bummel.logic_elements.Generator.Generator;
import net.unikernel.bummel.project_model.api.Circuit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openide.util.Lookup;

/**
 *
 * @author mcangel
 */
public class UserInteractionTest
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