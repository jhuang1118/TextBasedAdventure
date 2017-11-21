package jasonYJasonZMinigame;

import java.awt.event.KeyEvent;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;
import jasonYJasonZMinigame.JasonZSwat;

public class JasonZBackend implements JasonYSupport {

	private JasonZSupport frontend;
	public static JasonZGuns gun;
	public final static String[][] TYPE = {{"M16", "0"}, {"AR15","0"},{"Beretta 9000", "1"}, {"Beretta Cheetah", "1"}, {"Calico M960", "2"},{"Jatimatic","2"}};
	public static JasonZSwat[] Swat;
	public int direction = 0;
	public static int quantity;
	public static int killCount;
	public double spawnTime = 3;
	public static double[] difficulty = {1, 1.1, 1.3, 1.5, 2};
	public NPCRoom[][] validRooms;//north, east, south, west.
	public static NPCRoom[][] cave;
	
	public JasonZBackend(JasonZSupport frontend, int difficulty, NPCRoom[][] floor) {
		this.frontend = frontend;
		changeDifficulty(difficulty);
		Swat = new JasonZSwat[quantity];
		this.gun = new JasonZGuns(TYPE[0]);
		setValidRooms(floor);
		cave = floor;
	}
	 
	private void setValidRooms(NPCRoom[][] floor) {
		int currentRow = CaveExplorer.currentRoom.row;
		int currentCol = CaveExplorer.currentRoom.col;
		int range = gun.getRange();
		validRooms = new NPCRoom[4][gun.getRange()];
		int counter = 0;
		for(int row = currentRow; currentRow < (currentRow + range); row ++)
		{
			if(row < floor.length)
			{
				validRooms[0][counter] = floor[row][currentCol];
				counter ++;
			}
			
		}
		counter = 0;
		for(int row = currentRow; currentRow > (currentRow - range); row --)
		{
			if( row > 0)
			{
				validRooms[2][counter] = floor[row][currentCol];
				counter ++;
			}
		}
		counter = 0;
		for(int col = currentCol; col< (col + range); col ++)
		{
			if(col < floor[currentRow].length)
			{
				validRooms[1][counter] = floor[currentRow][col];
				counter ++;
			}
			
		}
		counter = 0;
		for(int col = currentCol; col > (col - range); col --)
		{
			if(col > 0)
			{
				validRooms[3][counter] = floor[currentRow][col];
				counter ++;
			}
			
		}
	}

	public static void createPolice(int row, int col)
	{
		for(int i =0; i<quantity; i++)
		{
			if(Swat[i] == null)
			{
				Swat[i] = new JasonZSwat(cave, checkNullCops());
				Swat[i].makeGuns(TYPE[(int) (Math.random()*TYPE.length)]);
				Swat[i].setPosition(row, col);
				break;
			}
		}
	}
	
	public int damagePlayers(int userHP, JasonZSwat damager)
	{
		return userHP -= damager.gun.trueDamage();
	}

	private static int checkNullCops() {
		for(int i = 0; i<Swat.length; i++)
		{
			if(Swat[i] == null)
			{
				return i;
			}
		}
		return 0;
	}

	public void attack()
	{
		JasonZSwat target = firstPersonDir();
		damage(target, gun.trueDamage());
	}

	public JasonZSwat firstPersonDir()
	{
		for(int i = 0; i<validRooms[direction].length; i++)
		{
			if(validRooms[direction][i].containsNPC())
			{
				return (JasonZSwat) validRooms[direction][i].getNpc();
			}
		}
		return null;
		
	}
	public void damage(JasonZSwat target, double damage)
	{
		target.hp -= damage;
	}

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
		JasonZBackend.killCount ++;
		
	}

	public void changeSpawnTime(double spawnTime) {
		this.spawnTime = spawnTime;
	}

	@Override
	public void validInput(String input) {
		if( input.equals("f"))
		{
			attack();
		}
		else
		{
			CaveExplorer.currentRoom.interpretInput(input);
		}
		//if it is a valid input ....
		
	}

	private void ArrowKeys(KeyEvent event) {
		direction = event.getKeyCode()%38;
		//change validRooms;
		
	}


}
