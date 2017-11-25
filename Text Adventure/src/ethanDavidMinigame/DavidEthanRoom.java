package ethanDavidMinigame;

public class DavidEthanRoom {

	private boolean userIn;
	private boolean containsTreasure;
	private boolean containsLaser;
	private int invincibleCounter;
	private int money;
	private boolean containsPowerup;

	public int getInvincibleCounter() {
		return invincibleCounter;
	}

	public void setInvincibleCounter(int invincibleCounter) {
		this.invincibleCounter = invincibleCounter;
	}

	public boolean isContainsLaser() {
		return containsLaser;
	}

	public void setContainsLaser(boolean containsLaser) {
		this.containsLaser = containsLaser;
	}
	
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
		if(containsLaser) {
			return "L";
		}
		if(containsPowerup) {
			return "P";
		}
		return " ";
	}

	public boolean isContainsPowerup() {
		return containsPowerup;
	}

	public void setContainsPowerup(boolean containsPowerup) {
		this.containsPowerup = containsPowerup;
	}
}
