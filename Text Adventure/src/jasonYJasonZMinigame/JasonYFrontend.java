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
	
	public Scanner in;//for user input
	
	private JasonYSupport backend;
	public int copCounter;
	public int neededKills;
	public int hp;
	public JasonZSwat[] npc;
	public static CaveRoom[][] caves;
	public NPCRoom[][] map;
	public Door[] doors;
	public String[] difficultyWords;

	public JasonYFrontend(int row, int col, CaveRoom[][] c) {
		super(row, col, c);
		String[] temp = {"easy", "casual", "hard", "extreme", "hell"};
		difficultyWords = temp;
		hp = 100;
	}

	public void createMap(int size) {
		//creates the map
		//get current position of player
		caves = CaveExplorer.caves;
		int[] coords = new int[2];
		if(getCurrentRow() > 0 && getCurrentCol() > 0)
		{
			coords[0] = getCurrentRow();
			coords[1] = getCurrentCol();
		}
		else
		{
			coords[0] = 0;
			coords[1] = 0;
		}
		
		int[] startRoom = new int[2];
		if( (coords[0]-size) < 0) 
		{
			startRoom[0] = 0;
		}
		else 
		{
			startRoom[0] = coords[0] - size;
			
		}
		if(coords[1]-size < 0)
		{
			startRoom[1] = 0;
		}
		else
		{
			startRoom[1] = coords[1] - size;
		}
		
		int[] finalRoom = new int[2];
		if(coords[0] +size > caves.length)
		{
			coords[0] = caves.length;
		}
		else
		{
			finalRoom[0] = coords[0] + size;
		}
		if( (coords[1]+size) > caves[0].length)
		{
			finalRoom[1] = caves.length;
		}
		else
		{
			finalRoom[1] = coords[1] + size ;
		}
		map = new NPCRoom[finalRoom[0] - startRoom[0]+1][finalRoom[1]- startRoom[1]+1];
		int mapRow = 0;
		int mapCol = 0;
		for(int row = startRoom[0]; row < finalRoom[0]+1; row++) {
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
		backend = new JasonZBackend(this, 1, map);
		while(hp != 0 || neededKills != 0) {
			int[] coords = CaveExplorer.currentRoom.getCoordinates(CaveExplorer.currentRoom);
			System.out.println("You are at coordinates (" + coords[0] + ", " + coords[1] + ").");
			System.out.println("What would you like to do?");
			String input = in.nextLine();
			backend.validInput(input);	
			for( JasonZSwat p: npc)
			{
				p.calculateMove(coords[0], coords[1]);
			}
			CaveExplorer.inventory.updateMap(map);
			killCounter();
			copCounter();
			System.out.println(CaveExplorer.inventory.getMap());
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
		in = new Scanner(System.in);
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
		System.out.println(CaveExplorer.inventory.getMap());
		populateMap(index);
	}

	public void controls() {
		System.out.println("The controls to this game are 'w', 'a', 's', 'd' to move up, left, down, and right "
				+ "respectively. You can also press 'f' to shoot. Enter 'back' to go back.");//'f' or whatever key Jason used for fire input
		String input = in.nextLine();
		while(!input.toLowerCase().equals("back")) {
			System.out.println("That is not a valid input. Enter 'back' to go back.");
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
		int baseNum = (int)(Math.random()*5);
		int spawnNum = (int) (baseNum * JasonZBackend.difficulty[mode]);
		for(int i = 0; i < spawnNum; i++) {
			int randomRow = (int)(Math.random() * 5);
			int randomCol = (int)(Math.random() * 5);
			JasonZBackend.createPolice(randomRow, randomCol);
		}
	}	
	
	public String getSymbol() {
		return "P";
	}
	
	public int[] calculateMove(int userRow, int userCol)
	{
		int dir = checkDirection();
		int[] newPosition = new int[2]; 
		newPosition[0] = getCurrentRow() + possibleMoves[dir][0];
		newPosition[1] = getCurrentCol() + possibleMoves[dir][1];
		return newPosition;
	}
	private int checkDirection() {
		//
		int row = CaveExplorer.currentRoom.getRow(CaveExplorer.currentRoom);
		int col = CaveExplorer.currentRoom.getCol(CaveExplorer.currentRoom);
		if(this.getCurrentRow() -  row> 0)
		{
			return 0;
		}
		if(this.getCurrentRow() - row < 0)
		{
			return 2;
		}
		if(this.getCurrentCol() -  col < 0)
		{
			return 1;
		}
		if(this.getCurrentCol() - col > 0)
		{
			return 3;
		}
		return 0;
	}
}
