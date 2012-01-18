package net.unikernel.bummel.logic_elements;

import net.unikernel.bummel.project_model.api.Circuit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openide.util.Lookup;
import static org.junit.Assert.*;

/**
 *
 * @author mcangel
 */
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

	@Test
	public void testSchemeN1()
	{
		System.out.println("scheme_process");
		Generator gen = new Generator();
		Analyzer an = new Analyzer();
		instance.addElement(gen);
		instance.addElement(an);
		instance.connectElements(gen, 0, an, 0);
		instance.step();
		assertEquals(1, an.getState());
	}
}
