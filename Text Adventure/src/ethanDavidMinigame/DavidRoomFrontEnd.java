package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPCRoom;

public class DavidRoomFrontEnd extends NPCRoom {

	public DavidRoomFrontEnd(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	public String spawnPolice() {
		int numberOfPolice = (int)(Math.random() * 5) + 3;
	}
	
}
