package project2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class tttEast extends JPanel implements ActionListener{
	
	private JButton input0 = new JButton(); 
	private JButton input1 = new JButton();
	private JButton input2 = new JButton();
	private JButton input3 = new JButton();
	private JButton input4 = new JButton();
	private JButton input5 = new JButton();
	private JButton input6 = new JButton();
	private JButton input7 = new JButton();
	private JButton input8 = new JButton();
	
	//This side of the board will change depending on how many players are playing
	//and what symbol is going first
	public tttEast(int status, int playnum){
		setLayout(new GridLayout(2,1));
		setBackground(Color.yellow);
			//Upper half
			JPanel upper = new JPanel(new GridLayout(3,3));
			upper.setBackground(Color.yellow);
				//Give action listener to the inputs
				input0.addActionListener(this);
				input1.addActionListener(this);
				input2.addActionListener(this);
				input3.addActionListener(this);
				input4.addActionListener(this);
				input5.addActionListener(this);
				input6.addActionListener(this);
				input7.addActionListener(this);
				input8.addActionListener(this);
				
				//Add button
				upper.add(input0); upper.add(input1); upper.add(input2);
				upper.add(input3); upper.add(input4); upper.add(input5);
				upper.add(input6); upper.add(input7); upper.add(input8);
			add(upper);
			//Lower half
			JPanel lower = new JPanel(new GridLayout(2,1));
				JLabel turn = new JLabel(playnum == 1? (status == 0 ? "Player 1's Turn" : "CPU's Turn") : (status == 0 ? "Player 1's Turn" : "Player 2's Turn"));
				lower.add(turn);
				JLabel gameType = new JLabel(playnum == 1 ? "Single Player" : "Multiplayer");
				
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == input0 && tttPanel.playStatus == 0){
			
		}

		if(e.getSource() == input1){
			
		}

		if(e.getSource() == input2){
			
		}

		if(e.getSource() == input3){
			
		}

		if(e.getSource() == input4){
			
		}

		if(e.getSource() == input5){
			
		}

		if(e.getSource() == input6){
			
		}

		if(e.getSource() == input7){
			
		}

		if(e.getSource() == input8){
			
		}
		
		
	}

}
