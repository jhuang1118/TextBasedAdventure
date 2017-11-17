package jasonYJasonZMinigame;

import caveExplorer.CaveRoom;

public class GameRoom extends CaveRoom{

	private int row;
	private int col;
	private boolean isObstacle;
	
	public GameRoom(int row, int col){
		super("empty area", row, col);
		isObstacle = false;
		this.row = row;
		this.col = col;
	} 

	public boolean isObstacle() {
		return isObstacle;
	}
	
}
