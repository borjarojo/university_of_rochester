/*
 * Borja Rojo
 * CSC 172
 * Project 3
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class WordFinder {
	public static void main(String[]args) throws FileNotFoundException, UnsupportedEncodingException{
		// HashTable accounting for size, avoids rehashing
		File legalWordsFile = new File("../" + args[0]);
		HashTable legalWords = new HashTable(1000000);	
		legalWords.hashFile(legalWordsFile);
		
		Puzzle puzzle = new Puzzle(new File("../" + args[1]));
		
		//Find all words
		/*
		 * This these will be loops that will cause the 
		 * puzzle to be traversed on every letter in every
		 * way it can, checking each word that is returned
		 * in the hashtable.
		 * 
		 * If it is a valid word, then it is saved, otherwise
		 * nothing is done with it
		 */
		String word;
		Set<String> foundWords = new TreeSet<String>();
		
		for(int l = 1; l <= puzzle.puzzle.length; l++){			//length
			for(int i = 0; i < puzzle.puzzle.length; i++){		//row
				for(int j = 0; j < puzzle.puzzle.length; j++){	//column
					for(int o = 1; o < 9; o++){					//orientation
						word = puzzle.getString(i, j, o, l);
//						if(word != null) System.out.println(word + ": " + "row " + i + ", column " + j 
//								+ ", orientation " + o + ", length " + l 
//								+ " - valid: " + legalWords.lookup(word));
						if((word != null) && legalWords.lookup(word)){
							
							foundWords.add(word);
						}
					}
				}
			}
		}
		
		//Write to file
		File output = new File("../" + args[2]);
		PrintWriter writer = new PrintWriter(output, "UTF-8");
		for(String s : foundWords){
			writer.write(s + "\n");
		}
		writer.close();
		
	}
}
