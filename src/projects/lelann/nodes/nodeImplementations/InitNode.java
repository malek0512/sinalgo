package projects.lelann.nodes.nodeImplementations;

import java.awt.Color;

import projects.lelann.nodes.messages.LeLannMessage;
import projects.lelann.nodes.timers.InitTimer;

/** the initiator node sends the message (the walker) */
public class InitNode extends LeLannNode {

	/* InitNode() { ... } */
	public void init() {
		super.init(); 
		setColor(Color.BLACK);
		(new InitTimer(this)).startRelative(InitTimer.timerRefresh, this);
		System.out.println("WAZAAAAAAAAA init");
	}
 
	public void initiate() {
		System.out.println("WAZAAAAAAAAA initiate");
		LeLannMessage walker = new LeLannMessage();
		System.out.println(this + " is sending now message " + walker);
		send(walker, rightWalkChoice(outgoingConnections));
	}

	public String toString() {
		return super.toString() + "(init)";
	}
}
