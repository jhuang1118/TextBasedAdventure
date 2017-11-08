package caveExplorer;

public class TreasureRoom extends NPCRoom {

	public boolean entered = false;
	private int treasure;
	private String content = "T";
	private String description = "";
	public TreasureRoom(String description) {
		super(description);
		treasure = (int) (Math.random()*100);
	}
	
	public void enteredRoom()
	{
		if(!entered)
		{
			int base = CaveExplorer.inventory.getCash();
			base += treasure;
			CaveExplorer.inventory.setCash(base);
			entered = true;
		}
	}
	
	public String getDescription()
	{
		description = "You got "+ treasure ;
		return description;
	}
	
	public String getContents()
	{
		return content;
	}

}
