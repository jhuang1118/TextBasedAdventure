package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;

/* INVALID CODE NEEDS TO BE MINIGAME*/
public class EthanRoomBackEnd implements DavidSupport{
	
	private int MONEY_CUT_OFF;
	private boolean cheating;
	private EthanSupport frontend;
	
	long startTime = System.currentTimeMillis();
	long elapsedTime = System.currentTimeMillis() - startTime;
	long elapsedSeconds = elapsedTime/1000;
	
	public EthanRoomBackEnd(EthanSupport frontend) {
		this.frontend = frontend;
		MONEY_CUT_OFF = 100000;
	}
	
	public boolean hitLaser() {
		if(EthanDavidObstacles.isUserHit()) {
			//gameover
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

	@Override
	public boolean stillPlaying() {
		return MONEY_CUT_OFF == 100000;
	}

	@Override
	public String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startTimer() {
		if(elapsedSeconds == 40 && amountOfMoney!= 10) {
			
		}
		if(amountOfMoney == 10) {
			
		}
		
	}

	@Override
	public Object victorious() {
		// TODO Auto-generated method stub
		return null;
	}
}