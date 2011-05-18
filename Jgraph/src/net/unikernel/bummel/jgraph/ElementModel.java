package net.unikernel.bummel.jgraph;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.shape.mxStencilShape;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.util.mxUtils;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * The exmaple of creation of visual element with i/o ports:
 * <pre><span style="color:#0000e6">new</span> ElementModel(<span style="color:#ce7b00">"Analyzer"</span>, <span style="color:#0000e6">new</span> Analyzer(), <span style="color:#0000e6">new</span> mxGeometry(0, 0, 100, 100), <span style="color:#ce7b00">""</span>,
 *	<span style="color:#009900">//Detailed info about shapes here:</span> <a href="http://www.jgraph.com/doc/mxgraph/index_javavis.html#3.1.1.1">http://www.jgraph.com/doc/mxgraph/index_javavis.html#3.1.1.1</a>
 *	<span style="color:#009900">//First shape will be the default shape.</span>
 *	new String[]{<span style="color:#ce7b00">"some/path/iamge.shape"</span>})
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
//	private ArrayList<ElementPort> inputPorts = null;
//	private ArrayList<ElementPort> outputPorts = null;
	protected String name;
	public final String[] stateImagesStyles;

	/**
	 * Constructs a new graph element based on the mxCell.
	 * @param name String that represents the name of the element (return of toString()).
	 * @param value Object that represents the value of the element.
	 * @param geometry Specifies the geometry of the element.
	 * @param styles Specifies the style as a formatted string.
	 * @param stateImagesPaths String array that maps images to element states.
	 */
	public ElementModel(String name, Object value, mxGeometry geometry, String styles, String[] stateImagesPaths)
	{
		super(value, geometry, styles);
		if (stateImagesPaths != null)
		{
			stateImagesStyles = new String[stateImagesPaths.length];
			for (int i = 0, n = stateImagesPaths.length; i < n; i++)
			{
					int lessthanIndex = stateImagesPaths[i].indexOf("<");
					stateImagesPaths[i] = stateImagesPaths[i].substring(lessthanIndex);
					mxStencilShape newShape = new mxStencilShape(stateImagesPaths[i]);
					stateImagesStyles[i] = newShape.getName();

					// Registers the shape in the canvas shape registry
					mxGraphics2DCanvas.putShape(stateImagesStyles[i], newShape);
			}
			if (stateImagesStyles.length > 0)
			{
				setShape(0);
			}
		}
		else
		{
			stateImagesStyles = null;
		}
		this.name = name;
		getGeometry().setAlternateBounds((mxRectangle) geometry.clone());
//		this.inputPorts = new ArrayList<ElementPort>();
//		this.outputPorts = new ArrayList<ElementPort>();
		setVertex(true);
		setConnectable(false);

		init();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		mxCell clone = (mxCell) super.clone();
		try
		{
			clone.setValue(value.getClass().newInstance());
		}
		catch(Exception e)
		{
		}
		return clone;
	}

	public void init()
	{
		init(2, 1);
	}

	final public void init(int inputPorts, int outputPorts)
	{
		for (int i = 0; i < inputPorts; i++)
		{
			ElementPort port = new ElementPort(null,
											   new mxGeometry(0, (i + 1.0) / (inputPorts + 1), 20, 20),
											   "shape=ellipse;perimeter=ellipsePerimeter");
			port.getGeometry().setOffset(new mxPoint(-10, -10));
//				this.inputPorts.add(port);
			insert(port);
		}


		for (int i = 0; i < outputPorts; i++)
		{
			ElementPort port = new ElementPort(null,
											   new mxGeometry(1.0, (i + 1.0) / (outputPorts + 1), 20, 20),
											   "shape=ellipse;perimeter=ellipsePerimeter");
			port.getGeometry().setOffset(new mxPoint(-10, -10));
//				this.inputPorts.add(port);
			insert(port);
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
		this(name, value, geometry, styles, null);
	}

	/**
	 * Constructs a new cell for the given user object.
	 * @param value Object that represents the value of the cell.
	 */
	public ElementModel(String name, Object value)
	{
		this(name, value, new mxGeometry(), "", null);
	}

	/**
	 * Constructs a new cell with an empty user object.
	 */
	public ElementModel()
	{
		this("", null, new mxGeometry(), "", null);
	}

//	public ArrayList<ElementPort> getInputPorts()
//	{
//		return inputPorts;
//	}

//	public ElementPort getInputPort(int i)
//	{
//		return inputPorts.get(i);
//	}

//	public void setInputPorts(ArrayList<ElementPort> inputPorts)
//	{
//		for (int i = 0, n = this.inputPorts.size(); i < n; i++)
//		{
//			remove(this.inputPorts.get(i));
//		}
//		this.inputPorts = new ArrayList<ElementPort>();
//		for (int i = 0, n = inputPorts.size(); i < n; i++)
//		{
//			addInputPort(inputPorts.get(i));
//		}
//	}

//	public ArrayList<ElementPort> getOutputPorts()
//	{
//		return outputPorts;
//	}
//
//	public ElementPort getOutputPort(int i)
//	{
//		return outputPorts.get(i);
//	}
//
//	public void setOutputPorts(ArrayList<ElementPort> outputPorts)
//	{
//		for (int i = 0, n = this.outputPorts.size(); i < n; i++)
//		{
//			remove(this.outputPorts.get(i));
//		}
//		this.outputPorts = new ArrayList<ElementPort>();
//		for (int i = 0, n = outputPorts.size(); i < n; i++)
//		{
//			addInputPort(outputPorts.get(i));
//		}
//	}
//
//	public void addInputPort(Object value, mxGeometry geometry, String styles)
//	{
//		inputPorts.add((ElementPort) insert(new ElementPort(value, geometry, styles)));
//	}
//
//	public void addInputPort(ElementPort port)
//	{
//		inputPorts.add((ElementPort) insert(port));
//	}
//
//	public void addOutputPort(Object value, mxGeometry geometry, String styles)
//	{
//		outputPorts.add((ElementPort) insert(new ElementPort(value, geometry, styles)));
//	}
//
//	public void addOutputPort(ElementPort port)
//	{
//		outputPorts.add((ElementPort) insert(port));
//	}
	
	public void addPort(ElementPort port)
	{
		insert(port);
	}

	@Override
	public String toString()
	{
		return name;
	}

	/**
	 * Sets shape style parameter from available "state-shape" array depending on state passed as parameter.
	 * @param state Represents current element state.
	 */
	public void setShape(int state)
	{
		if (stateImagesStyles != null && stateImagesStyles.length > 0)
		{
			int pos;
			if ((pos = style.indexOf("shape=")) > 0)
			{
				style.replace(style.substring(pos, ((pos = style.indexOf(";", pos)) > 0) ? pos : style.length() - 1), "shape=" + stateImagesStyles[state] + ";");
			}
			else
			{
				style = "shape=" + stateImagesStyles[state] + ";" + style;
			}
		}
	}
}
