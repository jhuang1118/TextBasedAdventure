package ethanDavidMinigame;

public class DavidEthanRoom {

	private boolean containsTreasure;
	private int money;
	
	public DavidEthanRoom() {
		// TODO Auto-generated constructor stub
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public boolean isContainsTreasure() {
		return containsTreasure;
	}

	public void setContainsTreasure(boolean containsTreasure) {
		this.containsTreasure = containsTreasure;
	}
	
	public String toString() {
		if(containsTreasure) {
			return "$";
		}
		else {
			return "O";
		}
	}

}
