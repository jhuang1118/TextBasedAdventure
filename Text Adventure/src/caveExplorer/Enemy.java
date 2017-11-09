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
		String[][] fightMoves = {{"punch","true"},{"slap", "true"},{"kick","true"},{"shoot","false"}};
		CaveExplorer.print("Stop right there!" + " Press '0', '1', '2', or '3' to punch, slap, kick, or shoot, respectively");
		String s = CaveExplorer.in.nextLine();
		
		int dmg = (int)(Math.random() * 21) + 10;
		while(Inventory.getHP() > 0 && Enemy.getHP() > 0) {
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
