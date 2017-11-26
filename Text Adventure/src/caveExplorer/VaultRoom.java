package caveExplorer;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;
import ethanDavidMinigame.DavidRoomFrontEnd;

public class VaultRoom extends NPCRoom {
	
	private DavidRoomFrontEnd DavidRoom;

	public VaultRoom(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	public String getContents() {
		return "V";
	}
	public String getDescription() {
		return "You have reached the vault. Interact using 'e'";
	}
	public boolean canEnter() {
		int randNum = (int)(Math.random() *5);
		if(CaveExplorer.inventory.isID() || randNum == 3) {
			System.out.print("\n You open the vault and enter.");
			//DavidRoom;
			return true;
		}else {
			System.out.print("\n It's impossible to open the vault with your bare hands. Maybe if you had the manager's key or if you kept trying...\n");
			return false;
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
		}else {
			super.performAction(direction);
		}		
	}
}
