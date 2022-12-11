package ticTacToe;
/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
enum Players{
	X(true),O(false);
	
	char sign;
	
	Players (boolean isX){
		if(isX)
			sign = 'X';
		else
			sign = 'O';
	}
}