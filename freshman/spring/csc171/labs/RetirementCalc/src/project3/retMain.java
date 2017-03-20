package project3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class retMain {


	public static void main(String[] args) 
	{   

		JOptionPane.showMessageDialog(null, "RETIREMENT CALCULATOR \n   by: Borja Rojo");
		
		
		//Making the JFrame and calling the boxArea
		JFrame classABC = new JFrame();	
		
		classABC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		classABC.setVisible(true);
		classABC.add(new boxArea());
		classABC.pack();
		classABC.setSize(1300, 500);

	}
}
