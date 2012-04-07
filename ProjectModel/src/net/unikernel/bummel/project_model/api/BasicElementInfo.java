package net.unikernel.bummel.project_model.api;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
	Map<Integer, URL> statesGraphicsURLS = new HashMap<>();
	
	HashMap<String, String> portsDirections = new HashMap<>();
	HashMap<String, Double> portsOffsets = new HashMap<>();
	HashMap<String, Double> portsIndents = new HashMap<>();

	/**
	 * Create new BasicElementInfo for the provided BasicElement.
	 */
	public BasicElementInfo(BasicElement element) throws IOException, SAXException
	{
		//get element's xml file with ports info and parse it - get org.w3c.dom.Document object
		Document document = XMLUtil.parse(new InputSource(element.getClass().getResourceAsStream(
				//check if path to the xml file with data is annotated
				element.getClass().getAnnotation(Element.ElementData.class)==null?
				//if not - default
				"element_info.xml":
				//if annotated - get it
				element.getClass().getAnnotation(Element.ElementData.class).dataFile()
				)),	true, false, null, EntityCatalog.getDefault());
		
		//take useful info from the Document object to specific containers
		org.w3c.dom.Element rootElement =
				((org.w3c.dom.Element) document.getElementsByTagName("element_info").item(0));
		org.w3c.dom.NodeList graphicsNodes =
				((org.w3c.dom.Element) rootElement.getElementsByTagName("images").item(0)).getElementsByTagName("graphics");
		for (int i = 0; i < graphicsNodes.getLength(); i++)
		{//graphics per state
			org.w3c.dom.Element el = (org.w3c.dom.Element) graphicsNodes.item(i);
			statesGraphicsURLS.put(Integer.valueOf(el.getAttribute("state")),
					element.getClass().getResource(el.getAttribute("filename")));
		}
		
		org.w3c.dom.NodeList portNodes =
				((org.w3c.dom.Element) rootElement.getElementsByTagName("ports")
				.item(0)).getElementsByTagName("port");
		for (int i = 0; i < portNodes.getLength(); i++)
		{//port specific info
			org.w3c.dom.Element el = (org.w3c.dom.Element) portNodes.item(i);
			portsDirections.put(el.getAttribute("name"),
					el.getAttribute("direction"));
			try
			{
				portsOffsets.put(el.getAttribute("name"),
						NumberFormat.getNumberInstance().parse(nds(el.getAttribute("offset"))).doubleValue());
				portsIndents.put(el.getAttribute("name"),
						NumberFormat.getNumberInstance().parse(nds(el.getAttribute("indent"))).doubleValue());
			} catch (ParseException ex)
			{
				Exceptions.printStackTrace(ex);
			}
		}
	}
	
	/**
	 * Normalise decimal separator.
	 * Replaces any separator (in case of one from the different standard) for the current locale one.
	 * @param value - string value with unknown or different from current decimal separator
	 * @return String with replaced decimal separator for the current locale one.
	 */
	private String nds(String value)
	{
		return value.replaceAll("\\.",
				new String(new char[]
				{
					DecimalFormatSymbols.getInstance().getDecimalSeparator()
				}));
	}
	
	public String getPortDirection(String port)
	{
		return portsDirections.get(port);
	}
	
	public Double getPortOffset(String port)
	{
		return portsOffsets.get(port);
	}

	public URL getGraphicsURL(Integer state)
	{
		return statesGraphicsURLS.get(state);
	}

	public Map<Integer, URL> getStatesGraphicsURLS()
	{
		return Collections.unmodifiableMap(statesGraphicsURLS);
	}
}