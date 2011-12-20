package net.unikernel.bummel.project_model;

import net.unikernel.bummel.project_model.api.Сircuit;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import net.unikernel.bummel.basic_elements.BasicElement;

/**
 * Class that represents project. 
 * It contains different data (name, path where to save the file, graphics components, etc).
 * @author mcangel
 */
public class ProjectModel implements Serializable
{
	private String name;
	private String filePath;
	transient private ArrayList<BasicElement> elements;

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
	public ArrayList<BasicElement> getModel()
	{
		return elements;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(ArrayList<BasicElement> model)
	{
		this.elements = model;
	}
	
}
