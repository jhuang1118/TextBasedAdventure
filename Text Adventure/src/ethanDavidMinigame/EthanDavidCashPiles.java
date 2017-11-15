package ethanDavidMinigame;

import caveExplorer.NPCRoom;

public class EthanDavidCashPiles extends NPCRoom {

	public EthanDavidCashPiles(String description) {
		super(description);
	}

	public int value() {
		return 0;
		
	}
	
	public String getContents() {
		return "$";
	}
}
