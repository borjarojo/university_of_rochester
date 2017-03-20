package lab2q4;

/* Part 4
* CSC 171
* 
* Version 1.0
* 
* DON'T COPY - It's the law
* 
* Assignment: Lab 2
* 
* File Name: lab2q4.java
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

import javax.swing.JOptionPane;

public class lab2q4{
		public static void main(String[]args){
			//This program tells you how long a certain number of seconds is in hours, minutes, and seconds
			
				//Tell user what the program is and prompt for an integer for analysis
				int secondInput, hours, minutes, seconds;
				
				secondInput = Integer.parseInt(JOptionPane.showInputDialog("This program tells you how many\nhours, minutes, and seconds there\nare in a certain number of seconds.\n\nPlease enter how many seconds\nyou want to be calculated"));
				
				//Show Results
					//Calculation
					hours = (secondInput-(secondInput%60))/3600;
					minutes = ((secondInput%3600)-(secondInput%60))/60;
					seconds = (secondInput)%60;
							
					//Display results
					JOptionPane.showMessageDialog(null, hours+" hours, "+minutes+" minutes, and "+seconds+" seconds");
			
		}

}
