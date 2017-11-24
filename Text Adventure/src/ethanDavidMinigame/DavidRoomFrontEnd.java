package ethanDavidMinigame;

import java.util.Scanner;

public class DavidRoomFrontEnd implements EthanSupport {
	
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
		while(backend.stillPlaying(ethanRoom.getCurrMoney())) {
			displayBoard();
			respondToInput(in.nextLine());
		}
		printGameOverMessage(backend.victorious());
	}
	
	public void printGameOverMessage(Object victorious) {
		System.out.println("You're dead.");
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
				}
				if(dir == 1 && currentCol < 14) {
					currentCol++;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
				}
				if(dir == 2 && currentRow < 4) {
					currentRow++;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
				}
				if(dir == 3 && currentCol > 0) {
					currentCol--;
					rooms[currentRow][currentCol].setUserIn(true);
					collectTreasure();
					touchedLaser();
				}
				if((dir == 0 && currentRow == 0) || (dir == 1 && currentCol == 14) || 
						(dir == 2 && currentRow == 4) || (dir == 3 && currentCol == 0)) {
					System.out.println("You hit your head against the wall. It hurts.");
					hHit++;
					deathFromWall();
				}
	}
	
	public void deathFromWall() {
		if(hHit == 5) {
			System.out.println("You have also started bleeding.");
		}
		if(hHit == 10) {
			System.out.println("You have hit your head on the wall too much times and died from blood loss.");
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
			backend.setLost(true);
			backend.checkLose();
		}
	}

	private boolean isValid(String input) {
		return validMoves().indexOf(input) > -1 && input.length() == 1;
	}
	
	public String validMoves() {
		return "wdsae";
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

	@Override
	public void displayCheating() {
		ethanRoom.setCheating(true);
		ethanRoom.cheat();
		System.out.println(ethanRoom.getCurrMoney());
		System.out.println("You have typed in the cheat code");
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
		EthanRoomBackEnd moneyDisplay = new EthanRoomBackEnd(this);
		System.out.println("You have collected " +  ethanRoom.getCurrMoney()
				+ " money. You still need to collect " + (ethanRoom.MONEY_CUT_OFF - ethanRoom.getCurrMoney())
				+ " money");
	}

}
