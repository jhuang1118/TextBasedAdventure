package johnsonDanielMinigame;

public class JohnsonBackEnd implements DanielSupport {
	
	private JohnsonSupport backend;
	private int num;
	
	private int passLength;
	private boolean unlockLocker;
	private int tries;
	private String[][] locker;
	private int numLockers;
	private int[] code;
	public JohnsonBackEnd(DanielSupport frontend) {
		this.backend = backend;
		passLength = 2;
		numLockers = 6;
		locker = new String[numLockers][4];
		code = new int[passLength];
		unlockLocker = false;
		tries = 3;
		generatePassword(code,passLength);
		makeLocker(turnToString(code), "true", "false", "false");
	}
	
	private void generatePassword(int[] arr, int pLength) {
		for(int i = 0; i < pLength; i++) {
			arr[i] = (int)(Math.random()* 10);
		}
		System.out.println(arr);//debugging
		
	}
	
	private String turnToString(int[] arr) {
		String pass = Integer.toString(code[0]) + Integer.toString(code[1]);
			return pass;
	}
	
	public void makeLocker(String pass, String hasKey, String hasPerson, String hasBomb) {
		String[] temp = {pass, hasKey, hasPerson, hasBomb};
		for(int i = 0; i < locker.length; i++) {
			for(int j = 0; j < locker[i].length; j++) {
				locker[i][j] = temp[j];
			}
		}
		
		locker[0][2] = "true"; //first locker has the person
		locker[5][3] = "true"; //last locker has the bomb 
	}
	
	
	
}
