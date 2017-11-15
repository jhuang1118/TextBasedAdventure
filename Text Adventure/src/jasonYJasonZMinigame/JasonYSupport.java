package jasonYJasonZMinigame;

import java.awt.event.KeyEvent;

public interface JasonYSupport {
	void createPolice();
	void kickOff();
	void increaseKillCount();
	void changeSpawnTime();
	void changeDifficulty(int i);
	void changeSpawnTime(double d);
	void validInput(String input, KeyEvent event);
}
