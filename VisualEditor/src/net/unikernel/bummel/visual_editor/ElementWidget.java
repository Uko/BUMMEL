package net.unikernel.bummel.visual_editor;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.PortsScanner;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;
import org.xml.sax.SAXException;

/**
 *
 * @author mcangel
 */
public class ElementWidget extends Widget
{
	private Widget body;
	private ImageWidget imageWidget;
	private LabelWidget labelWidget;
	private BasicElement element;
	private PortsScanner ps;
	
	int width = 20;
	int height = 20;

	public ElementWidget(Scene scene, BasicElement element)
	{
		super(scene);
		this.element = element;
		try
		{
			ps = new PortsScanner(this.element);
		} catch (IOException ex)
		{
			Exceptions.printStackTrace(ex);
		} catch (SAXException ex)
		{
			Exceptions.printStackTrace(ex);
		}
		body = new Widget(scene);
		body.setLayout(LayoutFactory.createVerticalFlowLayout());
		addChild(body);
		
		imageWidget = new ImageWidget(scene);
		imageWidget.setImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
		imageWidget.getImage().getGraphics().drawRect(width/4, height/4, width*3/4, height*3/4);
		body.addChild(imageWidget);
		
		labelWidget = new LabelWidget(scene, "Text");
		body.addChild(labelWidget);
	}
	
	/**
	 * Attaches a pin widget to the node widget.
	 *
	 * @param widget the pin widget
	 */
	
	//TODO: move scond parameter to the ElementPortWidget
	public void attachPortWidget(Widget widget, String port)
	{
		widget.setCheckClipping(true);
		addChild(widget);
		switch(ps.getPortDirection(port))
		{
			default:
			case "right":
				widget.setPreferredLocation(new Point(width, height/2+ps.getPortOffset(port)));
				break;
			case "left":
				widget.setPreferredLocation(new Point(-widget.getClientArea().width, height/2+ps.getPortOffset(port)));
				break;
			case "up":
			case "down":
				break;
		}
	}

}