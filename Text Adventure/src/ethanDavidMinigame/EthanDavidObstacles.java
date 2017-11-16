package ethanDavidMinigame;

public class EthanDavidObstacles{
	
	boolean userHit;

	public EthanDavidObstacles(String description) {
		setUserHit(false);
	}
	
	public boolean isUserHit() {
		return userHit;
	}

	public void setUserHit(boolean userHit) {
		this.userHit = userHit;
	}

	public String getContents() {
		return "---";
	}

}
