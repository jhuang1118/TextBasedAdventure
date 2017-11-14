package ethanDavidMinigame;

import caveExplorer.CaveRoom;

public class EthanDavidObstacles extends CaveRoom {

	public EthanDavidObstacles(String description) {
		super(description);
	}
	
	public String getContents() {
		return "---";
	}

}
