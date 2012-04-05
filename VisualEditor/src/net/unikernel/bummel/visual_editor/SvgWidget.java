/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.visual_editor;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
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
    public SvgWidget(Scene scene, SVGDiagram _diagram)
    {
        super (scene);
        diagram = _diagram;
        //this.setPreferredLocation(new Point(0, 0));
        //this.setPreferredSize(diagram.getViewRect().getBounds().getSize());
    }
    @Override
    protected Rectangle calculateClientArea () 
    {
        return diagram.getViewRect().getBounds();
    }
    @Override
    protected void paintWidget () 
    {
        try 
        {
            diagram.render(getScene().getGraphics());
        } catch (SVGException ex) 
        {
            Exceptions.printStackTrace(ex);
        }
    }
}
