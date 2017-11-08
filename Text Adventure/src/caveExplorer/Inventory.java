package caveExplorer;

import jasonYJasonZMinigame.JasonZGuns;

public class Inventory {
	

	private int money;
	private String map;

	private int cash;
	private String[] defaultGuns = {"M16", "0"};
	JasonZGuns currentGun = new JasonZGuns(defaultGuns);

	private static int hp;


	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Inventory()
	{
		cash = 0;
		setMoney(0);
		updateMap(CaveExplorer.caves);
	}
	
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public void updateMap(CaveRoom[][] caves) {

	
	public void updateMap() {
		map = " ";
		// make for. line across top:
		for(int i = 0; i< caves[0].length -1; i++)
		{
			map += "____";//4

		//make hor. line across top:

		map +="___ \n";//3
		for(CaveRoom[] row: caves)
		{
			for(int i = 0; i< 3; i++)

		//for symmetry, add only three underscores:
		map += "___\n";
		for(CaveRoom[] row: CaveExplorer.caves) {
			//three lines of text per room
			for(int i = 0; i < 3; i++) {

				String text = "";

				for(CaveRoom cr: row)
				{
					//if a wall is present draw a line otherwise draw a \
					if(cr.getDoor(CaveRoom.WEST) != null && cr.getDoor(CaveRoom.WEST).isOpen() )
					{
						text += " ";
					}
					else
					{

				for(CaveRoom cr: row) {
					//if a wall is present draw a line otherwise draw a "\"
					if(cr.getDoor(CaveRoom.WEST) != null &&
							cr.getDoor(CaveRoom.WEST).isOpen()) {
						text += " ";
					}else {

						text += "|";
					}
					if(i ==0) {
						text += " ";
					}else if(i == 1) {
						text += " " + cr.getContents() + " ";
					}else if(i == 2) {
						if(cr.getDoor(CaveRoom.SOUTH) != null && 
								cr.getDoor(CaveRoom.SOUTH).isOpen()) {
							text += "   ";//3 spaces
						}else {
							text += "___";//3 underscores(closed door or wall)
						}
					}
				}//last caveroom in row
				text += "|";
				map += text + "\n";
					}
	}


	public String getDescription()
	{
		return map + "\n" + "Also you have " + getMoney() + " money \n";
	}
	public void updateHP() {
		
		hp = 100;
	}
	
	public static int getHP() {
		return hp;
	}
	
}
