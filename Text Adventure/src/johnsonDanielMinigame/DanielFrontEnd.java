package johnsonDanielMinigame;

import java.util.Scanner;

import caveExplorer.CaveExplorer;
public class DanielFrontEnd implements JohnsonSupport {

	
	private DanielSupport backend;
	
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static final void main(String[] args) {
		CaveExplorer.in = new Scanner(System.in);
		CaveExplorer.inventory = new Inventory();
		CaveExplorer.inventory.setKey(3);		
		DanielFrontEnd demo = new DanielFrontEnd();
		demo.play();
	}
	
	

	public DanielFrontEnd() {
		backend = new JohnsonBackEnd(this);
		//hint = null;
		
	}
	
	public void makeRoom() {
		
	}
	
	public String displayInventory() {
		
		
	}
	
	
	public void play(){
	    while(backend.stillPlaying()){
	        displayBoard();
	        displayScore();
	        String input = backend.getValidUserInput();
	        respondToInput(input);
	        backend.computerMove();
	        analyzeBoard();
	        updateScore();
	    }
	        printGameOverMessage(backend.victorious());
	}
}
