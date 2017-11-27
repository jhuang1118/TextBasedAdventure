package caveExplorer;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;
import ethanDavidMinigame.DavidRoomFrontEnd;

public class VaultRoom extends NPCRoom {
	
	boolean entered;

	public VaultRoom(String description) {
		super(description);
		entered = false;
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
		if(entered) {
			System.out.print("It's too dangerous to enter again.\n");
		}else {
			if(CaveExplorer.inventory.isID() || randNum == 3 && !entered) {
				System.out.print("\n You manage to open the vault and enter.\n");
				entered = true;
				DavidRoomFrontEnd.main(null);
				return true;
			}else {
				System.out.print("\n It's impossible to open the vault with your bare hands. Maybe if you had the manager's key or if you kept trying...\n");
				return false;
			}
		}
		return false;
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
