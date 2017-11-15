package jasonYJasonZMinigame;

import java.awt.event.KeyEvent;

import caveExplorer.NPCRoom;
import jasonYJasonZMinigame.JasonZSwat;

public class JasonZBackend implements JasonYSupport {

	private JasonZSupport frontend;
	public JasonZGuns[] guns;
	public final static String[][] TYPE = {};
	public JasonZSwat[] Swat;
	public int direction;
	public int quantity;
	public int killCount;
	public double spawnTime = 3;
	public double[] difficulty = {1, 1.1, 1.3, 1.5, 2};
	public String validinputs = "wasdf";

	public JasonZBackend(JasonZSupport frontend, int difficulty) {
		this.frontend = frontend;
		changeDifficulty(difficulty);
		Swat = new JasonZSwat[quantity];
		
	}
	
	public void createPolice()
	{
		for(int i =0; i<quantity; i++)
		{
			Swat[i] = new JasonZSwat();
			Swat[i].makeGuns();
		}
	}

	public void attack(NPCRoom currentRoom)
	{
		
	}
	
	public void damage(JasonZSwat target, double damage)
	{
		target.hp -= damage;
	}

	@Override
	public void kickOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDifficulty(int i) {
		changeGunStats(difficulty[i]);
		changeSpawnTime((spawnTime*difficulty[i]));
		
	}

	private void changeGunStats(double d) {
		double[][] gStats = JasonZGuns.TYPE;
		double value = 0;
		for(int row = 0; row< gStats.length; row++)
		{
			for(int col = 0; col< gStats[row].length; col++)
			{
				value = gStats[row][col] * d;
				gStats[row][col] = value;
				
			}
		}
	}

	@Override
	public void increaseKillCount() {
		this.killCount ++;
		
	}

	public void changeSpawnTime(double spawnTime) {
		this.spawnTime = spawnTime;
	}

	@Override
	public void validInput(String input, KeyEvent event) {
		if(event.getKeyCode() >= 38 && event.getKeyCode() <= 40)
		{
			ArrowKeys(event);
		}
		
		//if it is a valid input ....
		
	}

	private void ArrowKeys(KeyEvent event) {
		direction = event.getKeyCode()%38;
	}


}
