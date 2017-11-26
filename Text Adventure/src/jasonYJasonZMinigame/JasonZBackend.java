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
	public static int quantity = 10;
	public static int killCount;
	public static double[] difficulty = {1, 1.1, 1.3, 1.5, 2};
	public NPCRoom[][] validRooms;//north, east, south, west.
	public static NPCRoom[][] cave;
	public static int starterRow;
	public static int starterCol;
	
	public JasonZBackend(JasonZSupport frontend, int difficulty, NPCRoom[][] floor) {
		this.frontend = frontend;
		changeDifficulty(difficulty);
		Swat = new JasonZSwat[quantity];
		this.gun = new JasonZGuns(TYPE[0]);
		this.starterRow = (int) (floor.length)/2;
		this.starterCol = (int) (floor[0].length)/2;
		setValidRooms(floor);
		this.cave = floor;
	}
	 
	private void setValidRooms(NPCRoom[][] floor) {
		int r = gun.getRange();
		validRooms = new NPCRoom[4][r];
		int counter = 0;
		for(int row = starterRow; row < (starterRow + r); row ++)
		{
			if(row < floor.length)
			{ 
				validRooms[0][counter] = floor[row][starterCol];
				counter ++;
			}
			
		}
		counter = 0;
		for(int row = starterRow; row > (starterRow - r); row --)
		{
			if( row > 0)
			{
				validRooms[2][counter] = floor[row][starterCol];
				counter ++;
			}
		}
		counter = 0;
		for(int col = starterCol; col< (col + r); col ++)
		{
			if(col < floor[starterRow].length)
			{
				validRooms[1][counter] = floor[starterRow][col];
				counter ++;
			}
			
		}
		counter = 0;
		for(int col = starterCol; col > (col - r); col --)
		{
			if(col > 0)
			{
				validRooms[3][counter] = floor[starterRow][col];
				counter ++;
			}
			
		}
	}

	public static void createPolice(int row, int col)
	{
		for(int i =0; i<quantity; i++)
		{
			if(Swat[i] != null)
			{
				Swat[i] = new JasonZSwat(row,col,cave, checkNullCops());
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


	@Override
	public void validInput(String input) {
		while(!isValid(input)) {
			printValidMoves();
			input = CaveExplorer.in.nextLine();
		}
		int direction = validMoves().indexOf(input);
		if(direction < 4) {
			/*
			 * convert w,a,s,d to directions 0,3,2,1
			 */
			goToRoom(direction);
		}else {
			attack();
		}
		//if it is a valid input ....
		
	}

	private void goToRoom(int dir) {
		NPCRoom currentRoom = cave[starterRow][starterCol];
		if(currentRoom.borderingRooms[dir] != null && currentRoom.doors[dir] != null && currentRoom.doors[dir].isOpen()){
			
			currentRoom = currentRoom.borderingRooms[dir];
			cave[starterRow][starterCol].leave();
			starterRow = currentRoom.row;
			starterCol = currentRoom.col;
			CaveExplorer.currentRoom.enter();
		}
		else {
				CaveExplorer.inventory.updateMap(CaveExplorer.caves);
		}
	}

	private String validMoves() {
		return "wdsaf";
	}

	private boolean isValid(String input) {
		return validMoves().indexOf(input) != -1 && input.length() == 1;
	}

	private void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', 'd,' or 'f'.");
	}

	private void ArrowKeys(KeyEvent event) {
		direction = event.getKeyCode()%38;
		//change validRooms;
		
	}

}
