package lab6q3;

import java.util.Scanner;

public class lab6q3 {
	public static void main(String[]args){
		
		//this programs prints a diamond depending on user input
		
		double lines;
		Scanner userInput = new Scanner(System.in);
		
		//Query user for input
		System.out.println("How many lines should the diamond be?");
		lines = userInput.nextDouble();
		
		//If statement to determine odd or even
		if(lines % 2 == 0){
			
			double halfLines = lines/2;
			
			//run top sequence for even
			for(double x = 1; x != halfLines+1; x++){
				
				for(double y = halfLines - x; y != 0; y--){
					System.out.printf(" ");
				}
				
				for(double y = 0; y <= x*2 -1; y++){
					System.out.printf("*");
				}
				System.out.println();
			}
			//run bottom sequence for even
			for(double x = halfLines; x != 0; x--){
				
				for(double y = halfLines - x; y != 0; y--){
					System.out.printf(" ");
				}
				
				for(double y = 0; y <= x*2 -1; y++){
					System.out.printf("*");
				}
				
				System.out.println();
			}
		}else{

			double halfLines = (lines+1)/2;
			
			//run top sequence for odd
			for(double x = 1; x != halfLines+1; x++){
				
				for(double y = halfLines - x; y != 0; y--){
					System.out.printf(" ");
				}
				
				for(double y = 0; y <= x*2 -2; y++){
					System.out.printf("*");
				}
				System.out.println();
			}
			
			//run bottom sequence for odd
			for(double x = halfLines; x != 1; x--){
				
				for(double y = (halfLines+1) - x; y != 0; y--){
					System.out.printf(" ");
				}
				
				for(double y = 0; y <= x*2 -4; y++){
					System.out.printf("*");
				}
				
				System.out.println();
			}
		}
		
	}
}
