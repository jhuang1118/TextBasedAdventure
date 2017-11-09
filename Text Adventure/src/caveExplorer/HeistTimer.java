package caveExplorer;

import java.util.Timer;
import java.util.TimerTask;

public class HeistTimer {
	private boolean vanArrival; 
	static int interval;
	static Timer copTimer;
	static Timer vanTimer;
	
	private static final int VAN_ARRIVAL_TIME = 500;
	private static final int COP_SPAWN_TIME = 20;
	
	public HeistTimer() {
		
	}	

	public void copTimer() {
		int delay = 1000;
		int period = 1000;
		copTimer = new Timer();
		interval = COP_SPAWN_TIME;
		System.out.println(COP_SPAWN_TIME);
		copTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println(copSetInterval(interval));
			}	
		}, delay, period);
	}
	
	private static final int copSetInterval(int interval) {
		if (interval == 1) {
			copTimer.cancel();
		}
		return --interval;
	}
	
	public void vanTimer() {
		int delay = 1000;
		int period = 1000;
		vanTimer = new Timer();
		interval = VAN_ARRIVAL_TIME;
		System.out.println(VAN_ARRIVAL_TIME);
		vanTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println(vanSetInterval(interval));
			}	
		}, delay, period);
	}
	
	private static final int vanSetInterval(int interval) {
		if (interval == 1) {
			vanTimer.cancel();
		}
		return --interval;
	}
}
