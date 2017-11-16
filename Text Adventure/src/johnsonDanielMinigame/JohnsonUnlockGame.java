package johnsonDanielMinigame;

import caveExplorer.CaveExplorer;

public class JohnsonUnlockGame {
	
	private JohnsonButton[][] theButtons;
	
	public JohnsonUnlockGame() {
		theButtons = new JohnsonButton[6][6];
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
		int row = (int)Math.random()*theButtons.length;
		int col = (int)Math.random()* theButtons[0].length;
		//JohnsonButton rightButton = theButtons[row][col];
		int chances = theButtons.length;
	    setColors(row, col);
	    System.out.println("You have to find the button to unlock the locker. Each square will indicate a 'R', 'Y', or 'G' when you move to it."
	    		+ "\n" + " R means you are very close, Y means you are somewhat close, and G means you are not close to it."
	    				+ " To play, please enter 2 numbers: the row of the button you wish to press and the column of the button you wish to press");
	   while(chances > 0){
	        displayBoard();
	        displayChancesLeft(chances);
	        String input = getValidUserInput();
	        respondToInput(input);
	       
	        
	        chances--;
	    }
	        printGameOverMessage();
	}

	private void printGameOverMessage() {
		System.out.println("Sorry, you used all your chances and haven't found the right button!");
		
	}

	private void respondToInput(String input) {
		int num1 = Integer.parseInt(input.substring(0,1));
		int num2 = Integer.parseInt(input.substring(1,2));
		JohnsonButton currentButton = theButtons[num1][num2];
		if(theButtons[num1][num2] != null) {
			String colorOfSquare = currentButton.getColor();
			theButtons[num1][num2].setRevealed(true);
			if(colorOfSquare == "B") {
				System.out.println("You won the game!!!");
				currentButton.setTrigger(true);
			}
			if(colorOfSquare == "R") {
				System.out.println("You are very close to the right button!!");
			}
			if(colorOfSquare == "Y") {
				System.out.println("You are kind of close to the right button!");
			}
			if(colorOfSquare == "G") {
				System.out.println("You are not that close. I suggest you pick a button elsewhere.");
			}
			currentButton = null;
		}
		System.out.println("You have already pressed this button. You cannot press it again.");
	}
	
	private String getValidUserInput() {
		String input = CaveExplorer.in.nextLine();
		String num1 = input.substring(0,1);
		String num2 = input.substring(1,2);
		if(!(isValid(num1) && isValid(num2))) {
			printValidMoves();
			CaveExplorer.in.nextLine();
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
		System.out.println(numChances);
		
	}

	private void displayBoard() {
		// TODO Auto-generated method stub
		
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
