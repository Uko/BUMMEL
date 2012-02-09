package net.unikernel.bummel.visual_editor;

import java.awt.Point;
import java.awt.image.BufferedImage;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author mcangel
 */
public class ElementWidget extends Widget
{
	private Widget body;
	private ImageWidget imageWidget;
	private LabelWidget labelWidget;

	public ElementWidget(Scene scene)
	{
		super(scene);
		
		body = new Widget(scene);
		body.setLayout(LayoutFactory.createVerticalFlowLayout());
		addChild(body);
		
		imageWidget = new ImageWidget(scene);
		imageWidget.setImage(new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB));
		imageWidget.getImage().getGraphics().drawRect(5, 5, 10, 10);
		body.addChild(imageWidget);
		
		labelWidget = new LabelWidget(scene, "Text");
		body.addChild(labelWidget);
	}
	
	/**
	 * Attaches a pin widget to the node widget.
	 *
	 * @param widget the pin widget
	 */
	public void attachPortWidget(Widget widget)
	{
		widget.setCheckClipping(true);
		addChild(widget);
		widget.setPreferredLocation(new Point(20, 10));
	}

}