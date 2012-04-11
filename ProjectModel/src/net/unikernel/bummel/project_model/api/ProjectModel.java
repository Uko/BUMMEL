package net.unikernel.bummel.project_model.api;

import java.io.Serializable;
import net.unikernel.bummel.project_model.BasicCircuit;

/**
 * Class that represents project. 
 * It contains different data (name, path where to save the file, graphics components, etc).
 * @author mcangel
 */
public class ProjectModel implements Serializable
{
	private String name;
	private String filePath;
	private Circuit circuit;

	public ProjectModel(String name)
	{
		this.name = name;
		filePath="";
		circuit = new BasicCircuit();
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
	public Circuit getModel()
	{
		return circuit;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(Circuit model)
	{
		this.circuit = model;
	}
	
}