
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Flag extends JPanel
{
	public void paintComponent(Graphics g)
	{
	
		super.paintComponent(g);
		
		//Snowman
		int[] xPoints = {200, 200, 300};
		int[] ypoints = {95, 115, 100};
		
		//Background
			g.setColor(Color.blue);
			g.fillRect(0, 0, getWidth(), getHeight());
		
		//Body
			g.setColor(Color.white);
			g.fillOval(150, 50, 100, 100);
			g.fillOval(140, 150, 125, 125);
			g.fillOval(130, 275, 150, 150);
		
		//nose
			g.setColor(Color.orange);
			g.fillPolygon(xPoints, ypoints, 3);
		
		//eyes/buttons
			g.setColor(Color.black);
			//eyes
			g.fillOval(180, 80, 10, 10);
			g.fillOval(220, 80, 10, 10);
			
			//buttons
			g.fillOval(200, 180, 10, 10);
			g.fillOval(200, 200, 10, 10);
			g.fillOval(200, 220, 10, 10);
		
		//mouth
			g.fillArc(160, 120, 60, 10, -20, 40);
			
		//arms
			g.setColor(Color.darkGray);
			g.drawLine(140, 200, 90, 120);
			g.drawLine(265, 200, 315, 120);
		
		//Name the snowman
			g.setColor(Color.black);
			g.drawString("Do you want to build a snowman?", 100, 30);
	}
}
