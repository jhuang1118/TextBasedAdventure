package ethanDavidMinigame;

public interface DavidSupport {

	boolean stillPlaying();

	String getValidUserInput();

	void startTimer();

	Object victorious();

	void setLost(boolean b);

	void loseGame();

}
