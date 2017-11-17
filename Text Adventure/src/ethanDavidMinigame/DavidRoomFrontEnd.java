package ethanDavidMinigame;

import java.util.Scanner;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidEthanRoom[][] rooms;
	private int currentRow;
	private int currentCol;
	
	long startTime = System.currentTimeMillis();
	long elapsedTime = System.currentTimeMillis() - startTime;
	long elapsedSeconds = elapsedTime/1000;
	
	private DavidSupport backend;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static Scanner in;
	
	public static void main(String[] args) {
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play(); 
	}
	
	private void play() {
		displayBoard();
		while(backend.stillPlaying()) {
			String input = backend.getValidUserInput();
			respondToInput(input);
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
			System.out.println("You can't do that. You must type 'w,a,s, or d.' You still have " + (40 - elapsedSeconds) + " seconds left.");
			input = in.nextLine();
		}
		int direction = validMoves().indexOf(input);
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
	
	public void updateBoard() {
		
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

}
