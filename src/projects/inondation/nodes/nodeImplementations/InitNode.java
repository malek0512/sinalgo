package projects.inondation.nodes.nodeImplementations;


import java.awt.Color;

import projects.inondation.nodes.messages.WalkerMessage;
import projects.inondation.nodes.timers.InitTimer;
import sinalgo.nodes.edges.Edge;

/** the initiator node sends the message (the walker) */
public class InitNode extends WalkerNode {

	/* InitNode() { ... } */
	public void init() {
		super.init(); 
		setColor(Color.GREEN);
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this); 		
	}

	public void initiate() {
		WalkerMessage walker = new WalkerMessage();
		System.out.println(this + " is sending now message " + walker);
		//send(walker, randomWalkChoice(outgoingConnections));
		for (Edge iterable_element : outgoingConnections) {
			send(walker, iterable_element.endNode);
		}
	}
		

	public String toString() {
		return super.toString() + "(init)";
	}
}
