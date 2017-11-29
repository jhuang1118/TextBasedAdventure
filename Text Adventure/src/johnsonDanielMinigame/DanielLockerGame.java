package johnsonDanielMinigame;

import java.util.*;
import caveExplorer.CaveExplorer;

public class DanielLockerGame {

    private DanielLocker[][] theLockers;
    private boolean onLocker;
    final int ROWTOT = 3;
    final int COLTOT = 2;
    private int newPsn;
    public DanielLockerGame() {
        theLockers = new DanielLocker[ROWTOT][COLTOT];
        onLocker = false;
        for(int ri = 0; ri < ROWTOT; ri++) {
            for(int ci = 0; ci < COLTOT; ci++) {
                makeLocker(ri,ci);
            }
        }
        createThing();
    }
    
    public boolean isOnLocker() {
        return onLocker;
    }

    public void setOnLocker(boolean onLocker) {
        this.onLocker = onLocker;
    }

    public static void main(String[] args) {
        DanielLockerGame demo = new DanielLockerGame();
        demo.play();
    }

    private void makeLocker(int row, int col) {
        theLockers[row][col] = new DanielLocker();
    }
    public void play() {
        int rowSelected = generateRandNum(0,ROWTOT-1);
        int colSelected = generateRandNum(0,COLTOT-1);
                
        System.out.println("In this part of the game you choose coordinates of a certain locker. You will have only 3 keys and each key can open any locker only once. "
                + "These lockers contain either the person you are looking for, a bomb, or"
                + " nothing. Your objective is to avoid the bomb, and return with the person stuck in the locker. CHEAT CODE = getmeout");
        int keys = ROWTOT;
        while(keys > 0) {
            displayBoard();
            displayKeysLeft(keys);
            String input = getValidUserInput();
            answer(input);
            keys--;
        }
        printGG();
    }

    
    private void createThing() {
        int personRowSelected = generateRandNum(0, ROWTOT -1);
        int personColSelected = generateRandNum(0, COLTOT -1);
        int bombRowSelected = 0;
        int bombColSelected = 0;
        
        theLockers[personRowSelected][personColSelected].setPerson(true);
        theLockers[personRowSelected][personColSelected].setEmpty(false);

        do { 
            bombRowSelected = generateRandNum(0, ROWTOT -1);
            bombColSelected = generateRandNum(0, COLTOT -1);
        } while(personRowSelected != bombRowSelected && personColSelected != bombColSelected);
        
        theLockers[bombRowSelected][bombColSelected].setBomb(true);
        theLockers[bombRowSelected][bombColSelected].setEmpty(false);
    }

    private int generateRandNum(int min, int max) {
        return (int)(Math.random()*(max - min + 1));        
    }

    private void printGG() {
        System.out.println("You Lose :(Ha, ha)");        
    }

    private String getValidUserInput() {
    	
        Scanner reader = new Scanner(System.in);  
        System.out.println("Enter a cell with the top left being 0,0 : row,col. ");
        String input = reader.next(); 
        String[] splitInput = input.split(",");
        Integer coor1 = Integer.valueOf(splitInput[0]);
        Integer coor2 = Integer.valueOf(splitInput[1]);
        
        while(!(coor1<3 && coor1>-1 && coor2<2 && coor2>-1)) {
        	
        	System.out.println("Please enter a valid input");
        	System.out.println("Enter a cell with the top left being 0,0 : row,col. ");
            input = reader.next(); 
        	
        }
        
        System.out.println("number entered: " + input);
        reader.close(); 
        return input;
        
    }

    private void answer(String input) {
    	
    	if(input.equals("getmeout")){
    		System.out.println("Ok you want the easy way out. YOU WIN!!");
    		    		
    		
    	}
    	
    	else {
    		String[] splitInput = input.split(",");
            Integer coor1 = Integer.valueOf(splitInput[0]);
            Integer coor2 = Integer.valueOf(splitInput[1]);
            theLockers[coor1][coor2].setOpen(true);
            johnsonDanielMinigame.JohnsonUnlockGame.main(null);
            System.out.println("This locker contained " + "a bomb: " + theLockers[coor1][coor2].getIsBomb() +", a person: " + theLockers[coor1][coor2].getIsPerson() ); 
    	}
    	
        

        
    }

    private void displayKeysLeft(int keys) {
        System.out.println("You have " + keys + " keys left.");
    }

    private void displayBoard() {
        for(int row = 0; row < ROWTOT; row++) {
            for(int col = 0; col < COLTOT; col++) {
                System.out.print(theLockers[row][col]);
            }
            System.out.println(" ");
        }
    }

}

