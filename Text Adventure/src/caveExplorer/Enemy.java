package caveExplorer;

public class Enemy extends NPC {
	
	private int currentRow;
	private int currentCol;
	private NPCRoom currentRoom;
	
	private CaveRoom[][] floor;
	private boolean active;
	private String activeDescription;
	private String inactiveDescription;
	private static int enemyHp;
	
	public Enemy() {
		this.floor = CaveExplorer.caves;
		this.activeDescription = "A guard has arrived and is not willing to give up without a fight. Press 'e' to fight him";
		this.inactiveDescription = "Your target has been knocked out; you can press 'g' to kidnap her";
		this.currentCol = -1;
		this.currentRow = -1;
		currentRoom = null;
		active = true;
	}
	
	public String getDescription() {
		return activeDescription;
	}

	public String getInactiveDescription() {
		return inactiveDescription;
	}
	public String getSymbol() {
		return "T";
	}
	
	public void interact() {
		CaveExplorer.print("Fack off, will ya?!" + " Press '1', '2', '3', or '4' to punch, neck, kick, or block, respectively");
		String[][] fightMoves = {{"punch","true"},{"slap", "true"},{"kick","true"},{"shoot","false"}};
		String s = CaveExplorer.in.nextLine();
		
		int dmg = (int)(Math.random() * 21) + 10;
		while(CaveExplorer.inventory.getHP() > 0 && Enemy.getHP() > 0) {
			int chosenMove = Integer.parseInt(s);
			if(fightMoves[chosenMove][0] == "true") {
				
			}
		}
		active = false;
	}

	private void setHP() {
		enemyHp = 100;
	}
	
	private static int getHP() {
		return enemyHp;
	}
	
	
}
