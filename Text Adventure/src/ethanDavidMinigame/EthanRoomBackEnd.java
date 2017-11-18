package ethanDavidMinigame;

import java.util.Timer;
import java.util.TimerTask;


import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;

/* INVALID CODE NEEDS TO BE MINIGAME*/
public class EthanRoomBackEnd implements DavidSupport{
	
	private int MONEY_CUT_OFF;
	private int currMoney;
	private boolean cheating;
	private EthanSupport frontend;
	private TimerTask task;
	private Timer timer;
	
	public EthanRoomBackEnd(EthanSupport frontend) {
		this.frontend = frontend;
		MONEY_CUT_OFF = 100000;
		currMoney = 0;
	}
	
	public void createLasers() {
		
	}
	
	public void createMoney() {
		int ROOM_LENGTH = frontend.getRooms().length;
		DavidEthanRoom[][] Room = frontend.getRooms();
		for(int i = 0; i < ROOM_LENGTH; i++) {
			int randNum1 = (int)(Math.random() * ROOM_LENGTH);
			int randNum2 = (int)(Math.random() * ROOM_LENGTH);
			while(Room[randNum1][randNum1].isContainsTreasure()) {
				randNum1 = (int)(Math.random() * ROOM_LENGTH);
				randNum2 = (int)(Math.random() * ROOM_LENGTH);
			}
			Room[randNum1][randNum2].setContainsTreasure(true);
			Room[randNum1][randNum2].setMoney((int)(Math.random() * 1000));
		}
	}
	//add an AI that attempts to change the laser locations?????
	
	public boolean isExited() {
		return false;
	}
	
	public boolean cheat() {
		if(!cheating) {
			
		}
		return cheating;
	}
	
	public void generateRooms() {
		currMoney = MONEY_CUT_OFF;
	}

	@Override
	public boolean stillPlaying() {
		return MONEY_CUT_OFF == 100000;
	}

	@Override
	public String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	private void loseGame() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void startTimer() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
	        public void run(){
	            //lose game.
	        }
	    };
	    long MILLISECONDS = 1000;
	    timer.schedule(task , MILLISECONDS * 45);
	    EthanSupport.displayTimer();
	    //
	}
	

	@Override
	public Object victorious() {
		// TODO Auto-generated method stub
		return null;
	}
}