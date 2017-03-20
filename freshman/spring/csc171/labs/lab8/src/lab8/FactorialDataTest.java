
/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 8 Question 2
 * 
 * File Name: FactorialDataTest.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 2/20/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: February 23th, 2014
 * 
 * Lab Partner: Haotion Li
 */

/**********************
 * The different calculations being made will result in more faulty calculations on the 
 * int variable type. This is due to the bits used in each kind of variable, which
 * determine how big of a number each type can hold. The long type has more bits, which
 * will result in being able to calculate larger numbers then int. 
 * 
 * In conclusion, the Int type calculations will hit a number "roof" before the long 
 * types will. If the roof is hit, calculations will no longer be accurate.
 */

import java.util.Scanner;

public class FactorialDataTest {
	
	public static void main(String[]args){
		
		System.out.println("This program calculates factorials with two differet data types, int and long\n");
		
		Scanner input = new Scanner(System.in);
		int intType, intCount, intFinal = 1;
		long longType, longCount, longFinal = 1;
		
		//int calc
			System.out.println("Please enter an integer for int calculations");
			intType = input.nextInt();
			intCount = intType;
			
			for(intCount = intType; intCount != 0; intCount--){
				intFinal *= intCount;
			}
		
		//long calc
			System.out.println("Please enter an integer for long calculations");
			longType = input.nextInt();
			longCount = longType;
			
			for(longCount = longType; longCount != 0; longCount--){
				longFinal *= longCount;
			}
		
		//Print results
			System.out.printf("Int Calc: %d Long Calc: %d", intFinal, longFinal);
		
	}

}

