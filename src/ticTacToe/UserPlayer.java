package ticTacToe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public class UserPlayer extends Player implements Runnable{
	
	Scanner s = new Scanner(System.in);
	
	public UserPlayer(Players type,	Game game) {
		super(type, game);
		
	}
	
	@Override
	public void run() {
		
		while(!game.getIsGameOver()) 		
			((UserGame)game).makeUserTurn(this);
		
		
	}
	

	public int[] takeInput () {
		/**This method's job is to take and verify a user's input. It will address 3 Possible 
		 * Input-errors : 
		 * 1) Invalid type (String, char etc)
		 * 2) Invalid length (2 or more  characters, 1 character)
		 * 3) Invalid indexes
		 * 
		 *  In order to check all three cases simultaneously, the method will
		 *  register the error type.
		 *  eventually it will take care of the each problem separately
		 *  in the switch case. */
		
		final int VALID_LEN = 2;

		
		boolean isPureDigit = false;
		boolean isTwoCharacters = false;
		boolean isOnBoard = false;

		
		String coor = s.next();
		int [] validCoor = new int [2];//Return array, 0 cell is the row while 1 cell is the column

		int error = 0;//For a switch case at the end of the method
		
		do {
			error = 0;
			
			//Issue 1: address invalid types
			boolean skip = false;
			
			for(int i = 0 ; i < coor.length(); i++) {			
				if(!Character.isDigit(coor.charAt(i))){
					error = 1;//Update error type
					skip = true;
					break;
				}		
			}

			//Issue 2: address invalid length
			if(coor.length() != VALID_LEN){
				error = 2;//Update error type
			}
			else isTwoCharacters = true;
			
			
			if(coor.length() == 2) {
				//Issue 3 : address out of board indexes
				
				//Cast into array
				validCoor[0] = coor.charAt(0) - '0';
				validCoor[1] = coor.charAt(1) - '0';
				
				boolean notOnBoard =
						validCoor [0] < 0 || 
						validCoor [0] > 2 ||
						validCoor [1] < 0 ||
						validCoor [1] > 2;
						
				if(notOnBoard && error == 0) 
					error = 3;
						
			}
			
			//Issue 4 : address taken cell
			if(error == 0) {				
				char[][] fc = game.getGameBoard();
				int row  = validCoor[0];
				int col  = validCoor[1];
				
				if(fc[row][col] != '\0')
					error = 4;

				
			}
			
			switch(error) {
			
			case 0: //Everything is valid - proceed as planned
				break;
				
			case 1://Invalid type
				System.out.println("Please enter a pure digits input such as 00 11 ...");
				coor = s.next();
				break;
				
			case 2: //Invalid length
				System.out.println("Please enter a two-digits input such as 00 11 ...");
				coor = s.next();
				break;
				
			case 3: //Invalid position
				System.out.println("Indexes are not on board, please enter again ");
				coor = s.next();
				break;
			case 4: //Invalid position
				System.out.println("This cell is already taken, please enter again  ");
				coor = s.next();
				break;
				
			}
			
		}while(error != 0);//End do-while
		
		return validCoor;
	}


}
