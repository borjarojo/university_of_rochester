/*
 * Author:Borja Rojo
 * CSC 172
 * Project 1
 * 
 * Mastmind Code Breaker
 */

import java.util.*;
import java.io.*;
import java.math.*;


public class Mastermind {
	public int pegAmount;
	public int colorAmount;
	public int totalPos;
	public String[] colors;
	public int[][] posList;
	public boolean[] boolList;
	public int[] currentGuess;
	
	public Mastermind(){
		pegAmount = pegAmount();
		colorAmount = colorAmount();
		totalPos = totalPos(colorAmount, pegAmount);

		colors = returnColors(colorAmount);
		posList = createPosList(colorAmount, pegAmount, totalPos);
		boolList = createBoolList(totalPos);
		currentGuess = new int[pegAmount];
		
		for(int x = 0; x < pegAmount; x++){
			currentGuess[x] = posList[x][0];
		}
		
	}
	
	public Mastermind(boolean status){
		if(status == false){
		}
	}
	
	public static int pegAmount(){
		Scanner scan = new Scanner(System.in);
		int pegAmount;
		
		System.out.print("How many slots will be used? Enter: ");
		pegAmount = scan.nextInt();
		
		return pegAmount;
		
	}
	
	public static int colorAmount(){
		int colorCount = 0;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\nHow many colors will be used? Enter an integer: ");
		colorCount = scan.nextInt();
		
		return colorCount;
	}
	
	public static int totalPos(int colorAmount, int pegAmount){
		int totalPos = 0;
		
		totalPos = (int) Math.pow(colorAmount, pegAmount);
		
		return totalPos;
	}
	
	public static String[] returnColors(int colorAmount){
		Scanner scan = new Scanner(System.in);
		String[] colorArray = new String[colorAmount];
		
		System.out.println("\nWhat colors will be used?");
		
		int x = 0;
		for(x = 0; x < colorAmount; x++){
			System.out.print("Color " + (x+1) + ": ");
			colorArray[x] = scan.next().toLowerCase();
		}
		return colorArray;
		
	}
	
	public static int[][] createPosList(int colorAmount, int pegAmount, int totalPos){
		int[][] posList = new int[pegAmount][totalPos];
		//int[] rowCode = new int[pegAmount];
		
		int i = 0;
		int j = 0;
		
		/*
		for(i = 0; i < pegAmount; i++){
			rowCode[i] = 0;
		}
		*/
		
		//Algorithm
		/*
		rotBlock = (int) (Math.pow(colorAmount, (pegAmount-i+1)));
		rotSpot = (j) % rotBlock;
		rotStep = rotBlock / colorAmount;
		color = (int) rotSpot / rotStep;
		*/
		int rotBlock = 0;
		int rotSpot = 0;
		int rotStep = 0;
		int color = 0;
		
		for(i = pegAmount; i > 0; i--){
			for(j = 0; j < totalPos; j++){
				
				rotBlock = (int) (Math.pow(colorAmount, (pegAmount-i+1)));
				rotSpot = (j) % rotBlock;
				rotStep = rotBlock / colorAmount;
				color = (int) rotSpot / rotStep;
				//System.out.println(rotBlock + ", " + rotSpot + ", " + color);
				//System.out.println((i-1) +", " + j + ", " + color);
				posList[(i-1)][j] = color; //System.out.println(posList[i-1][j]);
			}
		}
		return posList;
	}
	
	public static boolean[] createBoolList(int totalPos){
		boolean[] boolList = new boolean[totalPos];
		
		for(int x = 0; x < totalPos; x++){
			boolList[x] = true;
		}
		
		return boolList;
	}
	

	
	
	public int soloCorrectAmount(int[] code){
		int x = 0;
		int match = 0;
		
		for(x = 0; x < pegAmount; x++){
			if(currentGuess[x] == code[x]){
				match++;
			}
		}
		
		return match;
		
	}
	
	public static int correctAmount(){
		Scanner scan = new Scanner(System.in);
		
		int correct = 0;
		
		System.out.print("How many colors are right? ");
		System.out.print("How many are in the right place? ");
		correct = scan.nextInt();
		
		return correct;
	}
	
