package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.io.Serializable;
import java.util.Map;

public interface Element extends Serializable
{
	public String getLabel();
	public void setLabel(String label);
	public int getState();
	public void setState(int state);
	public Point getCoords();
	public void setCoords(Point point);
	public Map<Integer, Double> process(Map<Integer, Double> valuesOnPorts);
}