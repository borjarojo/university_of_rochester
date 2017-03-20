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

public class ArrayStuffSecond {
	
	//Print the 2d array of strings
	public static void print2DArray(String a[][]){
		//Go through each row
		for(int x = 0; x < a.length; x++){
			//Go through the elements in each row
			for(int y = 0; y < a[x].length; y++){
				System.out.print(a[x][y]);
				if(y != (a[x].length -1)){
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
	
	//Create a random 2d array, with determined row and column size
	public static int[][] random2D(int row, int col){
		Random gen = new Random();
		int[][] a = new int[row][col];
		
		//For as long as the row amount
		for(int x = 0; x < row; x++){
			//For as long as the column amount
			for(int y = 0; y < col; y++){
				a[x][y] = gen.nextInt(row*col);
			}
		}
		
		return a;
	}
	
	//Print the 2d array of ints
	public static void print2DArray(int a[][]){
		//Go through each row
		for(int x = 0; x < a.length; x++){
			//Go through the elements in each row
			for(int y = 0; y < a[x].length; y++){
				System.out.print(a[x][y]);
				if(y != (a[x].length -1)){
					System.out.print(",");
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[]args){
		String[][] friend = {{"zach","nina","connor"},{"kevin","arin","morgan"}};
		
		String[][] food = new String[3][2];
		food[0][0] = "pizza";
		food[0][1] = "burger";
		food[1][0] = "sushi";
		food[1][1] = "taco";
		food[2][0] = "salad";
		food[2][1] = "cake";
		
		Scanner input = new Scanner(System.in);
		
		//Print first array
		System.out.println("Print of first array\n");
		print2DArray(friend);
		//Print second array
		System.out.println("\nPrint second array\n");
		print2DArray(food);
		
		//Query
		System.out.print("\nPlease enter an integer for the row amount of an array...");
		int row = input.nextInt();
		System.out.print("\nPlease enter an integer for the col amount of that array...");
		int col = input.nextInt();
		
		//Test the new methods
			//RNG Array
			int[][] array = random2D(row,col);
			//Print int array with overloaded array
			System.out.println();
			print2DArray(array);
			
		
		
		
		
		
		
		
	}
}
