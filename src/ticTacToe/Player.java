package ticTacToe;
/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public abstract class Player {
	protected Game game;
	protected Players type;
	
	public Player(Players type,	Game game) {
		this.type = type;
		this.game = game;
	}
	
	public synchronized boolean currentTurn () {
		//This method's synchronization is vital in order to resolve visibility issues.
	
		//Figure out if this is the current thread's turn 
		
		
		boolean currentTurn = false;
		
		if(type == Players.X) 
			 currentTurn = game.isXTurn();
	
		if(type == Players.O)
			currentTurn = !game.isXTurn();
					
		return currentTurn;
		
	}
	
	
	public void setGame(Game game) {this.game = game;}
	
	
	
	
}
