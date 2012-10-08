package net.unikernel.bummel.visual_editor;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author mcangel
 */
public class ElementPortNode extends AbstractNode
{

	public ElementPortNode(String port)
	{
		super(Children.LEAF, Lookups.singleton(port));
	}
	
	public String getPort()
	{
		return getLookup().lookup(String.class);
	}
}
