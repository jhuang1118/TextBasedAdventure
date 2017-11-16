package ethanDavidMinigame;

public class DavidEthanRoom {

	private boolean containsTreasure;
	
	public DavidEthanRoom() {
		// TODO Auto-generated constructor stub
	}

	public boolean isContainsTreasure() {
		return containsTreasure;
	}

	public void setContainsTreasure(boolean containsTreasure) {
		this.containsTreasure = containsTreasure;
	}
	
	public String toString() {
		if(containsTreasure) {
			return "X";
		}
		else {
			return "O";
		}
	}

}
