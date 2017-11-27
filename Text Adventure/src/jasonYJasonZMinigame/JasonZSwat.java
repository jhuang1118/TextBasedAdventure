package jasonYJasonZMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

public class JasonZSwat extends NPC {

	public JasonZGuns gun;
	public int hp = 100;
	public int armor = 70;
	public int index;
	public int row;
	public int col;
	
	
	public JasonZSwat(int row, int col, NPCRoom[][] floor,int index)
	{
		//w.e the map is called.
		super(row, col, floor);
		this.index = index;
	}
	public void makeGuns(String[] info) {
		// random decider for guns;
		this.gun = new JasonZGuns(info);
	}
	
	public int[] calculateMove(int row, int col)
	{
		int dir = checkDirection();
		int[] newPosition = new int[2]; 
		newPosition[0] = row + possibleMoves[dir][0];
		newPosition[1] = col + possibleMoves[dir][1];
		return newPosition;
	}
	private int checkDirection() {
		//
		if(this.getCurrentRow() - JasonZBackend.starterRow > 0)
		{
			return 0;
		}
		if(this.getCurrentRow() - JasonZBackend.starterRow < 0)
		{
			return 2;
		}
		if(this.getCurrentCol() - JasonZBackend.starterCol >0)
		{
			return 1;
		}
		if(this.getCurrentCol() - JasonZBackend.starterCol < 0)
		{
			return 3;
		}
		return 0;
	}
	
	public String getSymbol()
	{
		return "S";
	}
}
