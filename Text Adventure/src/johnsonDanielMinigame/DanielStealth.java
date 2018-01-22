package johnsonDanielMinigame;

import java.util.Scanner;

public class DanielStealth {
	public static boolean start() {
		DanielStealthBackEnd game = new DanielStealthBackEnd(10, 10);
		Scanner in = new Scanner(System.in);
		System.out.println(game);
		printDescription();
		while (game.getState() == game.PLAYING) {
			String input = in.nextLine();
			if (!isValid(input)) {
				printValidMoves();
			}
			game.move(input);
			System.out.println(game);
		}
		if (game.getState() == game.WIN) {
			System.out.println("You got the treasure and escaped!");
			return true;
		} else {
			System.out.println("You were caught!");
			return false;
		}
	}
	
	public static void printDescription() {
		System.out.println("You enter the back of a bank and are seeking the treasure\n" +
				"There are agents walking around; Try to not get caught or you'll lose!\n" +
				"Move using wasd and reach the treasure!\n" +
				"Key: P = You, A = Agent, T = Treasure");
	}
	
	private static String validMoves() {
		return "wdsa";
	}
	
	private static boolean isValid(String input) {
		return validMoves().indexOf(input) != -1 && input.length() == 1;
	}
	
	private static void printValidMoves() {
		System.out.println("Enter 'w', 'a', 's', or 'd' to move.");
		
	}
}
