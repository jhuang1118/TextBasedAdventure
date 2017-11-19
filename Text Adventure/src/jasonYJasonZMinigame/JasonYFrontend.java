package jasonYJasonZMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;

import java.util.Scanner;

public class JasonYFrontend implements JasonZSupport {
	
	public static Scanner in;//for user input
	
	private JasonYSupport backend;
	public int copCounter;
	public int neededKills;
	public JasonZSwat[] npc;
	public CaveRoom[][] caves;
	public GameRoom[][] map;
	public Door[] doors;
	public String[] difficultyWords;
	public GameRoom currentMiniGameRoom;
	 
	public static final void main(String[] args) {
		in = new Scanner(System.in);
		JasonYFrontend demo = new JasonYFrontend();
		demo.play();
	}
	
	public JasonYFrontend() {
		backend = new JasonZBackend(this, 1,]);
		String[] temp = {"easy", "casual", "hard", "extreme", "hell"};
		difficultyWords = temp;
	}

	public void createMap(int size) {
		//creates the map
		//get current position of player
		map = new GameRoom[size*2][size*2];
		caves = CaveExplorer.caves;
		int[] coords = CaveExplorer.currentRoom.getCoordinates();
		int[] startRoom = new int[2];
		startRoom[0] = coords[0] - size;
		startRoom[1] = coords[1] - size;
		int[] finalRoom = new int[2];
		finalRoom[0] = coords[0] + size;
		finalRoom[1] = coords[1] + size;
		int[][] startendrooms = new int[1][2];
		startendrooms[0] = startRoom;
		startendrooms[1] = finalRoom;
		int mapRow = 0;
		int mapCol = 0;
		while(mapRow < size*2) {
			for(int row = startendrooms[1][0]; row < startendrooms[2][0]; row++) {
				for(int col = startendrooms[1][1]; col < startendrooms[2][1]; col++) {
					map[mapRow][mapCol] = (GameRoom) caves[row][col];
					mapCol++;
					if(mapCol > size*2) {
						mapCol = 0;
					}
				} 
			}
		}
	}
	
	public void follow(){
		//npcs in the map will move towards the player after played inputs a move
		//grab the coordinates of the player
		//run a for each loop
		//in that loop grab the coordinates of cops
		//based on the row or col
		//either add one or minus one
		//if the new coordinate has an obstacle,redo
	}
	
	public void rangeDisplay() {
		//colors in a number of boxes in all diretions based on the range of the gun
		//should be colored red 
		//grab the players coordinates
	}
	
	public void play() {
		introduction();
	}

	public void killCounter() {
		neededKills = neededKills - JasonZBackend.killCount;
		System.out.println("You have killed " + JasonZBackend.killCount + " cops. You need to kill " + neededKills + "cops.");
		
	}

	public void copCounter() {
		//displays the number of cops in the map
		System.out.println("There are " + copCounter + " in the area.");
	}
 
	public void introduction() {
		//will ask player for input : p to play and c for controls (something like that)
		//once they enter the play button (in this case p):
		//ask for difficulty
		System.out.println("Welcome to 'Shoot'em Cops'!");
		System.out.println("That's right! In this game you shoot cops!");
		System.out.println("Cops will spawn when you enter the game. Cops will continue to spawn after a "
				+ "certian amount of time has passed when playing. You need to kill a number of cops to "
				+ "win the game. If they kill you that's it.");
		introTwo();
	}
	
	public void introTwo() {
		System.out.println("So do you want to play? Enter 'p' to play. Enter 'c' for controls");
		String input = in.nextLine();
		while(!input.toLowerCase().equals("p")) {			
			if(input.toLowerCase().equals("c")) {
				controls();
			}
			else{
				System.out.println("Valid inputs are 'p' and 'c'.");
				input = in.nextLine();	
			}
		}
		System.out.println("So what difficulty do you want to put it on? "
				+ "The avaiable difficulties are 'easy', 'casual', 'hard', 'extreme', and 'hell'.");
		input = in.nextLine();
		while(!isValid(input, difficultyWords)) {
			System.out.println("The avaiable difficulties are 'easy', 'casual', 'hard', 'extreme', and 'hell'.");
			input = in.nextLine();
		}				
		int index = returnIndex(input,difficultyWords);
		createMap(2);
		CaveExplorer.inventory.updateMap(map);
		populateMap(index);
	}

	private void controls() {
		System.out.println("The controls to this game are 'w', 'a', 's', 'd' to move up, left, down, and right "
				+ "respectively. You can also press 'f' to shoot. Enter back to go back.");//'f' or whatever key Jason used for fire input
		String input = in.nextLine();
		while(!input.toLowerCase().equals("back")) {
			System.out.println("So you want to go back or nah?");
			input = in.nextLine();
		}
		introTwo();
	}

	private boolean isValid(String s, String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(s.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private int returnIndex(String s, String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(s.toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	private void populateMap(int mode) {
		int baseNum = (int)(Math.random() * 50);
		int spawnNum = (int) (baseNum * JasonZBackend.difficulty[mode]);
		for(int i = 0; i < spawnNum; i++) {
			int randomRow = (int)(Math.random() * 10);
			int randomCol = (int)(Math.random() * 10);
			JasonZBackend.createPolice(randomRow, randomCol);
		}
	}	
}
