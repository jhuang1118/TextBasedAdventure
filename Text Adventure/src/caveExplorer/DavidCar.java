package caveExplorer;

import ethanDavidMinigame.EthanRoomBackEnd;

public class DavidCar extends NPCRoom {

	public EthanRoomBackEnd ethanRoom;

	public DavidCar(String description,int row, int col) {
		super(description, row, col);
	}
	
	public String getContents() {
		return "C";
	}
	
	public String getDescription() {
		return "Press 'y' to leave!";
	} 
	
	public boolean canEnter() {
		if(CaveExplorer.inventory.getCash() < 65000) {
			System.err.println("You can't leave without getting the money from the bank!");
			return false;
		}
			return true;
	}
	
	public boolean isValid(String input) {
		return validMoves().indexOf(input.toLowerCase()) > -1 && input.length() == 1;
	}
	
	public void interpretInput(String input)
	{
		while(!isValid(input))
		{
			printValidMoves();
			input = CaveExplorer.in.nextLine();
		}
		if(input.equals("y")) {
			performAction(4);
		}else {
			int direction = "wdsa".indexOf(input);
			goToRoom(direction);
		}

	}
	
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move " + 
				"or you can press 'y' to interact.");
			
	}
	
	public String validMoves() {
		return "wdsay";
	}
	
	public void performAction(int direction) {
		if(direction == 4 && canEnter()) {
			System.out.println("You successfully robbed a bank.");
			CaveExplorer.playing = false;
		}else {
			super.performAction(direction);
		}		
	}

}
