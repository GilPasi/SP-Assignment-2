package ticTacToe;

import java.util.Random;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class SelfGame extends Game{

	public SelfGame(Player p1, Player p2) {
		super(p1, p2);
	}
	
	public synchronized void makeSelfTurn() {
		
		int [][] freeCells = getFreeCells();/*Certainly will not change, if CPU time is take
												    the other player will not be able to play and
												    change the free cells status.*/	
		
		
		//Generate a random number between 0 and maxRand
		int maxRand = freeCells.length;
		
		
        Random rand = new Random();
        int move = rand.nextInt(maxRand);
        int moveRow = freeCells[move][0];
        int moveColumn = freeCells [move][1];
        
        try {
			makeMove(moveRow, moveColumn);
		} catch (Exception e) {
			e.printStackTrace();
		}
        printBoard();
        System.out.println("\n");
        switchTurn();  
		notifyAll();

	}

}
