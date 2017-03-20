/*
 * Borja Rojo
 * brojo@u.rochester.edu
 *
 * Partner: Daniel Saltz
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Test {
	public static void main(String[]args) throws FileNotFoundException{
		System.out.println("---Hashing---\n");
		
		//creation
		System.out.println("Creating HashTable...");
		HashTable names = new HashTable();
		
		//Insertion
		System.out.println("Inserting names into the table...\n");
		names.insert("Borja");
		names.insert("Roy");
		names.insert("Jack");
		names.insert("Brandis");
		names.insert("Murray");
		//Repeats
		names.insert("Jack");
		names.insert("Roy");
		names.insert("Brandis");
		
		//Print
		System.out.println("Printing table contents...");
		names.printTable();
//		
		System.out.println();
		System.out.println("Adding more names...");
		names.insert("Anes");
		names.insert("Wrobel");
		names.insert("Greg");
		names.insert("Devon");
		names.insert("Reichman");
		names.insert("Henry");
		names.insert("Mullen");
		names.insert("Radin");
		names.insert("Wegbrite");
		
		System.out.println("Printing new table contents...");
		names.printTable();
		
		System.out.println();
		
		System.out.println("Creating new Hash Table for lorem ipsum of default size...");
		HashTable lipsum = new HashTable();
		Scanner in = new Scanner(new File("lipsum.txt"));
		
		System.out.println("Hashing lipsum...");
		
		int read = 0;
		String tok = in.next();
		while(in.hasNext()){
			lipsum.insert(tok);
			read++;
			tok = in.next();
		}
		
		System.out.println("Printing unique words in lipsum.txt:");
		lipsum.printTable();
		System.out.println("Number of unique words: " + lipsum.elementCount());
		System.out.println("Number of words read in: " + read);
		System.out.println("Final size of the HashTable lipsum: " + lipsum.capacity());
		
		
	}
}
