package caveExplorer;

import ethanDavidMinigame.EthanRoomBackEnd;
import ethanDavidMinigame.VaultRoom;

public class CaveRoom {
	
	private String description;
	private String directions;//tells you which door can be used
	private String contents;//a symbol showing you what is in the room
		//...('X' when you are in the room)
	private String defaultContents;//what is in the room when you aren't in the room 
	
	private CaveRoom[] borderingRooms;
	private Door[] doors;
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public CaveRoom(String description) {
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
				System.out.println(directions);
			}
		}
		directions = "You're trapped in this room!";
		System.out.println(directions);
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
		//CaveExplorer.caves = new CaveRoom[15][15];
		//CaveRoom[][] m = CaveExplorer.caves; 
		CaveRoom[][] c = CaveExplorer.caves; // the vault room
		CaveExplorer.caves = new NPCRoom[20][20];
		CaveRoom[][] c = CaveExplorer.caves; // shortcut refs/remotes/origin/master
		for(int row = 0; row < c.length; row++)
		{
			for(int col = 0; col < c[row].length; col ++)
			{
				c[row][col] = new NPCRoom("This has coordinates "+ row +", " + col+".");
			}
		}
		//Replace some default rooms with custom rooms (SAVE FOR LATER) 
		/* NPC testNPC = new NPC();
		testNPC.setPosition((int)(Math.random() * c.length),(int)(Math.random()* c.length));
		CaveExplorer.police = new NPC[1];
		CaveExplorer.police[0] = testNPC; */
		
		c[9][5] = new EthanRoomBackEnd("");


		c[0][2] = new JasonZRoom("TEST ROOM", 0, 2);
		//Replace some default rooms with custom rooms (SAVE FOR LATER) 
		NPC testNPC = new NPC();
		testNPC.setPosition(1,2);
		CaveExplorer.police = new NPC[1];
		CaveExplorer.police[0] = testNPC;
		c[2][3] = new EthanRoomBackEnd("");
		testNPC.setPosition(3,4);
		CaveExplorer.npcs = new NPC[1];
		CaveExplorer.npcs[0] = testNPC;
		//Set Starting Room

		}
		//3. Replace some default rooms with custom rooms (SAVE FOR LATER)
		/*NPC testNPC = new NPC();
		testNPC.setPosition(1,2);
		CaveExplorer.npcs = new NPC[1];
		CaveExplorer.npcs[0] = testNPC;*/
		
		NPC enemy = new Enemy();
		enemy.setPosition(1, 2);
		CaveExplorer.npcs = new NPC[1];
		CaveExplorer.npcs[0] = enemy;
		
		//4. Set starting room

		CaveExplorer.currentRoom = c[0][1];
		CaveExplorer.currentRoom.enter();
		
		//5. Set up doors 
		c[0][1].setConnection(SOUTH, c[1][1], new Door());
		c[1][1].setConnection(EAST, c[1][2], new Door());
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
		if(borderingRooms[dir] != null && doors[dir] != null && doors[dir].isOpen())
		{
			CaveExplorer.currentRoom.leave(); 
			CaveExplorer.currentRoom = borderingRooms[dir];
			CaveExplorer.currentRoom.enter();
			if(borderingRooms[dir] instanceof JasonZRoom)
			
				borderingRooms[dir].
				CaveExplorer.inventory.updateMap(borderingRooms[dir].newMap);
			}
			else 
			{
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
