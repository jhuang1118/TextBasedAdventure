package ethanDavidMinigame;

import java.util.Scanner;

import caveExplorer.CaveExplorer;
import caveExplorer.DavidCar;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidEthanRoom[][] rooms;
	private int currentRow;
	private int currentCol;
	
	private int hHit;
	
	public static DavidEthanRoom currentRoom;
	public EthanRoomBackEnd ethanRoom;
	
	private DavidSupport backend;
	private DavidCar car;
	
	public static Scanner in;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play(); 
	}
	
	private void play() {
		while(backend.stillPlaying(ethanRoom.getCurrMoney())) {
			displayBoard();
			respondToInput(in.nextLine());
		}
		printGameOverMessage(backend.victorious());
	}
	
	public void printGameOverMessage(Object victorious) {
		if(ethanRoom.getCurrMoney() >= 70000) {
			System.out.println("You collected enough money. Now get to the car!");
			//method for returning to the main map
			CaveExplorer.startExploring();
			car.setPosition(9,20);
		}else {
			System.out.println("You're dead.");
		}
	}

	public void respondToInput(String input) {
		while(!isValid(input)) {
			System.out.println("You can't do that. You must type 'w,a,s, or d.'");
			input = in.nextLine();
		}
		if(input.equals("e")) {
			displayCheating();
		}
		int direction = validMoves().indexOf(input);
		goToRoom(direction);
		rooms[currentRow][currentCol].setUserIn(false);
	}
	
	private void goToRoom(int dir) {
				if(dir == 0 && currentRow > 0) {
					currentRow--;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
					collectPowerup();
				}
				if(dir == 1 && currentCol < rooms[0].length-1) {
					currentCol++;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
					collectPowerup();
				}
				if(dir == 2 && currentRow < rooms.length-1) {
					currentRow++;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
					collectPowerup();
				}
				if(dir == 3 && currentCol > 0) {
					currentCol--;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
					collectPowerup();
				}
				if((dir == 0 && currentRow == 0) || (dir == 1 && currentCol == 14) || 
						(dir == 2 && currentRow == 4) || (dir == 3 && currentCol == 0)) {
					System.out.println("You hit your head against the wall. It hurts.");
					hHit++;
					deathFromWall();
				}
	}
	
	private void collectPowerup() {
		if(rooms[currentRow][currentCol].isContainsPowerup()) {
			ethanRoom.addInvincibility(rooms, currentRow, currentCol);
		}
	}

	public String validMoves() {
		return "wdsae";
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
			//System.out.println("went out of statement."); debug
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
		//car = new DavidCar();
		for(DavidEthanRoom[] row: rooms) {
			for(int col = 0; col < row.length; col++) {
				row[col] = new DavidEthanRoom();
			}
		}
		changeRoom();
	}

	@Override
	public void displayCheating() {
		ethanRoom.setCheating(true);
		ethanRoom.cheat();
	}

	@Override
	public DavidEthanRoom[][] getRooms() {
		// TODO Auto-generated method stub
		return rooms;
	}
	
	public void changeRoom() {
		ethanRoom.createMoney(0, true);
		ethanRoom.createLasers(0, true);
		ethanRoom.createPowerUps();
	}

	@Override
	public void displayMoney() {
		int moneyLeft = ethanRoom.MONEY_CUT_OFF - ethanRoom.getCurrMoney();
		if(moneyLeft <= 0) {
			moneyLeft = 0;
		}
		System.out.println("You have collected " +  ethanRoom.getCurrMoney()
				+ " money. You still need to collect " + moneyLeft
				+ " money.");
	}

	@Override
	public void displayPowerup() {
		System.out.println(" You have " + ethanRoom.getInvincibleCounter() + " chances to block the lasers.");
		
	}

}
