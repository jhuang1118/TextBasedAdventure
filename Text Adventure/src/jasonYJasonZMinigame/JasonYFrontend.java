package jasonYJasonZMinigame;

public class JasonYFrontend implements JasonZSupport {

	private JasonZSupport backend;
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	public static final void main(String[] args) {
		JasonYFrontend demo = new JasonYFrontend();
		demo.play();
	}
	
	public JasonYFrontend() {
		backend = new JasonZBackend(this);
	}

	private void createMap() {
		//makes the map
	}
	
	private void follow(){
		//npcs in the map will move towards the player after played inputs a move
	}
	
	private void rangeDisplay() {
		//colors in a number of boxes based on the range of the gun
		//should be colored red 
	}
	
	private void play() {
		//introduction()
		//createMap();
		//populateMap();
		//displays final message when player wins or dies
	}
	
	private void populateMap() {
		//populates the map with cops
	}
	
	
}
