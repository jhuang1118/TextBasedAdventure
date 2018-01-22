package johnsonDanielMinigame;

public class Room {
	private boolean hasAgent;
	private boolean hasPlayer;
	private boolean hasTreasure;
	private Placeable object;
	private DanielStealthBackEnd board;

	public Room(DanielStealthBackEnd board) {
		this.hasAgent = false;
		this.hasPlayer = false;
		this.hasTreasure = false;
		this.object = null;
		this.setBoard(board);
	}
	
	public boolean hasAgent() {
		return hasAgent;
	}

	public Placeable getCurrentObject() {
		return object;
	}

	public boolean hasPlayer() {
		return hasPlayer;
	}

	public void setObject(Placeable p) {
		if (p instanceof Player) {
			this.hasPlayer = true;
		} else if (p instanceof Agent) {
			this.hasAgent = true;
		} else if (p instanceof Treasure) {
			this.hasTreasure = true;
		}
		p.setRoom(this);
		this.object = p;
	}
	
	public void removeObject() {
		if (this.object instanceof Treasure) {
			return;
		}
		this.object = null;
		this.hasPlayer = false;
		this.hasAgent = false;
		this.hasTreasure = false;
	}
	
	@Override
	public String toString() {
		if (this.getCurrentObject() == null) {
			return "[ ]";
 		}
		return "[" + this.getCurrentObject() + "]";
	}

	public boolean hasTreasure() {
		return hasTreasure;
	}

	public DanielStealthBackEnd getBoard() {
		return board;
	}

	public void setBoard(DanielStealthBackEnd board) {
		this.board = board;
	}

}
