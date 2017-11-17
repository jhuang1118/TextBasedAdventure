import java.util.Timer;

import java.util.TimerTask;

public class ToDo  {
  Timer timer;
  int secPassed;
  TimerTask task;

  public ToDo ()   {
	secPassed = 0;
    timer = new Timer () ;
    task = new TimerTask() {
    	public void run() {
    		secPassed++;
    		System.out.println(secPassed + " secs passed");
    		if(secPassed == 5) {
    			System.out.println("5 sec passed. end.");
    			timer.cancel();
    		}
    	}
    };
  }

  public void start() {
	  timer.scheduleAtFixedRate(task, 1000, 1000);
  }

  public static void main ( String args [  ]  )   {
	ToDo timerthing = new ToDo();
	timerthing.start();
  }
}