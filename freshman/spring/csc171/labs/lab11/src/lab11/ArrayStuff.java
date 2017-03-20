/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 11
 * 
 * File Name: lab1.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 3/18/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: March 23th, 2014
 * 
 * Lab Partner: Haotion Li
 */

import java.util.*;

public class ArrayStuff {
	//2
	//RNG array creator
	public static int[] createArray(int n){
		int[] a = new int[n];
		//Create an array with random number generation
		Random gen = new Random();
		
		for(int x = 0; x < n; x++){
			a[x] = gen.nextInt(n+1);
		}
		return a;
	}
	//3
	//print method
	public static void printArray(int a[]){
		
		//Horizontally, print an array of numbers with spaces in between them
		for(int x = 0; x < a.length; x++){
			System.out.print(a[x] + " ");
		}
		//Drop down a line
		System.out.println();
	}
	//4
	//Copy method
	public static int[] copyArray(int a[]){
		int[] b = new int[a.length];
		
		//for every respective index, set the input array element to the copy element value
		for(int x = 0; x < a.length; x++){
			b[x] = a[x];
		}
		
		return b;
	}
	
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		
		int n = 0;
		//1
		//Query
		System.out.println("Please enter a positive integer...");
		n = input.nextInt();
		
		//5
			//Test Methods
				//create array using creation method
				int[] array = createArray(n);
				//print array with print method
				printArray(array);
				//copy array with copy method
				int[] copy = copyArray(array);
				
	
				//create alias by creating a new array and setting it equal to an existing array
				int[] alias = array;
			
				//Change original array
				array[0] = -1;
			
				//Print each array
				printArray(array); printArray(copy); printArray(alias);
		
	}
}
