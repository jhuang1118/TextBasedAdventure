package jasonYJasonZMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.DavidCar;
import caveExplorer.Door;
import caveExplorer.NPC;
import caveExplorer.NPCRoom;
import ethanDavidMinigame.VaultRoom;

import java.util.Scanner;

public class JasonYFrontend extends NPC implements JasonZSupport{
	
	public Scanner in;//for user input
	
	private JasonYSupport backend;
	public int copCounter;
	public static int hp;
	public static CaveRoom[][] caves;
	public NPCRoom[][] map;
	public Door[] doors;
	public String[] difficultyWords;
	public int[] coords;
	public int[] finalRoom;
	public int[] startRoom;
	public NPCRoom[] RoomsReplaced = new NPCRoom[3];
	

	public JasonYFrontend(int row, int col, CaveRoom[][] c) {
		super(row, col, c);
		String[] temp = {"easy", "casual", "hard", "extreme", "hell"};
		difficultyWords = temp;
		hp = 100;
		copCounter = 1;
	}

	public void createMap(int size) {
		//creates the map
		//get current position of player
		caves = CaveExplorer.caves;
		coords = new int[2];
		if(getCurrentRow() > 0 && getCurrentCol() > 0){
			coords[0] = getCurrentRow();
			coords[1] = getCurrentCol();
		}
		else{
			coords[0] = 0;
			coords[1] = 0;
		}
		startRoom = new int[2];
		if( (coords[0]-size) < 0) {
			startRoom[0] = 0;
		}
		else {
			startRoom[0] = coords[0] - size;
		}
		if(coords[1]-size < 0){
			startRoom[1] = 0;
		}
		else{
			startRoom[1] = coords[1] - size;
		}
		finalRoom = new int[2];
		if(coords[0] +size > caves.length){
			coords[0] = caves.length;
		}
		else{
			finalRoom[0] = coords[0] + size;
		}
		if((coords[1]+size) > caves[0].length){
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
				NPCRoom c = map[mapRow][mapCol];
				if(c.getNpc() != null)
				{
					c.getNpc().setActive(false);
				}
				if(c instanceof VaultRoom || c instanceof DavidCar)
				{
					RoomsReplaced[checknull(RoomsReplaced)] = c;
					c = new NPCRoom("This has coordinates "+ row +", " + col+".", row, col);
					c.setActive(false);
				}
				c.setMiniDescription("You are at "+mapRow+", "+mapCol);
				c.miniRow = mapRow;
				c.miniCol = mapCol;
				
				if(col == finalRoom[1]) {
					c.removeRoom(CaveRoom.EAST);
				}
				if(col == startRoom[1]) {
					c.removeRoom(CaveRoom.WEST);
				}
				if(row == finalRoom[0]) {
					c.removeRoom(CaveRoom.SOUTH);
				}	
				if(row == startRoom[0]) {
					c.removeRoom(CaveRoom.NORTH);
				}
				mapCol++;
			}
			mapRow ++;
			mapCol = 0;
		}
	}

	private int checknull(NPCRoom[] arr) {
		for(int i =0; i< arr.length; i++)
		{
			if( arr[i] == null)
			{
				return i;
			}
		}
		return 0;
	}

	public void play() {
		introduction();
		System.out.println(CaveExplorer.inventory.getMap());
		while(hp > 0 && copCounter != 0) {
			int[] coords = {JasonZBackend.starterRow, JasonZBackend.starterCol};
			System.out.println("You are at coordinates (" + coords[0]+ ", " + coords[1] + ").");
			System.out.println("What would you like to do?");
			String input = in.nextLine();
			if(input.equals("c")){
				copCounter = 0;
				break;
			}
			backend.validInput(input);			
			if(JasonZBackend.Swat != null) {
				for(JasonZSwat p: JasonZBackend.Swat){
					if( p != null){
						if(((JasonZBackend) backend).canFire(p))
						{
							((JasonZBackend) backend).damagePlayers(hp, p);
							System.out.println(p.gun.trueDamage());
							System.out.println("You were hit! You have "+ hp + " hp.");
						}
						else
						{
							int[] moves = p.calculateMove(coords[0], coords[1]);
							p.setPosition(moves[0], moves[1]);
						}
						
						
					}
				}
			}
			if(hp < 0) {
				break;
			}
			((JasonZBackend) backend).setValidTarget(((JasonZBackend) backend).getCurrentRoom());
			CaveExplorer.inventory.updateMap(JasonZBackend.cave);
			System.out.println(CaveExplorer.inventory.getMap());
			copCounter();
			System.out.println("You have killed " + JasonZBackend.killCount + " cop(s). You need to kill " + copCounter + " cop(s).");
		}
		if(hp < 0) {
			System.out.println("GAME OVER!");
			System.exit(0);
		}
		else {
			System.out.println("Congrats! You've won the game!");
		}
		clearAllContent();
		replaceRoom();
		caveExplorer.CaveRoom.setConnectionForAll();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	private void clearAllContent() {
		for(int i =0 ; i < map.length; i ++){
			for(int e =0; e< map[i].length; e++){
				map[i][e].leaveNPC();
			}
		}
	}

	public void copCounter() {
		//displays the number of cops in the map
		copCounter = 0;
		for(int i = 0; i < JasonZBackend.Swat.length;i++) {
			if(JasonZBackend.Swat[i] != null) {
				copCounter++;
			}
			else {
				break;
			}  
		}
		System.out.println("There are " + copCounter + " cop(s) in the area.");
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
		System.out.println("So do you want to play? Enter 'p' to play. Enter 'c' for controls");
		String input = in.nextLine();
		while(!input.toLowerCase().equals("p")) {			
			if(input.toLowerCase().equals("c")) {
				System.out.println("The controls to this game are 'w', 'a', 's', 'd' to move up, left, down, and right "
						+ "respectively. You can also press 'f' to shoot. Enter 'back' to go back.");//'f' or whatever key Jason used for fire input
				input = in.nextLine();
				while(!input.toLowerCase().equals("back")) {
					System.out.println("That is not a valid input. Enter 'back' to go back.");
					input = in.nextLine();
				}
				System.out.println("So do you want to play? Enter 'p' to play. Enter 'c' for controls");
				input = in.nextLine();
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
		input = "";
		createMap(2);
		this.backend = new JasonZBackend(this, index, map);
		populateMap(index);
		((JasonZBackend) backend).setValidTarget(((JasonZBackend) backend).getCurrentRoom());
		CaveExplorer.inventory.updateMap(map);
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
		for(int i = 0; i < mode + 1; i++) {
			int randomRow = ((int)(Math.random() * map.length));
			int randomCol = ((int)(Math.random() * map[0].length));
			JasonZBackend.createPolice(randomRow, randomCol);
		}
	}	
	
	public String getSymbol() {
		return "P";
	}
	
	public int[] calculateMove(int userRow, int userCol){
		int dir = checkDirection();
		int[] newPosition = new int[2]; 
		newPosition[0] = getCurrentRow() + possibleMoves[dir][0];
		newPosition[1] = getCurrentCol() + possibleMoves[dir][1];
		return newPosition;
	}
	private int checkDirection() {
		int row = CaveRoom.getRow(CaveExplorer.currentRoom);
		int col = CaveRoom.getCol(CaveExplorer.currentRoom);
		if(this.getCurrentRow() -  row> 0){
			return 0;
		}
		if(this.getCurrentRow() - row < 0){
			return 2;
		}
		if(this.getCurrentCol() -  col < 0){
			return 1;
		}
		if(this.getCurrentCol() - col > 0){
			return 3;
		}
		return 0;
	}
	
	public void replaceRoom()
	{
		int mapRow = 0;
		int mapCol = 0;
		for(int row = startRoom[0]; row < finalRoom[0]+1; row++) {
			for(int col = startRoom[1]; col < finalRoom[1]+1; col++) {
				map[mapRow][mapCol] = (NPCRoom) caves[row][col];
				NPCRoom c = map[mapRow][mapCol];
				if(c.containsNPC())
				{
					c.getNpc().setActive(true);
				}
				for(int i =0; i< RoomsReplaced.length; i++)
				{
					if( RoomsReplaced[i] != null )
					{
						NPCRoom ref = RoomsReplaced[i];
						caves[ref.row][ref.col] = ref;
						ref.setActive(true);
					}
				}
				if(col == finalRoom[1]) {
					c.replaceRoom(CaveRoom.EAST);
				}
				if(col == startRoom[1]) {
					c.replaceRoom(CaveRoom.WEST);
				}
				if(row == finalRoom[0]) {
					c.replaceRoom(CaveRoom.SOUTH);
				}	
				if(row == startRoom[0]) {
					c.replaceRoom(CaveRoom.NORTH);
				}
				mapCol++;
			}
			mapRow ++;
			mapCol = 0;
		}
	}
}
