package johnsonDanielMinigame;

import caveExplorer.CaveRoom;

public class MiniGameStarter extends CaveRoom {

	public MiniGameStarter(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	public void enter() {
		super.enter();
		DanielLockerGame.main(null);
	}

}
