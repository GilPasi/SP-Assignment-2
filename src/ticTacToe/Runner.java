package ticTacToe;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class Runner {
	public static void main (String [] args) {
		
		//____Self Game____
		SelfPlayer sp1 = new SelfPlayer(Players.X, null);
		SelfPlayer sp2 = new SelfPlayer(Players.O, null);
		SelfGame game1 = new SelfGame(sp2,sp1);

		
		sp1.setGame(game1);
		sp2.setGame(game1);

		
		Thread t1 = new Thread(sp1);
		Thread t2 = new Thread(sp2);
		
		game1.startGame();

		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//____User Game_____
		
		SelfPlayer sp3 = new SelfPlayer(Players.X, null);
		UserPlayer up = new UserPlayer(Players.O, null);
		UserGame game2 = new UserGame(up,sp1);

		
		sp1.setGame(game2);
		up.setGame(game2);

		
		Thread t3 = new Thread(sp1);
		Thread t4 = new Thread(up);
		
		game2.startGame();

		t3.start();
		t4.start();
		

	}

}
