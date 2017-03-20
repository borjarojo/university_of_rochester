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

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

public class MyPanel extends JPanel{
	
	int[] loc = {getHeight(), getWidth()};
	int cIndex = 1;
	
	MyKeyListener keyboard = new MyKeyListener();
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		
		//Use loc array to denote (x,y) corrodinates
		
		Color[] c = {Color.blue, Color.red, Color.yellow, Color.green, Color.orange};
		
		//set color of position of the rectangle based on the global
		//variables, which can then be controlled by the KeyListener
		//private class
			g.setColor(c[cIndex]);
			g.fillRect(loc[0], loc[1], 50, 50);
	}
	
	private class MyKeyListener 
	implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			//Check Keys being pushed for up, down, left, right, and enter
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				loc[0]++;
				//if the loc of x is less then 0, set to 0 
				if(loc[0] < 0){
					loc[0] = 0;
				}
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				loc[0]--;
				//if the loc of x is greater then width, set to width 
				if(loc[1] > getWidth()){
					loc[1] = getWidth();
				}
			}else if(e.getKeyCode() == KeyEvent.VK_UP){
				loc[1]++;
				//if the loc of y is less then 0, set to 0
				if(loc[1] == -1){
					loc[1] = 0;
				}
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				loc[1]--;
				//if the loc of y is greater then height, set to height
				if(loc[1] > getHeight()){
					loc[1] = getHeight();
				}
			}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				cIndex++;
				//if the index is higher then the array length-1, set to 0
				if(cIndex > 4){
					cIndex = 0;
				}
				
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
