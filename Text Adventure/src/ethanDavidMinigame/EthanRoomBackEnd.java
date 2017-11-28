package ethanDavidMinigame;

public class EthanRoomBackEnd implements DavidSupport{
	
	public int MONEY_CUT_OFF;
	private int currMoney;
	private int invincibleCounter;
	private boolean cheating;
	private EthanSupport frontend;
	private boolean lost;
	private boolean invincible;
	
	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public EthanRoomBackEnd(EthanSupport frontend) {
		this.frontend = frontend;
		MONEY_CUT_OFF = 65000;
		currMoney = 0;
		lost = false;
	}
	
	public void createPowerUps() {
				int ROOM_LENGTH = frontend.getRooms().length;
				DavidEthanRoom[][] Room = frontend.getRooms();
				for(int i = 0; i < 5; i++) {
					int[] randArr = randNums(Room, ROOM_LENGTH);
					Room[randArr[0]][randArr[1]].setContainsPowerup(true);
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
			int x = randArr[1]+1;
			if(randArr[1]+1 <= Room[ROOM_LENGTH-1].length-1) {
				while(checkSpecialRoom(Room, randArr[0], x)) {
					randArr = randNums(Room, 5);
				}
				Room[randArr[0]][randArr[1]+1].setContainsLaser(true);
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

	public boolean checkSpecialRoom(DavidEthanRoom[][] room, int num1, int num2) {
		return room[num1][num2].isContainsTreasure() || room[num1][num2].isContainsLaser() || room[num1][num2].isContainsPowerup() || room[num1][num2].isUserIn();
	}
	
	public void recieveMoney(DavidEthanRoom[][] room, int row, int col) {
		int moneyCount = room[row][col].getMoney();
		room[row][col].setContainsTreasure(false);
		currMoney += moneyCount;
		createMoney(1, false);
		createLasers(1, false);
		room[row][col].setUserIn(false);
		frontend.displayMoney();
	}

	public void cheat() {
		if(cheating) {
			currMoney = MONEY_CUT_OFF;
			frontend.displayMoney();
		}	
	}
	
	public boolean stillPlaying(int money) {
		if(lost == true) {
			return false;
		}
		if(money >= MONEY_CUT_OFF) {
			return false;
		}
		return true;
	}

	public boolean stillPlaying() {
		return MONEY_CUT_OFF == 65000;
	}

	private void activateAI() {
		// TODO Auto-generated method stub
		/**
		 * override to create response to keys other than wdsa
		 * @param direction
		 */
	}

	@Override
	public String getValidUserInput() {
		return null;
	}

	public void checkLose() {
		stillPlaying(currMoney);
	}

	@Override
	public Object victorious() {
		return stillPlaying(currMoney) == true;
	}

	public void addInvincibility(DavidEthanRoom[][] room, int row, int col) {
		room[row][col].setContainsPowerup(false);
		invincibleCounter++;
		setInvincibleCounter(invincibleCounter);
		frontend.displayPowerup();
		
	}

	public int getInvincibleCounter() {
		return invincibleCounter;
	}

	public void setInvincibleCounter(int invincibleCounter) {
		this.invincibleCounter = invincibleCounter;
	}
}