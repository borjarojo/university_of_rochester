package project2;

import java.awt.*;

import javax.swing.JPanel;

/*
 * This file hosts the graphics and values of the game.
 * This means that variables that determine the game type,
 * as well are inputs from the user during the game,
 * will be stored, accessed, and inputed from this class.
 */

public class tttPanel extends JPanel{
	
	public int playnum = 1;
	public int symbol = 1;
	public int[][] table = new int[3][3];
	public int teamOne = 0;
	public int teamTwo = 0;
	public int playStatus = 0;
	
	public tttPanel(){
		
		table[1][1] = 2;
		tttNorth north = new tttNorth(teamOne, teamTwo);
		tttEast east = new tttEast(playStatus, playnum);
		tttSouth south = new tttSouth(symbol, playnum);
		tttCenter center = new tttCenter(table);
		//tttWest west = new tttWest();
		
		setLayout(new BorderLayout(0,10));
		setBackground(Color.blue);
		add(south, BorderLayout.SOUTH);
		add(north, BorderLayout.NORTH);
		add(east, BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		//add(west, BorderLayout.WEST);
	}
	
	
	

}
