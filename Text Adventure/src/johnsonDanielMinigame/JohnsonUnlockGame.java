package johnsonDanielMinigame;

public class JohnsonUnlockGame {
	
	private JohnsonButton[][] theButtons;
	
	public JohnsonUnlockGame() {
		theButtons = new JohnsonButton[5][5];
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
	    theButtons[(int)Math.random()*theButtons.length][(int)Math.random()*theButtons[0].length]
		int chances = theButtons.length;
	    setColors();		
	   while(chances > 0){
	        displayBoard();
	        displayChancesLeft();
	        String input = getValidUserInput();
	        respondToInput(input);
	       
	        
	        chances--;
	    }
	        printGameOverMessage();
	}

	private void printGameOverMessage() {
		// TODO Auto-generated method stub
		
	}

	private void respondToInput(String input) {
		// TODO Auto-generated method stub
		
	}

	private String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	private void displayChancesLeft() {
		// TODO Auto-generated method stub
		
	}

	private void displayBoard() {
		// TODO Auto-generated method stub
		
	}

	private void setColors() {
		// TODO Auto-generated method stub
		
	}
}
