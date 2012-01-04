package net.unikernel.bummel.project_model.api;

/**
 *
 * @author uko
 */
public interface Connection
{
	public BasicElement getOtherElement(BasicElement thisElement);
	public BasicElement getFirstElement();
	public BasicElement getSecondElement();
	public void connectFirstElement(BasicElement firstElement, String port) throws Exception;
	public void connectSecondElement(BasicElement secondElement, String port) throws Exception;
	public String getLabel();
	public void setLabel(String label);
	public double getValue ();
}
