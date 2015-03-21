package projects.dm.nodes.messages;

import projects.dm.nodes.messages.AskMessage.Side;
import sinalgo.nodes.messages.Message;

public class ReplyMessage extends Message {

	private static int msgCounter = 0;
	private int msgId;
	public Side side;
	public int getMsgId() {
		return msgId;
	}

	public ReplyMessage(int id, Side s) {
		super();
//		msgId = msgCounter;
//		msgCounter++;
		msgId = id;
		side = s;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return this;
	}

	public String toString() {
		return " reply " + msgId;
	}
}
