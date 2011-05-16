package net.unikernel.bummel.jgraph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import java.util.ArrayList;

/**
 * The exmaple of creation of visual element with i/o ports:
 * <pre><span style="color:#0000e6">new</span> ElementModel(<span style="color:#ce7b00">"Analyzer"</span>, <span style="color:#0000e6">new</span> Analyzer(), <span style="color:#0000e6">new</span> mxGeometry(0, 0, 100, 100), <span style="color:#ce7b00">""</span>)
 * {
 *	&#64;Override
 *	<span style="color:#0000e6">public void</span> init()
 *	{
 *		<span style="color:#009900">//Create new mxGeometry for the port (coords are relative to the element):</span>
 *		mxGeometry geometry = new mxGeometry(0, 0, 20, 20);
 *		<span style="color:#009900">//set the offset, for example if you need to plase the port on an element's border</span>
 *		geometry.setOffset(new mxPoint(-10, -10));
 *		<span style="color:#009900">//Add the port in the form of circle with radius 10 and center (0,0) as 
 *		//specified in the geometry and null value</span>
 *		addInputPort(null, geometry, <span style="color:#ce7b00">"shape=ellipse;perimeter=ellipsePerimeter"</span>);
 *		<span style="color:#009900">//Or you can construct the ElementPort by yourself and pass it as 
 *		//a parameter to the addInputPort function. 
 *	
 *		//The same way you can use addOutputPort - to add output port.</span>
 *	}
 * };</pre>
 * @author mcangel
 */
public class ElementModel extends mxCell
{
	private ArrayList<ElementPort> inputPorts = null;
	private ArrayList<ElementPort> outputPorts = null;
	protected String name;

	/**
	 * Constructs a new graph element based on the mxCell.
	 * @param name String that represents the name of the element (return of toString()).
	 * @param value Object that represents the value of the element.
	 * @param geometry Specifies the geometry of the element.
	 * @param styles Specifies the style as a formatted string.
	 * @param inputPorts Specifies the amount of input Ports added by default generator.
	 * @param outputPorts Specifies the amount of output Ports added by default generator.
	 */
	public ElementModel(String name, Object value, mxGeometry geometry, String styles,
	int inputPorts, int outputPorts)
	{
		super(value, geometry, styles);
		this.name = name;
		getGeometry().setAlternateBounds((mxRectangle) geometry.clone());
		this.inputPorts = new ArrayList<ElementPort>();
		this.outputPorts = new ArrayList<ElementPort>();
		setVertex(true);
		setConnectable(false);
		
		init();
	}
	
	public void init()
	{
		init(2,1);
	}

	private void init(int inputPorts, int outputPorts)
	{
		if (inputPorts > 0)
		{
			for (int i = 0; i < inputPorts; i++)
			{
				this.inputPorts.add(new ElementPort(null,
													 new mxGeometry(0, (i + 1.0) / (inputPorts + 1), 20, 20),
													 "shape=ellipse;perimeter=ellipsePerimeter"));
				this.inputPorts.get(i).getGeometry().setOffset(new mxPoint(-10, -10));
				insert(this.inputPorts.get(i));
			}
		}
		if (outputPorts > 0)
		{
			for (int i = 0; i < outputPorts; i++)
			{
				this.outputPorts.add(new ElementPort(null,
													  new mxGeometry(1.0, (i + 1.0) / (outputPorts + 1), 20, 20),
													  "shape=ellipse;perimeter=ellipsePerimeter"));
				this.outputPorts.get(i).getGeometry().setOffset(new mxPoint(-10, -10));
				insert(this.outputPorts.get(i));
			}
		}
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
		inputPorts.add((ElementPort)insert(port));
	}
	
	public void addOutputPort(Object value, mxGeometry geometry, String styles)
	{
		outputPorts.add((ElementPort)insert(new ElementPort(value, geometry, styles)));
	}
	public void addOutputPort(ElementPort port)
	{
		outputPorts.add((ElementPort)insert(port));
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
