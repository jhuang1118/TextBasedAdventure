package johnsonDanielMinigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DanielStealthBackEnd {
	private Room[][] board;
	private List<Agent> agents;
	private final int rows;
	private final int cols;
	private Player player;
	public static final int PLAYING = 0;
	public static final int WIN = 1;
	public static final int LOSE = 2;
	private int state = PLAYING;
	private Treasure treasure;
	
	public DanielStealthBackEnd(int r, int c) {
		this.rows = r;
		this.cols = c;
		this.board = new Room[r][c];
		initBoard();
		
		this.agents = new ArrayList<>();
		placeAgents();
		
		Player user = new Player(0, 0);
		this.player = user;
		placeObject(user);
		
		Treasure win = new Treasure(r-1, c-1);
		this.treasure = win;
		placeObject(win);
	}
	
	public boolean move(String dir) {
		moveAgents();
		player.move(dir);
		if (playerOnTreasure()) {
			this.setState(WIN);
		}
		if (playerSighted()) {
			this.setState(LOSE);
		}
		return true;
	}
	
	private boolean playerOnTreasure() {
		return this.player.getRow() == this.treasure.getRow() && this.player.getCol() == this.treasure.getCol();
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
	private void moveAgents() {
		for (Agent a : agents) {
			a.move();
		}
	}
	
	private boolean playerSighted() {
		boolean found = false;
		for (Agent a : agents) {
			// if agent moves up and down
			if (a.getType() == 0) {
				if (a.getDirection() == Agent.UP) {
					found = found || a.getCol() == player.getCol() && player.getRow() <= a.getRow();
				}
				else if (a.getDirection() == Agent.DOWN) {
					found = found ||  a.getCol() == player.getCol() && player.getRow() >= a.getRow();
				}
			} else if (a.getType() == 1) {
				if (a.getDirection() == Agent.LEFT) {
					found = found ||  a.getRow() == player.getRow() && player.getCol() <= a.getCol();
				} else if (a.getDirection() == Agent.RIGHT) {
					found = found ||  a.getRow() == player.getRow() && player.getCol() >= a.getCol();
				}
			}
		}
		return found;
	}
	
	private void initBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Room(this);
			}
		}
	}

	public Room[][] getBoard() {
		return board;
	}

	private void placeObject(Placeable p) {
		board[p.getRow()][p.getCol()].setObject(p);
	}
	
 	private void placeAgents() {
		Random rand = new Random();
		for (int c = 1; c < cols; c++) {
			if (Math.random() < 0.5) {
				int r = rand.nextInt(rows - 1) + 1;
				Agent agent = new Agent(r, c, rows - 1, cols -1);
				agents.add(agent);
				placeObject(agent);
			}
		}
	}
 	
 	@Override
	public String toString() {
 		String board = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board += this.board[i][j];
			}
			board += "\n";
		}
		return board;
	}

	public int getState() {
		return state;
	}

	private void setState(int state) {
		this.state = state;
	}
}
