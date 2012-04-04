/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unikernel.bummel.visual_editor;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    }
    @Override
    protected Rectangle calculateClientArea () {
        return new Rectangle (-40, -40, 40, 40);
    }
    @Override
    protected void paintWidget () 
    {
        Graphics2D g = getGraphics ();
        try 
        {
            diagram.render(getScene().getGraphics());
        } catch (SVGException ex) 
        {
            Exceptions.printStackTrace(ex);
        }
    }
}
