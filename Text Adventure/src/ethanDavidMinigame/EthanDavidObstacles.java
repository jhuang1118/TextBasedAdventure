package ethanDavidMinigame;

import caveExplorer.CaveRoom;

public class EthanDavidObstacles extends CaveRoom {
	
	boolean userHit;

	public EthanDavidObstacles(String description) {
		super(description);
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
