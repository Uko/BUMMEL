package net.unikernel.bummel.jgraph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;

/**
 *
 * @author mcangel
 */
public class ElementModel extends mxCell
{
	protected ElementPort[] inputPorts = null;
	protected ElementPort[] outputPorts = null;

	/**
	 * Constructs a new cell for the given parameters.
	 * @param value Object that represents the value of the cell.
	 * @param geometry Specifies the geometry of the cell.
	 * @param styles Specifies the style as a formatted string.
	 * @param inputPorts Specifies the amount of input Ports.
	 * @param outputPorts Specifies the amount of output Ports.
	 */
	public ElementModel(Object value, mxGeometry geometry, String styles,
	int inputPorts, int outputPorts)
	{
		super("ElementModel", geometry, styles);
		getGeometry().setAlternateBounds((mxRectangle) geometry.clone());
		if (inputPorts > 0)
		{
			this.inputPorts = new ElementPort[inputPorts];
			for (int i = 0; i < inputPorts; i++)
			{
				this.inputPorts[i] = new ElementPort(null,
													 new mxGeometry(0, (i + 1.0) / (inputPorts + 1), 20, 20),
													 "shape=ellipse;perimter=ellipsePerimeter");
				this.inputPorts[i].getGeometry().setOffset(new mxPoint(-10, -10));
				insert(this.inputPorts[i]);
			}
		}
		if (outputPorts > 0)
		{
			this.outputPorts = new ElementPort[outputPorts];
			for (int i = 0; i < outputPorts; i++)
			{
				this.outputPorts[i] = new ElementPort(null,
													  new mxGeometry(1.0, (i + 1.0) / (outputPorts + 1), 20, 20),
													  "shape=ellipse;perimter=ellipsePerimeter",
													  1);
				this.outputPorts[i].getGeometry().setOffset(new mxPoint(-10, -10));
				insert(this.outputPorts[i]);
			}
		}

		setVertex(true);
		setConnectable(false);
	}

	/**
	 * Constructs a new cell for the given parameters.
	 * @param value Object that represents the value of the cell.
	 * @param geometry Specifies the geometry of the cell.
	 * @param styles Specifies the style as a formatted string.
	 */
	public ElementModel(Object value, mxGeometry geometry, String styles)
	{
		this(value, geometry, styles, 0, 0);
	}

	/**
	 * Constructs a new cell for the given user object.
	 * @param value Object that represents the value of the cell.
	 */
	public ElementModel(Object value)
	{
		this(value, new mxGeometry(), "");
	}

	/**
	 * Constructs a new cell with an empty user object.
	 */
	public ElementModel()
	{
		this(null);
	}

	public mxCell[] getInputPorts()
	{
		return inputPorts;
	}

	public mxCell getInputPort(int i)
	{
		if ((0 <= i) && (i < inputPorts.length))
		{
			return inputPorts[i];
		}
		else
		{
			return null;
		}
	}

	public void setInputPorts(ElementPort[] inputPorts)
	{
		this.inputPorts = inputPorts;
	}

	public mxCell[] getOutputPorts()
	{
		return outputPorts;
	}

	public mxCell getOutputPort(int i)
	{
		if ((0 <= i) && (i < outputPorts.length))
		{
			return outputPorts[i];
		}
		else
		{
			return null;
		}
	}

	public void setOutputPorts(ElementPort[] outputPorts)
	{
		this.outputPorts = outputPorts;
	}
}
