package caveExplorer;

public class Manager extends NPC {

	private boolean ID = true;
	private NPCRoom[][] office;
	private NPCRoom currentRoom;
	
	//checker
	public Manager(NPCRoom[][] manCave, int row, int col)
	{
		super(row, col, manCave);
		this.office = manCave;
		this.currentRoom = manCave[row][col];
		// chance for manager to not have ID
	}
	
	//When manager dies
	public void death()
	{
		CaveExplorer.inventory.setID(this.ID);
	}
	
	public String getDescription()
	{
		return "The manager is afraid press 'e' to interact.";
		
	}
	
	public String getInactiveDescription()
	{
		return "The manager is unresponsive.";
		
	}
}
