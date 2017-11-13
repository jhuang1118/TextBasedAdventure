package jasonYJasonZMinigame;

public interface JasonZSupport {

	void createMap();
	void follow();
		//npcs in the map will move towards the player after played inputs a move
	void rangeDisplay();
		//colors in a number of boxes based on the range of the gun
		//should be colored red 
	void play();
		//createMap();
		//populateMap();
		//display it all
	void populateMap();
		//populates the map with cops
	void killCounter();
		//displays number of kills
	void copCounter();
		//displays number of cops on the map
	void introduction();
		//displays background info and objective. Will ask for difficulty
}
