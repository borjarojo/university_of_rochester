
/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 7
 * 
 * File Name: lab7.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 2/18/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: February 23rd, 2014
 * 
 * Lab Partner: Haotion Li
 */

import javax.swing.JFrame;


public class lab7 {
	
	public static void main(String[]args)
	{
		Flag brit = new Flag();
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(brit);
		window.setSize(600,480);
		window.setVisible(true);
	}
}
