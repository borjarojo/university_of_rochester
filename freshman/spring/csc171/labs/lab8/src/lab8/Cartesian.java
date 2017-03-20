package lab8;



/* 
 * CSC 171
 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 8 Question 3 and 4
 * 
 * File Name: Cartesian.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 2/20/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: February 23th, 2014
 * 
 * Lab Partner: Haotion Li
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Cartesian {
	
	public static void main(String[]args){
		
		JFrame window = new JFrame();
		CartesianPanel plane = new CartesianPanel();
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600,600);
		window.add(plane);
		
	}
	
}
