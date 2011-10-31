package net.unikernel.bummel.visual_editor;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxMultiplicity;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Action;
import net.unikernel.bummel.basic_elements.BasicElement;
import net.unikernel.bummel.engine.Engine;
import net.unikernel.bummel.jgraph.ElementModel;
import net.unikernel.bummel.jgraph.ElementPort;
import net.unikernel.bummel.project_model.ProjectModel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.StatusDisplayer;
import org.openide.cookies.OpenCookie;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//net.unikernel.bummel.visual_editor//Editor//EN",
autostore = false)
@TopComponent.Description(preferredID = "EditorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "net.unikernel.bummel.visual_editor.EditorTopComponent")
@ActionReference(path = "Menu/Window", position = 0)
@TopComponent.OpenActionRegistration(displayName = "#CTL_NewEditorAction"/*,
preferredID = "EditorTopComponent"*/)
public final class EditorTopComponent extends TopComponent
{
	/**
	 * Counter of opened top components.
	 */
	private static int counter = 0;
	private static Map<File, EditorTopComponent> tcByFile = 
			new HashMap<File, EditorTopComponent>();
	
	public static EditorTopComponent findInstance(File f)
	{
		EditorTopComponent tc = tcByFile.get(f);
		if(tc == null)
		{
			//TODO - add a constructor from the File object ??
			try
			{
				tc = new EditorTopComponent(Opener.open(f));
				tcByFile.put(f, tc);
				FileObject fob = FileUtil.toFileObject(FileUtil.normalizeFile(f));
				tc.content.add(DataObject.find(fob));
			}
			catch(DataObjectNotFoundException ex)
			{
				Exceptions.printStackTrace(ex);
			}
		}
		return tc;
	}
	
	private InstanceContent content = new InstanceContent();
	private Saver saver = new Saver();
//	private static Opener opener = new Opener();
	private ProjectModel project;
	private Engine engine;

	public EditorTopComponent()
	{
		this(new ProjectModel(NbBundle.getMessage(EditorTopComponent.class, "CTL_EditorTopComponent", ++counter)));
	}
	
	private EditorTopComponent(ProjectModel obj)
	{
		initComponents();
		project = obj;
		//setName(NbBundle.getMessage(EditorTopComponent.class, "CTL_EditorTopComponent", ++counter));
		setName(project.getName());
		setToolTipText(NbBundle.getMessage(EditorTopComponent.class, "HINT_EditorTopComponent"));
		//putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
		putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
		putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
		putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);

		associateLookup(new ProxyLookup(Lookups.fixed(PaletteFactory.createPalette(new AbstractNode(Children.create(new CategoryChildFactory(), true)), new PaletteActions()
		{
			@Override
			public Action[] getImportActions()
			{
				return null;
			}

			@Override
			public Action[] getCustomPaletteActions()
			{
				return null;
			}

			@Override
			public Action[] getCustomCategoryActions(Lookup lkp)
			{
				return null;
			}

			@Override
			public Action[] getCustomItemActions(Lookup lkp)
			{
				return null;
			}

			@Override
			public Action getPreferredAction(Lookup lkp)
			{
				return null;
			}
		})), new AbstractLookup(content)));
		//associateLookup(new AbstractLookup(content));

		final mxGraph graph = graphComponent.getGraph();
		//mxGraph graph = new mxGraph(project.getModel());
		project.setModel(graph.getModel());
		//graph.setModel(project.getModel());
		//graphComponent = new mxGraphComponent(graph);
		//graphComponent.refresh();
		//graphComponent.getGraphControl().repaint();

		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				mxCell cell = (mxCell) graphComponent.getCellAt(e.getX(), e.getY());

				if (cell != null)
				{
					if (cell instanceof ElementModel)
					{
						Object val = cell.getValue();
						if (val instanceof BasicElement)
						{
							((BasicElement) val).toggleState();
							//((mxGraphModel) graph.getModel()).fireEvent(new mxEventObject(mxEvent.CHANGE)); //cause nullpointer
							engine.start();
						}
					}
				}
			}
		});

		graph.setMultigraph(false);
		graph.setAllowDanglingEdges(false);
		graph.setDisconnectOnMove(false);
		graph.setCellsResizable(false);
		graph.setCellsEditable(false);
		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.ElbowConnector);
		style.put(mxConstants.STYLE_ENDARROW, "");	//remove arrow
		graphComponent.setConnectable(true);
		graphComponent.setToolTips(true);
		graph.setLabelsVisible(false);
		graphComponent.setFoldingEnabled(false);

		// Enables rubberband selection
		new mxRubberband(graphComponent);
		new mxKeyboardHandler(graphComponent);

		mxMultiplicity[] multiplicities = new mxMultiplicity[2];

		multiplicities[0] = new mxMultiplicity(false, null, null, null, 0,
				"1", null, "", null, true)
		{
			@Override
			public boolean checkTerminal(mxGraph graph, Object terminal, Object edge)
			{
				return terminal instanceof ElementPort;
			}
		};
		multiplicities[1] = new mxMultiplicity(true, null, null, null, 0,
				"1", null, "", null, true)
		{
			@Override
			public boolean checkTerminal(mxGraph graph, Object terminal, Object edge)
			{
				return terminal instanceof ElementPort;
			}
		};

		graph.setMultiplicities(multiplicities);
		graphComponent.getConnectionHandler().setShowMessageDialogEnabled(false);

		// Installs automatic validation (use editor.validation = true
		// if you are using an mxEditor instance)
		graph.getModel().addListener(mxEvent.CHANGE, new mxIEventListener()
		{
			@Override
			public void invoke(Object sender, mxEventObject evt)
			{
				graphComponent.validateGraph();
				engine.start();
			}
		});
		graphComponent.showDirtyRectangle = true;
		graph.getModel().addListener(Engine.CIRCLE_DONE, new mxIEventListener()
		{
			@Override
			public void invoke(Object sender, mxEventObject evt)
			{
				System.out.println(evt.getName());
				graphComponent.getGraph().refresh();
//				graphComponent.refresh();
//				graphComponent.repaint(graphComponent.getViewport().getViewRect());
//				graphComponent.getGraph().getModel().beginUpdate();
//				graphComponent.getGraph().getModel().endUpdate();
//				graphComponent.validateGraph();
			}
		});

		// Initial validation
		graphComponent.validateGraph();



		engine = new Engine(graph);
