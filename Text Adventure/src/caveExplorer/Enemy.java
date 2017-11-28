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
		setFloor(CaveExplorer.caves);
		activeDescription = "A guard has arrived and is not willing to give up without a fight. Press 'e' to fight him";
		inactiveDescription = "He is a guard. Be careful!";
		currentCol = -1;
		currentRow = -1;
		setCurrentRoom(null);
		active = true;
		enemyHp = 100;
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
<<<<<<< HEAD
		String[][] fightMoves = {{"punch","true"},{"slap", "true"},{"kick","true"},{"shoot","false"}};
		CaveExplorer.print("Stop right there!" + " Press '0', '1', '2', or '3' to punch, slap, kick, or shoot, respectively");
=======
		String[][] fightMoves = {{"punch","30"},{"neck", "50"},{"kick","25"},{"block","0"}};
		CaveExplorer.print("Fack off, will ya?!" + " Press '1', '2', '3', or '4' to punch, neck, kick, or block, respectively");
>>>>>>> refs/heads/master
		String s = CaveExplorer.in.nextLine();
		
		int dmg = (int)(Math.random() * 21) + 10;
		int dmg2 = (int)(Math.random() * 21) + 10;
		
		while(s.length() == 1) {
			while(Inventory.getHP() > 0 && getHP() > 0 && isActive()) {
				int chosenMove = Integer.parseInt(s);
				while(fightMoves[chosenMove][0] == "true") {
					setHP(dmg);
					Inventory.updateHP(dmg2);
					CaveExplorer.in.nextLine();
				}
			}
			if(Inventory.getHP() == 0) {
				System.out.print("You have been killed.");
			}
			else 
				System.out.print("You have killed the enemy.");
				setActive(false);
		}
		System.out.print("Invalid input.");
		CaveExplorer.in.nextLine();
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getActiveDescription() {
		return activeDescription;
	}

	public void setActiveDescription(String activeDescription) {
		this.activeDescription = activeDescription;
	}

	public void setInactiveDescription(String inactiveDescription) {
		this.inactiveDescription = inactiveDescription;
	}

	private void setHP(int change) {
		enemyHp -= change;
	}
	
	private static int getHP() {
		return enemyHp;
	}

	public CaveRoom[][] getFloor() {
		return floor;
	}

	public void setFloor(CaveRoom[][] floor) {
		this.floor = floor;
	}

	public NPCRoom getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(NPCRoom currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	
}
