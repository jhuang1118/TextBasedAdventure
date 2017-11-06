package caveExplorer;

public class JasonZRoom extends NPCRoom {
	
	private String description; 
	private String directions; //which doors
	private String contents = "S"; // a symbol to show room you are in
	private String defaultContents; // what is in the room when your aren't in the room
	private int row = 0;
	private int col = 0;
	
	
	private CaveRoom[] borderingRooms;
	private Door[] doors; // valid 
	private CaveRoom[][] newMap;
	
	public JasonZRoom( String description, int i, int j) {
		super(description);
		this.description = description;
		this.row = i; 
		this.col = j;
		borderingRooms = new NPCRoom[4];
		this.doors = new Door[4];
		setDirections();
	}
	/**
	 * Override to create response to keys other than wdsa
	 * @param direction
	 */
	public void createNewMap()
	{
		CaveRoom[][] c = CaveExplorer.caves;
		int leftMargin = 0;
		int rightMargin = c[row].length;
		int topMargin = 0;
		int bottomMargin = c.length;
		
		if(col > 10)
		{
			leftMargin = col -10;
		}
		if(col < c.length-10)
		{
			rightMargin = col + 10;
		}
		if(row > 10)
		{
			topMargin = row -10;
		}
		if(row < c.length - 10)
		{
			bottomMargin = row +10;
		}
		
		newMap = new CaveRoom[rightMargin -leftMargin][bottomMargin - topMargin];
		int bRow = 0; 
		int bCol = 0;
		for(int row = topMargin; row < bottomMargin; row ++)
		{
			for(int col = leftMargin; col<rightMargin; col++)
			{
				newMap[bRow][bCol] = c[row][col];
				bCol ++;
			}
			bRow ++;
		}
	}
	public void performAction(int direction) {
		if(direction == 4)
		{
			fire();
		}
		else 
		{
			super.performAction(direction);
		}
		
	}

	private void fire() {
		
	}
	/**
	 * Override to change description of possible moves
	 */
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', 'd', 'f'");
	}

	public String validMoves()
	{
		return "wdsaf"; 
	}

	
	public String getDescription() {
		return description;
	}


	public String getContents() {
		return contents;
	}

	

}
