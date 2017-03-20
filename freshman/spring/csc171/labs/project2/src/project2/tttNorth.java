package project2;

import javax.swing.*;
import java.awt.*;

public class tttNorth extends JPanel{

	public tttNorth(int oneScore, int twoScore){
		setLayout(new BorderLayout());
		setBackground(Color.yellow);
		//center
		JLabel pic = new JLabel();
			pic.setBackground(Color.yellow);
			pic.setIcon(new ImageIcon("tictactoeyellow.jpg"));
		add(pic, BorderLayout.CENTER);
		//east
		JPanel score = new JPanel(new GridLayout(3,1));
		score.setBackground(Color.yellow);
			//Label
			JLabel scoreLabel = new JLabel("Score", SwingConstants.CENTER);
			score.add(scoreLabel);
			//score Display
			JPanel scoreCount = new JPanel(new GridLayout(1,3));
			scoreCount.setBackground(Color.yellow);
				JLabel p1 = new JLabel(Integer.toString(oneScore), SwingConstants.CENTER);
				scoreCount.add(p1);
				JLabel dash = new JLabel("-", SwingConstants.CENTER);
				scoreCount.add(dash);
				JLabel p2 = new JLabel(Integer.toString(twoScore), SwingConstants.CENTER);
				scoreCount.add(p2);
			score.add(scoreCount);
			//Reset button
			JButton scoreReset = new JButton("Reset");
			score.add(scoreReset);
		add(score, BorderLayout.EAST);
	}
}
