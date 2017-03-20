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

public class CartesianPanel extends JPanel{
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		g.setColor(Color.lightGray);
		
		for(int x = 0; x < 20; x++){
			g.drawLine(0, (getHeight()*19/20)*x/20, (getWidth()*19/20), (getHeight()*19/20)*x/20);
			g.drawLine((getWidth()*19/20)*x/20, 0, (getWidth()*19/20)*x/20, (getHeight()*19/20));
			
		}
		
		g.setColor(Color.black);
		g.drawLine((getWidth()*19/20)/2, 0, (getWidth()*19/20)/2, (getHeight()*19/20));
		g.drawLine(0, (getHeight()*19/20)/2, (getWidth()*19/20), (getHeight()*19/20)/2); 
		
		g.drawString("x-axis", (getWidth()*19/20)/2 - 10, (getHeight()));
		g.drawString("y-axis", getWidth() -40, (getHeight()*19/20)/2);
		
	}
}
