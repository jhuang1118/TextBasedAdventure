package jasonYJasonZMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;
import caveExplorer.NPCRoom;

public class JasonYRoom extends NPCRoom{

	private String description; 
	private String directions; //which doors
	private String contents; // a symbol to show room you are in
	private String defaultContents; // what is in the room when your aren't in the room
	
	private CaveRoom[] borderingRooms;
	private Door[] doors; // valid 
	private CaveRoom[][] shootMap;
	
	private static final int ROOM_SIZE = 10;
	
	public JasonYRoom(String description, int row, int col) {
		super(description);
		this.description = description;
		borderingRooms = new NPCRoom[4];
		this.doors = new Door[4];
		createMap(row,col);
		setDirections();
		spawnCops();
	}
	
	private void spawnCops() {
		int randomNum = (int)(Math.random()*10);
		
	}



	public void createMap(int row, int col) {
		CaveRoom[][] c = CaveExplorer.caves;
		int leftCorner = 0;
		int rightCorner = c[row].length;
		int topCorner = 0;
		int bottomCorner = c.length;
		
		if(col > ROOM_SIZE){
			leftCorner = col - ROOM_SIZE;
		}
		
		else if(col < c.length - ROOM_SIZE){
			rightCorner = col + ROOM_SIZE;
		}
		
		if(row > ROOM_SIZE){
			topCorner = row - ROOM_SIZE;
		}
		
		else if(row < c.length - ROOM_SIZE)
		{
			bottomCorner = row + ROOM_SIZE;
		}
		
		shootMap = new CaveRoom[rightCorner - leftCorner][bottomCorner - topCorner];
		int currentRow = 0; 
		int currentCol = 0;
		for(int i = topCorner; i < bottomCorner; i ++)
		{
			for(int j = leftCorner; j <rightCorner; j++)
			{
				shootMap[currentRow][currentCol] = c[i][j];
				currentCol ++;
			}
			currentCol = 0;
			currentRow ++;
		}
		
		CaveExplorer.inventory.updateMap(shootMap);
	}
	
	/**
	 * Override to create response to keys other than wdsa
	 * @param direction
	 */
	public void performAction(int direction) {
		if (direction == 4) {
			shoot();
		}
		else {
			CaveExplorer.print("Do somethin'! You can shoot or move around. Come on bro!");
			printValidMoves();
		}
	}
	
	private void shoot() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Override to change description of possible moves
	 */
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', 'd', or 'j'. 'j' is to shoot.");
	}
	
	public String validMoves()
	{
		return "wdsaj"; 
	}
	
	public String getDescription() {
		return description;
	}

	public void setDefaultContents(String defaultContents) {
		this.defaultContents = defaultContents;
	}
}
