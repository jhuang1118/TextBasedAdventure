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
	
	private void printGameOverMessage(Object victorious) {
		System.out.println("You're dead.");
	}

	public void displayTimer() {
		System.out.println("");
	}

	public void respondToInput(String input) {
		// TODO Auto-generated method stub
		
	}

	public void displayBoard() {
		// TODO Auto-generated method stub
		
	}

	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
