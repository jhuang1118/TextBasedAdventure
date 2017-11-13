package johnsonDanielMinigame;

public class JohnsonBackEnd implements DanielSupport {
	
	private JohnsonSupport backend;
	private int num;
	private int passLength;
	private boolean unlockLocker;
	
	private int[] code;
	public JohnsonBackEnd(DanielSupport frontend) {
		this.backend = backend;
		passLength = 3;
		code = new int[passLength];
		generatePassword(code,passLength);
	}
	
	private void generatePassword(int[] arr, int pLength) {
		for(int i = 0; i < pLength; i++) {
			arr[i] = (int)(Math.random()* 10);
		}
	}
	
}
