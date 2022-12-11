package ticTacToe;
/*System-Programming : Assignment 2
 *Authors: Yulia Moshan 319565610
 *			Gil Pasi    206500936 */
public abstract class Player extends Game{
	Game game;
	Players type;
	
	public Player(Players type,	Game game) {
		this.type = type;
		this.game = game;
	}
	
}
