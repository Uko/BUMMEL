package net.unikernel.bummel.jgraph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import java.io.Serializable;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

/**
 *
 * @author mcangel
 */
public class ElementPort extends mxCell
{
	/**
	 * Tells weather this port is input or output one:
	 *	&lt; 0	- input (default),
	 *	== 0	- input/output,
	 *	&gt; 0	- output.
	 */
	protected int inputOutput = -1;
	/**
	 * Constructs a new cell for the given parameters.
	 * @param value Object that represents the value of the cell.
	 * @param geometry Specifies the geometry of the cell.
	 * @param styles Specifies the style as a formatted string.
	 * @param inputOutput Specifies the type of this port {I, IO, O}.
	 */
	public ElementPort(Object value, mxGeometry geometry, String styles, int inputOutput)
	{
		super(null, geometry, styles);
		setVertex(true);
		getGeometry().setRelative(true);
		this.inputOutput = inputOutput;
		this.value = new ElementPortXMLElement(inputOutput);
//		if(inputOutput == 0)
//			this.value = "InputOutput";
//		if(inputOutput < 0)
//			this.value = "Input";
//		else
//			this.value = "Output";
	}
	/**
	 * Constructs a new cell for the given parameters.
	 * @param value Object that represents the value of the cell.
	 * @param geometry Specifies the geometry of the cell.
	 * @param styles Specifies the style as a formatted string.
	 */
	public ElementPort(Object value, mxGeometry geometry, String styles)
	{
		this(value, geometry, styles, -1);
	}

	/**
	 * Constructs a new cell for the given user object.
	 * @param value Object that represents the value of the cell.
	 */
	public ElementPort(Object value)
	{
		this(null, new mxGeometry(), "");
	}

	/**
	 * Constructs a new cell with an empty user object.
	 */
	public ElementPort()
	{
		this(null);
	}

	public int isInputOutput()
	{
		return inputOutput;
	}

	public void setInputOutput(int inputOutput)
	{
		this.inputOutput = inputOutput;
	}
	
	
	class ElementPortXMLElement implements Element, Serializable
	{
		protected String nodeName;
		public ElementPortXMLElement()
		{
			nodeName = null;
		}
		public ElementPortXMLElement(int inputOutput)
		{
			if(inputOutput == 0)
				nodeName = "InputOutput";
			if(inputOutput < 0)
				nodeName = "Input";
			else
				nodeName = "Output";
		}
		public ElementPortXMLElement(ElementPortXMLElement obj)
		{
			this();
			this.nodeName = obj.nodeName;
		}
		
		@Override
		public String getNodeName()
		{
			return nodeName;
		}
		
		@Override
		public String getAttribute(String name)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
		@Override
		public Node cloneNode(boolean deep)
		{
			if(deep)
				return new ElementPortXMLElement(this);
			else
				return this;
		}
		
		@Override
		public String toString()
		{
			return "["+nodeName+"]";
		}
		
		@Override
		public String getTagName()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setAttribute(String name, String value) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void removeAttribute(String name) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Attr getAttributeNode(String name)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Attr setAttributeNode(Attr newAttr) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Attr removeAttributeNode(Attr oldAttr) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public NodeList getElementsByTagName(String name)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getAttributeNS(String namespaceURI, String localName) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void removeAttributeNS(String namespaceURI, String localName) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Attr setAttributeNodeNS(Attr newAttr) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean hasAttribute(String name)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public TypeInfo getSchemaTypeInfo()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setIdAttribute(String name, boolean isId) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getNodeValue() throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setNodeValue(String nodeValue) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public short getNodeType()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node getParentNode()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public NodeList getChildNodes()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node getFirstChild()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node getLastChild()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node getPreviousSibling()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node getNextSibling()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public NamedNodeMap getAttributes()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Document getOwnerDocument()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node insertBefore(Node newChild, Node refChild) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node replaceChild(Node newChild, Node oldChild) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node removeChild(Node oldChild) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Node appendChild(Node newChild) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean hasChildNodes()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void normalize()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean isSupported(String feature, String version)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getNamespaceURI()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getPrefix()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setPrefix(String prefix) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getLocalName()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean hasAttributes()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getBaseURI()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public short compareDocumentPosition(Node other) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getTextContent() throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setTextContent(String textContent) throws DOMException
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean isSameNode(Node other)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String lookupPrefix(String namespaceURI)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean isDefaultNamespace(String namespaceURI)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String lookupNamespaceURI(String prefix)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean isEqualNode(Node arg)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Object getFeature(String feature, String version)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Object setUserData(String key, Object data, UserDataHandler handler)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Object getUserData(String key)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}
}
