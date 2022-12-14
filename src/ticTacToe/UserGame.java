package ticTacToe;

import java.util.Random;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class UserGame extends Game{

	public UserGame(UserPlayer p1, SelfPlayer p2) {
		super(p1, p2);
	}
	
	
	public synchronized void makeUserTurn(Player p) {		
			

			//If the turn is granted
			if(p.currentTurn()) {
				int [][] freeCells = getFreeCells();
				
				System.out.print("Possible moves : [");
				
				for(int i = 0; i < freeCells.length ; i++) {
					System.out.print(" " + freeCells[i][0] 
							 + freeCells[i][1]);
				}	
				System.out.println("]");
				
				System.out.println("Please enter your coordinations in the format rc \n"
						+ "where the r is the row's digit and c is the coloumn's digit");
				
				
				try {
					int [] validInput = ((UserPlayer)p).takeInput();
					makeMove(validInput[0],validInput[1]);
					printBoard();
			        System.out.println("\n");
					switchTurn();
					notifyAll();
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			
	
			else
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
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
