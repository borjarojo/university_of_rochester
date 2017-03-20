/*
 * Borja Rojo
 * project3
 * 4/11/15
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JPanel{
	private Line[] linesToDraw;
	private static JLabel text;
	public static Point p1;
	public static Point p2;
	public static Line l;
	public int count = 0;
	private static int c;
	private static File f2;

	public MainFrame(Line[] lines)
	{
	    linesToDraw = lines;
	    addMouseListener(new MyMouseListener());
	    text = new JLabel(); // adding text that changes with the clicks
	    add(text);
	    l = new Line(0, 0, 0, 0);
	    
	    f2 = new File("outfile.txt");
	}
	
	public void paintComponent(Graphics g)
	{
		 Graphics2D g2 = (Graphics2D) g;
		 super.paintComponent(g2);
		 Line2D line = new Line2D.Double();
		
		 // draws lines from the input file
	     for (int i = 0; i < linesToDraw.length; i++)
	     {
	        double  p1x = linesToDraw[i].p1.x*500;
	        double p1y = linesToDraw[i].p1.y*500;
	        double  p2x = linesToDraw[i].p2.x*500;
	        double p2y = linesToDraw[i].p2.y*500;
	        line.setLine(p1x,500-p1y,p2x,500-p2y);
	        g2.draw(line);  
	     } 
	}
	
	public static void resetDisplay(String x) throws IOException
	{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
		
		if (c == 0)
		{
			System.out.println(x+"\n");
			bw.write(x+"\n");
			text.setText(x);
		}
		c = 1;

		bw.close();
	}
	
	public static Line getLine()
	{
		return l;
	}
	
	private class MyMouseListener implements MouseListener {
		
		public void mouseClicked(MouseEvent event)
		{
			if(count == 0){
				p1 = new Point( (event.getPoint().x / 500.0), ((500.0 - event.getPoint().y) / 500.0));
				count++;
				c++;
			}
			else{
				p2 = new Point( (event.getPoint().x / 500.0), ((500.0 - event.getPoint().y) / 500.0));
				count = 0;
				l = new Line(p1.x, p1.y, p2.x, p2.y);
				c = 0;
			}
		}

		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
	}
}