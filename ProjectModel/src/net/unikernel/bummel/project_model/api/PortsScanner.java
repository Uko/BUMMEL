package net.unikernel.bummel.project_model.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author mcangel
 */
public class PortsScanner
{
	HashMap<String, String> portsDirections = new HashMap<String, String>();
	HashMap<String, Integer> portsOffsets = new HashMap<String, Integer>();

	/**
	 * Create new PortsScanner with org.w3c.dom.Document.
	 */
	public PortsScanner(Document document)
	{
		org.w3c.dom.NodeList nodes = 
				((org.w3c.dom.Element) document.getDocumentElement()
				.getElementsByTagName("ports").item(0))
				.getElementsByTagName("port");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			org.w3c.dom.Element el = (org.w3c.dom.Element) nodes.item(i);
			portsDirections.put(el.getAttribute("name"),
					el.getAttribute("direction"));
			portsOffsets.put(el.getAttribute("name"),
				Integer.parseInt(el.getAttribute("offset")));
		}
	}
	
	/**
	 * Create new PortsScanner for the provided BasicElement.
	 */
	public PortsScanner(BasicElement element) throws IOException, SAXException
	{
		//TODO: add *.xml files searching through class annotations.
		//this(XMLUtil.parse(new InputSource(element.getClass()
		//		.getResource("ports.xml").openStream()), true, false, null, null));
		
		//TODO: figure out WTF???!!!
		
		this(XMLUtil.parse(
				new InputSource(
				new StringReader(
				new Scanner(element.getClass().getResource("ports.xml")
				.openStream()).useDelimiter("\\A").next())),
				true, false, null, null));
	}
	
	public String getPortDirection(String port)
	{
		return portsDirections.get(port);
	}
	
	public Integer getPortOffset(String port)
	{
		return portsOffsets.get(port);
	}
}
