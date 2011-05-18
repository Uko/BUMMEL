package net.unikernel.bummel.logic_elements.visual;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import net.unikernel.bummel.jgraph.ElementModel;
import net.unikernel.bummel.jgraph.ElementPort;
import net.unikernel.bummel.logic_elements.Analyzer;
import net.unikernel.bummel.logic_elements.And;
import net.unikernel.bummel.logic_elements.Generator;
import net.unikernel.bummel.logic_elements.Not;
import net.unikernel.bummel.logic_elements.Or;
import net.unikernel.bummel.logic_elements.Split;

/**
 *
 * @author mcangel
 */
public class VisualizationsFactory
{
	public static final ElementModel[] elements = new ElementModel[]{
		new ElementModel("Generator", new Generator(), new mxGeometry(0, 0, 41, 50), "", 
			new String[]{	"/net/unikernel/bummel/logic_elements/visual/images/generator.shape",
							"/net/unikernel/bummel/logic_elements/visual/images/generator1.shape"})
		{
			@Override
			public void init()
			{
				ElementPort port = new ElementPort(null, new mxGeometry(1, 0.62, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
			}
		},
		new ElementModel("Not", new Not(), new mxGeometry(0, 0, 63, 40), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/not.shape"})
		{
			@Override
			public void init()
			{
				ElementPort port = new ElementPort(null, new mxGeometry(0, 0.5, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
				port = new ElementPort(null, new mxGeometry(1, 0.5, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
			}
		},
		new ElementModel("And", new And(), new mxGeometry(0, 0, 56, 40), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/and.shape"})
		{
			@Override
			public void init()
			{
				ElementPort port = new ElementPort(null, new mxGeometry(0, 0.25, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
				port = new ElementPort(null, new mxGeometry(0, 0.75, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
				port = new ElementPort(null, new mxGeometry(1, 0.5, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
			}
		},
		new ElementModel("Or", new Or(), new mxGeometry(0, 0, 52, 40), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/or.shape"})
		{
			@Override
			public void init()
			{
				ElementPort port = new ElementPort(null, new mxGeometry(0, 0.25, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
				port = new ElementPort(null, new mxGeometry(0, 0.75, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
				port = new ElementPort(null, new mxGeometry(1, 0.5, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
			}
		},
		new ElementModel("Analyzer", new Analyzer(), new mxGeometry(0, 0, 30, 18), "", 
			new String[]{"/net/unikernel/bummel/logic_elements/visual/images/analyzer0.shape",
						 "/net/unikernel/bummel/logic_elements/visual/images/analyzer1.shape"})
		{
			@Override
			public void init()
			{
				ElementPort port = new ElementPort(null, new mxGeometry(0, 0.5, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
				port.getGeometry().setOffset(new mxPoint(-2, -2));
				insert(port);
			}
		},
		new ElementModel("Split", new Split(), new mxGeometry(0, 0, 15, 20), "", new String[]{"/net/unikernel/bummel/logic_elements/visual/images/split.shape"})
			{
				@Override
				public void init()
				{
					ElementPort port = new ElementPort(null, new mxGeometry(0, 0.5, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
					port.getGeometry().setOffset(new mxPoint(-2, -2));
					insert(port);
					port = new ElementPort(null, new mxGeometry(1, 0, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
					port.getGeometry().setOffset(new mxPoint(-2, -2));
					insert(port);
					port = new ElementPort(null, new mxGeometry(1, 1, 4, 4),"shape=ellipse;perimeter=ellipsePerimeter;fillColor=black");
					port.getGeometry().setOffset(new mxPoint(-2, -2));
					insert(port);
				}
			}
	};
}
