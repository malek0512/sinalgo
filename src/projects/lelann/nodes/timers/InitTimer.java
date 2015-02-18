package projects.lelann.nodes.timers;

import projects.lelann.nodes.nodeImplementations.InitNode;
import sinalgo.nodes.timers.Timer;


public class InitTimer extends Timer {
	static public int timerRefresh = 100;

	private InitNode initiator;
	
	public InitTimer(InitNode initiator) {
			this.initiator = initiator;
	}
		
	/* the function "fire" is called when the timer is over */
	public void fire() {
		initiator.initiate();
		//this.startRelative(timerRefresh, initiator); // recursive restart of the timer (sends several walkers)
	}
}

