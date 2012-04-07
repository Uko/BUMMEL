package net.unikernel.bummel.visual_editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.model.ObjectState;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author mcangel
 */
public class ElementPortWidget extends Widget
{
	String port;
	ElementNode node;
	ImageWidget line;
	ImageWidget anchorWidget;
	List<Image> anchorGlyphs;
	
	int length = 15;

	public ElementPortWidget(Scene scene, ElementNode node, String port)
	{
		super(scene);
		this.node = node;
		this.port = port;
		setLayout(LayoutFactory.createAbsoluteLayout());
		Graphics2D g2d;
		
		anchorGlyphs = new ArrayList<>();
		anchorGlyphs.add(new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB));
		g2d = ((Graphics2D)anchorGlyphs.get(0).getGraphics());
		g2d.setPaint(Color.BLACK);
		g2d.drawRect(0, 0, 4, 4);
		anchorGlyphs.add(new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB));
		
		anchorWidget = new ImageWidget(scene, anchorGlyphs.get(0));
		BufferedImage lineImage;
		line = new ImageWidget(scene);
		switch (node.getPortDirection(port))
		{
			default:
			case "right":
				lineImage = new BufferedImage(length, 1, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, length - 1, 0);
				//draw anchor
				g2d = ((Graphics2D) anchorGlyphs.get(1).getGraphics());
				g2d.setPaint(Color.WHITE);
				g2d.drawLine(1, 1, 2, 1);
				g2d.setPaint(Color.RED);
				g2d.drawLine(0, 0, 2, 0);
				g2d.drawLine(0, 0, 0, 2);
				g2d.drawLine(0, 2, 2, 2);
				anchorWidget.setPreferredLocation(new Point(length, -1));
				break;
			case "left":
				lineImage = new BufferedImage(length, 1, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, length - 1, 0);
				line.setPreferredLocation(new Point(-length, 0));
				//draw anchor
				g2d = ((Graphics2D) anchorGlyphs.get(1).getGraphics());
				g2d.setPaint(Color.WHITE);
				g2d.drawLine(0, 1, 1, 1);
				g2d.setPaint(Color.RED);
				g2d.drawLine(0, 0, 2, 0);//_
				g2d.drawLine(2, 0, 2, 2);// |
				g2d.drawLine(0, 2, 2, 2);//_
				anchorWidget.setPreferredLocation(new Point(-length - 3, -1));
				break;
				
				//next cases are default - there is need to implement them and check the result
			case "up":
				lineImage = new BufferedImage(1, length, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, 0, length - 1);
				break;
			case "down":
				lineImage = new BufferedImage(1, length, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, 0, length - 1);
				break;
		}
		line.setImage(lineImage);
		addChild(line);
		addChild(anchorWidget);
	}

	public String getPort()
	{
		return port;
	}

	@Override
	protected void notifyStateChanged(ObjectState previousState, ObjectState state)
	{
		super.notifyStateChanged(previousState, state);
		if(state.isHovered())
		{
			anchorWidget.setImage(anchorGlyphs.get(1));
		}
		else
		{
			anchorWidget.setImage(anchorGlyphs.get(0));
		}
	}
	
	public void indentPort(int size)
	{
		Double indent = node.getPortIndent(port);
		int newLength = (int) (length + size*indent);
		BufferedImage lineImage;
		Graphics2D g2d;
		switch (node.getPortDirection(port))
		{
			default:
			case "right":
				lineImage = new BufferedImage(newLength, 1, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, newLength - 1, 0);
				line.setPreferredLocation(new Point((int)(-size*indent), 0));
				break;
			case "left":
				lineImage = new BufferedImage(newLength, 1, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, newLength - 1, 0);
				break;

			//next cases are default - there is need to implement them and check the result
			case "up":
				lineImage = new BufferedImage(1, newLength, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, 0, length - 1);
				break;
			case "down":
				lineImage = new BufferedImage(1, newLength, BufferedImage.TYPE_INT_ARGB);
				//draw line
				g2d = ((Graphics2D) lineImage.getGraphics());
				g2d.setPaint(Color.BLACK);
				g2d.drawLine(0, 0, 0, length - 1);
				break;
		}
		line.setImage(lineImage);
	}
}