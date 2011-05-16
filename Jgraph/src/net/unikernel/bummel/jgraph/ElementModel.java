package net.unikernel.bummel.jgraph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import java.util.ArrayList;

/**
 *
 * @author mcangel
 */
public class ElementModel extends mxCell
{
	protected ArrayList<ElementPort> inputPorts = null;
	protected ArrayList<ElementPort> outputPorts = null;
	protected String name;

	/**
	 * Constructs a new cell for the given parameters.
	 * @param value Object that represents the value of the cell.
	 * @param geometry Specifies the geometry of the cell.
	 * @param styles Specifies the style as a formatted string.
	 * @param inputPorts Specifies the amount of input Ports.
	 * @param outputPorts Specifies the amount of output Ports.
	 */
	public ElementModel(String name, Object value, mxGeometry geometry, String styles,
	int inputPorts, int outputPorts)
	{
		super(value, geometry, styles);
		this.name = name;
		getGeometry().setAlternateBounds((mxRectangle) geometry.clone());
		this.inputPorts = new ArrayList<ElementPort>();
		if (inputPorts > 0)
		{
			for (int i = 0; i < inputPorts; i++)
			{
				this.inputPorts.add(new ElementPort(null,
													 new mxGeometry(0, (i + 1.0) / (inputPorts + 1), 20, 20),
													 "shape=ellipse;perimter=ellipsePerimeter"));
				this.inputPorts.get(i).getGeometry().setOffset(new mxPoint(-10, -10));
				insert(this.inputPorts.get(i));
			}
		}
		this.outputPorts = new ArrayList<ElementPort>();
		if (outputPorts > 0)
		{
			for (int i = 0; i < outputPorts; i++)
			{
				this.outputPorts.add(new ElementPort(null,
													  new mxGeometry(1.0, (i + 1.0) / (outputPorts + 1), 20, 20),
													  "shape=ellipse;perimter=ellipsePerimeter"));
				this.outputPorts.get(i).getGeometry().setOffset(new mxPoint(-10, -10));
				insert(this.outputPorts.get(i));
			}
		}

		setVertex(true);
		setConnectable(false);
		
		init();
	}
	
	public void init()
	{
		
	}

	/**
	 * Constructs a new cell for the given parameters.
	 * @param value Object that represents the value of the cell.
	 * @param geometry Specifies the geometry of the cell.
	 * @param styles Specifies the style as a formatted string.
	 */
	public ElementModel(String name, Object value, mxGeometry geometry, String styles)
	{
		this(name, value, geometry, styles, 0, 0);
	}

	/**
	 * Constructs a new cell for the given user object.
	 * @param value Object that represents the value of the cell.
	 */
	public ElementModel(String name, Object value)
	{
		this(name, value, new mxGeometry(), "", 0, 0);
	}

	/**
	 * Constructs a new cell with an empty user object.
	 */
	public ElementModel()
	{
		this("", null, new mxGeometry(), "", 0, 0);
	}

	public ArrayList<ElementPort> getInputPorts()
	{
		return inputPorts;
	}

	public ElementPort getInputPort(int i)
	{
		return inputPorts.get(i);
	}

	public void setInputPorts(ArrayList<ElementPort> inputPorts)
	{
		for(int i = 0, n = this.inputPorts.size(); i < n; i++)
			remove(this.inputPorts.get(i));
		this.inputPorts = new ArrayList<ElementPort>();
		for(int i = 0, n = inputPorts.size(); i < n; i++)
			addInputPort(inputPorts.get(i));
	}

	public ArrayList<ElementPort> getOutputPorts()
	{
		return outputPorts;
	}

	public ElementPort getOutputPort(int i)
	{
		return outputPorts.get(i);
	}

	public void setOutputPorts(ArrayList<ElementPort> outputPorts)
	{
		for(int i = 0, n = this.outputPorts.size(); i < n; i++)
			remove(this.outputPorts.get(i));
		this.outputPorts = new ArrayList<ElementPort>();
		for(int i = 0, n = outputPorts.size(); i < n; i++)
			addInputPort(outputPorts.get(i));
	}
	
	public void addInputPort(Object value, mxGeometry geometry, String styles)
	{
		inputPorts.add((ElementPort)insert(new ElementPort(value, geometry, styles)));
	}
	public void addInputPort(ElementPort port)
	{
		//port.getGeometry().setOffset(new mxPoint(-1*port.getGeometry().getWidth()/2, -1*port.getGeometry().getHeight()/2));
		inputPorts.add((ElementPort)insert(port));
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
