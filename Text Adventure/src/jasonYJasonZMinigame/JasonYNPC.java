package jasonYJasonZMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class JasonYNPC extends NPC{
	
	//fields needed to program navigation 
	private CaveRoom[][] floor;
	private int currentRow; 
	private int currentCol;
	private NPCRoom currentRoom; 
	private int hp;
	private int armor;
	
	//fields for interaction commment
	private boolean alive;
	private String aliveDescription;
	private String deadDescription; 
	//you can add more
	
	public JasonYNPC() {
		this.floor = CaveExplorer.caves;
		this.aliveDescription = "There's a cop coming at you brother!";
		this.deadDescription = "The cop's dead. Keep moving brother!";
		this.currentCol = -1; 
		this.currentRow = -1;
		currentRoom = null; 
		alive = true;
		hp = 100;
		armor = 100;
	}
	
	public String getDescription() {
		return aliveDescription;
	}

	public String getInactiveDescription() {
		return deadDescription;
	}
	
	public String getSymbol() {
		return "C";
	}
}
