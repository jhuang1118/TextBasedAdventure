package johnsonDanielMinigame;

public interface Placeable {
	public int getRow();
	public int getCol();
	public void setRow(int row);
	public void setCol(int col);
	public String toString();
	public void setRoom(Room r);
}
