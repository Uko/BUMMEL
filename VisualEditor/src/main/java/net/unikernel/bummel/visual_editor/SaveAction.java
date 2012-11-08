package net.unikernel.bummel.visual_editor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.IOException;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.cookies.SaveCookie;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

//@ActionID(category = "File", id = "net.unikernel.bummel.visual_editor.SaveAction")
//@ActionRegistration(iconBase = "org/openide/resources/actions/save.png",
//displayName = "#CTL_SaveAction")
//@ActionReferences(
//{
//	@ActionReference(path = "Menu/File", position = 0),
//	@ActionReference(path= "Toolbar/File", position = 0),
//	
//})
//@Messages("CTL_SaveAction=Save")
public final class SaveAction implements ActionListener
{
	private final SaveCookie context;

	public SaveAction(SaveCookie context)
	{
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent ev)
	{
		try
		{
			context.save();
		} catch (IOException ex)
		{
			Exceptions.printStackTrace(ex);
		}
	}
}
