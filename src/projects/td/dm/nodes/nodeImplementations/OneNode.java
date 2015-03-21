package projects.td.dm.nodes.nodeImplementations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import projects.td.dm.nodes.messages.TokenMessage;

import sinalgo.configuration.WrongConfigurationException;
import sinalgo.nodes.Connections;
import sinalgo.nodes.Node;
import sinalgo.nodes.messages.Inbox;
import sinalgo.nodes.messages.Message;

public class OneNode extends sinalgo.nodes.Node {

	final static public int Bottom = -1;
	final static public int NotBottom = 1;
	
	public static List<OneNode> nodeList= new LinkedList<OneNode>();
	private static int counter =0;
	
	public boolean initiateur = false;
	public boolean[] visite;
	public int pere  = Bottom;
	
	@Override
	public void handleMessages(Inbox inbox) {
		while(inbox.hasNext()) {
			
			TokenMessage msg = (TokenMessage) inbox.next();
			int q = msg.getMsgId();
			if (pere == Bottom) {
				pere = q; 
			}
			
			boolean ok = true;
			for(int i=1; i<=outgoingConnections.size(); i++) {
				ok = visite[this.getNode(outgoingConnections, i).ID] && ok;
			}
			
			if(ok) {
				//decide
			} else if (pere != q && ! visite[q]) {
				visite[q] = true;
				//send Jeton to q
				((TokenMessage)msg).setMsgId(this.ID);
				send(msg, nodeFromID(q));
			} else {
				ok  = false;
				int qp = 0 ;
				for(int i=1; i<=outgoingConnections.size(); i++) {
					qp = this.getNode(outgoingConnections, i).ID;
					if (qp != q && pere != q && ! visite[qp])
							ok = true;
				}
				if (ok) {
					visite[qp] = true;
					//send Token to qp
					((TokenMessage)msg).setMsgId(this.ID);
					send(msg, nodeFromID(qp));
				} else {
					visite[pere] = true;
					//send Token to pere
					((TokenMessage)msg).setMsgId(this.ID);
					send(msg, nodeFromID(pere));
				}
			}
		}
	}

	
	@Override
	public void preStep() {

	}

	@Override
	public void init() {
		setColor(Color.YELLOW);
		nodeList.add(this);
//		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this); 	
		this.ID = counter++;
		
	}

	public void InitiatingMessages () {
		// TODO Auto-generated method stub
		Message first = new TokenMessage(this.ID);
		
		for(int i=1; i<=outgoingConnections.size(); i++) {
			visite[i] = false;
		}
		
		if (initiateur) {
			visite[1] = true;
			pere = NotBottom;
			send(first, nodeFromID(1));
		} else {
			pere = Bottom;
		}
	}
	
	public Node nodeFromID (int id) {
		
		for(Node node : nodeList) {
			if (node.ID == id)
				return node;
		}
		throw new RuntimeException("no neighbor left");
	}
	
	public Node getNode (Connections neighbors, int n) {
		int degree = neighbors.size();
		if (degree == 0) throw new RuntimeException("no neighbor in nextNode()");
		
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> iter = neighbors.iterator();
		Node node = iter.next().endNode;

		for (int i = 1; i <= degree; i++){
			if (i == n) {
				node = iter.next().endNode;
				return node;
			}
		}
		
		throw new RuntimeException("no neighbor left");
	}
	
	public Node nextNodeExcept (Connections neighbors, List<Node> nodeList) {
		int degree = neighbors.size();
		if (degree == 0) throw new RuntimeException("no neighbor in nextNodeExcept()");
		
		sinalgo.tools.storage.ReusableListIterator<sinalgo.nodes.edges.Edge> iter = neighbors.iterator();
		
		for (int i = 0; i <= degree; i++){
			Node node = iter.next().endNode;
			if (! nodeList.contains(node))
				return node;
		}

//		return null;
		throw new RuntimeException("no neighbor left");
	}
	
	@Override
	public void neighborhoodChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postStep() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkRequirements() throws WrongConfigurationException {
		// TODO Auto-generated method stub

	}

	public void draw(java.awt.Graphics g, sinalgo.gui.transformation.PositionTransformation pt, 
			 boolean highlight) {
		// draw the node as a circle with the text inside
		super.drawNodeAsDiskWithText(g, pt, highlight, this.getID(), 40, Color.black);
	}
	
	public String getID () {
		return String.valueOf(this.ID);
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	
	static public void clear() {
		counter = 0;
		nodeList.clear();
	}
}
