package net.unikernel.bummel.visual_editor;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import javax.swing.BorderFactory;
import net.unikernel.bummel.project_model.api.BasicElement;
import org.netbeans.api.visual.layout.Layout;
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
	private SVGDiagram diagram;
	int width = 20;
	int height = 20;

	public ElementWidget(Scene scene, ElementNode element) throws MalformedURLException, SVGException, FileNotFoundException
	{
		super(scene);
		this.elNode = element;
		this.elNode.getLookup().lookup(BasicElement.class)
				.addPropertyChangeListener(this);
		
		body = new Widget(scene);
		body.setLayout(LayoutFactory.createVerticalFlowLayout()); //all child widgets will be located at their preffered location
                //body.setBorder(BorderFactory.createLineBorder(Color.black));//TODO:remove this for the release
                addChild(body);
                //this string should contain relative path. in contains shit now...
                String path_to_svg = this.elNode.getGraphicsFilename(0);//"C:\\DevRepos\\BUMMEL\\LogicElements\\src\\net\\unikernel\\bummel\\logic_elements\\Analyzer\\1.svg";
                File f = new File(path_to_svg);
                if(!f.exists())
                {
                    throw new FileNotFoundException("File with component image not found.");
                }
                SVGUniverse svgUniverse = new SVGUniverse();
                diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(f.toURI().toURL()));
                imageWidget = new SvgWidget(scene, diagram);
		body.addChild(imageWidget);
		body.addChild(new LabelWidget(scene, "Label:"+elNode.getDisplayName()));
		body.addChild(new LabelWidget(scene, "State:"+elNode.getLookup().lookup(BasicElement.class).getState()));
                body.repaint();//first repaint must be called ater dropping.
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
        @Override
        protected Rectangle calculateClientArea () 
        {
            return new Rectangle (0,0, (int)diagram.getWidth(), (int)diagram.getHeight());
        }

}