	public int guess(){
		Scanner scan = new Scanner(System.in);
		int correct = 0;
		
		System.out.println("Guessing...");
		for(int x = 0; x < pegAmount; x++){
			System.out.print(colors[currentGuess[x]] + " ");
		}
		System.out.println();
		
		correct = correctAmount();
		
		return correct;
	}
	
	public int guess(int[] code){
		Scanner scan = new Scanner(System.in);
		int correct = 0;
		
		System.out.println("Guessing...");
		for(int x = 0; x < pegAmount; x++){
			System.out.print(colors[currentGuess[x]] + " ");
		}
		System.out.println();
		
		correct = soloCorrectAmount(code);
		
		return correct;
	}
	
	public void editList(int[] currentGuess, int guessCorrect){
		int match = 0;
		int x = 0;
		int y = 0;
		
		for(x = 0; x < totalPos; x++){
			for(y = 0; y < pegAmount; y++){
				if(currentGuess[y] == posList[y][x]){
					match++;
				}
			}
			
			if(match != guessCorrect){
				boolList[x] = false;
			}
			match = 0;
		}
	}
	
	public void newCurrentGuess(){
		for(int x = 0; x < totalPos; x++){
			//System.out.println(boolList[x]);
			if(boolList[x] == true){
				for(int y = 0; y < pegAmount; y++){
					currentGuess[y] = posList[y][x];
				}
				//System.out.println(currentGuess.toString());
				return;
			}
		}
	}

	public boolean checkCorrect(){
		int x = 0;
		int one = 0;
		
		for(x = 0; x < totalPos; x++){
			if(boolList[x] == true){
				one++;
			}
		}
		if(one == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	//Required Methods
	
	public String[] newMove(){
		String[] list = new String[pegAmount];
		
		for(int x = 0; x < totalPos; x++){
			//System.out.println(boolList[x]);
			if(boolList[x] == true){
				for(int y = 0; y < pegAmount; y++){
					currentGuess[y] = posList[y][x];
				}
				//System.out.println(currentGuess.toString());
				for(int y = 0; y < pegAmount; y++){
					list[y] = colors[currentGuess[y]];
				}
				return list;
			}
		}
		return list;
	}
	
	public void response(String[] stringGuess, int guessCorrect){
		guess();
		
			int match = 0;
			int mark;
			int x = 0;
			int y = 0;
			
			
			int[] currentGuess = new int[pegAmount];
			
			for(x = 0; x < pegAmount; x++){
				for(y = 0; y < colorAmount; y++){
					if(stringGuess[x] == colors[y]){
						currentGuess[x] = y;
					}
				}
	
			}
			
			for(x = 0; x < totalPos; x++){
				for(y = 0; y < pegAmount; y++){
					if(currentGuess[y] == posList[y][x]){
						match++;
					}
				}
				
				if(match != guessCorrect){
					boolList[x] = false;
				}
				match = 0;
			}
			
		newCurrentGuess();
	}

	public static boolean reset(){
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Would you like to play? [yes:1 , no:0] - ");
		if(scan.nextInt() == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void main(String[]args){
		/*
		int color = 6;
		int peg = 4;
		int pos = totalPos(6,4);
		
		int[][] test = createPosList(color, peg, pos);
		boolean[] booltest = createBoolList(pos);
		
		for(int y = 0; y < pos; y++){
			for(int x = 0; x < peg; x++){
				System.out.print(test.posList[x][y]+ " ");
			}
			System.out.println();
		}
		
		for(int x = 0; x < pos; x++){
			System.out.println(booltest[x]);
		}
		*/
		
		/*
		
		for(int y = 0; y < test.totalPos; y++){
			for(int x = 0; x < test.pegAmount; x++){
				System.out.print(test.posList[x][y]+ " ");
			}
			System.out.println();
		}
		*/
		boolean game = true;
		
		
		while(reset()){
			Mastermind test = new Mastermind();
			
			int[] myCode = {1,3,2,3,4};
			while(test.checkCorrect() == false){
				int guess = test.guess();
				test.editList(test.currentGuess, guess);
				test.newCurrentGuess();
				System.out.println();
			}
			
			System.out.println("-Final Guess-");
			for(int x = 0; x < test.pegAmount; x++){
				System.out.print(test.colors[test.currentGuess[x]] + " ");
			}
			System.out.println();
			System.out.println();
		}
		
		
		
		
			
	}
	
	
}

