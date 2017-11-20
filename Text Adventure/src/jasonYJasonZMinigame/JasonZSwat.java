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
	
	
	public JasonZSwat(NPCRoom[][] floor,int index)
	{
		//w.e the map is called.
		super(floor);
		this.index = index;
	}
	public void makeGuns(String[] info) {
		// random decider for guns;
		this.gun = new JasonZGuns(info);
	}
	
	public int[] calculateMove(int userRow, int userCol)
	{
		int dir = checkDirection();
		return possibleMoves[dir];
	}
	private int checkDirection() {
		//
		if(this.getCurrentRow() - CaveRoom.getRow() > 0)
		{
			return 0;
		}
		if(this.getCurrentRow() - CaveRoom.getRow() < 0)
		{
			return 3;
		}
		if(this.getCurrentCol() - CaveRoom.getCol() >0)
		{
			return 1;
		}
		if(this.getCurrentCol() - CaveRoom.getCol() < 0)
		{
			return 2;
		}
		return 0;
	}
}
