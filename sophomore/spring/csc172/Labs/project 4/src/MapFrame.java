import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 * 
 * Holds my frame class
 */

public class MapFrame extends JFrame{
	
	public MapFrame(JPanel panel){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(30,300);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
