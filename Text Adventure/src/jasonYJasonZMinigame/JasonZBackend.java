package jasonYJasonZMinigame;

import caveExplorer.JasonZRoom;
import caveExplorer.NPCRoom;

public class JasonZBackend extends JasonZRoom {

	//set guns manually
	public final static String[][] GUNS = {};
	public final static String[][] TYPE = {};
	public int direction;
	
	public JasonZBackend(String description) {
		super(description, col, col);

		
	}
	
	public void createPolice(String type, NPCRoom spawnPoint, int quantity)
	{
		
	}

	public void attack(NPCRoom currentRoom)
	{
		
	}
	
	public void damage(String target)
	{
		
	}
}