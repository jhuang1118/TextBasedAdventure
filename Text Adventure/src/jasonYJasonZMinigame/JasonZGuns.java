package jasonYJasonZMinigame;

public class JasonZGuns {

	private int range;
	private int dmg;
	private int ammo;
	private int rateOfFire;
	private String name;
	private int maxAmmo;
	
	public final static int AUTOMATIC = 0;
	public final static int PISTOL = 1;
	public final static int SUBMACHINE = 2;
				//auto		pistol		sub machine gun 
	public final static int[][] TYPE = {{4,10,30,4},{2,4,8,3},{3,3,20,4}};
	//range, damage, magazines, fire rate 
	public JasonZGuns(String[] description) {
		name = description[0];
		setValues(description[1]);
		
	}

	private void setValues(String string) {
		int[] stats = TYPE[Integer.parseInt(string)];
		this.range = stats[0];
		this.dmg = stats[1];
		this.ammo = stats[2];
		this.maxAmmo = stats[2];
		this.rateOfFire = stats[3];
	}

	public void reload()
	{
		this.ammo = this.maxAmmo;
	}
	
}
