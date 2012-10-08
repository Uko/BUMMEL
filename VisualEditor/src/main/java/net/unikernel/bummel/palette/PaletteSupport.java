package net.unikernel.bummel.palette;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.beans.BeanInfo;
import java.io.IOException;
import javax.swing.Action;
import org.netbeans.spi.palette.DragAndDropHandler;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.datatransfer.ExTransferable;

/**
 *
 * @author mcangel
 */
public class PaletteSupport
{
	public static PaletteController createPalette()
	{
		AbstractNode paletteRoot = new AbstractNode(Children.create(new CategoryChildFactory(), true));
		paletteRoot.setName("NB VL attempt No2");
		//return PaletteFactory.createPalette(paletteRoot, new OurActions(), null, new OurDnDHandler());
		return PaletteFactory.createPalette(paletteRoot, new OurActions());
	}

	private static class OurActions extends PaletteActions
	{
		@Override
		public Action[] getImportActions()
		{
			return null;
		}

		@Override
		public Action[] getCustomPaletteActions()
		{
			return null;
		}

		@Override
		public Action[] getCustomCategoryActions(Lookup lookup)
		{
			return null;
		}

		@Override
		public Action[] getCustomItemActions(Lookup lookup)
		{
			return null;
		}

		@Override
		public Action getPreferredAction(Lookup lookup)
		{
			return null;
		}
	}

	private static class OurDnDHandler extends DragAndDropHandler
	{
		@Override
		public void customize(ExTransferable exTransferable, Lookup lookup)
		{
			Node node = lookup.lookup(Node.class);
			final String nodeHtmlDisplayName = node.getDisplayName();
			exTransferable.put(new ExTransferable.Single(DataFlavor.stringFlavor) {

				@Override
				protected Object getData() throws IOException, UnsupportedFlavorException
				{
					return nodeHtmlDisplayName;
				}
			});
			final Image image = (Image) node.getIcon(BeanInfo.ICON_COLOR_16x16);
			exTransferable.put(new ExTransferable.Single(DataFlavor.imageFlavor)
			{
				@Override
				protected Object getData() throws IOException, UnsupportedFlavorException
				{
					return image;
				}
			});
		}
	}
}
