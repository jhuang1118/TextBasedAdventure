package johnsonDanielMinigame;

import caveExplorer.CaveExplorer;

public class DanielLockerGame {

	private DanielLocker[][] theLockers;
	private boolean onLocker;
	int[] rows = {1,3};
	int[] cols = {1,3,5};
	
	public DanielLockerGame() {
		theLockers = new DanielLocker[6][6];
		onLocker = false;
		makeLocker(theLockers,1,1);
		makeLocker(theLockers,1,3);
		makeLocker(theLockers,1,5);
		makeLocker(theLockers,3,1);
		makeLocker(theLockers,3,3);
		makeLocker(theLockers,3,5);
	}
	
	public boolean isOnLocker() {
		return onLocker;
	}

	public void setOnLocker(boolean onLocker) {
		this.onLocker = onLocker;
	}

	public static void main(String[] args) {
		DanielLockerGame demo = new DanielLockerGame();
		demo.play();
	}

	private void makeLocker(DanielLocker[][] arr,int row, int col) {
		arr[row][col] = new DanielLocker();
	}
	public void play() {
		int rowSelected = generateRandNum(0,rows.length-1);
		int colSelected = generateRandNum(0,cols.length-1);
		
		createThing(rowSelected, colSelected);
		
		
		
		
		System.out.println("In this part of the game you will walk around the map and go to lockers. You will have only 3 keys and each key can open any locker only once. "
				+ "These lockers contain either the person you are looking for, a bomb, or"
				+ " nothing. When you walk up to a locker, press 'x'");
		int keys = theLockers.length / 2;
		while(keys > 0) {
			displayBoard();
			displayKeysLeft(keys);
			String input = getValidUserInput();
			answer(input);
			keys--;
		}
		printGG();
	}

	
	private void createThing(int rowSelected, int colSelected) {
		theLockers[rowSelected][colSelected].setPerson(true);
		theLockers[rowSelected][colSelected].setEmpty(false);
		
		
		
		int[] options = generateRandNumExcept(rowSelected, colSelected);
		theLockers[options[0]][options[1]].setBomb(true);
		theLockers[options[0]][options[1]].setEmpty(false);
	}

	private int generateRandNum(int min, int max) {
		return (int)(Math.random()*(max - min + 1));		
	}
	
	private int[] generateRandNumExcept(int exceptRow, int exceptCol) {
		int[] arr  = new int[2]; 
		int bombRow = generateRandNum(0,rows.length-1);
		int bombCol = generateRandNum(0,cols.length-1);
		
		while(bombRow == exceptRow && bombCol == exceptCol) {
			bombRow = generateRandNum(0,rows.length-1);
			bombCol = generateRandNum(0,cols.length-1);
		}
		arr[0] = bombRow;
		arr[1] = bombCol;
		
		return arr;
	}

	private void printGG() {
		System.out.println("You Lose :(");		
	}

	private String getValidUserInput() {
		String input = CaveExplorer.in.nextLine();
		
		while(!validInput(input)) {
			System.out.print("Please enter 'w', 'a', 's', 'd'.");
			input = CaveExplorer.in.nextLine();
			return input;
		}
			
		return input;
		
	
	}

	private boolean validInput(String input) {
		String[] directions = {"w","a","s","d"};
		if(input.length() == 1) {
			for(int i = 0; i < directions.length; i++) {
				if(input != directions[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean isOnLocker(int row, int col) {
		int[][] lockerpsns = {{1,1},{1,3},{1,5},{3,1},{3,3},{3,5}};
		for(int i = 0; i < lockerpsns.length; i++) {
			if(lockerpsns[i] == [row][col]) {
		}
		return false;
		
		
	}
	private void answer(String input) {
		// write a method to interpret the input. For example, if the guy moves left then his col would shift left 1. If he 
		// is one tile away, then 
		
	}

	private void displayKeysLeft(int keys) {
		System.out.println("You have " + keys + " keys left.");
	}

	private void displayBoard() {
		// TODO Auto-generated method stub
		
	}

}
