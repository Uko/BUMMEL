package net.unikernel.bummel.palette;

import java.util.List;
import net.unikernel.bummel.jgraph.ElementModel;
import net.unikernel.bummel.logic_elements.visual.VisualizationsFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author uko
 */
public class CategoryChildFactory extends ChildFactory<String>
{
	@Override
	protected boolean createKeys(List<String> list)
	{
		list.add("Logical");
		return true;
	}
	@Override
	protected Node createNodeForKey(String category)
	{
		Node node = new AbstractNode(Children.create(new ElementChildFactory(category), true));
		node.setDisplayName(category);
		return node;
	}

	private class ElementChildFactory extends ChildFactory<ElementModel>
	{
		private String category;
		private ElementChildFactory(String category)
		{
			this.category = category;
		}
		@Override
		protected boolean createKeys(List<ElementModel> list)
		{
			for(int i = 0, n = VisualizationsFactory.elements.length; i < n; i++)
				list.add(/*new ElementModel(*/VisualizationsFactory.elements[i]/*)*/);
			//list.add(new ElementModel("Hello", null, new mxGeometry(0,0,100,100), "", 2, 1));
			return true;
		}
		@Override
		protected Node createNodeForKey(final ElementModel element)
		{
			return new ElementNode(element);
		}
	}
}