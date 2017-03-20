package lab1;
//Part 2
/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 1
 * 
 * File Name: lab1.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 1/28/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: January 28th, 2014
 * 
 * Lab Partner: Haotion Li
 */

public class lab1 {
	
	//Start of main method
	public static void main (String[]args) {
		
		//Say this program was written by Borja Rojo, Part 1
		System.out.println("This program was "
							+"written by Borja Rojo "
							+"on January 28th, 2014.");						//Print author name and program creation date
		
		//List some of my favorite things, Part 3
		System.out.println("\nThese are a few of my \"favorite\" things.");
		System.out.println("\n\tRugby\n\tRowing\n\tMusic");					//Use one line to print a formated list
		
		//Say this program was written by Borja Rojo using the %s indicator, Part 1
		System.out.printf("\nThis program was "
							+"written by %s "
							+"on %s.\n"
							, "Borja Rojo", "January 28th, 2014");			//Print author name and program creation date
		
		//List some of my favorite things using %s indicators, Part 3
				System.out.println("\nThese are a few of my \"favorite\" things.");
				System.out.printf("\n\t%s\n\t%s\n\t%s", "Rugby", "Rowing", "Music");	//Use one line to print a formated list

	}//end of main method

}//End of class definition
