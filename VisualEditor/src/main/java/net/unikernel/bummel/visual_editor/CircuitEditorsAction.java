package net.unikernel.bummel.visual_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import net.unikernel.bummel.project_model.api.Circuit;
import net.unikernel.bummel.project_model.api.ProjectModel;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.Presenter;

@ActionID(
        category = "File",
        id = "net.unikernel.bummel.visual_editor.CircuitEditorsAction")
@ActionRegistration(
        displayName = "#CTL_CircuitEditorsAction",
        lazy = false)
@ActionReference(path = "Menu/File", position = 375)
@Messages("CTL_CircuitEditorsAction=New Editor")
public final class CircuitEditorsAction extends AbstractAction implements ActionListener, Presenter.Menu
{
  private JMenu menu;

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Nothing needs to happen here.
  }

  @Override
  public JMenuItem getMenuPresenter()
  {
    if(menu != null)
    {
      return menu;
    }

    menu = new JMenu(NbBundle.getMessage(CircuitEditorsAction.class, "CTL_CircuitEditorsAction"));
    for(final Circuit circuit : Lookup.getDefault().lookupAll(Circuit.class))
    {
      JMenuItem item = new JMenuItem(new AbstractAction(circuit.getClass().getSimpleName())
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          EditorTopComponent tc;
          try
          {
            tc = new EditorTopComponent(new ProjectModel(circuit.getClass().newInstance(),
                    (String)this.getValue(Action.NAME)));
            tc.open();
            tc.requestActive();
          } catch (  InstantiationException | IllegalAccessException ex)
          {
            Exceptions.printStackTrace(ex);
          }
        }
      });
      menu.add(item);
    }
    return menu;
  }
}
