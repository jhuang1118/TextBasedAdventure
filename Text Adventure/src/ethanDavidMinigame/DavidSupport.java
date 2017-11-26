package ethanDavidMinigame;

public interface DavidSupport {

	int MONEY_CUT_OFF = 65000;

	String getValidUserInput();

	Object victorious();

	void setLost(boolean b);

	void checkLose();

	int getCurrMoney();

	boolean stillPlaying(int money);

}
