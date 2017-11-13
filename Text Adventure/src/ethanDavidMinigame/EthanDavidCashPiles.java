package ethanDavidMinigame;

import caveExplorer.NPCRoom;

public class EthanDavidCashPiles extends NPCRoom {

	public EthanDavidCashPiles(String description) {
		super(description);
	}

	public String getContents() {
		return "$";
	}
}
