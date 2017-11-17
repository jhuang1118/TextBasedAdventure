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
	public CaveRoom[][] map;
	public Door[] doors;
	public String[] difficultyWords;
	public GameRoom currentRoom;
	 
	public static final void main(String[] args) {
		JasonYFrontend demo = new JasonYFrontend();
		demo.play();
	}
	
	public JasonYFrontend() {
		backend = new JasonZBackend(this, 1);
		String[] temp = {"easy", "casual", "hard", "extreme", "hell"};
		difficultyWords = temp;
	}

	public void createMap() {
		//creates the map
		map = new CaveRoom[10][10];
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[row].length; col++) {
				map[row][col] = new GameRoom(row,col);
			}
		}
		
		currentRoom = (GameRoom) map[0][1];
		currentRoom.enter();
	}
	
	public void follow(){
		//npcs in the map will move towards the player after played inputs a move
	}
	
	public void rangeDisplay() {
		//colors in a number of boxes based on the range of the gun
		//should be colored red 
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
		System.out.println("So do you want to play? Enter 'p' to play. Enter 'c' for controls");
		String input = in.nextLine();
		while(!isValid(input, "pc")) {
			System.out.println("Valid inputs are 'p' and 'c'.");
			input = in.nextLine();
		}
		if("pc".indexOf(input) == 2) {
			controls();
		}
		System.out.println("So what difficulty do you want to put it on? "
				+ "The avaiable difficulties are 'easy', 'casual', 'hard', 'extreme', and 'hell'.");
		input = in.nextLine();
		while(!isValid(input, difficultyWords)) {
			System.out.println("The avaiable difficulties are 'easy', 'casual', 'hard', 'extreme', and 'hell'.");
			input = in.nextLine();
		}				
		int index = returnIndex(input,difficultyWords);
		createMap();
		CaveExplorer.inventory.updateMap(map);
		populateMap(index);
	}

	private void controls() {
		System.out.println("The controls to this game are 'w', 'a', 's', 'd' to move up, left, down, and right "
				+ "respectively. You can also press 'f' to shoot. Enter back to go back.");//'f' or whatever key Jason used for fire input
		String input = in.nextLine();
		while(!input.toLowerCase().equals("back")) {
			System.out.println("So you want to go back or nah?");
		}
	}

	private boolean isValid(String input, String string) {
		return string.indexOf(input) > -1;
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
