package jasonYJasonZMinigame;

public class JasonZGuns {

	private int range;
	private int dmg;
	private int ammo;
	private int rateOfFire;
	private String name;
				//auto		pistol		smg 
	
	public final static int[][] TYPE = {{4,10,30,4},{2,4,8,3},{3,3,20,4}};
	
	public JasonZGuns(String[] description) {
		name = description[0];
		
	}

	public void reload(String type)
	{
		
	}
	
	
}
