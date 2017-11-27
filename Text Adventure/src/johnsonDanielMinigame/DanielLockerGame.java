package johnsonDanielMinigame;

import java.util.Scanner;
import caveExplorer.CaveExplorer;

public class DanielLockerGame {

	int[][] placement= {{-1,0},{0,-1},{1,0},{0,1}};
	private DanielLocker[][] theLockers;
	private boolean onLocker;
	int[] rows = {1,3};
	int[] cols = {1,3,5};
	int ROWTOT = 6;
	int COLTOT = 6;
	private int newPsn;
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
			//answer(input);
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
			System.out.print("Please enter coordinates in the form row, col without any parenthesis or spaces. Remember the minimum row and columumn is 0 and the max is 5.");
			input = CaveExplorer.in.nextLine();
			return input;
		}

			

		return input;
	
		
	


	}

	private boolean validInput(String input) {
		String[] splitInput = input.split(",");
		if(splitInput.length == 2) {
			return Integer.valueOf(splitInput[0]) > 0 && Integer.valueOf(splitInput[0]) < 6 && Integer.valueOf(splitInput[1]) > 0 && Integer.valueOf(splitInput[0]) < 6; 
		}
		return false;
	}

	public boolean isOnLocker(int row, int col) {
		int[][] lockerpsns = {{1,1},{1,3},{1,5},{3,1},{3,3},{3,5}};
		int[][] personpsn = {{row,col}};
		int count = 0;
		for(int i = 0; i < lockerpsns.length; i++) {
			if(lockerpsns[i].equals(personpsn[0])) {
				count++;
			}
		}
		if(count > 0) {
			return true;
		}
		return false;
	
	}
	private void answer(String input) {


		if(input.equals("w") && isPossibleMove(placement[0])) {
			move(placement[0]);
		}
		else if(input.equals("a") && isPossibleMove(placement[1])){
			move(placement[1]);
		}
		else if(input.equals("s") && isPossibleMove(placement[2])) {
			move(placement[2]);
		}
		else if(input.equals("d") && isPossibleMove(placement[3])) {
			move(placement[3]);

		String[] splitInput = input.split(",");
		Integer coor1 = Integer.valueOf(splitInput[0]);
		Integer coor2 = Integer.valueOf(splitInput[1]);
		if(isOnLocker(coor1,coor2)) {
			theLockers[coor1][coor2].setOpen(true);
			johnsonDanielMinigame.JohnsonUnlockGame.main(null);

		}
	}


	private boolean isPossibleMove(int[] is) {
		// TODO Auto-generated method stub
		return false;
	}

	private void move(int[] placement) {
		if(isPossibleMove(int[])) {
			
		}

		String[] directions = {"w","a","s","d"};
		

		
	}


	private void displayKeysLeft(int keys) {
		System.out.println("You have " + keys + " keys left.");
	}

	private void displayBoard() {
		for(int row = 0; row < ROWTOT; row++) {
			for(int col = 0; col < COLTOT; col++) {
				System.out.print(theLockers[row][col]);
			}
			System.out.println(" ");
		}
	}

}
