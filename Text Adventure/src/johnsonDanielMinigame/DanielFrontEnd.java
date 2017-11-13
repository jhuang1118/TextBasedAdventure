package johnsonDanielMinigame;

public class DanielFrontEnd implements JohnsonSupport {

	private DanielSupport frontend;
	private String hint;
	private String cheatCode;
	
	
	
	public static final void main(String[] args) {
		DanielFrontEnd mineSweeper = new DanielFrontEnd();
		mineSweeper.play();
	}
	
	public DanielFrontEnd() {
		backend = new JohnsonBackEnd(this);
		hint = null; 
	}
	
	public startGame() {
		
	}

}
