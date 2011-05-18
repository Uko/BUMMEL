package net.unikernel.bummel.logic_elements.visual;

import com.mxgraph.model.mxGeometry;
import net.unikernel.bummel.jgraph.ElementModel;
import net.unikernel.bummel.logic_elements.Analyzer;
import net.unikernel.bummel.logic_elements.And;
import net.unikernel.bummel.logic_elements.Generator;
import net.unikernel.bummel.logic_elements.Not;
import net.unikernel.bummel.logic_elements.Or;

/**
 *
 * @author mcangel
 */
public class VisualizationsFactory
{
	public static final ElementModel[] elements = new ElementModel[]{
		new ElementModel("Analyzer", new Analyzer(), new mxGeometry(0, 0, 30, 18), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/analyzer0.shape",
						 "/net/unikernel/bummel/logic_elements/visual/images/analyzer1.shape"})
		{
			@Override
			public void init()
			{
				init(1, 0);
			}
		},
		new ElementModel("And", new And(), new mxGeometry(0, 0, 56, 40), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/and.shape"})
		{
			@Override
			public void init()
			{
				super.init();
			}
		},
		new ElementModel("Generator", new Generator(), new mxGeometry(0, 0, 41, 50), "", 
			new String[]{	"/net/unikernel/bummel/logic_elements/visual/images/generator.shape",
							"/net/unikernel/bummel/logic_elements/visual/images/generator.shape"})
		{
			@Override
			public void init()
			{
				init(0, 1);
			}
		},
		new ElementModel("Not", new Not(), new mxGeometry(0, 0, 63, 40), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/not.shape"})
		{
			@Override
			public void init()
			{
				init(1, 1);
			}
		},
		new ElementModel("Or", new Or(), new mxGeometry(0, 0, 52, 40), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/or.shape"})
		{
			@Override
			public void init()
			{
				super.init();
			}
		}
	};
}
