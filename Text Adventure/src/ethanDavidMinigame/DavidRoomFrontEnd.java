package ethanDavidMinigame;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidSupport backend;
	private int moveCount;
	

	public static void main(String[] args) {
		DavidRoomFrontEnd game = new DavidRoomFrontEnd();
		game.play();
	}
	
	private void play() {
		// TODO Auto-generated method stub
		
	}

	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
