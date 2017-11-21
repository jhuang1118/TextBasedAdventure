package jasonYJasonZMinigame;

import java.awt.event.KeyEvent;

public interface JasonYSupport {
	void kickOff();
	void increaseKillCount();
	void changeDifficulty(int i);
	void changeSpawnTime(double d);
	void validInput(String input);
}
