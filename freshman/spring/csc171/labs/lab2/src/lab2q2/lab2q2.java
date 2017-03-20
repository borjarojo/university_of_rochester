package lab2q2;

/* Part 2
* CSC 171
* 
* Version 1.0
* 
* DON'T COPY - It's the law
* 
* Assignment: Lab 2
* 
* File Name: lab2q2.java
* 
* Author: Borja Rojo
* 
* Date: 1/30/14
* 
* Course Number: CSC 171, 76456
* 
* Lab Session: Tues/Thurs 2:00-3:15
* 
* TA Name: David Sekora
* 
* Last Revised: January 30th, 2014
* 
* Lab Partner: None
*/

import java.util.Scanner;

public class lab2q2 {
	public static void main(String[]args){
		
		//This program calculates Fahrenheit to Celsius conversions
			
		double fahrenheit, celsius;
		Scanner temp = new Scanner(System.in);
		
			//Tell user purpose of program
			System.out.println("This program converts Fahrenheit to Celsius");
			
			//Prompt user for input and save it
			System.out.print("What is the temperature today? ");
			fahrenheit = temp.nextDouble();
			System.out.printf("%.2f", fahrenheit);
			
			//Calculation
			celsius = (((fahrenheit - 32)*5)/9);
			
			//print results
			System.out.printf("\n\nThe temperature in Celsius is %.2f today.", celsius);
		
	}
}
