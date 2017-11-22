package jasonYJasonZMinigame;

import java.util.Scanner;

public class JasonYFrontend implements JasonZSupport {

	public static Scanner in;//for user input
	
	private JasonYSupport backend;
	private int killCounter;
	private int copCounter;
	private NPCs[] npc;//call the data type whatever Johnson called it
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static final void main(String[] args) {
		in = new Scanner(System.in);
		
		JasonYFrontend demo = new JasonYFrontend();
		demo.play();
	}
	
	public JasonYFrontend() {
		backend = new JasonZBackend(this);
	}

	public void displayMap() {
		//displays the map
	}
	
	public void follow(){
		//npcs in the map will move towards the player after played inputs a move
	}
	
	public void rangeDisplay() {
		//colors in a number of boxes based on the range of the gun
		//should be colored red 
	}
	
	public void play() {
		//introduction()
		//will go through a while loop
		//displays final message when player wins or loses
		//player wins when they have killed a number of cops
		//player loses if they die
	}

	public void killCounter() {
		//displays the number of cops player killed
		
	}

	public void copCounter() {
		//displays the number of cops in the map
		
	}

	public void introduction() {
		//will ask player for input : p to play and c for controls (something like that)
		//once they enter the play button (in this case p):
		//ask for difficulty
		//displayMap();
		//populateMap();
	}
	
	
}
