package jasonYJasonZMinigame;

import caveExplorer.NPC;

public class JasonZSwat extends NPC {

	public JasonZGuns Gun;
	public int hp = 100;
	public int armor = 70;
	public JasonZSwat()
	{
		makeGuns();
	}
	public void makeGuns() {
		// random decider for guns;
		this.Gun = new JasonZGuns(description)
	}

}
