package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPCRoom;

public class DavidRoomFrontEnd extends NPCRoom {
	
	public int index;

	public DavidRoomFrontEnd(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	
	/* public void spawnPolice() {
		int numberOfPolice = (int)(Math.random() * 5) + 3;
		int policeOnSite = 0;
		while(policeOnSite != numberOfPolice) {
			CaveRoom[][] c = CaveExplorer.caves;
			int index = (int)(Math.random() * c.length);
			while(index == cRIndex) {
				index = (int)(Math.random() * c.length);
			}
			c[index][index] = new DavidRoomFrontEnd("Police!");
			policeOnSite++;
		}
	} */
	
}
