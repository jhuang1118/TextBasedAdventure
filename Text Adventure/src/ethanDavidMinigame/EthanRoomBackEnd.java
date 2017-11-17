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
		if(elapsedSeconds == 40 /*&& Inventory.getMoney() == MONEY_CUT_OFF*/) {
			
		}
	}

	@Override
	public Object victorious() {
		// TODO Auto-generated method stub
		return null;
	}
}