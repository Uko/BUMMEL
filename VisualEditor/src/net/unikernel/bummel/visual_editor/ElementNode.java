package net.unikernel.bummel.visual_editor;

import java.io.IOException;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Element;
import net.unikernel.bummel.project_model.api.PortsScanner;
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
	PortsScanner ps;
	
	public ElementNode(Element element)
	{
		super(Children.LEAF, Lookups.singleton(element));
		this.setDisplayName(element.getClass().getSimpleName());
	}
	
	protected void constructPortsScanner()
	{
		try
		{
			ps = new PortsScanner(this.getLookup().lookup(BasicElement.class));
		} catch (IOException ex)
		{
			Exceptions.printStackTrace(ex);
		}
		catch (SAXException ex)
		{
			Exceptions.printStackTrace(ex);
		}
	}
	
	//TODO: find out if there is a possibility to realize below methods through BeanInfo (from PortsScanner)
	
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
}