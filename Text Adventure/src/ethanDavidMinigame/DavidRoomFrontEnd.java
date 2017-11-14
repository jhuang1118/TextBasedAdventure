package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Enemy;
import caveExplorer.JasonZRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidSupport backend;
	private int moveCount;
	

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
		CaveExplorer.caves = new NPCRoom[10][10];
		CaveRoom[][] c = CaveExplorer.caves; 
		for(int row = 0; row < c.length; row++)
		{
			for(int col = 0; col < c[row].length; col ++)
			{
				c[row][col] = new NPCRoom("This has coordinates "+ row +", " + col+".");
			}
		}
		
		CaveExplorer.currentRoom = c[0][1];
		CaveExplorer.currentRoom.enter();

		c[0][1].setConnection(SOUTH, c[1][1], new Door());
		c[1][1].setConnection(EAST, c[1][2], new Door()); 
		}

	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
