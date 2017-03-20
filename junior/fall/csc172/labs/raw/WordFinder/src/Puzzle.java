/*
 * Borja Rojo
 * CSC 172
 * Project 3
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Puzzle {
	
	String[][] puzzle;
	
	public Puzzle(File file) throws FileNotFoundException{
		/*
		 * Goes through the file and iterates and tokenizes into
		 * the global string array. Pretty straight forward.
		 * it assumes a square.
		 */
		Scanner in = new Scanner(file);
		
		String line = in.next();
		int length = line.length();
		puzzle = new String[length][length];
		int row = 0;
		
		for(int column = 0; column < line.length(); column++){
			puzzle[row][column] = Character.toString(line.charAt(column));
		}
		row++;
		
		while(in.hasNext()){
			line = in.next();
			for(int column = 0; column < line.length(); column++){
				puzzle[row][column] = Character.toString(line.charAt(column));
			}
			row++;
		}
		
		in.close();
	}
	
	public void print(){
		for(int i = 0; i < puzzle.length; i++){
			for(int j = 0; j < puzzle.length; j++){
				System.out.print(puzzle[i][j]);
			}
			System.out.println();
		}
	}
	
	/*
	 * Using the orientation, it chooses a traversal scheme 
	 * by the switch statement. It there would ever be a 
	 * situation particular to the traversal that would cause
	 * and array-out-of-bounds issue, it is handled accordingly.
	 * Return null if invalid, otherwise, returns the word
	 */
	public String getString(int row, int column, int orientation, int length){
		String word = "";
		switch (orientation){
		
			//up
			case 1:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if((row - i) < 0) return null;	//then return an empty string
					word += puzzle[row - i][column];
				}
				break;
				
			//up left to right diagonal
			case 2:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if(((row - i) < 0) || ((column + i) == puzzle.length)) return null;	//then return an empty string
					word += puzzle[row - i][column + i];
				}
				break;
				
			//left to right
			case 3:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if((column + i) == puzzle.length) return null;	//then return an empty string
					word += puzzle[row][column + i];
				}
				break;
				
			//down left to right diagonal
			case 4:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if(((row + i) == puzzle.length) || ((column + i) == puzzle.length)) return null;	//then return an empty string
					word += puzzle[row + i][column + i];
				}
				break;
				
			//down
			case 5:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if((row + i) == puzzle.length) return null;	//then return an empty string
					word += puzzle[row + i][column];
				}
				break;
				
			//down right to left diagonal
			case 6:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if(((row + i) == puzzle.length) || ((column - i) < 0)) return null;	//then return an empty string
					word += puzzle[row + i][column - i];
				}
				break;
			
			//right to left
			case 7:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if((column - i) < 0) return null;	//then return an empty string
					word += puzzle[row][column - i];
				}
				break;
				
			//up diagonal righ to left
			case 8:
				for(int i = 0; (i < length); i++){
					//if the indexes fall outside of the puzzle array
					if(((row - i) < 0) || ((column - i) < 0)) return null;	//then return an empty string
					word += puzzle[row - i][column - i];
				}
				break;
		}
		return word;
	}
}
