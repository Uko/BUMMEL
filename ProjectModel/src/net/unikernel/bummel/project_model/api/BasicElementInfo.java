package net.unikernel.bummel.project_model.api;

import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import org.openide.util.Exceptions;
import org.openide.xml.EntityCatalog;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author mcangel
 */
public class BasicElementInfo
{
	HashMap<Integer, String> statesGraphics = new HashMap<>();
	
	HashMap<String, String> portsDirections = new HashMap<>();
	HashMap<String, Double> portsOffsets = new HashMap<>();
	HashMap<String, Double> portsIndents = new HashMap<>();

	/**
	 * Create new BasicElementInfo with org.w3c.dom.Document.
	 */
	public BasicElementInfo(Document document)
	{
		org.w3c.dom.Element rootElement = 
				((org.w3c.dom.Element) document.getElementsByTagName("element_info").item(0));
		org.w3c.dom.NodeList graphicsNodes = 
				((org.w3c.dom.Element) rootElement.getElementsByTagName("images")
				.item(0)).getElementsByTagName("graphics");
		for (int i = 0; i < graphicsNodes.getLength(); i++)
		{
			org.w3c.dom.Element el = (org.w3c.dom.Element) graphicsNodes.item(i);
			statesGraphics.put(Integer.valueOf(el.getAttribute("state")),
					el.getAttribute("filename"));
		}
		
		org.w3c.dom.NodeList portNodes = 
				((org.w3c.dom.Element) rootElement.getElementsByTagName("ports")
				.item(0)).getElementsByTagName("port");
		for (int i = 0; i < portNodes.getLength(); i++)
		{
			org.w3c.dom.Element el = (org.w3c.dom.Element) portNodes.item(i);
			portsDirections.put(el.getAttribute("name"),
					el.getAttribute("direction"));
			try
			{
				portsOffsets.put(el.getAttribute("name"),
						NumberFormat.getNumberInstance()
						.parse(el.getAttribute("offset")
						.replaceAll("\\.", 
						new String(new char[]{DecimalFormatSymbols.getInstance()
						.getDecimalSeparator()}))).doubleValue());
				portsIndents.put(el.getAttribute("name"),
						NumberFormat.getNumberInstance()
						.parse(el.getAttribute("indent")
						.replaceAll("\\.", 
						new String(new char[]{DecimalFormatSymbols.getInstance()
						.getDecimalSeparator()}))).doubleValue());
			} catch (ParseException ex)
			{
				Exceptions.printStackTrace(ex);
			}
		}
	}
	
	/**
	 * Create new BasicElementInfo for the provided BasicElement.
	 */
	public BasicElementInfo(BasicElement element) throws IOException, SAXException
	{
		//get element's xml file with ports info and parse it - get org.w3c.dom.Document object
		this(XMLUtil.parse(new InputSource(element.getClass().getResourceAsStream(
				//check if path to the xml file with data is annotated
				//element.getClass().getAnnotation(PortsData.class)==null?
				//if not - default
				"element_info.xml"	//:
				//if annotated - get it
				//element.getClass().getAnnotation(PortsData.class).portsFile()
				)),	true, false, null, EntityCatalog.getDefault()));
	}
	
	public String getPortDirection(String port)
	{
		return portsDirections.get(port);
	}
	
	public Double getPortOffset(String port)
	{
		return portsOffsets.get(port);
	}

	public String getStateGraphics(Integer state)
	{
		return statesGraphics.get(state);
	}
}