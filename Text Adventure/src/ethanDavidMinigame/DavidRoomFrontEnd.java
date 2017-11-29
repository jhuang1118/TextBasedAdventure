package ethanDavidMinigame;

import java.util.Scanner;

import caveExplorer.CaveExplorer;
import caveExplorer.DavidCar;
import caveExplorer.Inventory;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private CaveExplorer CaveExplorer;
	private DavidEthanRoom[][] rooms;
	private int currentRow;
	private int currentCol;
	
	private int hHit;
	
	public static DavidEthanRoom currentRoom;
	public EthanRoomBackEnd ethanRoom;
	
	private DavidSupport backend;
	
	public static Scanner in;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play(); 
	}
	
	private void play() {
		System.out.println("Your objective is to collect at least $65,000 all the while avoiding the lasers 'L'. If need be, collect the powerups 'p' to prevent death from lasers!");
		while(backend.stillPlaying(ethanRoom.getCurrMoney())) {
			displayBoard();
			respondToInput(in.nextLine());
		}
		printGameOverMessage(backend.victorious());
	}
	 
	public void printGameOverMessage(Object victorious) {
		if(ethanRoom.getCurrMoney() >= 65000) {
			caveExplorer.CaveExplorer.inventory.setCash(ethanRoom.getCurrMoney() + caveExplorer.CaveExplorer.inventory.getCash());
			System.out.println("You collected enough money. Now get to the car!");
		}else {
			System.out.println("You're dead.");
			System.exit(0);
		}
	}

	public void respondToInput(String input) {
		if(input.equals("m")) {
			displayCheating();
		}
		while(!isValid(input)) {
			System.out.println("You can't do that. You must type 'w,a,s, or d.'");
			input = in.nextLine();
		}
		if(isValid(input)) {
			int direction = validMoves().indexOf(input);
			goToRoom(direction);
			rooms[currentRow][currentCol].setUserIn(false);
		}
	}
	
	private void goToRoom(int dir) {
		rooms[currentRow][currentCol].setUserIn(false);
					if((dir == 0 && currentRow == 0) || (dir == 1 && currentCol == 14) || 
					(dir == 2 && currentRow == 4) || (dir == 3 && currentCol == 0)) {
					System.out.println("You hit your head against the wall. It hurts.");
					hHit++;
					deathFromWall();
				} 
				if(dir == 0 && currentRow > 0) {
					currentRow--;
					rooms[currentRow][currentCol].setUserIn(true);
					touchedLaser();
					collectTreasure();
					collectPowerup();
				}
				if(dir == 1 && currentCol < rooms[0].length-1) {
					currentCol++;
					rooms[currentRow][currentCol].setUserIn(true);
					touchedLaser();
					collectTreasure();
					collectPowerup();
				}
				if(dir == 2 && currentRow < rooms.length-1) {
					currentRow++;
					rooms[currentRow][currentCol].setUserIn(true);
					touchedLaser();
					collectTreasure();
					collectPowerup();
				}
				if(dir == 3 && currentCol > 0) {
					currentCol--;
					rooms[currentRow][currentCol].setUserIn(true);
					touchedLaser();
					collectTreasure();
					collectPowerup();
				}
	}
	
	private void collectPowerup() {
		if(rooms[currentRow][currentCol].isContainsPowerup()) {
			ethanRoom.addInvincibility(rooms, currentRow, currentCol);
		}
	}

	public String validMoves() {
		return "wdsam";
	}
	
	public void deathFromWall() {
		if(hHit == 5 && hHit < 7) {
			System.out.println("You have started bleeding. Who the hell in their right minds starts hitting their head against "
					+ "the wall in a heist!?");
		}
		if(hHit == 7 && hHit < 10) {
			System.out.println("You are bleeding a lot now. You should really stop before something stupid happens...");
		}
		if(hHit == 10) {
			System.out.println("You have hit your head on the wall too much times and died from blood loss. The worst heist America"
					+ " has ever seen. \nYou are a disgrace to bank robbers everywhere. However the bank has put up a sign "
					+ "near the dent in the wall to \ncommemorate the one time they got robbed but didn't.");
			backend.setLost(true);
			backend.checkLose();
		}
	}
	
	public void collectTreasure() {
		if(rooms[currentRow][currentCol].isContainsTreasure()) {
			ethanRoom.recieveMoney(rooms, currentRow, currentCol);
		}
	}
	
	public void touchedLaser() {
		if(rooms[currentRow][currentCol].isContainsLaser()) {
			if(ethanRoom.getInvincibleCounter() > 0) {
				ethanRoom.setInvincibleCounter(ethanRoom.getInvincibleCounter() - 1);
				System.out.println("Thank goodness! Your powerup saved you!");
				displayPowerup();
			}
			else {
			backend.setLost(true);
			backend.checkLose();
			}
		}
	}

	private boolean isValid(String input) {
		return validMoves().indexOf(input) > -1 && input.length() == 1;
	}
	
	public void displayBoard() {
		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[row].length; col++) {
				if(row == currentRow && col == currentCol) {
					System.out.print("X");
				}
				else {
					System.out.print(rooms[row][col]);
				}
			}
			System.out.println("");
		}
		}	
	
	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
		ethanRoom = new EthanRoomBackEnd(this);
		rooms = new DavidEthanRoom[5][15];
		for(DavidEthanRoom[] row: rooms) {
			for(int col = 0; col < row.length; col++) {
				row[col] = new DavidEthanRoom();
			}
		}
		changeRoom();
	}

	public void displayCheating() {
		ethanRoom.setCheating(true);
		ethanRoom.cheat();
	}

	public DavidEthanRoom[][] getRooms() {
		return rooms;
	}
	
	public void changeRoom() {
		ethanRoom.createMoney(0, true);
		ethanRoom.createLasers(0, true);
		ethanRoom.createPowerUps();
	}

	public void displayMoney() {
		int moneyLeft = ethanRoom.MONEY_CUT_OFF - ethanRoom.getCurrMoney();
		if(moneyLeft <= 0) {
			moneyLeft = 0;
		}
		System.out.println("You have collected " +  ethanRoom.getCurrMoney()
				+ " money. You still need to collect " + moneyLeft
				+ " money.");
	}

	public void displayPowerup() {
		System.out.println(" You have " + ethanRoom.getInvincibleCounter() + " chances to block the lasers.");
	}

}
