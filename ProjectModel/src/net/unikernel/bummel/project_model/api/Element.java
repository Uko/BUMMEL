package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;


interface Element extends Serializable
{
	public String getLabel();
	public void setLabel(String label);
	public int getState();
	public void setState(int state);
	public Point getCoords();
	public void setCoords(Point point);
	public void plug(Connection connection, String port) throws Exception;
	public Connection unplug(String port);
	public boolean unplug(Connection connection);
	public ArrayList<Double> step();
}
