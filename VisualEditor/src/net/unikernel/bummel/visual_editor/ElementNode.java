package net.unikernel.bummel.visual_editor;

import java.io.IOException;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.BasicElementInfo;
import net.unikernel.bummel.project_model.api.Element;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;
import org.xml.sax.SAXException;

/**
 *
 * @author mcangel
 */
public class ElementNode extends AbstractNode
{
	BasicElementInfo ps;
	
	public ElementNode(Element element)
	{
		super(Children.LEAF, Lookups.singleton(element));
		this.setDisplayName(element.getClass().getSimpleName());
	}
	
	protected void constructPortsScanner()
	{
		try
		{
			ps = new BasicElementInfo(this.getLookup().lookup(BasicElement.class));
		} catch (IOException | SAXException ex)
		{
			Exceptions.printStackTrace(ex);
		}
	}
	
	//TODO: find out if there is a possibility to realize below methods through BeanInfo (from BasicElementInfo)
	
	public String getPortDirection(String port)
	{
		if(ps == null)
		{
			constructPortsScanner();
		}
		return ps.getPortDirection(port);
	}
	
	public Double getPortOffset(String port)
	{
		if(ps == null)
		{
			constructPortsScanner();
		}
		return ps.getPortOffset(port);
	}
        
        public String getGraphicsFilename(int state)
	{
		if(ps == null)
		{
			constructPortsScanner();
		}
		return ps.getStateGraphics(state);
	}
}