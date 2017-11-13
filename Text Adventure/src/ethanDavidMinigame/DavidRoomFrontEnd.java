package ethanDavidMinigame;

public class DavidRoomFrontEnd implements EthanSupport {
	
	private DavidSupport backend;
	private int moveCount;
	

	public static void main(String[] args) {
		
	}
	
	public DavidRoomFrontEnd() {
		backend = new EthanRoomBackEnd(this);
	}

}
