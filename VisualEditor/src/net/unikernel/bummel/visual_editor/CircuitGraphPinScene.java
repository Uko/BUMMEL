package net.unikernel.bummel.visual_editor;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import net.unikernel.bummel.project_model.api.BasicElement;
import net.unikernel.bummel.project_model.api.Circuit;
import org.netbeans.api.visual.action.*;
import org.netbeans.api.visual.anchor.AnchorFactory;
import org.netbeans.api.visual.anchor.AnchorShape;
import org.netbeans.api.visual.anchor.PointShape;
import org.netbeans.api.visual.graph.GraphPinScene;
import org.netbeans.api.visual.router.RouterFactory;
import org.netbeans.api.visual.vmd.VMDConnectionWidget;
import org.netbeans.api.visual.widget.ConnectionWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.util.Exceptions;

/**
 *
 * @author mcangel
 */
public class CircuitGraphPinScene extends GraphPinScene<ElementNode, String, String>
{
	private LayerWidget mainLayer = new LayerWidget(this);
	private LayerWidget connectionLayer = new LayerWidget(this);
	private LayerWidget interractionLayer = new LayerWidget(this);
	private long edgeCounter = 0;
	private WidgetAction connectAction = ActionFactory.createConnectAction(interractionLayer, new SceneConnectProvider());
	private WidgetAction reconnetAction = ActionFactory.createReconnectAction(new SceneReconnectProvider());
	private Circuit circuit;

	public CircuitGraphPinScene(final Circuit circuit)
	{
		this.circuit = circuit;
		this.addChild(mainLayer);
		this.addChild(connectionLayer);
		this.addChild(interractionLayer);
		
		getActions().addAction(ActionFactory.createAcceptAction(new AcceptProvider()
		{
			@Override
			public ConnectorState isAcceptable(Widget widget, Point point, Transferable transferable)
			{
				Node node = NodeTransfer.node(transferable,	NodeTransfer.DND_COPY);
				if (node != null && (node.getLookup().lookup(BasicElement.class)) != null)
				{
					JComponent view = getView();
					Graphics2D g2 = (Graphics2D) view.getGraphics();
					Rectangle visRect = view.getVisibleRect();
					view.paintImmediately(visRect.x, visRect.y, visRect.width, visRect.height);
					g2.drawString(node.getDisplayName(),
							(float) point.getLocation().getX(),
							(float) point.getLocation().getY());
					return ConnectorState.ACCEPT;
				} else
				{
					return ConnectorState.REJECT_AND_STOP;
				}
			}

			@Override
			public void accept(Widget widget, Point point, Transferable transferable)
			{
				Node node = NodeTransfer.node(transferable, NodeTransfer.DND_COPY);
				
				JComponent view = getView();
				Rectangle visRect = view.getVisibleRect();
				view.paintImmediately(visRect.x, visRect.y, visRect.width, visRect.height);

				BasicElement el = node.getLookup().lookup(BasicElement.class);
				ElementNode elNode;
				try
				{
					elNode = new ElementNode(el.getClass().newInstance());
					CircuitGraphPinScene.this.addNode(elNode)
						.setPreferredLocation(widget.convertLocalToScene(point));
					for (String port : el.getPorts())
					{
						addPin(elNode, port);
					}
					
					//add element to the model
					circuit.addElement(elNode.getLookup().lookup(BasicElement.class));
					
				} catch (InstantiationException | IllegalAccessException ex)
				{
					Exceptions.printStackTrace(ex);
				}
				validate();
			}
		}));
	}
	
	@Override
	protected Widget attachNodeWidget(ElementNode node)
	{
		ElementWidget widget = new ElementWidget(this, node);
		mainLayer.addChild(widget);

		widget.getActions().addAction(createSelectAction());
		widget.getActions().addAction(ActionFactory.createMoveAction());

		return widget;
	}

	@Override
	protected Widget attachPinWidget(ElementNode node, String pin)
	{
		ElementPortWidget widget = new ElementPortWidget(this, node, pin);
		widget.setPort(pin);
		((ElementWidget) findWidget(node)).attachPortWidget(widget);

		widget.getActions().addAction(createObjectHoverAction());
		widget.getActions().addAction(createSelectAction());
		widget.getActions().addAction(connectAction);

		return widget;
	}
	
	@Override
	protected Widget attachEdgeWidget(String edge)
	{
		VMDConnectionWidget connection = new VMDConnectionWidget(this,
				RouterFactory.createOrthogonalSearchRouter(mainLayer, connectionLayer));
		connection.setTargetAnchorShape(AnchorShape.NONE);
		connection.setEndPointShape(PointShape.SQUARE_FILLED_BIG);

		connection.getActions().addAction(createObjectHoverAction());
		connection.getActions().addAction(createSelectAction());
		connection.getActions().addAction(reconnetAction);
		connection.getActions().addAction(ActionFactory.createOrthogonalMoveControlPointAction());

		connectionLayer.addChild(connection);
		return connection;
	}

