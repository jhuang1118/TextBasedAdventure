package caveExplorer;

import jasonYJasonZMinigame.JasonYFrontend;

public class CaveRoom {
	
	public static int[] getCoordinates(NPCRoom c) {
		int[] coords = new int[2];
		coords[0] = c.row;
		coords[1] = c.col;
		return coords;
	}
	public static int getRow(CaveRoom c) {
		return c.row;
	}

	public static void setRow(CaveRoom c, int row) {
		c.row = row;
	}

	public static int getCol(CaveRoom c) {
		return c.col;
	}

	public static void setCol(CaveRoom c, int col) {
		c.col = col;
	}
	 
	public static int getRow(NPCRoom c) {
		return c.row;
	}

	public static void setRow(NPCRoom c, int row) {
		c.row = row;
	}

	public static int getCol(NPCRoom c) {
		return c.col;
	}

	public static void setCol(NPCRoom c, int col) {
		c.col = col;
	}
	
	private String description;
	private String directions;//tells you which door can be used
	private String contents;//a symbol showing you what is in the room
		//...('X' when you are in the room)
	private String defaultContents;//what is in the room when you aren't in the room 
	
	public CaveRoom[] borderingRooms;
	public Door[] doors;
	public int row;
	public int col;
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public CaveRoom(String description, int row, int col) {
		this.row = row; 
		this.col = col;
		this.description = description;
		setDefaultContents(" ");
		contents = defaultContents;
		//NOTE: Arrays are instantiated with 'null' values
		borderingRooms = new CaveRoom[4];
		doors = new Door[4];
		setDirections();
	}

	/**
	 * for every Door in doors[] that is not null,
	 * this method appends a String to "directions" describing the door and where it is. For example,
	 * 		There is a (passage) to (the North)
	 * 		There is a (passage) to (the East)
	 * If there are no doors that are not null, this sets directions to:
	 * 		"There is no way out. You are trapped in this room"
	 */
	public void setDirections() {
		directions = "";
		//hint: to check if a door is null, use:
		//doors[0] == null OR USE doors[0] != null
		boolean doorFound = false;
		for(int i = 0; i < doors.length; i++) {
			if(doors[i] != null){
				doorFound = true;
				directions += "There is a " + doors[i].getDescription() 
				+ " to the " + toDirection(i) + ". " + doors[i].getDetails() + "\n";
			}
		}
	}
	
	/**
	 * converts an int to a direction:
	 * 		0 -> "the North:
	 * 		1 -> "the East"
	 * hint: complete this method without using an if statement 
	 * @param dir
	 * @return
	 */
	public static String toDirection(int dir) {
		String[] direction = {"the North", "the East", "the South", "the West"};
		//NOTE: when I say "no long if-else" statements,
		//this is how you should be thinking
		return direction[dir];
	}
	
	public void enter() {
		contents = "X";
	}
	
	public void leave() {
		contents = defaultContents;
	}
	
	/**
	 * This is how we join rooms together.
	 * It gives this room access to anotherRoom and vice-versa
	 * It also puts the door between both rooms
	 * @param direction
	 * @param anotherRoom
	 * @param door
	 */
	public void setConnection(int direction, CaveRoom anotherRoom, Door door) {
		addRoom(direction,anotherRoom,door);
		anotherRoom.addRoom(oppositeDirection(direction), this, door);
	}
	
	public void addRoom(int dir, CaveRoom caveRoom, Door door) {
		borderingRooms[dir] = caveRoom;
		doors[dir] = door;
		setDirections();//updates the directions 
	}
	
	public void interpretInput(String input) {
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
			performAction(direction);
		}
		
		
	}
	
	/**
	 * override to create response to keys other than wdsa
	 * @param direction
	 */
	public void performAction(int direction) {
		CaveExplorer.print("That key does nothing.");
		
	}

	/**
	 * Override to change description of possible moves
	 */
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd'.");
		
	}

	/**
	 * THIS IS WHERE YOU EDIT YOUR CAVES 
	 */

	public static void setUpCaves()
	{
		CaveExplorer.caves = new NPCRoom[10][10];
		CaveRoom[][] c = CaveExplorer.caves; // shortcut
		for(int row = 0; row < c.length; row++)
		{
			for(int col = 0; col < c[row].length; col ++)
			{
				c[row][col] = new NPCRoom("This has coordinates "+ row +", " + col+".",row,col);
			}
		}
		
		
		CaveExplorer.currentRoom = c[0][1];
		CaveExplorer.currentRoom.enter();
		//Set up doors
		
		c[5][5] = new VaultRoom(null,5,5);
		c[0][0] = new DavidCar(null,9,9);
		JasonYFrontend testNPC = new JasonYFrontend(5,5,c);
		testNPC.setPosition(5,5);
		CaveExplorer.police = new JasonYFrontend[1];
		CaveExplorer.police[0] = testNPC;
		Merchant Merchant = new Merchant(6,2,c);
		Merchant.setPosition(6, 2);
		
		setConnectionForAll();
	//	setConnectionRooms();
	}
	private static void setConnectionRooms() {
		CaveRoom[][] c = CaveExplorer.caves;
		c[0][9].setConnection(SOUTH, c[0][9], new Door());
		for(int row = 0; row< 5; row++)
		{

			for(int col = 0; col < c.length-1; col++)
			{
				c[row][col].setConnection(SOUTH, c[row+1][col], new Door());
				c[row][col].setConnection(EAST, c[row][col+1], new Door());
			}
		
		}
	}
	public static void setConnectionForAll() {
		CaveRoom[][] c = CaveExplorer.caves;
		for(int row = 0; row< c.length-1; row++)
		{
			for(int col = 0; col < c.length-1; col++)
			{
				c[row][col].setConnection(SOUTH, c[row+1][col], new Door());
				c[row][col].setConnection(EAST, c[row][col+1], new Door());
			}
		}
		
		for(int i = 0; i<c[c.length-1].length-1; i++)
		{
			c[c.length-1][i].setConnection(EAST, c[c.length-1][i+1], new Door());
		}
		
		for(int i = 0; i< c.length-1; i++)
		{
			c[i][c[i].length-1].setConnection(SOUTH, c[i][c[i].length-1], new Door());
		}
	}
	/**
	 * override to add more moves
	 * @return
	 */
	public String validMoves() {
		return "wdsa";
	}
	
	/**
	 * returns true if w,a,s, or d is the input (NO IF STATEMENTS)
	 * @param input
	 * @return
	 */
	private boolean isValid(String input) {
		return validMoves().indexOf(input) != -1 && input.length() == 1;
	}


	public void goToRoom(int dir)
	{
		if(borderingRooms[dir] != null && doors[dir] != null && doors[dir].isOpen()){
			CaveExplorer.currentRoom.leave(); 
			CaveExplorer.currentRoom = borderingRooms[dir];
			CaveExplorer.currentRoom.enter();
			Inventory.updateMap(CaveExplorer.caves);
		}

	}
		/* else
		{
			System.err.println("You can't do that");

		} */
	/**
	 * returns the OPPOSITE direction
	 * 		oD(0) returns 2
	 * @param dir
	 * @return
	 */
	public static int oppositeDirection(int dir) {
		return (dir + 2) % 4;
	}
	
	public void setDefaultContents(String defaultContents) {
		this.defaultContents = defaultContents;
	}

	public String getDescription() {
		return description;
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


	public Door getDoor(int direction) {
		return doors[direction];
	}

	public void removeRoom(int dir)
	{
		doors[dir] = new Door();
		setDirections();
	}
}

