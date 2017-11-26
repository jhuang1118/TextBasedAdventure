package caveExplorer;

import jasonYJasonZMinigame.JasonYFrontend;

public class NPC {

	//fields needed to program navigation 
	public CaveRoom[][] floor;
	private int currentRow; 
	private int currentCol;
	private NPCRoom currentRoom;
	
	//fields for interaction comment
	private boolean active;
	private String activeDescription;
	private String inactiveDescription; 
	//you can add more
	
	public static int[][] possibleMoves = {{-1,0}, {0,1}, {1, 0},{0,-1}};
	
	public NPC(int row, int col, CaveRoom[][] cave) {
		this.floor = cave;
		this.currentRow = row;
		this.currentCol = col;
		this.activeDescription = "There is a person standing in the room, waiting to talk to you. Press 'e' to talk";
		this.inactiveDescription = "The person you spoke to earlier is standing here.";
		currentRoom = null; 
		active = true;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public int[] getCoords() {
		int[] coords = new int[2];
		coords[0] = currentRow;
		coords[1] = currentCol;
		return coords;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void interact() {
		CaveExplorer.print("Lets interact! Type 'bye' to stop.");
		String s = CaveExplorer.in.nextLine();
		while(!s.equalsIgnoreCase("bye"))
		{
			CaveExplorer.print("Yeah... I don't do a whole lot.");
			s = CaveExplorer.in.nextLine();
		}
		CaveExplorer.print("Later, friend!");
		active = false;
	}

	public String getDescription() {
		return activeDescription;
	}

	public String getInactiveDescription() {
		return inactiveDescription;
	}

	public String getSymbol() {
		return "P";
	}

	public void setPosition(int row, int col) {
		if((row>= 0 && row< floor.length) && (col>=0 && col < floor[row].length) && floor[row][col] instanceof NPCRoom) 
		{
			if(currentRoom != null)
			{
				currentRoom.leaveNPC();
			}
			currentRow = row;
			currentCol = col;
			currentRoom = (NPCRoom) floor[row][col];
			if( currentRoom.equals(CaveExplorer.currentRoom))
			{
				this.play();
				this.active = false;
			}
			currentRoom.enterNPC(this);
		}
		
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}

	public void autoMove() {
		if(active)
		{
			int[] move = calculateMove(currentRow, currentCol);
			int newRow = move[0];
			int newCol = move[1];
			setPosition(newRow, newCol);
		}
		
	}

	public int[] calculateMove(int userRow, int userCol) {
								//North East	South	West
		int index = (int)(Math.random() * possibleMoves.length);
		int[] newPosition = new int[2]; 
		newPosition[0] = currentRow + possibleMoves[index][0];
		newPosition[1] = currentCol + possibleMoves[index][1];
		int count = 0;
				
		while(count < 4 && (currentRoom.getDoor(index) == null || !(CaveExplorer.caves[newPosition[0]][newPosition[1]] instanceof NPCRoom)))
		{
			index = (int) (Math.random() * possibleMoves.length);
			newPosition[0] = currentRow + possibleMoves[index][0];
			newPosition[1] = currentCol + possibleMoves[index][1];
			count++;
		}
		return newPosition;
	}

}

