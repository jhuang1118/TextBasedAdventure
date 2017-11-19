package ethanDavidMinigame;

public class DavidEthanRoom {

	
	public boolean isUserIn() {
		return userIn;
	}

	public void setUserIn(boolean userIn) {
		this.userIn = userIn;
	}

	public boolean isContainLaser() {
		return containLaser;
	}

	public void setContainLaser(boolean containLaser) {
		this.containLaser = containLaser;
	}

	private boolean userIn;
	private boolean containLaser;
	private boolean containsTreasure;
	private int money;
	
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
			return "O";
		}
	}

}
