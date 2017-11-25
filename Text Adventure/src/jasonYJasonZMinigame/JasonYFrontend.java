package jasonYJasonZMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.Door;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;

import java.awt.Color;
import java.awt.event.KeyEvent;

import java.util.Scanner;

public class JasonYFrontend extends NPC implements JasonZSupport{
	
	public static Scanner in;//for user input
	
	private JasonYSupport backend;
	public int copCounter;
	public int neededKills;
	public int hp;	
	public int row; 
	public int col;
	public JasonZSwat[] npc;
	public static CaveRoom[][] caves;
	public NPCRoom[][] map;
	public Door[] doors;
	public String[] difficultyWords;

	
	public static final void main(String[] args) {
		in = new Scanner(System.in);
		JasonYFrontend demo = new JasonYFrontend(0, 0, null);
		demo.play();
	}
	
	public JasonYFrontend(int row, int col, CaveRoom[][] c) {
		super(c);
		this.row = row; 
		this.col = col;
		createMap(2);
		backend = new JasonZBackend(this, 1, map);
		String[] temp = {"easy", "casual", "hard", "extreme", "hell"};
		difficultyWords = temp;
		hp = 100;
	}

	public void createMap(int size) {
		//creates the map
		//get current position of player
		map = new NPCRoom[(size*2)+1][(size*2)+1];
		caves = CaveExplorer.caves;
		int[] coords = new int[2];
		coords[0] = row;
		coords[1] = col;
		int[] startRoom = new int[2];
		startRoom[0] = coords[0] - size;
		startRoom[1] = coords[1] - size;
		int[] finalRoom = new int[2];
		finalRoom[0] = coords[0] + size;
		finalRoom[1] = coords[1] + size;
		int mapRow = 0;
		int mapCol = 0;
		for(int row = startRoom[0]; row < finalRoom[0]+1 ; row++) {
			for(int col = startRoom[1]; col < finalRoom[1]+1; col++) {
				map[mapRow][mapCol] = (NPCRoom) caves[row][col];
				mapCol++;
			}
			mapRow ++;
			mapCol = 0;
		}
	}
	
//	public void rangeDisplay() {
		//colors in a number of boxes in all directions based on the range of the gun
		//should be colored red 
		//grab the players coordinates
//		int[] coords = CaveExplorer.currentRoom.getCoordinates();
//		int range = JasonZBackend.gun.getRange();
//		for(int i = 0; i < range; i++) {
//			map[coords[0]+i][coords[1]] = " | ";//down
//			map[coords[0]-i][coords[1]] = " | ";//up
//			map[coords[0]][coords[1]+1] = " - ";//right
//			map[coords[0]][coords[1]-1] = " - ";//left
//		}
//	} 
	
	public void play() {
		introduction();
		while(hp != 0 || neededKills != 0) {
			System.out.println("What would you like to do?");
			String input = in.nextLine();
			backend.validInput(in.nextLine());
			int[] coords = CaveExplorer.currentRoom.getCoordinates();
			for( JasonZSwat p: npc)
			{
				p.calculateMove(coords[0], coords[1]);
			}
			killCounter();
			copCounter();
		}
		if(hp == 0) {
			System.out.println("GAME OVER!");
		}
		if(neededKills == 0) {
			System.out.println("Congrats! You've won the game!");
		}
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
	
	public String getSymbol() {
		return "P";
	}
}
