package ticTacToe;
/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class UserGame extends Game{

	public UserGame(Player p1, Player p2) {
		super(p1, p2);
	}
	
	
	public synchronized void makeTurn(UserPlayer p) {		
			
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
					int [] validInput = p.takeInput();
					makeMove(validInput[0],validInput[1]);
					printBoard();
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
	

	


	

}
