package lab13;


/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 13
 * 
 * File Name: lab13main.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 4/08/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: April 13th, 2014
 * 
 * Lab Partner: Haotion Li
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

public class lab13main {
	
	//Main method to test
	public static void main(String[]args) throws FileNotFoundException{//SHOULDN'T DO!! 
		//Create a scanner to take in the input for the file name
		Scanner user = new Scanner(System.in);
		String fileName = user.next();
		
		//Run methods with fileName as the parameter
		makeRandFile(fileName);
		printFile(fileName);
		//Run squareChange method and then print the new file
		System.out.println();
		squareChange(fileName);
		printFile(fileName);
	}
	
	//Make formated file
	public static void makeRandFile(String fileName) throws FileNotFoundException{//SHOULDN'T DO!! 
		//String names
		String[] names = {"Morgan", "Roy", "Dan", "Josh", "Adam", "Greg", "Alex", "Henry", "Zach", "Connor"};
		//Try to create file
		Formatter fileOut = new Formatter(fileName);	
		Random intGen = new Random();
		
		for(int x = 0; x < intGen.nextInt(11)+10; x++){
			fileOut.format("%s %d\n", names[intGen.nextInt(10)], intGen.nextInt(10)+1);
		}
		fileOut.close();
	}
	
	//Print file contents of earlier format
	public static void printFile(String fileName) throws FileNotFoundException{//SHOULDN'T DO!! 
		//Create scanner
		Scanner in = new Scanner(new File(fileName));
		in.useDelimiter("[\\n]");
		
		//Read file from left to right for every line, and make a new line 
		//for every line in the file
		while(in.hasNext()){
			System.out.println(in.next());
		}
		
	}
	
	//Method for reading file and rewriting it with it's squares
	public static void squareChange(String fileName) throws FileNotFoundException{//SHOULDN'T DO!! 
		//The way this is going to be read is a system of read old file,
		//save said tokens, and re-write the new file with the adjustments
		
		//Scanner to count lines
		Scanner line = new Scanner(new File(fileName));
		line.useDelimiter("[\\n]");
		//Scanner to read tokens in lab format
		Scanner fileIn = new Scanner(new File(fileName));
		fileIn.useDelimiter("[ \\n]");
		
		//Find out how many lines
		int lineCount = 0;
		//Count the lines
		while(line.hasNext()){
			String stuff = line.next(); lineCount++;
		}
		//Variables to save the names read, the numbers read, and the line amount read
		String[] name = new String[lineCount];
		int[] num = new int[lineCount];
		
		//Run for how ever many lines there are
		for(int x = 0; x < lineCount; x++){
			name[x] = fileIn.next();
			num[x] = fileIn.nextInt();
		}
		
		Formatter fileOut = new Formatter(fileName);
		//Run for how ever many lines there WERE
		for(int x = 0; x < lineCount; x++){
			fileOut.format("%s %d\n", name[x], num[x]*num[x]);
		}
		fileOut.close();
	}
	

}
