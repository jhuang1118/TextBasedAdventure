package caveExplorer;


public class Merchant {


private int currentRow;
private int currentCol;
private NPCRoom currentRoom;

private CaveRoom[][] floor;
private boolean active;
private String activeDescription;
private String inactiveDescription;
private static int enemyHp;

public Merchant() {
	this.floor = CaveExplorer.caves;
	this.activeDescription = "I am the local merchant. We have not met yet. Tell me, what is your name?";
	this.inactiveDescription = "The merchant does not want to talk anymore.";
	this.currentCol = -3;
	this.currentRow = -5;
	currentRoom = null;
	active = true;
}
public String getSymbol() {
	return "M";
}
	
public String getDescription() {
	return activeDescription;
}

public String getInactiveDescription() {
	return inactiveDescription;
}

public void interact() {
	String[][] fightMoves = {{"Food","5"},{"Water", "10"}};
	CaveExplorer.print("What would you like to buy?");
	String s = CaveExplorer.in.nextLine();
	active = false;
}

}
