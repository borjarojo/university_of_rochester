package project2;

import java.awt.*;
import javax.swing.*;

public class tttCenter extends JPanel{

	public tttCenter(int[][] score){
		setBackground(Color.blue);
		JPanel center = new JPanel(new GridLayout(3,3, 10, 10));
		center.setBackground(Color.blue);
			JLabel q11 = new JLabel();
			JLabel q12 = new JLabel();
			JLabel q13 = new JLabel();
			JLabel q21 = new JLabel();
			JLabel q22 = new JLabel();
			JLabel q23 = new JLabel();
			JLabel q31 = new JLabel();
			JLabel q32 = new JLabel();
			JLabel q33 = new JLabel();
			//Statements that check the table status. 0 is blank. 1 is x. 2 is o.
			q11.setIcon(new ImageIcon((score[0][0] == 0) ? "yellowBlock.jpg" : ((score[0][0] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q12.setIcon(new ImageIcon((score[0][1] == 0) ? "yellowBlock.jpg" : ((score[0][1] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q13.setIcon(new ImageIcon((score[0][2] == 0) ? "yellowBlock.jpg" : ((score[0][2] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q21.setIcon(new ImageIcon((score[1][0] == 0) ? "yellowBlock.jpg" : ((score[1][0] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q22.setIcon(new ImageIcon((score[1][1] == 0) ? "yellowBlock.jpg" : ((score[1][1] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q23.setIcon(new ImageIcon((score[1][2] == 0) ? "yellowBlock.jpg" : ((score[1][2] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q31.setIcon(new ImageIcon((score[2][0] == 0) ? "yellowBlock.jpg" : ((score[2][0] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q32.setIcon(new ImageIcon((score[2][1] == 0) ? "yellowBlock.jpg" : ((score[2][1] == 1) ? "blueX.jpg" : "blueO.jpg")));
			q33.setIcon(new ImageIcon((score[2][2] == 0) ? "yellowBlock.jpg" : ((score[2][2] == 1) ? "blueX.jpg" : "blueO.jpg")));
			center.add(q11);
			center.add(q12);
			center.add(q13);
			center.add(q21);
			center.add(q22);
			center.add(q23);
			center.add(q31);
			center.add(q32);
			center.add(q33);
		
		add(center);
		
	}
}
