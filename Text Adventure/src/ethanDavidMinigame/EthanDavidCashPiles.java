package ethanDavidMinigame;

import caveExplorer.NPCRoom;

public class EthanDavidCashPiles extends NPCRoom {
	
	private int value;

	public EthanDavidCashPiles(String description) {
		super(description);
	}

	public int getvalue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getContents() {
		return "$";
	}
}
