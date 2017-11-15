package ethanDavidMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.NPCRoom;

/* INVALID CODE NEEDS TO BE MINIGAME*/
public class EthanRoomBackEnd implements DavidSupport{
	
	private int MONEYCUTOFF;
	private EthanSupport frontend;
	
	public EthanRoomBackEnd(EthanSupport frontend) {
		this.frontend = frontend;
		MONEYCUTOFF = 100000;
	}
	
	public boolean hitLaser() {
		if(EthanDavidObstacles.isUserHit()) {
			
		}
	}
	
	public void generateCash() {
		
	}
	
	public boolean isExited() {
		return false;
	}

	@Override
	public boolean stillPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValidUserInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startTimer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object victorious() {
		// TODO Auto-generated method stub
		return null;
	}
}