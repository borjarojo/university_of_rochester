package lab2q1;

/* Part 1
* CSC 171
* 
* Version 1.0
* 
* DON'T COPY - It's the law
* 
* Assignment: Lab 2
* 
* File Name: lab2q1.java
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

public class lab2q1 {
	//Start main method
	public static void main(String[]args) {
		//Declare purpose of program
		System.out.println("This program is shows the results of four arithmetic operations of two numbers");
		
		//Initialize two doubles for input and four doubles for output
		double numberOne, numberTwo;
		double addition, subtraction, multiplication, division;
		
		//Initialize a scanner for the keyboard
		Scanner keyboard = new Scanner(System.in);
		
		//Prompt and save user input
		System.out.print("\nPlease enter the first number: ");
		numberOne = keyboard.nextDouble();
		System.out.printf("%f", numberOne);
		
		System.out.print("\nPlease enter the second number: ");
		numberTwo = keyboard.nextDouble();
		System.out.printf("%f", numberOne);
		
		//Perform and display arithmetic operations
		addition = numberOne + numberTwo;
		subtraction = numberOne - numberTwo;
		multiplication = numberOne * numberTwo;
		division = numberOne / numberTwo;
		
		System.out.printf("\n\n%f+%f=%f\n%f-%f=%f\n%f*%f=%f\n%f/%f=%f", numberOne, numberTwo, addition, numberOne, numberTwo, subtraction, numberOne, numberTwo, multiplication, numberOne, numberTwo, division);
		
	}
}
