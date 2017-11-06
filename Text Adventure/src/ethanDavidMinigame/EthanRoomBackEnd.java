package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class EthanRoomBackEnd extends NPCRoom{
	public EthanRoomBackEnd(String description) {
		super(description);
		// TODO Auto-generated constructor stub
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
			int n = CaveExplorer.inventory.getMoney();
			CaveExplorer.inventory.setMoney(n+50);
			System.out.print("Looks like you earned some money!");
		}
		public String getContents() {
			return "V";
		}
		
		public String getDescription() {
			return "You have entered the vault. Enter using 'e'";
		}
	}