//                try{Thread.sleep(100);}
//                catch(InterruptedException e){}
//		engine.stop();
//		engine.start();
//                try{Thread.sleep(100);}
//                catch(InterruptedException e){}
//		engine.stop();

		enableSaveAction(true);
//		content.add(opener);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphComponent = new com.mxgraph.swing.mxGraphComponent();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphComponent, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphComponent, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mxgraph.swing.mxGraphComponent graphComponent;
    // End of variables declaration//GEN-END:variables

	@Override
	public void componentOpened()
	{
		engine.start();
		// TODO add custom code on component opening
	}

	@Override
	public void componentClosed()
	{
		counter--;
		engine.stop();
		//engine = null;
		// TODO add custom code on component closing
	}

	void writeProperties(java.util.Properties p)
	{
		// better to version settings since initial version as advocated at
		// http://wiki.apidesign.org/wiki/PropertyFiles
		p.setProperty("version", "1.0");
		// TODO store your settings
	}

	void readProperties(java.util.Properties p)
	{
		String version = p.getProperty("version");
		// TODO read your settings according to their version
	}

	private void enableSaveAction(boolean canSave)
	{
		//If the canvas is modified, we add SaveCookie impl to Lookup:
		if (canSave)
		{
			content.add(saver);
		} //Otherwise, we remove the SaveCookie impl from the lookup
		//and add listener for any changes to the graph mode
		//to enable save capability again
		else
		{
			content.remove(saver);
			project.getModel().addListener(mxEvent.CHANGE, new mxIEventListener()
			{
				@Override
				public void invoke(Object sender, mxEventObject evt)
				{
					//Once we can save, we are done listening.
					//If enableSaveAction(false) is called, we will
					//start listening again.
					project.getModel().removeListener(this);
					enableSaveAction(true);
				}
			});
		}
	}

	@NbBundle.Messages(
	{
		"MSG_Saved=BUM Project succesfully saved: {0}",
		"TTL_SAVE_DIALOG=Save Project",
		"MSG_Overwrite=There is already \"{0}\", do you want to overwrite it?",
		"MSG_SaveFailed=BUM Project failed to save: {0}",
		"EXT_FileType=.bummel"
	})
	private class Saver implements SaveCookie
	{
		@Override
		public void save() throws IOException
		{
			DataObject theFile = getLookup().lookup(DataObject.class);
			if (theFile != null)
			{
				File saveTo = FileUtil.toFile(theFile.getPrimaryFile());
				save(saveTo);
			} else
			{
				saveAs();
			}
		}

		public void saveAs() throws IOException
		{
			String title = NbBundle.getMessage(Saver.class, "TTL_SAVE_DIALOG");
			Object userChoice = NotifyDescriptor.NO_OPTION;
			File f;
			while (NotifyDescriptor.NO_OPTION.equals(userChoice))
			{
				f = new FileChooserBuilder(Saver.class).setTitle(title).showSaveDialog();
				if (f != null)
				{
					if (!f.getAbsolutePath().endsWith(NbBundle.getMessage(Saver.class, "EXT_FileType")))
					{
						f = new File(f.getAbsolutePath() + NbBundle.getMessage(Saver.class, "EXT_FileType"));
					}
					String projectPrevName = project.getName();
					project.setName(f.getName().substring(0, f.getName().length() - 4));
					try
					{
						if (!f.exists())
						{
							if (!f.createNewFile())
							{
								String failMsg = NbBundle.getMessage(
										Saver.class,//EditorTopComponent.class,
										"MSG_SaveFailed", f.getName());
								DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(failMsg));
								return;
							}
						}
						else
						{
							String overwriteMessage = NbBundle.getMessage(Saver.class, "MSG_Overwrite", f.getName());
							userChoice = DialogDisplayer.getDefault().notify(new NotifyDescriptor.Confirmation(overwriteMessage));
							if (NotifyDescriptor.CANCEL_OPTION.equals(userChoice))
							{
								return;
							}
							if (NotifyDescriptor.NO_OPTION.equals(userChoice))
							{
								continue;
							}
						}
						//Need getAbsoluteFile(), or X.bum and x.bum are different on *nix
						save(f.getAbsoluteFile());
						tcByFile.put(f, EditorTopComponent.this);
						userChoice = NotifyDescriptor.YES_OPTION;
					}
					catch (IOException ioe)
					{
						project.setName(projectPrevName);
						Exceptions.attachMessage(ioe, "Saver.saveAs():");
						Exceptions.printStackTrace(ioe);
					}
				}
				else
				{
					userChoice = NotifyDescriptor.YES_OPTION;
				}
			}
		}

		private void save(File f) throws IOException
		{
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(project);
			out.close();
			fos.close();
			String savedMessage = NbBundle.getMessage(Saver.class, "MSG_Saved", f.getName());
			StatusDisplayer.getDefault().setStatusText(savedMessage);
			FileObject fob = FileUtil.toFileObject(FileUtil.normalizeFile(f));
			assert fob != null : "MasterFS excluded from suite?";
			//Store the file, so we don't show the Save dialog again
			content.add(DataObject.find(fob));
			setDisplayName(fob.getName());
			enableSaveAction(false);
		}
	}

	@NbBundle.Messages(
	{
		"MSG_Opened=BUM Project succesfully opened: {0}",
		"TTL_OPEN_DIALOG=Open Project",
		"MSG_OpenFailed=Failed to open BUM Project: {0}"
	})
	private static class Opener implements OpenCookie
	{
		@Override
		public void open()
		{
//			String title = NbBundle.getMessage(Opener.class, "TTL_OPEN_DIALOG");
//			File f = new FileChooserBuilder(Opener.class).setTitle(title).showOpenDialog();
//			open(f);
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
		public static ProjectModel open(File f)
		{
			ProjectModel projectModel = null;
			if (f != null)
			{
				FileInputStream fis = null;
				ObjectInputStream oin = null;
				try
				{
					fis = new FileInputStream(f);
					oin = new ObjectInputStream(fis);
					projectModel = (ProjectModel) oin.readObject();
					oin.close();
//					if (project.getName().equals(projectModel.getName()))
//					{
//						System.out.println("Serialization succeded.");
//					}
				}
				catch (IOException ex)
				{
					Exceptions.printStackTrace(ex);
				}
				catch (ClassNotFoundException ex)
				{
					Exceptions.printStackTrace(ex);
				}
			}
			return projectModel;
		}
	}
}