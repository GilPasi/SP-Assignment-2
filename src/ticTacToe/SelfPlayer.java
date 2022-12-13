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
		
		while(!game.getIsGameOver()) {
			
			while(!currentTurn()) {
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
			
			
			try {
				((SelfGame)game).makeTurn();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	



}
