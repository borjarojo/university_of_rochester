
/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 8 Question 1
 * 
 * File Name: DataAnalysis.java
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
 * Last Revised: ebruary 23th, 2014
 * 
 * Lab Partner: Haotion Li
 */

import java.util.Scanner;

public class DataAnalysis {

	public static void main(String[]args){
		//tell user what the program is
		System.out.println("This program tells you the max, min, average, \nand total number of integers in an entered set.\n");
		
		//set varibles
		Scanner input = new Scanner(System.in);
		int numEnter = 0;
		int count = 0;
		int total = 0;
		int max = 0;
		int min = 0;
		double average;

		//getinputs
		while(numEnter >=0){
			System.out.println("Please enter a positive integer. Negative integers will end data entry: ");
			
			//applying the entered number to a reference variable in the loop
			numEnter = input.nextInt();
			
			//only do calculations if the number is positive
			if(numEnter >= 0){
				//adding numbers together for the average
				total = total + numEnter;
				
				//set number entered to max if it is larger then the last largest number entered
				if(numEnter > max){
					max = numEnter;
				}
				
				//set number entered equal to min on first iteration
				if(count == 0){
					min = numEnter;
				}
				
				//set numer entered to min if smaller then last entered smallest number
				if(numEnter < min){
					min = numEnter;
				}
			}
			
			count++;
			
		}
		//off by 1 correction
		count--;
		
		//average calculation
		average = (double)total/count;
		
		//data printing
		System.out.printf("Average: %f Max: %d Min: %d Count: %d", average, max, min, (count));
		
	}
}
