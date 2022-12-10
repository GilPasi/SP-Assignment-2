/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public abstract class Game {
	final static int SIZE = 3;
	private char [][] gameBoard = new char[SIZE][SIZE];
	
	public void printBoard() {
		System.out.println(gameBoard [0][1] + "\t||" + gameBoard[0][2] + "\t||" + gameBoard[0][3] );
		
		
	}
}
