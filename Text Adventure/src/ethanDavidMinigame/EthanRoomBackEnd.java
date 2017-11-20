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
	private int secPassed;
	
	public EthanRoomBackEnd(EthanSupport frontend) {
		this.frontend = frontend;
		MONEY_CUT_OFF = 100000;
		currMoney = 0;
		secPassed = 0;
	}
	
	public void createLasers() {
		int ROOM_LENGTH = frontend.getRooms().length;
		DavidEthanRoom[][] Room = frontend.getRooms();
		for(int i = 0; i < ROOM_LENGTH; i++) {
			int[] randArr = randNums(Room, ROOM_LENGTH);
			Room[randArr[0]][randArr[1]] = new EthanDavidObstacles();
		}
	}
	//idea add powerups.
	public void createMoney() {
		int ROOM_LENGTH = frontend.getRooms().length;
		DavidEthanRoom[][] Room = frontend.getRooms();
		for(int i = 0; i < ROOM_LENGTH; i++) {
			int[] randArr = randNums(Room, ROOM_LENGTH);
			Room[randArr[0]][randArr[1]].setContainsTreasure(true);
			Room[randArr[0]][randArr[1]].setMoney((int)(Math.random() * 1000));
		}
		createLasers();
	}
	//add an AI that attempts to change the laser locations?????
	
	public int[] randNums(DavidEthanRoom[][] room, int length) {
		int[] myArr = new int[2];
		for(int i = 0; i < length; i++) {
			int randNum1 = (int)(Math.random() * length);
			int randNum2 = (int)(Math.random() * length);
			while(checkSpecialRoom(room, randNum1, randNum2)) {
				randNum1 = (int)(Math.random() * length);
				randNum2 = (int)(Math.random() * length);
			}
		myArr[0] = randNum1;
		myArr[1] = randNum2;
		}
		return myArr;
	}
	
	public boolean checkSpecialRoom(DavidEthanRoom[][] room, int num1, int num2) {
		return room[num1][num2].isContainsTreasure();
	}
	
	public void cheat() {
		if(!cheating) {
			currMoney = MONEY_CUT_OFF;
		}
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
		 task = new TimerTask() {
		    	public void run() {
		    		secPassed++;
		    		System.out.println(secPassed + " secs passed");
		    		if(secPassed == 45) {
		    			System.out.println("You got trapped in the vaultroom.");
		    			timer.cancel();
		    			loseGame();
		    		}
		    	}
		    };
	    EthanSupport.displayTimer();
	    //display timer needs: 
	    /*
	     * ToDo timerthing = new ToDo();
			timerthing.scheduleAtFixedRate(task, 1000, 1000); 1000 milliseconds = 1
	     */
	}
	

	@Override
	public Object victorious() {
		// TODO Auto-generated method stub
		return null;
	}
}