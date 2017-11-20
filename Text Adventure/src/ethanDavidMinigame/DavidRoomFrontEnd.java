package ethanDavidMinigame;

import java.util.Scanner;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidEthanRoom[][] rooms;
	private int currentRow;
	private int currentCol;
	
	private DavidSupport backend;
	
	public static Scanner in;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play(); 
	}
	
	private void play() {
		displayBoard();
		displayMoney();
		while(backend.stillPlaying()) {
			respondToInput(in.nextLine());
			backend.startTimer();
		}
		printGameOverMessage(backend.victorious());
	}
	
	public void printGameOverMessage(Object victorious) {
		System.out.println("You're dead.");
	}

	public void displayTimer() {
		System.out.println("You have 40 seconds to collect money!");
	}
	
	public void respondToInput(String input) {
		while(!isValid(input)) {
			System.out.println("You can't do that. You must type 'w,a,s, or d.' You still have " +  " seconds left.");
			input = in.nextLine();
		}
		int direction = validMoves().indexOf(input);
		System.out.println(direction);
		goToRoom(direction);
		displayBoard();
		System.out.println("You are now at " + rooms[currentRow][currentCol]);
		displayMoney();
	}
	
	private void goToRoom(int dir) {
		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[row].length; col++) {
				if(dir == 0 && currentRow > rooms.length) {
					currentRow--;
				}
				if(dir == 1 && currentCol > rooms[row].length) {
					currentCol++;
				}
				if(dir == 2 && currentRow < rooms.length) {
					currentRow++;
				}
				if(dir == 3 && currentCol < rooms[row].length) {
					currentCol--;
				}
			}
		}	
	}

	private boolean isValid(String input) {
		return validMoves().indexOf(input) > -1 && input.length() == 1;
	}
	
	public String validMoves() {
		return "wdsa";
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
		System.out.println(" ");
	}

	@Override
	public DavidEthanRoom[][] getRooms() {
		// TODO Auto-generated method stub
		return rooms;
	}
	
	public void changeRoom() {
		EthanRoomBackEnd back = new EthanRoomBackEnd(this);
		back.createMoney();
	}

	@Override
	public void displayMoney() {
		System.out.println("You have collected " + currMoney 
				+ " money. You still need to collect " + (MONEY_CUT_OFF - currMoney) + " money");
	}

}
