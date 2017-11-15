package jasonYJasonZMinigame;

public class JasonZGuns {

	private double range;
	private double dmg;
	private int ammo;
	private double rateOfFire;
	private String name;
	private int maxAmmo;
	
	public final static int AUTOMATIC = 0;
	public final static int PISTOL = 1;
	public final static int SUBMACHINE = 2;
				//auto		pistol		sub machine gun 
	public final static double[][] TYPE = {{4,14,30,4},{2,6,8,3},{3,8,20,4}};
	//range, damage, magazines, fire rate 
	public JasonZGuns(String[] description) {
		name = description[0];
		setValues(description[1]);
		
	}

	private void setValues(String string) {
		double[] stats = TYPE[Integer.parseInt(string)];
		this.range = stats[0];
		this.dmg = (int)stats[1];
		this.ammo = (int) stats[2];
		this.maxAmmo = (int) stats[2];
		this.rateOfFire = stats[3];
	}

	public void reload()
	{
		this.ammo = this.maxAmmo;
	}
	
	public double trueDamage()
	{
		return (dmg * rateOfFire);
	}
}
