package johnsonDanielMinigame;

import caveExplorer.CaveRoom;

public class MiniGameStarter extends CaveRoom {

	public MiniGameStarter(String description, int row, int col) {
		super(description, row, col);
		// TODO Auto-generated constructor stub
	}
	public void enter() {
		super.enter();
		DanielStealth.start();
	}

}
