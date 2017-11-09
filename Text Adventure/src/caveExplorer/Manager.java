package caveExplorer;

public class Manager extends NPC {

	private boolean ID = true;
	private NPCRoom[][] office;
	private NPCRoom currentRoom;
	
	public Manager(NPCRoom[][] manCave, int row, int col)
	{
		this.office = manCave;
		this.currentRoom = manCave[row][col];
		// chance for manager to not have ID
		int chance = (int) Math.random();
		if(chance >= .5)
		{
			ID = false;
		}
	}
	
	//When people dies
	public void death()
	{
		CaveExplorer.inventory.setID(this.ID);
	}
}
