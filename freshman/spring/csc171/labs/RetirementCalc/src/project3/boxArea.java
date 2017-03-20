package project3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class boxArea extends JPanel{


	//This codes implements both the textboxes and the graph in the same frame
	public boxArea()
	{
		setLayout (new BorderLayout());
		
		
        add (new textBoxes(), BorderLayout.WEST);
        add(new graphOBars(), BorderLayout.CENTER);
		

	}
	    
	
}
