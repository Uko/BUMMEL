package net.unikernel.bummel.visual_editor;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;
import java.awt.Rectangle;
import java.net.URL;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Exceptions;

/**
 *
 * @author Main
 */
public class SvgWidget extends Widget
{
	SVGDiagram diagram;

	public SvgWidget(Scene scene, URL svgImageURL)
	{
		super(scene);
		SVGUniverse svgUniverse = new SVGUniverse();
		if (svgImageURL != null)
			diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(svgImageURL));
	}

	public SVGDiagram getDiagram()
	{
		return diagram;
	}

	public void setDiagram(SVGDiagram diagram)
	{
		this.diagram = diagram;
	}
	
	/**
	 * Loads the SVGDiagram from the specified URL.
	 * @param svgImageURL 
	 */
	public void setDiagram(URL svgImageURL)
	{
		SVGUniverse svgUniverse = new SVGUniverse();
		if (svgImageURL != null)
		{
			diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(svgImageURL));
		}
	}

	public SvgWidget(Scene scene, SVGDiagram diagram)
	{
		super(scene);
		this.diagram = diagram;
	}

	@Override
	protected Rectangle calculateClientArea()
	{
		if(diagram == null)
			return null;
		return diagram.getViewRect().getBounds();
	}

	@Override
	protected void paintWidget()
	{
		try
		{
			diagram.render(getGraphics());
		} catch (SVGException ex)
		{
			Exceptions.printStackTrace(ex);
		}
	}
}