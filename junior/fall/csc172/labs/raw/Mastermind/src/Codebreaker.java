/*
 * Author: Borja Rojo
 * CSC172
 */

public class Codebreaker implements Mastermind{
	
	String[] colors;
	int pos;
	int totalGuess;
	
	int[][] codes;
	int[][] guesses;
	
	int[] currentGuess;
	int[] badGuess;
	
	int[] secretCode;
	
	public Codebreaker(String[] tokencolors, int positions){
		colors = tokencolors;
		pos = positions;
		totalGuess = (int) Math.pow(colors.length, pos);
		//Create an array that can hold all of the possible solutions
		codes = new int[totalGuess][pos];
		
		/* Fill array
		 * I used the algorthim of transforming
		 * one number of a certain base to the 
		 * base of another number. The base is 
		 * set to be the color amount, and because
		 * of how the system is set, the numbers
		 * run from 0 to the end of the array.
		 */
		for(int i = 0; i < totalGuess; i++){
			int x = i;
			int quad;
			int quo;
			for(int j = 0; j < pos; j++){
				quad = x % colors.length;
				quo = x / colors.length;
				codes[i][j] = quad;
				x = quo;
			}
		}
		
		//set guesses to the codes
		guesses = codes;
		
		//create the starting guess, 
		//always with half of the first 
		//color and half of the second
		currentGuess = new int[pos];
		secretCode = new int[pos];
		badGuess = new int[pos];
		for(int i = 0; i < pos/2; i++){
			currentGuess[i] = 0;
			badGuess[i] = -1;
			secretCode[i] = (int) Math.floor(Math.random()*100 % colors.length);
		}
		for(int i = pos/2; i < pos; i++ ){
			currentGuess[i] = 1;
			badGuess[i] = -1;
			secretCode[i] = (int) Math.floor(Math.random()*100 % colors.length);
		}
	}
	//Required methods
	
	//processes the result of making a guess and getting back the pegs counts
	//it adjusts the guess list
	public void response(int colorsRightPositionWrong,
			int positionsAndColorRight) {
		// TODO Auto-generated method stub
		//This has the pegs that are correct in color and place first
		int[] pegs = {positionsAndColorRight, colorsRightPositionWrong};
		int[] r = compareGuesses(currentGuess, guesses[0]);
		for(int i = 0; i < totalGuess; i++){
			r = compareGuesses(currentGuess, guesses[i]);
			if(pegs[0] == r[0] && pegs[1] == r[1]){	
			}else{
				guesses[i] = badGuess;
			}
		}

	}

	public void newGame() {
		// TODO Auto-generated method stub
		
		codes = new int[totalGuess][pos];
		//resets the game
		//sets the next guess to starting guess
		//sets a new secret code
		//resets the guess list
		for(int i = 0; i < totalGuess; i++){
			int x = i;
			int quad;
			int quo;
			for(int j = 0; j < pos; j++){
				quad = x % colors.length;
				quo = x / colors.length;
				codes[i][j] = quad;
				x = quo;
			}
		}
		
		//set guesses to the codes
		guesses = codes;
		
		//create the starting guess, 
		//always with half of the first 
		//color and half of the second
		currentGuess = new int[pos];
		secretCode = new int[pos];
		badGuess = new int[pos];
		for(int i = 0; i < pos/2; i++){
			currentGuess[i] = 0;
			badGuess[i] = -1;
			secretCode[i] = (int) Math.floor(Math.random()*100 % colors.length);
		}
		for(int i = pos/2; i < pos; i++ ){
			currentGuess[i] = 1;
			badGuess[i] = -1;
			secretCode[i] = (int) Math.floor(Math.random()*100 % colors.length);
		}
	}

	//Returns a string array that holds the next guess
	public String[] nextMove() {
		String[] s = new String[pos];
		for(int i = 0; i < s.length; i++){
			s[i] = colors[currentGuess[i]];
		}
		return s;
	}
	
	//My methods
	public int[] nextMoveInt(){
	// TODO Auto-generated method stub
			//Find the next possible guess
			int i = 0;
			while(guesses[i][0] == -1){
				i++;
			}
			
			return guesses[i];
	}
	
	public void setNextGuess(){
		//set new guess
		int i = 0;
		while(guesses[i][0] == -1){
			i++;
		}
		
		currentGuess = guesses[i];
	}
	
	//returns an int array that holds black peg count and white peg count
	public int[] compareGuesses(int[] first, int[] second){
		int white = 0;
		int black = 0;
		/*
		 * go through each color and add the minimum amount
		 * of matched colors to the white pegs. This will keep 
		 * track of the maximum amount of similarities in colors
		 * between two guesses
		 */
		for(int i = 0; i < colors.length; i++){
			white += Math.min(colorCount(first, i), colorCount(second, i));
		}
		
		/*
		 * Now, it goes and compares each location to see if
		 * colors match. If they do, then the black pegs go up.
		 * Later, I subtract the black pegs from the white in 
		 * order to keep consistency.
		 */
		for(int i = 0; i < pos; i++){
			if(first[i] == second[i])
				black++;
		}
		
		white = white - black;
		
		int[] a = {black, white};
		
		return a;
	}
	
	//returns the amount of a color present in a guess
	public int colorCount(int[] guess, int color){
		int count = 0;
		for(int i = 0; i < pos; i++){
			if(guess[i] == color)
				count++;
		}
		return count;
	}
	
	public void printCodes(){
		for(int i = 0; i < totalGuess; i++){
			for(int j = 0; j < pos; j++){
				System.out.print(codes[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void printGuesses(){
		for(int i = 0; i < totalGuess; i++){
			for(int j = 0; j < pos; j++){
				System.out.print(guesses[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void printSecretCode(){
		for(int i = 0; i < pos; i++){
			System.out.print(secretCode[i] + " ");
		}
		System.out.println();
	}
	
	public void printCurrentGuess(){
		for(int i = 0; i < pos; i++){
			System.out.print(currentGuess[i] + " ");
		}
		System.out.println();
	}

	public void printCodeToString(int[] code){
		for(int i = 0; i < code.length; i++){
			System.out.print(colors[code[i]] + " ");
		}
		System.out.println();
	}
}
