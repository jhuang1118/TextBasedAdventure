package ethanDavidMinigame;

public class DavidEthanRoom {

	private boolean userIn;
	private boolean containsTreasure;
	private int money;
	
	public boolean isUserIn() {
		return userIn;
	}

	public void setUserIn(boolean userIn) {
		this.userIn = userIn;
	}
	
	public DavidEthanRoom() {
		
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
			return " ";
		}
	}

}
