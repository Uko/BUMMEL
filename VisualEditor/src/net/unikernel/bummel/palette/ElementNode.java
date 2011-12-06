package net.unikernel.bummel.palette;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxRectangle;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import net.unikernel.bummel.jgraph.ElementModel;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author mcangel
 */
public class ElementNode extends AbstractNode
{
	
	public ElementNode(ElementModel element)
	{
		super(Children.LEAF, Lookups.singleton(element));
		this.setDisplayName(element.toString());
	}
	
	@Override
	public Transferable drag() throws IOException
	{
		ElementModel element = this.getLookup().lookup(ElementModel.class);
		mxRectangle bounds = (mxGeometry) element.getGeometry().clone();
		final mxGraphTransferable t = new mxGraphTransferable(
				new Object[]{element},bounds);
		return t;
	}
}