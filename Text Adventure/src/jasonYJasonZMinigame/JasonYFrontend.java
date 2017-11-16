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
	public JasonZSwat[] npc;//call the data type whatever Johnson called it
	public CaveRoom[][] map;
	public Door[] doors;
	public GameRoom currentRoom;
	
	 
	public static final void main(String[] args) {
		JasonYFrontend demo = new JasonYFrontend();
		demo.play();
	}
	
	public JasonYFrontend() {
		backend = new JasonZBackend(this, 1);
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
		createMap();
		CaveExplorer.inventory.updateMap(map);
		//populateMap();
		
		
	}
	
	
}
