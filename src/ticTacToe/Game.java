package ticTacToe;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */

public abstract class Game {
	
	private boolean isXTurn = true; //X starts by default
	public final static int SIZE = 3;
	private char [][] gameBoard = new char[SIZE][SIZE];
	
	public Game() {

		
	}
	
	
	public void printBoard() {

		
		System.out.println("  " + gameBoard [0][0] + "  |  " + gameBoard[0][1] + "  |  " + gameBoard[0][2] );
		System.out.println("_____|_____|_____");
		System.out.println("  " + gameBoard [1][0] + "  |  " + gameBoard[1][1] + "  |  " + gameBoard[1][2] );
		System.out.println("_____|_____|_____");
		System.out.println("  " + gameBoard [2][0] + "  |  " + gameBoard[2][1] + "  |  " + gameBoard[2][2] );
		System.out.println("     |     |     ");
	
	}
	
	
	public Players getTurn() {
		if(isXTurn)
			return Players.X;
		else
			return Players.O;
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
		
		isXTurn = !isXTurn;//Change turn 
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

}
