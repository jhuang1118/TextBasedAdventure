package johnsonDanielMinigame;

import caveExplorer.CaveExplorer;
import caveExplorer.CaveRoom;
import caveExplorer.NPC;
import johnsonDanielMinigame.MiniGameStarter;

public class SuspiciousMan extends NPC{

	public SuspiciousMan(int row, int col, CaveRoom[][] cave) {
		super(row, col, cave);
		
	}

	@Override
	public void interact() {
		CaveExplorer.print("'Hey you. I got a job if ya need one.' Type 'bye' to stop.");
		String s = CaveExplorer.in.nextLine();
		while(!s.equalsIgnoreCase("bye"))
		{
			CaveExplorer.print("'I need you to rob the treasure from a bank\n" +
								"and I'll give you something special in return\n" +
								"Do you want in?' Type 'yes' to accept" );
			s = CaveExplorer.in.nextLine();
			if (s.equals("yes")) {
				if (DanielStealth.start()) {
					CaveExplorer.print("'You've earned it, here's $100'"); 
					CaveExplorer.inventory.setCash(CaveExplorer.inventory.getCash() + 100);
					break;
				} else {
					System.out.println("'I knew you weren't cut for the job. Talk to me later.'");
					break;
				}
			}
		}
		CaveExplorer.print("Later, friend!");
		this.setActive(false);
	}
	
	@Override
	public String getSymbol() {
		return "S";
		
	}
}
