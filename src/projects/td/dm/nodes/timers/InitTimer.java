package projects.dm.nodes.timers;

import java.util.LinkedList;
import java.util.List;

import sinalgo.nodes.timers.Timer;
import projects.dm.nodes.nodeImplementations.AllNode;

public class InitTimer extends Timer {
	static public int timerRefresh = 100;

	private AllNode initiator;
	private List<AllNode> nodeList= new LinkedList<AllNode>();
	private int nbNode = 10;
	public InitTimer() {
//			this.initiator = initiator;
			this.node = null;
	}
	
		
	/* the function "fire" is called when the timer is over */
	public void fire() {
//		initiator.initiate();
		//this.startRelative(timerRefresh, initiator); // recursive restart of the timer (sends several walkers)
//		for(int i=0; i<nbNode; i++) 
//			nodeList.add(new AllNode());
		System.out.println("InitTimerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr\n");
	}
}

