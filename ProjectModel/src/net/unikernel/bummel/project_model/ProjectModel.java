package net.unikernel.bummel.project_model;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import java.io.Serializable;

/**
 * Class that represents project. 
 * It contains different data (name, path where to save the file, graphics components, etc).
 * @author mcangel
 */
public class ProjectModel implements Serializable
{
	private String name;
	private String filePath;
	transient private mxIGraphModel model;

	public ProjectModel(String name)
	{
		this.name = name;
		filePath="";
		model = new mxGraphModel();
	}
	
	public ProjectModel()
	{
		this("");
	}

	public String getFilePath()
	{
		return filePath;
	}

	public String getName()
	{
		return name;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @return the model
	 */
	public mxIGraphModel getModel()
	{
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(mxIGraphModel model)
	{
		this.model = model;
	}
	
}
