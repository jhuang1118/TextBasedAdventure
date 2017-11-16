package ethanDavidMinigame;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidEthanRoom[][] rooms;
	private int currentRow;
	private int currentCol;
	
	private DavidSupport backend;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static String map;
	
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
		System.out.println("You have 40 seconds to collect money!");
	}

	public void respondToInput(String input) {
		// TODO Auto-generated method stub
		
	}

	public void displayBoard() {
		//spawn cash piles (randomly)
		//spawn obstacles (randomly)
		//if they choose the same random location choose somewhere else
		for(int row = 0; row < rooms.length; row++) {
			for(int col = 0; col < rooms[row].length; col++){
				if(row == currentRow && col == currentCol) {
					System.out.print("V");
				}
				else {
					System.out.print(rooms[row][col]);
				}
			}
			System.out.println("");
		}
		}	
				
				

	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
		rooms = new DavidEthanRoom[10][10];
		for(DavidEthanRoom[] row: rooms) {
			for(int col = 0; col < row.length; col++) {
				row[col] = new DavidEthanRoom();
			}
		}
	}

}
