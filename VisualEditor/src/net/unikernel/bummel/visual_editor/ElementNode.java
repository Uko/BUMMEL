package net.unikernel.bummel.visual_editor;

import net.unikernel.bummel.project_model.api.Element;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author mcangel
 */
public class ElementNode extends AbstractNode
{
	public ElementNode(Element element)
	{
		super(Children.LEAF, Lookups.singleton(element));
		this.setDisplayName(element.getClass().getSimpleName());
	}
}