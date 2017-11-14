package jasonYJasonZMinigame;

import caveExplorer.NPCRoom;

public class JasonZBackend implements JasonYSupport {

	private JasonZSupport frontend;
	public JasonZGuns[] guns;
	public final static String[][] TYPE = {};
	public int direction;
	

	public JasonZBackend(JasonZSupport frontend) {
		this.frontend = frontend;

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

	@Override
	public void createPolice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeGuns() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kickOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDifficulty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseKillCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSpawnTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void percentageDamageTaken() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validInput() {
		// TODO Auto-generated method stub
		
	}
}
