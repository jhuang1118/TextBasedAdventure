package johnsonDanielMinigame;

public class DanielLockerGame {

	private DanielLocker[][] theLockers;

	public DanielLockerGame() {
		theLockers = new DanielLocker[6][6];
		makeLocker(theLockers,1,1);
		makeLocker(theLockers,1,3);
		makeLocker(theLockers,1,5);
		makeLocker(theLockers,3,1);
		makeLocker(theLockers,3,3);
		makeLocker(theLockers,3,5);
	}
	
	public static void main(String[] args) {
		DanielLockerGame demo = new DanielLockerGame();
		demo.play();
	}

	private void makeLocker(DanielLocker[][] arr,int row, int col) {
		arr[row][col] = new DanielLocker();
	}
	public void play() {
		int[] rows = {1,3};
		int[] cols = {1,3,5};
		int rowSelected = generateRandNum(0,rows.length-1);
		int colSelected = generateRandNum(0,cols.length-1);
		
		
		
		
		
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

	
	private int generateRandNum(int min, int max) {
		return (int)(Math.random()*(max - min + 1));		
	}

	private void printGG() {
		System.out.println("You Lose :(");		
	}

	private String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	private void answer(String input) {
		// TODO Auto-generated method stub
		
	}

	private void displayKeysLeft(int keys) {
		
	}

	private void displayBoard() {
		// TODO Auto-generated method stub
		
	}

}
