package projects.lelann.nodes.messages;

import sinalgo.nodes.messages.Message;

public class LeLannMessage extends Message {

	private static int msgCounter = 0;
	private int msgId;
	public LeLannMessage() {
		super();
		msgId = msgCounter;
		msgCounter++;
	}

	public Message clone() {
		return this;
	}
	
	public String toString() {
		return "walker" + msgId;
	}
}