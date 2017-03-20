package lab6q2;

import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class lab6q2 {
	public static void main(String[]args){
		
		double circCount;
		JFrame window = new JFrame();
		
			window.setTitle("Concentric Circles");
			window.setSize(480,480);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
			
		circCount = Double.parseDouble(JOptionPane.showInputDialog("Enter how many circles you want:"));
		
		for(double x = 0; x != circCount; x++){
			//set code to create circles based on how many were requested
		}
	}
}
