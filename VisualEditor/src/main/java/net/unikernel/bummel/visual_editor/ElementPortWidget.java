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
	int pinRadius = 4;
	int length = 15;

	public ElementPortWidget(Scene scene, ElementNode node, String port)
	{
		super(scene);
		this.node = node;
		this.port = port;
		setLayout(LayoutFactory.createAbsoluteLayout());
		Graphics2D g2d;
		anchorGlyphs = new ArrayList<>();
                
                //Construct default graphics for anchor
                BufferedImage defaultAnchorImage = new BufferedImage(pinRadius*2, pinRadius*2, BufferedImage.TYPE_INT_ARGB);
                g2d = (Graphics2D)defaultAnchorImage.getGraphics();
                //clear background
                g2d.setBackground(Color.WHITE);
                g2d.clearRect(0,0,pinRadius*2, pinRadius*2);
                //paint circle
                g2d.setPaint(Color.BLACK);
		g2d.fillOval(0, 0, pinRadius*2, pinRadius*2);
		anchorGlyphs.add(defaultAnchorImage);
		
                line = new ImageWidget(scene);//line mage will be created in the indentPort method
                
                //Construct hover anchor image
                BufferedImage hoverAnchorImage = new BufferedImage(pinRadius*2, pinRadius*2, BufferedImage.TYPE_INT_ARGB);
                g2d = (Graphics2D) hoverAnchorImage.getGraphics();
                //g2d.setBackground(Color.WHITE);
                //g2d.clearRect(0,0,pinRadius*2, pinRadius*2);
                g2d.setPaint(Color.RED);
                g2d.fillOval(0, 0, pinRadius*2, pinRadius*2);
                anchorGlyphs.add(hoverAnchorImage);
                
                anchorWidget = new ImageWidget(scene, anchorGlyphs.get(0));
                
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
                int imageWidth = length+newLength;//make it double wide so that center of it will be on anchor point
                lineImage = new BufferedImage(imageWidth, 1, BufferedImage.TYPE_INT_ARGB);
                //draw line
		Graphics2D g2d = ((Graphics2D) lineImage.getGraphics());
                g2d.setPaint(Color.BLACK);
		switch (node.getPortDirection(port))
		{
			default:
			case "right":
				g2d.drawLine(0, 0, newLength, 0);
				line.setPreferredLocation(new Point((int)(-size*indent), 0));
                                anchorWidget.setPreferredLocation(new Point(length - (int)(0.5*size*indent) - pinRadius, -pinRadius));
				break;
			case "left":
				g2d.drawLine(length, 0, length+newLength, 0);
                                line.setPreferredLocation(new Point(- length * 2, 0));
                                anchorWidget.setPreferredLocation(new Point( - length - pinRadius + (int)(0.5*size*indent), -pinRadius));
				break;
			//next cases are default - there is need to implement them and check the result
			case "up":
				g2d.drawLine(0, 0, 0, length - 1);
				break;
			case "down":
				g2d.drawLine(0, 0, 0, length - 1);
				break;
		}
		line.setImage(lineImage);
	}
}