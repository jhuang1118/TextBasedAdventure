package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;

public class EthanDavidCashPiles extends NPCRoom {
	
	private int value;

	public EthanDavidCashPiles(String description) {
		super(description);
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getContents() {
		return "$";
	}
	
	public void generateCash() {
		int randMoney = (int)(10 * Math.random() * 1000);
		setValue(randMoney);
	}
	
	public void addMoney() {
		CaveExplorer.inventory.setCash(CaveExplorer.inventory.getCash() + getValue());
	}
}
