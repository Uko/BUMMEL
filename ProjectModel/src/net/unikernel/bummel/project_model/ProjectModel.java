package net.unikernel.bummel.project_model;

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
	private mxIGraphModel schema;
	private Object starters;

	public ProjectModel()
	{
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
	
}
