package caveExplorer;

import jasonYJasonZMinigame.JasonZGuns;

public class Inventory {
	
	private boolean ID = false;
	
	public boolean isID() {
		return ID;
	}
	public void setID(boolean iD) {
		ID = iD;
	}

	private String map;

	private int cash;
	private String[] defaultGuns = {"M16", "0"};
	JasonZGuns currentGun = new JasonZGuns(defaultGuns);

	private static int hp;


	public Inventory()
	{
		setCash(0);
		updateMap(CaveExplorer.caves);
	}
	
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public void updateMap(CaveRoom[][] caves) {
		map = " ";
		// make for. line across top:
		for(int i = 0; i< caves.length -1; i++)
		{
			map += "____";//4
		}
		map +="___ \n";//3
		for(CaveRoom[] row: caves)
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
		return map + "\n" + "Also you have " + getCash() + " cash \n";
	}
	public void updateHP() {
		
		hp = 100;
	}
	
	public static int getHP() {
		return hp;
	}
	
}
