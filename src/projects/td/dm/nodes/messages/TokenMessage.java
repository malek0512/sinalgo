package projects.td.dm.nodes.messages;

import sinalgo.nodes.messages.Message;

public class TokenMessage extends Message {

	private int msgId;
	
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getMsgId() {
		return msgId;
	}

	public TokenMessage(int id) {
		super();
		msgId = id;
	}
	
	@Override
	public Message clone() {
		// TODO Auto-generated method stub
		return this;
	}

	public String toString() {
		return " token " + msgId;
	}

}
