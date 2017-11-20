package johnsonDanielMinigame;

public class JohnsonButton {
	
	private boolean revealed;
	private String color;
	private boolean trigger;
	
	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isTrigger() {
		return trigger;
	}

	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
	}

	public JohnsonButton() {
		revealed = false;
		color = "G";
		trigger = false;
	}
	public String toString() {
		if(revealed) {
			return color;
		}else
			return "O";
	}
}
