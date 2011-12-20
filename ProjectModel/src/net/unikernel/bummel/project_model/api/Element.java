package net.unikernel.bummel.project_model.api;

import java.awt.Point;
import java.io.Serializable;


public interface Element extends Serializable
{
	public String getLabel();
	public void setLabel(String label);
	public int getState();
	public void setState(int state);
	public Point getCoords();
	public void plug(Connection connection, int port);
	public Connection unplug(int port);
	public Connection unplug(Connection connection);
}
