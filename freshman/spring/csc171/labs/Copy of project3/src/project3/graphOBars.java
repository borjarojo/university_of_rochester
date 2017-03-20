package project3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import java.awt.Component.*;
import java.awt.event.*;

public class graphOBars extends JPanel{

	Timer happening = new Timer();
	
	static bars [] barGraph;
	
	double inter [];
	static int barArrayLint;
	
	double theHi;
	
	//Setting up the background
	public graphOBars() {
		super();
		setBackground(new Color(220,220,220));
		
		//setting up the timer
		happening.scheduleAtFixedRate(new tikTok(), 0, 1000);
		
	}
	
	
	//DRAWING ALL THE STUFF
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		
		
		//draws the bars based on the array of bars (methods called inside)
		if(textBoxes.getDNewCurrent() < textBoxes.getDNewDeath()&&textBoxes.getDNewAgeRetire() < textBoxes.getDNewDeath()&&textBoxes.getDNewCurrent() < textBoxes.getDNewAgeRetire())
		{
			inter = generalMeth.yearValues(textBoxes.getDNewDaily(), textBoxes.getDNewCurrent(), textBoxes.getDNewStarting(), textBoxes.getDNewAgeRetire(), textBoxes.getDNewRetireIncome(), textBoxes.getDNewDeath(), textBoxes.getDNewInterest(), textBoxes.getDNewInflation());
			barGraph = generalMeth.barMaker(inter);
			theHi = generalMeth.highest(inter);

			for(int i = 0; i<barGraph.length; i++)
			{
				g.setColor(barGraph[i].getHue());
				g.fillRect(barGraph[i].getXPoint(),barGraph[i].getYPoint(),barGraph[i].getHi(),barGraph[i].getWid());
			}
		}
		//should scale correctly
		int height = getHeight();    
		int width = getWidth();
	
		//Declaring the two lines that will be the axis
		g.setColor(Color.BLUE);
    	g.drawLine(0, 300, width, 300);
    	g.drawLine(10, 0, 10, height);
    	
    	//Showing numbers on the axis
    	g.setColor(Color.BLACK);
    	g.setFont(new Font("Serif", Font.BOLD, 10));
    	int yel = 0;
    	for (int p = (int)textBoxes.getDNewCurrent(); p <= width; p++)
    	{
    	
    		String Xaxis = String.valueOf(p);
    		g.drawString(Xaxis, (14*yel)+11, 310);
    		yel++;
    	}
    	
    	//Resizes the Y axis based on the highest number of savings 
    	if(theHi<30000)
    	{
    		for (int p = 0; p >= -height; p--)
    		{
    				int value = 0;
    				//draws numbers on y axis
    				g.setFont(new Font("Serif", Font.BOLD, 15));
    				String Yaxis = String.valueOf(p+11);
    				int use = Integer.parseInt(Yaxis);
    				g.drawString(use*3+"k", 2, -28*p);
    				value = value*3;
    		}
    	}
    	
    	else if(theHi<60000)
    	{
    		for (int p = 0; p >= -height; p--)
    		{
    				int value = 0;
    				//draws numbers on y axis
    				g.setFont(new Font("Serif", Font.BOLD, 15));
    				String Yaxis = String.valueOf(p+11);
    				int use = Integer.parseInt(Yaxis);
    				g.drawString(use*6+"k", 2, -28*p);
    				value = value*3;
    		}
    	}
    	else if (theHi<150000 )
    	{
    		for (int p = 0; p >= -height; p--)
    		{
    				int value = 0;
    				//draws numbers on y axis
    				g.setFont(new Font("Serif", Font.BOLD, 15));
    				String Yaxis = String.valueOf(p+11);
    				int use = Integer.parseInt(Yaxis);
    				g.drawString(use*15+"k", 2, -28*p);
    				value = value*3;
    		}
    	}
    	else
    	{
    		for (int p = 0; p >= -height; p--)
    		{
    				int value = 0;
    				//draws numbers on y axis
    				g.setFont(new Font("Serif", Font.BOLD, 15));
    				String Yaxis = String.valueOf(p+11);
    				int use = Integer.parseInt(Yaxis);
    				g.drawString(use*90+"k", 2, -28*p);
    				value = value*3;
    		}
    	}
    	
    	
				
	}


//the timer class
	class tikTok extends TimerTask
	{
		public void run() 
		{
			repaint();
		}
	}
}