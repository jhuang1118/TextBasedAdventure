package caveExplorer;

public class JasonJason extends NPCRoom {
	
	private String description; 
	private String directions; //which doors
	private String contents; // a symbol to show room you are in
	private String defaultContents; // what is in the room when your aren't in the room
	private int row = 0;
	private int col = 0;
	
	
	private CaveRoom[] borderingRooms;
	private Door[] doors; // valid 
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public JasonJason( String description, int row, int col) {
		super(description);
		this.description = description;
		setDefaultContents("S");
		borderingRooms = new NPCRoom[4];
		doors = new Door[4];
		this.row = row;
		this.col = col;
		setDirections();
	}

	/**
	 * for every Door in doors[] that is not null, 
	 * this method appends a String describing the door and 
	 * where it is ie: There is a (passage to the north);
	 */
	public void setDirections() {
		directions = "";
		boolean doorFound = true;
		for(int i =0; i< this.doors.length; i++)
		{
			if(this.doors[i] != null)
			{
				doorFound = false;
				this.directions += "There is a "+ this.doors[i].getDescription()+" to "+ toDirection(i)+". "+this.doors[i].getDetails(); 
			}
		}
	}
	
	public Door getDoor(int direction) {
		return doors[direction];
	}

	/**
	 * converts an int to a direction:
	 * 0 = north
	 * 1 = east
	 * @param dir
	 * @return
	 */
	public static String toDirection(int dir)
	{
		String[] directions = {"the North", "the East", "the South", "the West"};
		//NOTE: When i sat "no long if-else stat. 
		// this is how you should be thinking
		return directions[dir];
	}
	
	public void enter()
	{
		this.contents = "X";
	}
	
	public void leave()
	{
		this.contents = defaultContents;
	}
	
	public void setConnection(int direction, CaveRoom anotherRoom, Door door)
	{
		addRoom(direction, anotherRoom, door);
		anotherRoom.addRoom(oppositeDirection(direction), this, door);
	}
	
	public void addRoom(int dir, CaveRoom caveRoom, Door door) {
		borderingRooms[dir] = caveRoom;
		doors[dir] = door;
		setDirections(); //update Directions
	}
	
	public void interpretInput(String input)
	{
		
		while(!isValid(input))
		{
			printValidMoves();
			
			input = CaveExplorer.in.nextLine();
		}
		int direction = validMoves().indexOf(input);
		if(direction<4)
		{
			goToRoom(direction);
		}
		else
		{
			performAction(direction);
		}
		
	}
	
	/**
	 * Override to create response to keys other than wdsa
	 * @param direction
	 */
	public void performAction(int direction) {
		CaveExplorer.print("that key does nothing.");
		
	}

	/**
	 * Override to change description of possible moves
	 */
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd'");
	}

	public boolean isValid(String input) {
		return validMoves().indexOf(input.toLowerCase()) > -1 && input.length() == 1;
	}
	
	public String validMoves()
	{
		return "wdsaf"; 
	}

	public static int oppositeDirection(int dir) {
		return (dir+2)%4;
	}

	public String getDescription() {
		return description;
	}

	public void setDefaultContents(String defaultContents) {
		this.defaultContents = defaultContents;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
