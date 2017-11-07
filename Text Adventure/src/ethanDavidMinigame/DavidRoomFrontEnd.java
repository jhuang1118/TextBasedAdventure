package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPCRoom;

public class DavidRoomFrontEnd extends NPCRoom {

	public DavidRoomFrontEnd(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	public String getSymbol() {
		return "P";
	}
	
	public String spawnPolice() {
		int numberOfPolice = (int)(Math.random() * 5) + 3;
		for(int i = 0; i < numberOfPolice; i++) {
			CaveRoom[][] c = CaveExplorer.caves;
			int index = (int)(Math.random() * c.length);
		}
	}
	
}
