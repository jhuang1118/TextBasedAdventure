package caveExplorer;

public class NPCRoom extends CaveRoom {

	private NPC car;
	
	public NPCRoom(String description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canEnter() {
		return npc == null;
	}
	
	public void enterNPC(Car car) {
		this.npc = car;
	}
	
	public void leaveNPC() {
		this.npc = null;
	}
	
	public boolean containsNPC() {
		return npc != null;
	}
	
	/**
	 * Everything above is a NEW function of a CaveRoom,
	 * the methods below REPLACE original CaveRoom methods
	 */
	
	public void printValidMoves() {
		System.out.println("You can only enter 'w', 'a', 's', or 'd' to move " + 
				"or you can press 'e' to interact.");
		
	}
	public String validMoves() {
		return "wdsae";
	}
	/**
	 * override to create response to keys other than wdsa
	 * @param direction
	 */
	public void performAction(int direction) {
		if(direction == 4) {
			if(npc != null && npc.isActive()) {
				npc.interact();
			}else {
				CaveExplorer.print("There is nothing to interact with.");
			}
		}else {
			CaveExplorer.print("That key does nothing");
		}
		
	}
	
	public String getContents() {
		if(containsNPC() && npc.isActive()) {
			return npc.getSymbol();
		}else {
			return super.getContents();
		}
	}
	
	public String getDescription() {
		if(containsNPC() && npc.isActive()) {
			return super.getDescription() + "\n" + npc.getDescription();
		}else if(containsNPC() && !npc.isActive()){
			return super.getDescription() + "\n" + npc.getInactiveDescription();
		}else {
			return super.getDescription();
		}
	}
}
