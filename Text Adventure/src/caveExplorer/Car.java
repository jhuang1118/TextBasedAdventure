package caveExplorer;

public class Car extends NPC{

	//fields needed to program navigation 
	private CaveRoom[][] floor;
	private int currentRow; 
	private int currentCol;
	private NPCRoom currentRoom; 
	
	//fields for interaction
	private boolean active;
	private String activeDescription;
	//you can add more
	
	
	public Car() {
		this.floor = CaveExplorer.caves;
		this.activeDescription = "You have reached your car! Quickly press 'e' to enter the get-away car!";
		this.currentCol = -1; 
		this.currentRow = -1;
		currentRoom = null; 
		active = true;
	}
	

	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public void interact() {
		CaveExplorer.print("You're in! Quickly get to the safe point!");
		active = false;
	}

	public String getDescription() {
		return activeDescription;
	}


	public String getSymbol() {
		return "C";
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
			currentRoom.enterNPC(this);
		}
		
	}

	public void autoMove() {
		if(active)
		{
			int[] move = calculateMove();
			int newRow = move[0];
			int newCol = move[1];
			setPosition(newRow, newCol);
		}
		
	}
	
	private int[] calculateMove() {
		int[][] possibleMoves = {{0,-1}};
		int[] newPosition = new int[1];
		newPosition[0] = currentRow + possibleMoves[0][0];
		return newPosition;
	}
	
	private boolean enteredCar() {
		return false;
	}

}


