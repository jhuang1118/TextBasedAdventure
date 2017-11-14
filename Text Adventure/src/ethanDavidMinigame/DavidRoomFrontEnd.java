package ethanDavidMinigame;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidSupport backend;
	private int moveCount;
	

	public static void main(String[] args) {
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play();
	}
	
	private void play() {
		while(backend.stillPlaying()) {
			displayBoard();
			String input = backend.getValidUserInput();
			respondToInput(input);
			backend.startTimer();
		}
		printGameOverMessage(backend.victorious());
	}
	
	public void printGameOverMessage(Object victorious) {
		System.out.println("You're dead.");
	}

	public void displayTimer() {
		System.out.println("");
	}

	public void respondToInput(String input) {
		// TODO Auto-generated method stub
		
	}

	public void displayBoard() {
		//spawn cash piles (randomly)
		//spawn obstacles (randomly)
		//if they choose the same random location choose somewhere else
		
	}

	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
