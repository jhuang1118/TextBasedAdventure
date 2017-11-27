package caveExplorer;

import ethanDavidMinigame.EthanRoomBackEnd;
import ethanDavidMinigame.VaultRoom;
import jasonYJasonZMinigame.JasonYFrontend;
import johnsonDanielMinigame.MiniGameStarter;

public class CaveRoom {
	
	public static int[] getCoordinates(CaveRoom c) {
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
	
	private String description;
	private String directions;//tells you which door can be used
	private String contents;//a symbol showing you what is in the room
		//...('X' when you are in the room)
	private String defaultContents;//what is in the room when you aren't in the room 
	
	public NPCRoom[] borderingRooms;
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
		borderingRooms = new NPCRoom[4];
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
				directions += "You can move to " + toDirection(i) + ". " + "\n";
			}
		
	//		if(!doorFound) {
	//			directions = "You're trapped in this room!";
	//		}
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
		addRoom(direction,(NPCRoom) anotherRoom,door);
		anotherRoom.addRoom(oppositeDirection(direction), (NPCRoom) this, door);
	}
	
	public void addRoom(int dir, NPCRoom caveRoom, Door door) {
		borderingRooms[dir] = caveRoom;
		doors[dir] = door;
		setDirections();//updates the directions 
	}
	public void removeRoom(int dir)
	{
		borderingRooms[dir] = null;
		setDirections();
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
		CaveExplorer.caves = new NPCRoom[20][20];
		CaveRoom[][] c = CaveExplorer.caves; // shortcut 
		for(int row = 0; row < c.length; row++)
		{
			for(int col = 0; col < c[row].length; col ++)
			{
				c[row][col] = new NPCRoom("You are at coordinates ("+ row +", " + col+").", row, col);
			}
		}

		CaveExplorer.currentRoom = c[0][1];
		CaveExplorer.currentRoom.enter();
		//c[9][5] = new EthanRoomBackEnd("");
		//c[1][1] = new MiniGameStarter("");

		//Replace some default rooms with custom rooms (SAVE FOR LATER) 
		JasonYFrontend testNPC = new JasonYFrontend(5,5,c);
		testNPC.setPosition(5,5);
		CaveExplorer.police = new JasonYFrontend[1];
		CaveExplorer.police[0] = testNPC;
		//c[2][3] = new EthanRoomBackEnd("");

		
		//Set up doors
		setConnectionForAll();
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
		//5. Set up doors 
		
		setupStore();
		c[0][1].setConnection(SOUTH, c[1][1], new Door());
		c[1][1].setConnection(EAST, c[1][2], new Door());
	}
	
	private static void setupStore() {
		for(int i = 18; i>15; i--)
		{
			for( int e = 0; e< 20; e++)
			{
				CaveExplorer.caves[i][e].setConnection(NORTH, CaveExplorer.caves[i][e], new Door());
			}
		}
		//horizontal doors
		for(int i = 19; i>15; i--)
		{
			for( int e =0; e<19; e++)
			{
				CaveExplorer.caves[i][e].setConnection(EAST, CaveExplorer.caves[i][e+1], new Door());
			}
		}
		//vertical doors
		//doors
		CaveExplorer.caves[16][10].setConnection(NORTH, CaveExplorer.caves[15][10], new Door());
		CaveExplorer.caves[16][9].setConnection(NORTH, CaveExplorer.caves[15][10], new Door());
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
			if(borderingRooms[dir].getNpc() instanceof JasonYFrontend)
			{
				((JasonYFrontend) borderingRooms[dir].getNpc()).play();
				((JasonYFrontend) borderingRooms[dir].getNpc()).setActive(false);
			}
			CaveExplorer.currentRoom = borderingRooms[dir];
			row = borderingRooms[dir].row;
			col = borderingRooms[dir].col;
			CaveExplorer.currentRoom.enter();
		}
		else {
				CaveExplorer.inventory.updateMap(CaveExplorer.caves);
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

}
