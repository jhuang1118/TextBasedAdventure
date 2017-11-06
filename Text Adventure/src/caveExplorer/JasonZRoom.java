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
	
	public JasonZRoom( String description) {
		super(description);
		this.description = description;
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
