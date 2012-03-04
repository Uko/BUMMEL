package net.unikernel.bummel.visual_editor;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import net.unikernel.bummel.project_model.api.BasicElement;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;

/**
 *
 * @author mcangel
 */
public class ElementWidget extends Widget implements PropertyChangeListener
{
	private Widget body;
	private ImageWidget imageWidget;
	private LabelWidget labelWidget;
	private LabelWidget stateWidget;
	private ElementNode elNode;
	
	int width = 20;
	int height = 20;

	public ElementWidget(Scene scene, ElementNode element)
	{
		super(scene);
		this.elNode = element;
		this.elNode.getLookup().lookup(BasicElement.class)
				.addPropertyChangeListener(this);
		
		body = new Widget(scene);
		body.setLayout(LayoutFactory.createVerticalFlowLayout());
		addChild(body);
		
		imageWidget = new ImageWidget(scene);
		imageWidget.setImage(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
		imageWidget.getImage().getGraphics().drawRect(width/4, height/4, width/2, height/2);
		body.addChild(imageWidget);
		
		labelWidget = new LabelWidget(scene, "Label:"+elNode.getDisplayName());
		body.addChild(labelWidget);
		stateWidget = new LabelWidget(scene, "State:"+elNode.getLookup()
				.lookup(BasicElement.class).getState());
		body.addChild(stateWidget);
	}
	
	/**
	 * Attaches a pin widget to the node widget.
	 *
	 * @param widget the pin widget
	 */
	
	public void attachPortWidget(ElementPortWidget widget)
	{
		widget.setCheckClipping(true);
		addChild(widget);
		switch (elNode.getPortDirection(widget.getPort()))
		{
			case "right":
				widget.setPreferredLocation(new Point(width, 
							//offset from the middle, offset in "percents" of the height half
							(int) (height*0.5*(1+0.5*elNode.getPortOffset(widget.getPort()).doubleValue()))));
				break;
			case "left":
				widget.setPreferredLocation(new Point(0, 
							//offset from the middle, offset in "percents" of the height half
							(int) (height*0.5*(1+0.5*elNode.getPortOffset(widget.getPort()).doubleValue()))));
				break;
			case "up":
				break;
			case "down":
				break;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if(BasicElement.PROP_STATE.equals(evt.getPropertyName()))
		{
			stateWidget.setLabel("State:"+evt.getNewValue());
		}
	}

}