	@Override
	protected void attachEdgeSourceAnchor(String edge, String oldSourcePin, String sourcePin)
	{
		Widget w = sourcePin != null ? findWidget(sourcePin) : null;
		((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(w));
		//((ConnectionWidget) findWidget(edge)).setSourceAnchor(((ElementPortWidget)w).getAnchor());
		//((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(((ElementPortWidget)w).getAnchorWidget()));
	}

	@Override
	protected void attachEdgeTargetAnchor(String edge, String oldTargetPin, String targetPin)
	{
		Widget w = targetPin != null ? findWidget(targetPin) : null;
		((ConnectionWidget) findWidget(edge)).setTargetAnchor(AnchorFactory.createRectangularAnchor(w));
		//((ConnectionWidget) findWidget(edge)).setSourceAnchor(((ElementPortWidget)w).getAnchor());
		//((ConnectionWidget) findWidget(edge)).setSourceAnchor(AnchorFactory.createRectangularAnchor(((ElementPortWidget)w).getAnchorWidget()));
	}

	private class SceneConnectProvider implements ConnectProvider
	{
		private String source = null;
		private String target = null;

		@Override
		public boolean isSourceWidget(Widget sourceWidget)
		{
			Object object = findObject(sourceWidget);
			source = isPin(object) ? (String) object : null;
			return source != null;
		}

		@Override
		public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget)
		{
			//TODO: add single connection check
			Object object = findObject(targetWidget);
			target = isPin(object) ? (String) object : null;
			if (target != null)
			{
				return !source.equals(target) ? ConnectorState.ACCEPT : ConnectorState.REJECT_AND_STOP;
			}
			return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
		}

		@Override
		public boolean hasCustomTargetWidgetResolver(Scene scene)
		{
			return false;
		}

		@Override
		public Widget resolveTargetWidget(Scene scene, Point sceneLocation)
		{
			return null;
		}

		@Override
		public void createConnection(Widget sourceWidget, Widget targetWidget)
		{
			String edge = "edge" + edgeCounter++;
			addEdge(edge);
			setEdgeSource(edge, source);
			setEdgeTarget(edge, target);
			
			//connect elements in the model
			BasicElement srcElem = (getPinNode((String)findObject(sourceWidget)))
					.getLookup().lookup(BasicElement.class);
			BasicElement tgtElem = (getPinNode((String)findObject(targetWidget)))
					.getLookup().lookup(BasicElement.class);
			circuit.connectElements(srcElem, (String)findObject(sourceWidget), 
					tgtElem, (String)findObject(targetWidget));
			circuit.step();
			circuit.step();
			circuit.step();
			circuit.step();
			circuit.step();
		}
	}

	private class SceneReconnectProvider implements ReconnectProvider
	{
		String edge;
		String originalNode;
		String replacementNode;

		@Override
		public void reconnectingStarted(ConnectionWidget connectionWidget, boolean reconnectingSource)
		{
		}

		@Override
		public void reconnectingFinished(ConnectionWidget connectionWidget, boolean reconnectingSource)
		{
		}

		@Override
		public boolean isSourceReconnectable(ConnectionWidget connectionWidget)
		{
			Object object = findObject(connectionWidget);
			edge = isEdge(object) ? (String) object : null;
			originalNode = edge != null ? getEdgeSource(edge) : null;
			return originalNode != null;
		}

		@Override
		public boolean isTargetReconnectable(ConnectionWidget connectionWidget)
		{
			Object object = findObject(connectionWidget);
			edge = isEdge(object) ? (String) object : null;
			originalNode = edge != null ? getEdgeTarget(edge) : null;
			return originalNode != null;
		}

		@Override
		public ConnectorState isReplacementWidget(ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource)
		{
			Object object = findObject(replacementWidget);
			replacementNode = isPin(object) ? (String) object : null;
			if (replacementNode != null)
			{
				return ConnectorState.ACCEPT;
			}
			return object != null ? ConnectorState.REJECT_AND_STOP : ConnectorState.REJECT;
		}

		@Override
		public boolean hasCustomReplacementWidgetResolver(Scene scene)
		{
			return false;
		}

		@Override
		public Widget resolveReplacementWidget(Scene scene, Point sceneLocation)
		{
			return null;
		}

		@Override
		public void reconnect(ConnectionWidget connectionWidget, Widget replacementWidget, boolean reconnectingSource)
		{
			if (replacementWidget == null)
			{
				removeEdge(edge);
			} else if (reconnectingSource)
			{
				setEdgeSource(edge, replacementNode);
			} else
			{
				setEdgeTarget(edge, replacementNode);
			}
		}
	}
}
