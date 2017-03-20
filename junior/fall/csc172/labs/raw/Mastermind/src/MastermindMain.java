/*
 * Author: Borja Rojo
 * CSC172
 */

import java.util.Scanner;


public class MastermindMain {
	public static void main(String[]args){
		Scanner in = new Scanner(System.in);
		System.out.print("Human game or automatic game? (h/a): ");
		String a = in.next();
		while(a.compareTo("h") != 0 && a.compareTo("a") != 0){
			System.out.print("Sorry... Invalid choice. Human or automatic? (h/a): ");
			a = in.next();
		}
		
		if(a.compareTo("h") == 0){
			humanGame();
		}else{
			autoGame();
		}	
	}
	
	public static void humanGame(){
		boolean play = true;
		System.out.println("---Welcome! This is a Mastermind solver!---\n\n"
				+ "This program tries to guess a secret code with colors \nand"
				+ " length of your choosing, then it solves the puzzle!\n"
				+ "Watch the process in real time!\n");
		int positions = getPositions();
		String[] colors = getColors(positions);
		System.out.println();
		
		Codebreaker cpu = new Codebreaker(colors, positions);
		
		while(play){
			System.out.print("This is the current guess: ");
			cpu.printCodeToString(cpu.currentGuess);
			int[] r = getPegs();
			System.out.println(r[0] + " black and " + r[1] + " white pegs\n");
			
			while(r[0] != cpu.pos){
				//Flipped in code
				cpu.response(r[1], r[0]);
				cpu.setNextGuess();
				
				System.out.print("This is the current guess: ");
				cpu.printCodeToString(cpu.currentGuess);
				r = getPegs();
				System.out.println(r[0] + " black and " + r[1] + " white pegs");
				System.out.println();
			}
			
			System.out.print("This is the final guess: ");
			cpu.printCodeToString(cpu.currentGuess);
			
			System.out.print("\nWould you like to play again? (y/n): ");
			Scanner in = new Scanner(System.in);
			String p = in.next();
			while(p.compareTo("y") != 0 && p.compareTo("n") != 0){
				System.out.print("Sorry...Invalid input. Play again? (y/n);");
				p = in.next();
			}
			if(p.compareTo("y") == 0){
				cpu.newGame();
			}else{
				play = false;
			}
		}
	}
	
	public static void autoGame(){
		boolean play = true;
		System.out.println("---Welcome! This is a Mastermind solver!---\n\n"
				+ "This program Generates a random secret code with colors \nand"
				+ " length of your choosing, then it solves the puzzle!\n"
				+ "Watch the process in real time!\n");
		int positions = getPositions();
		String[] colors = getColors(positions);
		System.out.println();
		
		Codebreaker cpu = new Codebreaker(colors, positions);
		
		while(play){
			System.out.print("This is the secret code: ");
			cpu.printCodeToString(cpu.secretCode);
			
			System.out.print("This is the current guess: ");
			cpu.printCodeToString(cpu.currentGuess);
			System.out.print("Guessing... response is ");
			int[] r = cpu.compareGuesses(cpu.currentGuess, cpu.secretCode);
			System.out.println(r[0] + " black and " + r[1] + " white pegs\n");
			
			while(r[0] != cpu.pos){
				//Flipped in code
				cpu.response(r[1], r[0]);
				cpu.setNextGuess();
				
				System.out.print("This is the current guess: ");
				cpu.printCodeToString(cpu.currentGuess);
				System.out.print("Guessing... response is ");
				r = cpu.compareGuesses(cpu.currentGuess, cpu.secretCode);
				System.out.println(r[0] + " black and " + r[1] + " white pegs");
				System.out.println();
			}
			
			System.out.print("This is the final guess: ");
			cpu.printCodeToString(cpu.currentGuess);
			System.out.print("The secret code was: ");
			cpu.printCodeToString(cpu.secretCode);
			
			System.out.print("\nWould you like to play again? (y/n): ");
			Scanner in = new Scanner(System.in);
			String p = in.next();
			while(p.compareTo("y") != 0 && p.compareTo("n") != 0){
				System.out.print("Sorry...Invalid input. Play again? (y/n);");
				p = in.next();
			}
			if(p.compareTo("y") == 0){
				cpu.newGame();
			}else{
				play = false;
			}
		}
	}
	
	public static int[] getPegs(){
		Scanner in = new Scanner(System.in);
		int[] a = new int[2];
		
		System.out.print("How many black pegs? ");
		a[0] = in.nextInt();
		System.out.print("How many white pegs? ");
		a[1] = in.nextInt();
		
		return a;
	}
	
	public static void printIntArray(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static int getPositions(){
		Scanner in = new Scanner(System.in);
		System.out.print("How many tokens will a combination have? ");
		return in.nextInt();
	}
	
	public static String[] getColors(int pos){
		String[] s = new String[pos];
		Scanner in = new Scanner(System.in);
		
		System.out.println("What tokens would you like to use?");
		for(int i = 0; i < pos; i++){
			System.out.print("Token " + (i+1) + ": ");
			s[i] = in.next();
		}
		
		return s;
	}
}
