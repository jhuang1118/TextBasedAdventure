package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;

public class DavidRoomFrontEnd implements EthanSupport {
	
	public static CaveRoom[][] caves;
	
	private DavidSupport backend;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static void main(String[] args) {
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play(); 
	}
	
	private void play() {
		while(backend.stillPlaying()) {
			displayBoard();
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
		// TODO Auto-generated method stub
		
	}

	public static void displayBoard() {
		//spawn cash piles (randomly)
		//spawn obstacles (randomly)
		//if they choose the same random location choose somewhere else
				CaveExplorer.caves = new CaveRoom[5][5];
				CaveRoom[][] c = CaveExplorer.caves;
				for(int row = 0; row < c.length; row ++) {
					for(int col = 0; col < c[row].length; col++) {
						c[row][col] = new CaveRoom("This cave has coordinates "+row+", "+col);
					}
				}
				
				//4.set starting room
				CaveExplorer.currentRoom = c[0][1];
				CaveExplorer.currentRoom.enter();
				c[0][1].setConnection(SOUTH, c[1][1], new Door());
				c[1][1].setConnection(EAST, c[1][2], new Door()); 
		caveExplorer.Inventory.updateMap(c);
	}

	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
