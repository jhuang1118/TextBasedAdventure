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
                
        System.out.println("In this part of the game you will walk around the map and go to lockers. You will have only 3 keys and each key can open any locker only once. "
                + "These lockers contain either the person you are looking for, a bomb, or"
                + " nothing. When you walk up to a locker, press 'x'");
        int keys = ROWTOT;
        while(keys > 0) {
            displayBoard();
            displayKeysLeft(keys);
            String input = getValidUserInput();
            //answer(input);
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
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter cell as coma delimited row number and cell number: ");
        String input = reader.next(); // Scans the next token of the input as an int.
        System.out.println("number entered: " + input);
        //once finished
        reader.close(); 
        return input;
    }

    private boolean validInput(String input) {
        String[] splitInput = input.split(",");
        if(splitInput.length == 2) {
            return Integer.valueOf(splitInput[0]) > 0 && Integer.valueOf(splitInput[0]) < 6 && Integer.valueOf(splitInput[1]) > 0 && Integer.valueOf(splitInput[0]) < 6; 
        }
        return false;
    }

    public boolean isOnLocker(int row, int col) {
        int[][] lockerpsns = {{1,1},{1,3},{1,5},{3,1},{3,3},{3,5}};
        int[][] personpsn = {{row,col}};
        int count = 0;
        for(int i = 0; i < lockerpsns.length; i++) {
            if(lockerpsns[i].equals(personpsn[0])) {
                count++;
            }
        }
        if(count > 0) {
            return true;
        }
        return false;
    
    }
    private void answer(String input) {
        String[] splitInput = input.split(",");
        Integer coor1 = Integer.valueOf(splitInput[0]);
        Integer coor2 = Integer.valueOf(splitInput[1]);
        if(isOnLocker(coor1,coor2)) {
            theLockers[coor1][coor2].setOpen(true);
            johnsonDanielMinigame.JohnsonUnlockGame.main(null);

        }
    }

    private boolean isPossibleMove(int[] is) {
        // TODO Auto-generated method stub
        return false;
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

