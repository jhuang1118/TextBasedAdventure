package jasonYJasonZMinigame;

public class GameRoom {

	private int row;
	private int col;
	private boolean isObstacle;
	
	public GameRoom(int row, int col){
		isObstacle = false;
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isObstacle() {
		return isObstacle;
	}
	
}
