package caveExplorer;

public class Inventory {
	
	private String map;
	private static int hp;
	private boolean isKidnapped = false;
	private boolean hasGun = false;
	
	public Inventory() {
		updateMap();
		
	}
	
	public void updateMap() {
		map = " ";
		//make hor. line across top:
		for(int i = 0; i < CaveExplorer.caves[0].length -1; i++) {
			map += "____"; //4 underscores
		}
		//for symmetry, add only three underscores:
		map += "___\n";
		for(CaveRoom[] row: CaveExplorer.caves) {
			//three lines of text per room
			for(int i = 0; i < 3; i++) {
				String text = "";
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
	}
	
	public String getDescription() {
		return map;
		//return "There is nothing in your inventory.";
	}
	
	public void updateHP() {
		
		hp = 100;
	}
	
	public static int getHP() {
		return hp;
	}
	
	public void toggleIsKidnapped() {
		isKidnapped = !isKidnapped;
	}
	public boolean getIsKidnapped() {
		return isKidnapped;
	}
	
	public void getHasGun() {
		hasGun = !hasGun;
	}
	public boolean toggleHasGun() {
		return hasGun;
	}
	
}
