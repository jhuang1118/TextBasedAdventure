package ethanDavidMinigame;

public class EthanDavidPowerUp extends DavidEthanRoom{

	private boolean obtained;
	private boolean containsTreasure;
	
	EthanDavidPowerUp(boolean obtained){
		this.obtained = obtained;
	}

	public String toString() {
		return "P";
	}

	public boolean isObtained() {
		return obtained;
	}
	
	public boolean isContainsTreasure() {
		return containsTreasure;
	}

	public void setContainsTreasure(boolean containsTreasure) {
		this.containsTreasure = containsTreasure;
	}

	public void setObtained(boolean obtained) {
		this.obtained = obtained;
	}
}
