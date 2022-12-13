package ticTacToe;

import java.util.Random;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class SelfPlayer extends Player implements Runnable{
	private static final int SLEEP_TIME = 500;//In milliseconds
	
	public SelfPlayer(Players type,	Game game) {
		super(type, game);
		
	}

	@Override
	public void run() {
		
		
		while(!currentTurn()) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		
		
		try {
			makeTurn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void makeTurn() {
		
		int [][] freeCells = game.getFreeCells();/*Certainly will not change, if CPU time is take
												    the other player will not be able to play and
												    change the free cells status.*/	
		
		
		//Generate a random number between 0 and maxRand
		int maxRand = freeCells.length;
		
		
        Random rand = new Random();
        int move = rand.nextInt(maxRand);
        int moveRow = freeCells[move][0];
        int moveColumn = freeCells [move][1];
        
        try {
			game.makeMove(moveRow, moveColumn);
		} catch (Exception e) {
			e.printStackTrace();
		}
        game.switchTurn();  
        game.printBoard();
	}

}
