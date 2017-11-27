package caveExplorer;


public class Merchant extends NPC {

	
	private int currentRow;
	private int currentCol;
	private NPCRoom currentRoom;
	
	private CaveRoom[][] floor;
	private boolean active;
	private String activeDescription;
	private String inactiveDescription;
		
	public Merchant(int r, int c, CaveRoom[][] cave) {
		super(r, c, cave);
		this.floor = CaveExplorer.caves;
		this.activeDescription = "I am the local merchant. We have not met yet but I give away free stuff to whoever can answer my question.";
		this.inactiveDescription = "The merchant does not want to talk anymore.";
		this.currentCol = c;
		this.currentRow = r;
		currentRoom = null;
		active = true;
	}
	public String getSymbol() {
		return "M";
	}
		
	public String getDescription() {
		return activeDescription;
	}
	
	public String getInactiveDescription() {
		return inactiveDescription;
	}
	
	public void interact() {
		CaveExplorer.print("I will give you 5 keys if you can tell me how many legs a spider has.");
		String s = CaveExplorer.in.nextLine();
		while(s.equals("8")){
			CaveExplorer.print("Try again!");
			s = CaveExplorer.in.nextLine();
		}
		CaveExplorer.print("Congrats, now you will be able to play games with those keys.");
		active = false;
		johnsonDanielMinigame.DanielLockerGame.main(null);
		
	}

}
