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
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author mcangel
 */
public class ElementPortWidget extends Widget
{
	ImageWidget imageWidget;
	ImageWidget anchorWidget;
	LabelWidget nameWidget;
	List<Image> glyphs;
	
	int length = 15;

	public ElementPortWidget(Scene scene)
	{
		super(scene);
		
		setLayout(LayoutFactory.createAbsoluteLayout());
		
		imageWidget = new ImageWidget(scene, 
				new BufferedImage(length, 1, BufferedImage.TYPE_INT_ARGB));
		Graphics2D g2d = ((Graphics2D)imageWidget.getImage().getGraphics());
		g2d.setPaint(Color.BLACK);
		g2d.drawLine(0, 0, length-1, 0);
		addChild(imageWidget);
		
		anchorWidget = new ImageWidget(scene);
		addChild(anchorWidget);
		anchorWidget.setPreferredLocation(new Point(length, -1));
		
		nameWidget = new LabelWidget(scene);
		addChild(nameWidget);
		nameWidget.setPreferredLocation(new Point(length+10, 0));
		
		glyphs = new ArrayList<Image>();
		glyphs.add(new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB));
		g2d = ((Graphics2D)glyphs.get(0).getGraphics());
		g2d.setPaint(Color.BLACK);
		g2d.drawRect(0, 0, 4, 4);
		glyphs.add(new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB));
		g2d = ((Graphics2D)glyphs.get(1).getGraphics());
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(1, 1, 2, 1);
		g2d.setPaint(Color.RED);
		g2d.drawLine(0, 0, 2, 0);
		g2d.drawLine(0, 0, 0, 2);
		g2d.drawLine(0, 2, 2, 2);
		anchorWidget.setImage(glyphs.get(0));
	}
	
	public void setPortName(String newName)
	{
		nameWidget.setLabel(newName);
	}
	
	public String getPortName()
	{
		return nameWidget.getLabel();
	}

	@Override
	protected void notifyStateChanged(ObjectState previousState, ObjectState state)
	{
		super.notifyStateChanged(previousState, state);
		if(state.isHovered())
		{
			nameWidget.setOrientation(LabelWidget.Orientation.ROTATE_90);
			anchorWidget.setImage(glyphs.get(1));
		}
		else
		{
			nameWidget.setOrientation(LabelWidget.Orientation.NORMAL);
			anchorWidget.setImage(glyphs.get(0));
		}
	}	
}