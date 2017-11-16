package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;
import caveExplorer.Inventory;

public class DavidRoomFrontEnd implements EthanSupport {
	
	public static CaveRoom[][] caves;
	
	private DavidSupport backend;
	private int moveCount;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	

	public static void main(String[] args) {
		/*DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play(); */
		displayBoard();

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
		System.out.println("");
	}

	public void respondToInput(String input) {
		// TODO Auto-generated method stub
		
	}

	public static void displayBoard() {
		//spawn cash piles (randomly)
		//spawn obstacles (randomly)
		//if they choose the same random location choose somewhere else
				CaveExplorer.caves = new VaultRoom[5][5];
				CaveRoom[][] c = CaveExplorer.caves;
				for(int row =0; row < c.length; row ++) {
					for(int col = 0; col < c[row].length; col++) {
						c[row][col] = new VaultRoom("This cave has coordinates "+row+", "+col);
					}
				}
				
				//4.set starting room
				CaveExplorer.currentRoom = c[0][1];
				CaveExplorer.currentRoom.enter();
				setConnectionForAll();
	}
	
	public static void setConnectionForAll() {
		CaveRoom[][] c = CaveExplorer.caves;
		for(int row = 0; row< c.length-1; row++)
		{
			for(int col = 0; col < c.length-1; col++)
			{
				c[row][col].setConnection(SOUTH, c[row+1][col], new Door());
				c[row][col].setConnection(EAST, c[row][col+1], new Door());
			}
		} 
		
		for(int i = 0; i<c[c.length-1].length-1; i++)
		{
			c[c.length-1][i].setConnection(EAST, c[c.length-1][i+1], new Door());
		}
		
		for(int i = 0; i< c.length-1; i++)
		{
			c[i][c[i].length-1].setConnection(SOUTH, c[i][c[i].length-1], new Door());
		}
	} 


	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
