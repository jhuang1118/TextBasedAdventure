package johnsonDanielMinigame;

import java.util.Scanner;

//import caveExplorer.CaveExplorer;

public class JohnsonUnlockGame {
	public static Scanner inputSource = new Scanner(System.in);
	
	private JohnsonButton[][] theButtons;
	private int chances;
	private boolean won;
	
	public JohnsonUnlockGame() {
		theButtons = new JohnsonButton[6][6];
		chances = theButtons.length;
		won = false;
		for(int row = 0; row < theButtons.length; row++) {
			for(int col = 0; col < theButtons[row].length; col++) {
				theButtons[row][col] = new JohnsonButton();
			}
		}
	}

	public static void main(String[] args) {
		JohnsonUnlockGame demo = new JohnsonUnlockGame();
		demo.play();
	}
	
	public void play(){
		int row = (int)(Math.random()*(theButtons.length -1));
		int col = (int)(Math.random()* (theButtons[row].length -1));
		//JohnsonButton rightButton = theButtons[row][col];
		
	    setColors(row, col);
	    System.out.println("You have to find the button to unlock the locker. Each square will indicate a 'R', 'Y', or 'G' when you move to it."
	    		+ "\n" + " R means you are very close, Y means you are somewhat close, and G means you are not close to it."
	    				+ " To play, please enter 2 numbers: the row of the button you wish to press \n and the column of the button you wish to press");
	   while(chances > 0){
	        displayBoard();
	        displayChancesLeft(chances);
	        String input = getValidUserInput();
	        respondToInput(input);
	       
	        
	        chances--;
	    }
	        printGameOverMessage(row, col);
	}

	private void printGameOverMessage(int row, int col) {
		if(won) {
			System.out.println("You won the game!!!");
		}else {
			System.out.println("Sorry, you used all your chances and haven't found the right button! The right button was at " + row + " , " + col);
		}
	}
	public String getInput() {
		return inputSource.nextLine();
	}
	private void respondToInput(String input) {
		int num1 = Integer.parseInt(input.substring(0,1));
		int num2 = Integer.parseInt(input.substring(1,2));
		JohnsonButton currentButton = theButtons[num1][num2];
		if(currentButton.isRevealed() == false) {
			String colorOfSquare = currentButton.getColor();
			currentButton.setRevealed(true);
			if(colorOfSquare == "B") {
				currentButton.setTrigger(true);
				won = true;
				chances = 0;
			}else if(colorOfSquare == "R") {
				System.out.println("You are very close to the right button!!");
				chances += 2;
			}else if(colorOfSquare == "Y") {
				System.out.println("You are kind of close to the right button!");
				chances++;
			}else if(colorOfSquare == "G") {
				System.out.println("You are not that close. I suggest you pick a button elsewhere.");
				chances--;
			}
			
		}else
		System.out.println("You have already pressed this button. You cannot press it again.");
		chances++;
	}
	public String getValidUserInput() {
		String input = getInput();

		while(input.length() != 2) {
			printValidMoves();
			System.out.print("Please follow directions. Remember, do not include commas or spaces!");
			getInput();
		}
		if(input.substring(0,1).equals("c") && input.substring(1,2).equals("c")) {
			won = true;
			printGameOverMessage(0,0);
			DanielLockerGame.main(null);
		}
		String num1 = input.substring(0,1);
		String num2 = input.substring(1,2);
		while(((isValid(num1) == false || isValid(num2) == false))) {
			printValidMoves();
			System.out.print("Please enter a valid input. Just numbers, no letters or weird symbols!");
			input = getInput();
			return input;
		}
		
		return input;
		
	}
	private boolean isValid(String input) {
		return validMoves().indexOf(input) != -1 && input.length() == 1;
	}
	private void printValidMoves() {
		System.out.println("Enter two numbers from 0 to 5.");
		
	}

	private String validMoves() {
		return "012345";
	}
	private void displayChancesLeft(int numChances) {
		System.out.println("You have " + numChances + " chances left.");
		
	}

	private void displayBoard() {
		for(int row = 0; row < theButtons.length; row++) {
			for(int col = 0; col < theButtons[row].length; col++) {
				System.out.print(theButtons[row][col]);
			}
			System.out.println("");
		}
	}

	private void setColors(int targetRow, int targetCol) {
		String color = "";
		String[] colorArray = {"B","R","Y","G"};
		for(int i = 0; i < theButtons.length; i++) {
			for(int j = 0; j < theButtons[i].length; j++) {
				boolean[] colorNum = determineColor(targetRow, targetCol, i, j);
				int index = indexOf(colorNum);
				color = colorArray[index];
				theButtons[i][j].setColor(color);
			}
		}
	}
	private boolean[] determineColor(int targetRow, int targetCol, int currentRow, int currentCol) {
		boolean[] checklist = new boolean[4]; 
		double distance = Math.hypot(targetRow - currentRow, targetCol - currentCol);
		
		checklist[0] = (distance == 0);
		checklist[1] = (distance > 0 && distance <= sqrt(2));
		checklist[2] = (distance > sqrt(2) && distance <= sqrt(8));
		checklist[3] = (distance > sqrt(8));
	
		return checklist;
	}
	private double sqrt(int num) {
		return Math.sqrt(num);
	}
	private int indexOf(boolean[] arr) {
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i]) {
				index = i;
			}
		}
		return index;
	}
	
	
}
