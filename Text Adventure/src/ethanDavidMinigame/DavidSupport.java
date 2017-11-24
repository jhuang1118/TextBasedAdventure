package ethanDavidMinigame;

public interface DavidSupport {

	int MONEY_CUT_OFF = 50000;

	String getValidUserInput();

	void startTimer();

	Object victorious();

	void setLost(boolean b);

	void checkLose();

	int getCurrMoney();

	boolean stillPlaying(int money);

}
