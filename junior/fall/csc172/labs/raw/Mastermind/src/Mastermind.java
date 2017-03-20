/*
 * Author: Borja Rojo
 * CSC172
 */

public interface Mastermind {
	public void response(int colorsRightPositionWrong, 
			int positionsAndColorRight);
	public void newGame();
	public String [] nextMove();
}
