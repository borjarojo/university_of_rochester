package project2;

import java.awt.*;

import javax.swing.*;

public class tttSouth extends JPanel{
	
	
	
	public tttSouth(int symbol, int playnum){
		setBackground(Color.yellow);
		setLayout(new GridLayout(1,5));
		//add the parts to the Grid
		
			//Grid 0
			JPanel newGame = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
			newGame.setBackground(Color.yellow);
				//New
				JLabel i = new JLabel("Input", SwingConstants.CENTER);
				JLabel n = new JLabel("New", SwingConstants.CENTER);
				JLabel g = new JLabel("Game", SwingConstants.CENTER);
				JLabel p = new JLabel("Parameters", SwingConstants.CENTER);
				JLabel h = new JLabel("Here", SwingConstants.CENTER);
				newGame.add(i);
				newGame.add(n);
				newGame.add(g);
				newGame.add(p);
				newGame.add(h);
			add(newGame);
			
			//Grid 1
			JPanel grid1 = new JPanel();
			grid1.setLayout(new GridLayout(3,1));
			grid1.setBackground(Color.yellow);
				//1,1 - player label
				JLabel players = new JLabel("Players", SwingConstants.CENTER);
				grid1.add(players);
				//2,1 - player button
				JButton playerOne = new JButton("1");
				grid1.add(playerOne);
				//3,1 - player 2
				JButton playerTwo = new JButton("2");
				grid1.add(playerTwo);
			add(grid1);
			
			//Grid 2
			JPanel grid2 = new JPanel();
			grid2.setLayout(new GridLayout(3,1));
			grid2.setBackground(Color.yellow);
				//1,2 - symbol label
				JLabel s = new JLabel("Symbol", SwingConstants.CENTER);
				grid2.add(s);
				//2,1 - symbol button
				JButton playerX = new JButton("X");
				grid2.add(playerX);
				//3,1 - symbol button
				JButton playerO = new JButton("O");
				grid2.add(playerO);
			add(grid2);
			
			//Grid 3 - Input data 
			//Changeable display parts
			JPanel grid3 = new JPanel(new GridLayout(2,2));
				JLabel pl = new JLabel("Player(s)");
				JPanel sym = new JPanel(new GridLayout(2,1));
					JLabel ps1 = new JLabel("Start");
					JLabel ps2 = new JLabel("Symbol");
				JLabel plnum = new JLabel(Integer.toString(playnum));
				JLabel symChar = new JLabel((symbol == 0) ? "X" : "O");
				grid3.setBackground(Color.yellow);
				grid3.add(pl); 
				grid3.add(plnum);
					sym.setBackground(Color.yellow);
					sym.add(ps1); sym.add(ps2);
				grid3.add(sym); 
				grid3.add(symChar);
			add(grid3);	
			
			//Grid 4 - in
			JButton game = new JButton("Start!");
			add(game);
	}
}
