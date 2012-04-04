package net.unikernel.bummel.visual_editor;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Graphics2D;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import net.unikernel.bummel.project_model.api.BasicElement;
import org.netbeans.api.visual.layout.LayoutFactory;
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
	private SvgWidget imageWidget;
	private LabelWidget labelWidget;
	private LabelWidget stateWidget;
	private ElementNode elNode;
	
	int width = 20;
	int height = 20;

	public ElementWidget(Scene scene, ElementNode element) throws MalformedURLException, SVGException, FileNotFoundException
	{
		super(scene);
		this.elNode = element;
		this.elNode.getLookup().lookup(BasicElement.class)
				.addPropertyChangeListener(this);
		
		body = new Widget(scene);
		body.setLayout(LayoutFactory.createVerticalFlowLayout());
		addChild(body);
                
                String path_to_svg = "C:\\Users\\Main\\Desktop\\1.svg";
                File f = new File(path_to_svg);
                if(!f.exists())
                {
                    throw new FileNotFoundException("File with component image not found.");
                }
                SVGUniverse svgUniverse = new SVGUniverse();
                SVGDiagram diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(f.toURL()));
                imageWidget = new SvgWidget(scene, diagram);
		//imageWidget.set(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
                //Graphics2D tmp = imageWidget.getScene().getGraphics();
		
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