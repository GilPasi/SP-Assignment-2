package ticTacToe;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */

public abstract class Game {
	
	protected boolean isXTurn = true; //X starts by default
	protected boolean isGameOver = false;

	public final static int SIZE = 3;
	private char [][] gameBoard = new char[SIZE][SIZE];
	private Player p1 , p2;
	private Player current;

	public Game(Player p1 , Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		//X Always start
		if(p1.type == Players.X)
			current = p1;
		else 
			current = p2;
	}
	
	
	public void startGame () {
		System.out.println("==========Welcome to the amazing TIC - TAC - TOE !===========");
	}
	
	public void printBoard() {

		System.out.println("\n\t[0]    [1]    [2]\n");
		System.out.println("[0]\t  " + gameBoard [0][0] + "  |  " + gameBoard[0][1] + "  |  " + gameBoard[0][2] );
		System.out.println("   \t_____|_____|_____");
		System.out.println("[1]\t  " + gameBoard [1][0] + "  |  " + gameBoard[1][1] + "  |  " + gameBoard[1][2] );
		System.out.println("   \t_____|_____|_____");
		System.out.println("[2]\t  " + gameBoard [2][0] + "  |  " + gameBoard[2][1] + "  |  " + gameBoard[2][2] );
		System.out.println("\t     |     |     ");
	
	}
	
	
	public synchronized int [][] getFreeCells() {
		String freeCells = "";
		
		//Each two characters in the string represent a free cell
		for(int i = 0; i < SIZE ; i++) {
			for(int j = 0; j < SIZE ; j++) {
				if(gameBoard[i][j] == '\0' ) {
					freeCells += i;
					freeCells +=j;
				}
			}
		}		
		
		return stringToMatrix(freeCells);//Convert to easier format
	}
	
	private int[][] stringToMatrix(String str){
		
		
		final int CELL_SIZE = 2;
		final int LEN = str.length() / CELL_SIZE;
		int [][] converted = new int [LEN][CELL_SIZE];
		
		for(int i = 0 ; i < LEN ; i ++) {
			converted [i][0] = str.charAt(CELL_SIZE * i) - '0';
			converted [i][1] = str.charAt(CELL_SIZE * i + 1) - '0';
	
		}

		return converted;
		
	}
	public synchronized void makeMove(int row , int col) throws Exception{

		if(gameBoard [row][col] != '\0') 
			throw new Exception("Illegal move");
			
		
		
		char sign;
		
		if(isXTurn)
			sign = 'X';
		else
			sign = 'O';
		
		gameBoard[row][col] = sign;
		
	}
	
	public boolean checkIfWon (char sign) {

		return 
				
				//Check rows
				(gameBoard[0][0] == sign && gameBoard [0][1] == sign && gameBoard[0][2] == sign)
														||
				(gameBoard[1][0] == sign && gameBoard [1][1] == sign && gameBoard[1][2] == sign)
														||
				(gameBoard[2][0] == sign && gameBoard [2][1] == sign && gameBoard[2][2] == sign)


														||
				//Check Columns
				(gameBoard[0][0] == sign && gameBoard [1][0] == sign && gameBoard[2][0] == sign)
														||
				(gameBoard[0][1] == sign && gameBoard [1][1] == sign && gameBoard[2][1] == sign)
														||
				(gameBoard[0][2] == sign && gameBoard [1][2] == sign && gameBoard[2][2] == sign)
				
														||
				//Check diagonals
				(gameBoard[0][0] == sign && gameBoard [1][1] == sign && gameBoard[2][2] == sign)
														||
				(gameBoard[0][2] == sign && gameBoard [1][1] == sign && gameBoard[2][0] == sign)

														;
				
			
		
	}
	
	
	public synchronized void switchTurn () {
		
		//Also make sure that the game is not over
		if(checkIfWon(Players.X.sign)) {
			System.out.println("\n========== X WON! CONGRATIONLATIONS! ============");
			isGameOver = true;

		}
		
		else if(checkIfWon(Players.O.sign)) {
			System.out.println("\n========== O WON! CONGRATIONLATIONS! ============");
			isGameOver = true;

		}
		
		else if(getFreeCells().length == 0) {
			System.out.println("\n========== A TIE! ============");
			isGameOver = true;

		}

		isXTurn = !isXTurn;
		
	}

	//Getters + Setters

	public boolean isXTurn() {
		return isXTurn;
	}


	public void setXTurn(boolean isXTurn) {
		this.isXTurn = isXTurn;
	}


	public char[][] getGameBoard() {
		return gameBoard;
	}


	public void setGameBoard(char[][] gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public boolean getIsGameOver() {
		return isGameOver;
		
	}
	
	

}
