package jasonYJasonZMinigame;

public class JasonYFrontend implements JasonZSupport {
	
	private JasonYSupport backend;
	public int killCounter;
	public int copCounter;
	public int neededKills;
	public NPCs[] npc;//call the data type whatever Johnson called it
	public GameRoom[][] map;
	
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static final void main(String[] args) {
		JasonYFrontend demo = new JasonYFrontend();
		demo.play();
	}
	
	public JasonYFrontend() {
		backend = new JasonZBackend(this);
		createMap();
	}

	public void createMap() {
		//creates the map
		map = new GameRoom[10][10];
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[row].length; col++) {
				map[row][col] = new GameRoom(row,col);
			}
		}
	}
	
	public void displayMap() {
		//displays the map
		String[][] pic = new String[10][10];
		drawVerticalLine(pic,0);
		drawVerticalLine(pic,9);
		drawHorizontalLine(pic,0);
		drawHorizontalLine(pic,9);
		print(pic);
	}

	private static void print(String[][] pic) {
		for(String[] row: pic) {
			for(String col : row) {
				System.out.print(col);
			}
			System.out.println("");
		}
	}
	
	private static void drawVerticalLine(String[][] pic, int col) {
		for(int i = 0; i < pic.length; i++) {
			pic[i][col] = "|";
		}
	}
	
	private static void drawHorizontalLine(String[][] pic, int row) {
		for(int i = 0; i < pic[row].length; i++) {
			pic[row][i] = "-";
		}
	}
	
	public void follow(){
		//npcs in the map will move towards the player after played inputs a move
	}
	
	public void rangeDisplay() {
		//colors in a number of boxes based on the range of the gun
		//should be colored red 
	}
	
	public void play() {
		//introduction();
		//will go through a while loop
		//displays final message when player wins or loses
		//player wins when they have killed a number of cops
		//player loses if they die
	}

	public void killCounter() {
		neededKills = neededKills - killCounter;
		System.out.println("You have killed " + killCounter + " cops. You need to kill " + neededKills + "cops.");
		
	}

	public void copCounter() {
		//displays the number of cops in the map
		System.out.println("There are " + copCounter + " in the area.");
	}

	public void introduction() {
		//will ask player for input : p to play and c for controls (something like that)
		//once they enter the play button (in this case p):
		//ask for difficulty
		createMap();
		displayMap();
		//populateMap();
		
		
	}
	
	
}
