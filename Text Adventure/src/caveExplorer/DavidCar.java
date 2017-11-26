package caveExplorer;

import ethanDavidMinigame.EthanRoomBackEnd;

public class DavidCar extends NPCRoom {

	public EthanRoomBackEnd ethanRoom;

	public DavidCar(String description) {
		super(description);
	}
	
	public String getContents() {
		return "C";
	}
	
	public String getDescription() {
		if(canEnter()) {
			return "Great you got the cash! Quickly, press 'e' so we can get out of here!";
		}
		return "You can't leave yet remember? We came to this town for one thing\nand one thing only. Go get "
		+ "the money!";
	} 
	
	public boolean canEnter() {
		if(ethanRoom.getCurrMoney() != ethanRoom.MONEY_CUT_OFF) {
			return false;
		}
		else {
			return true;
		}
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
		if(input.equals("e")) {
			performAction(4);
		}else {
			int direction = "wdsa".indexOf(input);
			goToRoom(direction);
		}

	}
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move " + 
				"or you can press 'e' to interact.");
			
	}
	public String validMoves() {
		return "wdsae";
	}
	public void performAction(int direction) {
		if(direction == 4) {
			canEnter();
			//end the game
		}else {
			super.performAction(direction);
		}		
	}

}
