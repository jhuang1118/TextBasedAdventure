package johnsonDanielMinigame;

public class Player implements Placeable {
	private int row;
	private int col;
	private Room room;
	public Player(int r, int c) {
		this.row = r;
		this.col = c;
	}
	
	public boolean move(String dir) {
		switch(dir) {
			case "w":
				if (row == 0) {
					return false;
				}
				this.row--;
				break;
			case "d":
				if (col == this.room.getBoard().getCols() - 1) {
					return false;
				}
				this.col++;
				break;
			case "s":
				if (row == this.room.getBoard().getRows() - 1) {
					return false;
				}
				this.row++;
				break;
			case "a":
				if (col == 0) {
					return false;
				}
				this.col--;
				break;
			default:
				return false;
		}
		this.room.removeObject();
		Room newRoom = this.room.getBoard().getBoard()[row][col];
		newRoom.setObject(this);
		this.setRoom(newRoom);
		return true;
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
		return "P";
	}
	@Override
	public void setRoom(Room r) {
		this.room = r;
	}
}
