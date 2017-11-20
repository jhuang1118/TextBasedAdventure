package ethanDavidMinigame;

public class EthanDavidObstacles extends DavidEthanRoom{
	
	boolean userHit;

	public EthanDavidObstacles() {
		setUserHit(false);
	}
	

	public boolean isUserHit() {
		return userHit;
	}

	public void setUserHit(boolean userHit) {
		this.userHit = userHit;
	}

	public String toString() {
		return "L";
	}

}
