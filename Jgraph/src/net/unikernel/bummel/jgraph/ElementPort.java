package net.unikernel.bummel.jgraph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

/**
 *
 * @author mcangel
 */
public class ElementPort extends mxCell
{
	/**
	 * Constructs a new port for the given parameters.
	 * @param value Object that represents the value of the port.
	 * @param geometry Specifies the geometry of the port.
	 * @param styles Specifies the style as a formatted string.
	 */
	public ElementPort(Object value, mxGeometry geometry, String styles)
	{
		super(value, geometry, styles);
		setVertex(true);
		getGeometry().setRelative(true);
	}

	/**
	 * Constructs a new port for the given user object.
	 * @param value Object that represents the value of the port.
	 */
	public ElementPort(Object value)
	{
		this(null, new mxGeometry(), "");
	}

	/**
	 * Constructs a new port with an empty user object.
	 */
	public ElementPort()
	{
		this(null);
	}
}