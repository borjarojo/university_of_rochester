/* 
 * CSC 171

 * 
 * Version 1.0
 * 
 * DON'T COPY - It's the law
 * 
 * Assignment: Lab 12
 * 
 * File Name: Cartesian.java
 * 
 * Author: Borja Rojo
 * 
 * Date: 4/06/14
 * 
 * Course Number: CSC 171, 76456
 * 
 * Lab Session: Tues/Thurs 2:00-3:15
 * 
 * TA Name: David Sekora
 * 
 * Last Revised: April 6th, 2014
 * 
 * Lab Partner: Haotion Li
 */

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

public class lab12main extends JFrame{
	
	public static void main(String[]args){
		JFrame window = new JFrame();
		MyPanel rect = new MyPanel();
		
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500, 500);
		window.add(rect);
	}
}
