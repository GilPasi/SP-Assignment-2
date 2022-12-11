package ticTacToe;

import java.util.Random;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class SelfUser extends Player implements Runnable{
	public SelfUser(Players type,	Game game) {
		super(type, game);
		
	}

	@Override
	public void run() {
		
		
	}
	
	public synchronized void makeTurn() {
		int [][] freeCells = game.getFreeCells();
		
		//Generate a random number between 0 and maxRand
		int maxRand = freeCells.length;
        Random rand = new Random();
        int move = rand.nextInt(maxRand);
        
        

		
		
	}

}
