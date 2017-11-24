package ethanDavidMinigame;

import java.util.Timer;
import java.util.TimerTask;


import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;

public class EthanRoomBackEnd implements DavidSupport{
	
	int MONEY_CUT_OFF;
	private int currMoney;
	private boolean cheating;
	private EthanSupport frontend;
	private boolean lost;
	
	public EthanRoomBackEnd(EthanSupport frontend) {
		this.frontend = frontend;
		MONEY_CUT_OFF = 50000;
		currMoney = 0;
		lost = false;
	}
	
	public void createPowerUps() {
		int ROOM_LENGTH = frontend.getRooms().length;
		DavidEthanRoom[][] Room = frontend.getRooms();
		for(int i = 0; i < 5; i++) {
			int[] randArr = randNums(Room, ROOM_LENGTH);
			if(randArr[0]+1 <= ROOM_LENGTH-1 && randArr[1]+1 <= Room[ROOM_LENGTH-1].length-1) {
				Room[randArr[0]][randArr[1]+1].setContainsPowerup(true);
			}
		}
	}
	
	public void createLasers(int repeat, boolean defaultLen) {
		int ROOM_LENGTH = repeat;
		if(defaultLen) {
			ROOM_LENGTH = frontend.getRooms().length;
		}
		DavidEthanRoom[][] Room = frontend.getRooms();
		for(int i = 0; i < ROOM_LENGTH; i++) {
			int[] randArr = randNums(Room, 5);
			if(randArr[1]+1 <= Room[ROOM_LENGTH-1].length-1) {
				Room[randArr[0]][randArr[1]+1].setContainsLaser(true); //remove +1 in a bit for row. spawning laser
			}
		}
	}
	public void setCheating(boolean cheating) {
		this.cheating = cheating;
	}

	public int getCurrMoney() {
		return currMoney;
	}

	public void setCurrMoney(int currMoney) {
		this.currMoney = currMoney;
	}

	public void createMoney(int repeat, boolean defaultLen) {
		int ROOM_LENGTH = repeat;
		DavidEthanRoom[][] Room = frontend.getRooms();
		if(defaultLen) {
			ROOM_LENGTH = frontend.getRooms().length;
			repeat = ROOM_LENGTH;
		}
		for(int i = 0; i < repeat; i++) {
			ROOM_LENGTH = frontend.getRooms().length;
			int[] randArr = randNums(Room, ROOM_LENGTH);
			Room[randArr[0]][randArr[1]].setContainsTreasure(true);
			Room[randArr[0]][randArr[1]].setMoney(2500 + (int)(Math.random() * 10000));
		}
	}
	//bug not adding all the $ and L?
	
	public int[] randNums(DavidEthanRoom[][] room, int length) {
		int[] myArr = new int[2];
			int randNum1 = (int)(Math.random() * length);
			int randNum2 = (int)(Math.random() * room[length-1].length);
			while(checkSpecialRoom(room, randNum1, randNum2)) {
				randNum1 = (int)(Math.random() * length);
				randNum2 = (int)(Math.random() * room[length-1].length);
			}
		myArr[0] = randNum1;
		myArr[1] = randNum2;
		return myArr;
	}
	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	//every 5 moves add laser
	public boolean checkSpecialRoom(DavidEthanRoom[][] room, int num1, int num2) {
		return room[num1][num2].isContainsTreasure() || room[num1][num2].isContainsLaser();
	}
	
	public void recieveMoney(DavidEthanRoom[][] room, int row, int col) {
		int moneyCount = room[row][col].getMoney();
		room[row][col].setContainsTreasure(false);
		currMoney += moneyCount;
		createMoney(1, false);
		createLasers(1, false);
		frontend.displayMoney();
	}

	public void cheat() {
		DavidRoomFrontEnd cheating1 = new DavidRoomFrontEnd();
		if(cheating) {
			currMoney = MONEY_CUT_OFF;
			cheating1.displayMoney();
		}	
	}
	
	@Override
	public boolean stillPlaying() {
		if(lost == true) {
			return false;
		}
		return !(currMoney >= 100000) || !lost;
	}

	@Override
	public String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loseGame() {
		System.out.println("You hit the laser and died a miserable death.");
		stillPlaying();
	}

	@Override
	public Object victorious() {
		// TODO Auto-generated method stub
		return stillPlaying() == true;
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
		
	}
}