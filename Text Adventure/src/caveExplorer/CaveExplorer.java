package caveExplorer;

import java.util.Scanner;

public class CaveExplorer {

	public static CaveRoom[][] caves;
	public static Scanner in;
	public static CaveRoom currentRoom;
	public static Inventory inventory;
	public static boolean playing = true;
	public static NPC[] police;
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		CaveRoom.setUpCaves(); // creates caves and starting room
		inventory = new Inventory();
		startExploring();
	}
	public static void print(String s)
	{
		System.out.println(s); // consider replacing with the "printline thing in chatbot"
	}
	private static void startExploring() {
		while(playing)
		{
			moveNPCs();
			print(inventory.getDescription());
			System.out.println("You have $"+inventory.getCash());
			print(currentRoom.getDescription());
			print(currentRoom.getDirections());
			print(" what would you like to do?");
			currentRoom.interpretInput(in.nextLine());
		}
	}
	private static void moveNPCs() {
		for(NPC n: npcs)
		{
			n.autoMove();
		}
		inventory.updateMap();
	}

}
