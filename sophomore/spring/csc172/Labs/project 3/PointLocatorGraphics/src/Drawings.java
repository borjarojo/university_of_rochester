/*
 * Borja Rojo
 * Project3
 * 4/11/15
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Drawings{
	private JLabel text;
	
  public Drawings(Line[] lines)
  {
    JFrame frame = new JFrame();
    frame.setSize(500,550);
    frame.setTitle("Point Locator Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    // creating a container JPanel, and 2 GUI elements: one for drawing the lines and one for the message
    JPanel container = new JPanel();
    JPanel drawMessage = new JPanel();
    MainFrame drawlines = new MainFrame(lines);
    
    // setting sizes of 2 inner GUI elements
    drawlines.setPreferredSize(new Dimension(500, 500));
    drawMessage.setPreferredSize(new Dimension(500, 50));
    
    // assigning borders to the 2 inner elements so they are easier to see
	Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
	drawlines.setBorder(border);
	Border border1 = BorderFactory.createLineBorder(Color.RED, 1);
	drawMessage.setBorder(border1);	
	
	// adding the inner elements to the container JPanel
    container.setLayout(new BorderLayout());
    container.add(drawlines, BorderLayout.CENTER);
    container.add(drawMessage, BorderLayout.NORTH);
    frame.add(container);
    
    // adding a JLabel that contains the text, inside of the drawMessage JPanel
    text = new JLabel();
    text.setText("Click twice anywhere in the region below to test if they are in the same region.");
    drawMessage.setLayout(new BorderLayout());
    drawMessage.add(text, BorderLayout.CENTER);

    frame.setVisible(true);
  }	
}

