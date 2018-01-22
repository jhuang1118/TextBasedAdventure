package johnsonDanielMinigame;

public class Treasure implements Placeable {
	private int row;
	private int col;
	private Room room;
	public Treasure(int r, int c) {
		this.row = r;
		this.col = c;
	}
	@Override
	public int getRow() {
		return row;
	}
	@Override
	public void setRow(int row) {
		this.row = row;
	}
	@Override
	public int getCol() {
		return col;
	}
	@Override
	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "T";
	}
	@Override
	public void setRoom(Room r) {
		this.room = r;
	}
}
