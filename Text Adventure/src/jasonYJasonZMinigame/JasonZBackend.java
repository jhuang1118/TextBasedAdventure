package jasonYJasonZMinigame;

import java.awt.event.KeyEvent;

import caveExplorer.CaveExplorer;
import caveExplorer.Merchant;
import caveExplorer.NPCRoom;
import jasonYJasonZMinigame.JasonZSwat;

public class JasonZBackend implements JasonYSupport {

	private JasonZSupport frontend;
	public static JasonZGuns gun;
	public final static String[][] TYPE = {{"M16", "0"}, {"AR15","0"},{"Beretta 9000", "1"}, {"Beretta Cheetah", "1"}, {"Calico M960", "2"},{"Jatimatic","2"}};
	public static JasonZSwat[] Swat;
	public static int quantity = 10;
	public static int killCount;
	public static double[] difficulty = {1, 1.1, 1.3, 1.5, 2};
	public JasonZSwat[] validTarget;
	public static NPCRoom[][] cave;
	public static int starterRow;
	public static int starterCol;
	public NPCRoom currentRoom;
	
	public NPCRoom getCurrentRoom() {
		return currentRoom;
	}

	public JasonZBackend(JasonZSupport frontend, int difficulty, NPCRoom[][] floor) {
		this.frontend = frontend;
		changeDifficulty(difficulty);
		Swat = new JasonZSwat[quantity];
		this.gun = new JasonZGuns(TYPE[0]);
		JasonZBackend.starterRow = (int) Math.ceil(floor.length/2);
		JasonZBackend.starterCol = (int) Math.ceil(floor[starterRow].length/2);
		this.cave = floor;
		currentRoom = floor[starterRow][starterCol];
	}
	 
	void setValidTarget(NPCRoom room) {
		int r = gun.getRange();
		validTarget = new JasonZSwat[r*4];
		int startR = starterRow -r;
		int startC = starterCol-r;
		int finalR = starterRow +r;
		int finalC = starterCol +r;
		if(startR < 0)
		{
			startR = 0;
		}
		if( startC <0)
		{
			startC = 0;
		}
		if( finalR > cave.length)
		{
			finalR = cave.length;
		}
		if(finalC > cave[0].length)
		{
			finalC = cave[0].length;
		}
		int counter =0;
		for(int row = startR; row < finalR; row ++)
		{
			NPCRoom c = cave[row][starterCol];
			if(c.getNpc() != null)
			{
				if(!(c.getNpc() instanceof Merchant)) {
				validTarget[counter] = (JasonZSwat) c.getNpc();
				counter++;
				}
			}
		}
		for(int col = startC; col< finalC; col ++)
		{
			NPCRoom c = cave[starterRow][col];
			if(c.getNpc() != null)
			{
				validTarget[counter] = (JasonZSwat) c.getNpc();
				counter++;
			}
		}
	}

	public static void createPolice(int row, int col)
	{
		for(int i =0; i<quantity; i++)
		{
			if(Swat[i] == null)
			{
				Swat[i] = new JasonZSwat(row,col,cave, checkNullCops());
				Swat[i].makeGuns(TYPE[(int) (Math.random()*TYPE.length)]);
				Swat[i].setPosition(row, col);
				break;
			}
		}
	}
	
	public void damagePlayers(int userHP, JasonZSwat damager)
	{
		userHP -= damager.gun.trueDamage();
		JasonYFrontend.hp = userHP;
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
		if(target != null) damage(target, gun.trueDamage());
		else System.out.println("You fire at air to show your dominance.");
	}

	public JasonZSwat firstPersonDir()
	{
		if(validTarget != null)
		{
			for( JasonZSwat s: validTarget){
				if(s != null){
					return s;
				}
			}	
		}
		return null;
		
	}
	public void damage(JasonZSwat target, double damage)
	{
		if(target.armor > 0)
		{
			target.armor -= damage;
		}
		else target.hp -= damage;
		System.out.println("That cop took " + damage + ". He has ");
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
			
			currentRoom = (NPCRoom) currentRoom.borderingRooms[dir];
			cave[starterRow][starterCol].leave();
			starterRow = currentRoom.miniRow;
			starterCol = currentRoom.miniCol;
			currentRoom.enter();
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

	public boolean canFire(JasonZSwat swat) {
		if( starterRow == swat.currentRow || swat.currentCol == starterCol)
		{
			return true; 
		}
		return false;
	}
}
