package lab2q3;

/* Part 3
* CSC 171
* 
* Version 1.0
* 
* DON'T COPY - It's the law
* 
* Assignment: Lab 2
* 
* File Name: lab2q3.java
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

public class lab2q3 {
	public static void main(String[]args){
		//This program calculates Celsius to Fahrenheit conversions
		
				double fahrenheit, celsius;
				
				//Tell user purpose of program
				//Prompt user to enter a celsius temperature using JOptionPane
				celsius = Double.parseDouble(JOptionPane.showInputDialog("This program converts Celsius to Fahrenheit\n\nPlease enter a Celsius temperature: "));
				
				//Calculation
				fahrenheit = (((celsius)*(5/9))+32);
				
				//Display Farenheit
				JOptionPane.showMessageDialog(null, "Temperature in Fahrenheit is "+fahrenheit);
	}
}
