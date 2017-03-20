package lab5;

import java.util.Scanner;

public class Lab5 {
		public static void main(String[]args) {
			/*This program collects basic info 
			* from a user and gives them useful 
			* information in turn */
			
			//Tell the user the purpose of the program and ask them for information
				double age = 0, gradeNum = 0, testTotal = 0, gradeCount = 0, testAverage, gradeCountFalse = 0;
				boolean drinkSmart = false, testInput = true;
				String name = "null", drinkAnswer, grade, testAverageLetter;
				Scanner userInput = new Scanner(System.in);
				
				System.out.println("This program evaluates information you give it.");	//Tell user program purpose
				
				System.out.println("\nWhat is your name? ");							//Ask user for name
					name = userInput.nextLine();	
				
				System.out.println("\nHow old are you? ");								//Ask user for age
					age = userInput.nextDouble();
				
				while (drinkSmart == false) {
					
					System.out.println("\nAre your old enough to drink? (Y/N)");			//Ask user if he/she can drink
						drinkAnswer = userInput.next();
						
						if (!(drinkAnswer.equals("y")|
							  drinkAnswer.equals("Y")|
							  drinkAnswer.equals("n")|
							  drinkAnswer.equals("N"))){					//Check Input
							System.out.println("\nPlease enter a valid answer: Are your old enough to drink? (Y/N)");
							drinkAnswer = userInput.nextLine();
						}
						//Evaluate the input
					if (((age < 21) && (drinkAnswer.equals("n")|drinkAnswer.equals("N")))|
						((age >= 21) && (drinkAnswer.equals("y")|drinkAnswer.equals("Y")))) {
						System.out.printf("\n\n%s, are well informed.", name);
						drinkSmart = true;
					}
					else{
						System.out.println("\nYou are wrong! Please try again!");
					}
				}
				//Run the for-loop to demonstrate counting
				for(; age < 21; age++){
					System.out.printf("\nWhen you are %.0f, you will have %.0f years left to drink!", age, 21.0 - age);
				}
				
				//Letter grade averaging 
				
				System.out.println("This part of the program give you the average of some test grades. \nEnter the grade you received, or type \"Q\" to quit: ");
				gradeCount = 0;
					//While loop to receive inputs and add tests to the total stack
				while(testInput){
					grade = userInput.nextLine();
					if(grade.equals("A")|grade.equals("a")){
						gradeNum = 100;
					}else{
						if(grade.equals("B")|grade.equals("b")){
							gradeNum = 90;
						}else{
							if(grade.equals("C")|grade.equals("c")){
								gradeNum = 70;
							}else{
								if(grade.equals("D")|grade.equals("d")){
									gradeNum = 60;
								}else{
									if(grade.equals("F")|grade.equals("f")){
										gradeNum = 50;
									}else{
										if(grade.equals("N")|grade.equals("n")){
											gradeNum = 0;
										}else{
											if(grade.equals("Q")|grade.equals("q")){
												testInput = false;
										}else{
											System.out.println("Please enter an appropriate letter grade.");
											gradeCountFalse++;
										}
										}
									}
								}
							}
						}
					}
					testTotal = testTotal + gradeNum; gradeNum = 0; //Add grades together
					gradeCount++;									//Keep track of test amount
					
					System.out.println("You entered: " + grade);	
				}
				//Grade evaluation
				
				gradeCount = gradeCount - gradeCountFalse;
				testAverage = testTotal/gradeCount;
				
				if(testAverage>=97){
					testAverageLetter = "A+";
				}else{
					if(testAverage>=93){
						testAverageLetter = "A";
					}else{
						if(testAverage>=90){
							testAverageLetter = "A-";
						}else{
							if(testAverage>=87){
								testAverageLetter = "B+";
							}else{
								if(testAverage>=83){
									testAverageLetter = "B";
								}else{
									if(testAverage>=80){
										testAverageLetter = "B-";
									}else{
										if(testAverage>=77){
											testAverageLetter = "C+";
										}else{
											if(testAverage>=73){
												testAverageLetter = "C";
											}else{
												if(testAverage>=70){
													testAverageLetter = "C-";
												}else{
													if(testAverage>=67){
														testAverageLetter = "D+";
													}else{
														if(testAverage>=63){
															testAverageLetter = "D";
														}else{
															if(testAverage>=60){
																testAverageLetter = "D-";
															}else{
																testAverageLetter = "F";
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				//Print out average result
				System.out.printf("\nThe average of your tests is %s", testAverageLetter);
	}
}
