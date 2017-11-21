package ethanDavidMinigame;

public class EthanDavidPowerUp extends DavidEthanRoom{

	private boolean obtained;
	
	EthanDavidPowerUp(boolean obtained){
		this.obtained = obtained;
	}

	public String toString() {
		return "O";
	}

	public boolean isObtained() {
		return obtained;
	}

	public void setObtained(boolean obtained) {
		this.obtained = obtained;
	}
}
