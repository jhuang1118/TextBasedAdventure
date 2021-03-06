package caveExplorer;

import java.util.Scanner;

import jasonYJasonZMinigame.JasonYFrontend;

public class CaveExplorer {

	public static CaveRoom[][] caves; 
	public static Scanner in;//for user input
	public static CaveRoom currentRoom;//changes as the user moves
	public static Inventory inventory;
	public static boolean playing = true;
	public static JasonYFrontend[] police;
	public static NPC[] npcs;
	
	
	public static void main(String[] args) {
		in = new Scanner(System.in);

		CaveRoom.setUpCaves(); // creates caves and starting room

		inventory = new Inventory();
		startExploring();
	}

	public static void print(String s)
	{
		System.out.println(s); // consider replacing with the "print line thing in chatbot"
	}

	public static void startExploring() {
		System.out.println("We are dropping you off here. You are on your own now. Your mission is to rob the bank.\nHowever you do it "
				+ "is up to you. Once you get the money come back here!\n C: Car\n P: Police \n M: Merchant \n V: Vault");
		while(playing) {
			moveNPCs();
			print("What would you like to do?");
			print(inventory.getDescription());
			print(currentRoom.getDescription());
			print(currentRoom.getDirections());
			currentRoom.interpretInput(in.nextLine());
		}
		
	}

	private static void moveNPCs() {
		if(police != null) {
			for(JasonYFrontend n: police)
			{
				if( n != null)n.autoMove();
			}	
		}
		Inventory.updateMap(caves);
	}
	
	public static void remove(JasonYFrontend npc) {
		for( int i = 0; i< police.length; i++)
		{
			if( npc.equals(police[i]))
			{
				police[i] = null;
				break;
			}
		}
	}
}
