package caveExplorer;

public class Inventory {
	
	private String map;
	private int cash;
	
	public Inventory()
	{
		cash = 0;
		updateMap();
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public void updateMap() {
		map = " ";
		// make for. line across top:
		for(int i = 0; i< CaveExplorer.caves[0].length -1; i++)
		{
			map += "____";//4
		}
		map +="___ \n";//3
		for(CaveRoom[] row: CaveExplorer.caves)
		{
			for(int i = 0; i< 3; i++)
			{
				String text = "";
				for(CaveRoom cr: row)
				{
					//if a wall is present draw a line otherwise draw a \
					if(cr.getDoor(CaveRoom.WEST) != null && cr.getDoor(CaveRoom.WEST).isOpen() )
					{
						text += "  ";
					}
					else
					{
						text += "|";
					}
					if(i == 0)
					{
						text += "   ";
					}
					else
					{
						if(i ==1)
						{
							text += " "+ cr.getContents() + " ";
						}
						else
						{
							if(i ==2)
							{
								if(cr.getDoor(CaveRoom.SOUTH) != null && cr.getDoor(CaveRoom.SOUTH).isOpen())
								{
									text += "   ";
								}
								else
								{
									text += "___";
								}
							}
						}
					}
				}
				text += "|";
				map += text +"\n";
			}
		}
		
	}

	public String getDescription()
	{
		return map;
	}
}
