package net.unikernel.bummel.project_model.api;

import java.io.Serializable;

/**
 *
 * @author uko, mcangel
 */
public interface Connection extends Serializable
{
	public Element getFirstElement();
	public String getFirstElementPort();
	public Element getSecondElement();
	public String getSecondElementPort();

  public Element getOther(Element element);
  public String getElementPort(Element element);
}
