package net.unikernel.bummel.project_model;

import net.unikernel.bummel.project_model.api.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents project. 
 * It contains different data (name, path where to save the file, graphics components, etc).
 * @author mcangel
 */
public class ProjectModel implements Serializable
{
	private String name;
	private String filePath;
	transient private ArrayList<Element> elements;

	public ProjectModel(String name)
	{
		this.name = name;
		filePath="";
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
	public ArrayList<Element> getModel()
	{
		return elements;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(ArrayList<Element> model)
	{
		this.elements = model;
	}
	
}
