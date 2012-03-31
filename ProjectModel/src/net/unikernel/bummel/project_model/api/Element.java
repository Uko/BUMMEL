package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

public interface Element extends Serializable
{
	public String getLabel();
	public void setLabel(String label);
	public int getState();
	public void setState(int state);
	public Point getCoords();
	public void setCoords(Point point);
	public List<String> getPorts();
	public Map<String, Double> process(Map<String, Double> valuesOnPorts);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Annotation should be used to define the location of element's XML data
	 * file.
	 *
	 * @author uko
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface ElementData
	{
		/**
		 * Path to a datafile starting from classes directory. Default:
		 * "element_info.xml"
		 *
		 * @return path to element's XML data file.
		 */
		String dataFile() default "element_info.xml";
	}

}