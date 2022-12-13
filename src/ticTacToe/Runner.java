package ticTacToe;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Runner {
	public static void main (String [] args) {
		
		SelfPlayer sp1 = new SelfPlayer(Players.X, null);
		SelfPlayer sp2 = new SelfPlayer(Players.O, null);
		SelfGame game = new SelfGame(sp1,sp2);

		
		sp1.setGame(game);
		sp2.setGame(game);

		
		Thread t1 = new Thread(sp1);
		Thread t2 = new Thread(sp2);

		t1.start();
		t2.start();

	}

}
