package lab2q5;

/* Part 5
* CSC 171
* 
* Version 1.0
* 
* DON'T COPY - It's the law
* 
* Assignment: Lab 2
* 
* File Name: lab2q5.java
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

public class lab2q5 {
	public static void main(String[]args){
		//This program estimates the population size in the future
		
		//Describe program and prompt user for values
		double currentPop, currentYear, annualGrowth, estimateYear, futurePop;
		Scanner info = new Scanner(System.in);
		
		System.out.println("This program help estimate the population size of the future.");
		System.out.println("\nPlease enter-");
		
			//Retrieve values in a list format
		
				//Current Population
				System.out.print("\n\tCurrent Population Size: ");
				currentPop = info.nextDouble();
				System.out.printf("\t%.0f", currentPop);
			
				//Current Year
				System.out.print("\n\tCurrent Year: ");
				currentYear = info.nextDouble();
				System.out.printf("\t%.0f", currentYear);
				
				//annual growth rate
				System.out.print("\n\tAnnual Growth Rate (in percent): ");
				annualGrowth = info.nextDouble();
				System.out.printf("\t%.0f", annualGrowth);
				
				//estimated Year
				System.out.print("\n\tEstimated Year: ");
				estimateYear = info.nextDouble();
				System.out.printf("\t%.0f", estimateYear);
				
			//Calculation
				futurePop = currentPop*Math.pow((1+(annualGrowth/100)), (estimateYear-currentYear));
				
			//Display results
				System.out.printf("\n\nThe estimated population size in %.0f is %.0f.", estimateYear, futurePop);
				
			
	}
}
