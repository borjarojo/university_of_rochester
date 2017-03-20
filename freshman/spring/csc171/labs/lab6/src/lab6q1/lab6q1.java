package lab6q1;

import java.util.Scanner;

public class lab6q1 {
	public static void main(String[]args){
		//This program evaluates a distance traveled after a 
		//certain number of hours at a certain speed, both 
		//which are given by the user
		
		//initialize variables
		double time, speed, distance;
		Scanner userInput = new Scanner(System.in);
		
		//tell user what the program is about
		System.out.println("This program determines how far you will travel at a certain speed for a certain amount of time.\n");
		
		//quarry user
		System.out.println("Enter how many hours of travel: ");
		time = userInput.nextDouble();
		
		System.out.println("Enter the speed or travel: ");
		speed = userInput.nextDouble();
		
		for(double x = 1.0; x != time +1; x++){
			distance = x * speed;
			System.out.printf("\nAfter %.0f hour you will have gone %.2f miles", x, distance);
		}
		
	}
}
