package net.unikernel.bummel.visual_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "net.unikernel.bummel.visual_editor.OpenProjectAction")
@ActionRegistration(displayName = "#CTL_OpenProjectAction")
@ActionReferences(
{
	@ActionReference(path = "Menu/File", position = 475),
	@ActionReference(path = "Shortcuts", name = "D-O")
})
@Messages(
		{
			"CTL_OpenProjectAction=Open Project...",
			"TTL_OPENPROJECT_DIALOG=Open Project"
		})
public final class OpenProjectAction implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String title = NbBundle.getMessage(OpenProjectAction.class, "TTL_OPENPROJECT_DIALOG");
		File f = new FileChooserBuilder(OpenProjectAction.class).setTitle(title).showOpenDialog();
		if (f != null)
		{
			EditorTopComponent tc = EditorTopComponent.findInstance(f);
			tc.open();
			tc.requestActive();
		}
	}
}