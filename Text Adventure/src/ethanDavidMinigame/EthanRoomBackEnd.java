package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;

public class EthanRoomBackEnd extends NPCRoom{
	public EthanRoomBackEnd(String description) {
		super(description);
		// TODO Auto-generated constructor stub
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
		/**
		 * override to create response to keys other than wdsa
		 * @param direction
		 */
		public void performAction(int direction) {
			if(direction == 4) {
				interactVault();
			}else {
				super.performAction(direction);
				
			}		
		}
		private void interactVault() {
			int n = CaveExplorer.inventory.getCash();
			CaveExplorer.inventory.setCash(n+50);
			System.out.print("\n" + " Looks like you earned some money! " + "\n");
		}
		public String getContents() {
			return "V";
		}
		
		public String getDescription() {
			return "You have reached the vault. Interact using 'e'";
		}
	}