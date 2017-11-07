package caveExplorer;

import ethanDavidMinigame.DavidRoomFrontEnd;
import ethanDavidMinigame.EthanRoomBackEnd;
import ethanDavidMinigame.Police;

public class CaveRoom {
	//comment
	private String description; 
	private String directions; //which doors
	private String contents; // a symbol to show room you are in
	private String defaultContents; // what is in the room when your aren't in the room
	
	private CaveRoom[] borderingRooms;
	private Door[] doors; // valid 
	
	//constants
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static int cRIndex;
	public static int index;
	
	public CaveRoom( String description) {
		this.description = description;
		setDefaultContents(" ");
		contents = defaultContents;
		borderingRooms = new CaveRoom[4];
		doors = new Door[4];
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
				this.directions += "There is a "+ this.doors[i].getDescription()+" to "+ toDirection(i)+". "+this.doors[i].getDetails() + "\n"; 
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
			System.out.println("You can only enter 'w', 'a', 's', or 'd'");
			input = CaveExplorer.in.nextLine();
		}
		int direction = "wdsa".indexOf(input);
		goToRoom(direction);
	}
	
	private boolean isValid(String input) {
		return "wdsa".indexOf(input.toLowerCase()) > -1 && input.length() == 1;
	}
	
	/**
	 * Determine the size of the cave:
	 */
	public static void setUpCaves()
	{
		CaveExplorer.caves = new CaveRoom[10][10];
		CaveRoom[][] c = CaveExplorer.caves;// shortcut
		c[3][3] = new Police("Police!");
		//Replace some default rooms with custom rooms (SAVE FOR LATER) 
		c[9][5] = new EthanRoomBackEnd("sdkjhaskd");
		for(int row = 0; row < c.length; row++)
		{
			for(int col = 0; col < c[row].length; col ++)
			{
				c[row][col] = new DavidRoomFrontEnd("You are currently in the bank." + "\n" 
						+ "You are located at "+ row +", " + col+"." + "\n" +
						 "GET TO THE VAULT! (Avoid the police! 'P')"
						 + "\n");
			}
		}
		c[9][5] = new EthanRoomBackEnd("sdkjhaskd");
		//Set Starting Room
		int cRIndex = (int)(Math.random() * c.length/2);
		CaveExplorer.currentRoom = c[cRIndex][cRIndex];
		CaveExplorer.currentRoom.enter();
		setConnectionForAll();
		//Set up doors  
	}
		private static void setConnectionForAll() {
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
		
	
	public void goToRoom(int dir)
	{
		if(borderingRooms[dir] != null && doors[dir] != null && doors[dir].isOpen())
		{
			CaveExplorer.currentRoom.leave(); 
			CaveExplorer.currentRoom = borderingRooms[dir];
			CaveExplorer.currentRoom.enter();
			CaveExplorer.inventory.updateMap();
			CaveExplorer.inventory.getMoney();
		}
		else
		{
			System.err.println("You can't do that");
		}
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
