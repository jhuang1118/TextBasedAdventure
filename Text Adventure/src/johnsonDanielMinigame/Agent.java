package johnsonDanielMinigame;

public class Agent implements Placeable {
	private int row;
	private int col;
	private final int type; // type 0 = move up and down, type 1 = left and right
	private int direction;
	private int boundX; // max X
	private int boundY; // max Y
	public static final int UP = -1;
	public static final int RIGHT = 1;
	public static final int DOWN = 1;
	public static final int LEFT = -1;
	private Room room;
	
	public Agent(int r, int c, int boundX, int boundY) {
		this.row = r;
		this.col = c;
		this.boundX = boundX;
		this.boundY = boundY;
		if (Math.random() < 0.5) {
			this.type = 0;
			if (Math.random() < 0.5) {
				this.direction = UP;
			} else {
				this.direction = DOWN;
			}
		} else {
			this.type = 1;
			if (Math.random() < 0.5) {
				this.direction = RIGHT;
			} else {
				this.direction = LEFT;
			}
		}
	}
	
	public void move() {
		// up and down agent
		if (this.type == 0) {
			// Check for bounds, move the other way if on edge
			if (this.row == 0) {
				this.direction = DOWN;
			} else if (this.row == boundY) {
				this.direction = UP;
			}
			// Move
			this.setRow(this.getRow() + this.direction);
		// left and right agent
		} else {
			if (this.col == 0) {
				this.direction = RIGHT;
			} else if (this.col == boundX) {
				this.direction = LEFT;
			}
			// Move
			this.setCol(this.getCol() + this.direction);
		}
		this.room.removeObject();
		Room newRoom = this.room.getBoard().getBoard()[row][col];
		newRoom.setObject(this);
		this.setRoom(newRoom);
	}
	
	public int getType() {
		return type;
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
		return "A";
	}

	@Override
	public void setRoom(Room r) {
		this.room = r;
	}
	public int getDirection() {
		return direction;
	}
}
