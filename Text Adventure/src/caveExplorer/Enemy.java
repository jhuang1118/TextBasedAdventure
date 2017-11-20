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
		this.activeDescription = "You have found your target, but they are not willing to give up without a fight. Press 'e' to fight her";
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
		String[][] fightMoves = {{"punch","30"},{"neck", "50"},{"kick","25"},{"block","0"}};
		CaveExplorer.print("Fack off, will ya?!" + " Press '1', '2', '3', or '4' to punch, neck, kick, or block, respectively");
		String s = CaveExplorer.in.nextLine();
		while(Inventory.getHP() > 0 && Enemy.getHP() > 0) {
			
		}
		active = false;
	}

	private static int getHP() {
		return enemyHp;
	}
	
}
