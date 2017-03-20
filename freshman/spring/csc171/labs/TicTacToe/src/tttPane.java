import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.*;

/*
 * This class houses all of the GUI for the game. 
 * It will also control all of the variables. It 
 * will use the JPane class in order to build the 
 * GUI and will use the ActionListener interface
 * to retrieve signal from various inputs from the
 * game. This will also be able to read a file that
 * has the data of the games stored.
 */
public class tttPane extends JPanel	implements ActionListener{
	
	//Data variables
		//Current Variables i.e. while the game is being played
		private int[][] table = new int[3][3];	//Keeps track of the board status. 0 = Blank; 1 = X; 2 = O
		private int currentPCount = 2;			//Keeps track of how many players want to play when a new game is being started
		private int currentP1Symbol = 1;		//Keeps track of what player 1 wants to play as when a new game is being started. 1 = X; 2 = O
		private int [] score = new int[2];	//Keeps track of the score depending on what is in the external file
		private int currentPlayer = 1;			//Keeps track of who's turn it is. 0 = CPU; 1 = P1; 2 = P2
		private boolean gameWon = false;
		private boolean winX = false;
		private boolean winO = false;
		
		
		//Changing variables
		private int selectPCount = 1; 			//Variable to select the play for a new game
		private int selectP1Symbol = 1;			//Variable to select the symbol for P1
	
	//JButtons to input moves
		private JButton input11 = new JButton();
		private JButton input12 = new JButton();
		private JButton input13 = new JButton();
		private JButton input21 = new JButton();
		private JButton input22 = new JButton();
		private JButton input23 = new JButton();
		private JButton input31 = new JButton();
		private JButton input32 = new JButton();
		private JButton input33 = new JButton();
		
	//Game mode selection
		private JButton pCountOne = new JButton("1");
		private JButton pCountTwo = new JButton("2");
		private JButton p1SymbolX = new JButton("X");
		private JButton p1SymbolO = new JButton("O");
		private JButton newGame = new JButton("New Game");
		
	//Start game button
		private JButton startButton = new JButton("Start!");
	
	public tttPane()
	{
		//This constructor will format a pane that has the buttons and 
		//will give those buttons the ActionListener
		
		//Read and set the score from the file
		readScore();
		
		//Create the Pane
				//This JPane
				setLayout(new BorderLayout(0,5));
				setBackground(Color.blue);
				
					//Change all method that creates the other parts to the master JPanel
						splashScreen();
						
					

						//Give the buttons ActionListener
						
						//Move inputs
						input11.addActionListener(this);
						input12.addActionListener(this);
						input13.addActionListener(this);
						input21.addActionListener(this);
						input22.addActionListener(this);
						input23.addActionListener(this);
						input31.addActionListener(this);
						input32.addActionListener(this);
						input33.addActionListener(this);
						
						//Game parameters
						pCountOne.addActionListener(this);
						pCountTwo.addActionListener(this);
						p1SymbolX.addActionListener(this);
						p1SymbolO.addActionListener(this);
						newGame.addActionListener(this);
						
						//Add button
						startButton.addActionListener(this);
					
					/*
					//North
						changeNorth(new JPanel());
				
					//West
						//Not used
						
					//Center
						changeCenter(new JPanel());
						
					//East
						changeEast(new JPanel());
								
					//South
						changeSouth(new JPanel());
						
						*/
			
		
	}

	public void actionPerformed(ActionEvent e){
		if(		(e.getSource() == input11 || 
				e.getSource() == input12 || 
				e.getSource() == input13 || 
				e.getSource() == input21 || 
				e.getSource() == input22 || 
				e.getSource() == input23 || 
				e.getSource() == input31 ||
				e.getSource() == input32 || 
				e.getSource() == input33) &&
				!gameWon){
			
			if(e.getSource() == input11 ){
				if(table[0][0] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[0][0] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[0][0] = 2;
											currentPlayer = 0;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[0][0] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[0][0] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[0][0] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[0][0] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
				
			}
			
			if(e.getSource() == input12){
				if(table[0][1] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[0][1] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[0][1] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[0][1] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[0][1] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[0][1] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[0][1] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			if(e.getSource() == input13){
				if(table[0][2] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[0][2] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[0][2] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[0][2] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[0][2] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[0][2] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[0][2] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			if(e.getSource() == input21 ){
				if(table[1][0] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[1][0] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[1][0] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[1][0] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[1][0] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[1][0] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[1][0] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			if(e.getSource() == input22){
				if(table[1][1] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[1][1] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
										case 2:
											table[1][1] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[1][1] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[1][1] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[1][1] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[1][1] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			if(e.getSource() == input23){
				if(table[1][2] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[1][2] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[1][2] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[1][2] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[1][2] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[1][2] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[1][2] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
				
			}
			
			if(e.getSource() == input31 ){
				if(table[2][0] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[2][0] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[2][0] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[2][0] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[2][0] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[2][0] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[2][0] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			if(e.getSource() == input32){
				if(table[2][1] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[2][1] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[2][1] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[2][1] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[2][1] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[2][1] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[2][1] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			if(e.getSource() == input33){
				if(table[2][2] == 0){
					switch (currentPCount){
						case 1:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[2][2] = 1;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
										case 2:
											table[2][2] = 2;
											winCheck(currentP1Symbol, table);
											cpuCheck(table, currentP1Symbol, gameWon);
											break;
									}
									break;
							}
							break;
						case 2:
							switch (currentPlayer){
								case 1:
									switch (currentP1Symbol){
										case 1:
											table[2][2] = 1;
											currentPlayer = 2;
											break;
										case 2:
											table[2][2] = 2;
											currentPlayer = 2;
											break;
									}
									break;
								case 2:
									switch (currentP1Symbol){
										case 1:
											table[2][2] = 2;
											currentPlayer = 1;
											break;
										case 2:
											table[2][2] = 1;
											currentPlayer = 1;
											break;
									}
									break;
							}
							break;
					}
				}
			}
			
			winCheck(currentP1Symbol, table);
		}
		
		
		
		//Game creation buttons
			//Select players
				if(e.getSource() == pCountOne){
					selectPCount = 1;
					if(selectPCount == 1){
						selectP1Symbol = 1;
					}
				}
				if(e.getSource() == pCountTwo){
					selectPCount = 2;
				}
			//Select Symbol
				if(e.getSource() == p1SymbolX){
					selectP1Symbol = 1;
				}
				if(e.getSource() == p1SymbolO){
					selectP1Symbol = 2;
					if(selectPCount == 1){
						selectP1Symbol = 1;
					}
					
				}
			//Create new game
				if(e.getSource() == newGame){
					currentP1Symbol = selectP1Symbol;
					currentPCount = selectPCount;
					if(currentP1Symbol == 1){
						currentPlayer = 1;
					}else{
						if(currentPCount == 1){
							currentPlayer = 0;
						}else{
							currentPlayer = 2;
						}
					}
					gameWon = false;
					winX = false;
					winO = false;
					resetCenter();
				}
		
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				System.out.print(table[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(currentPlayer);System.out.println();
		
		if(e.getSource() == startButton){
			
		}
		//update
		changeAll();
	}

	private void resetCenter() {
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				table[x][y] = 0;
			}
		}
	}
	
	private void changeCenter(JPanel c){
		
		//Center
		c.setLayout(new GridLayout(3,3));
		c.setBackground(Color.blue);
			//add pictures to the center Grid to represent board status
			JLabel c11 = new JLabel(new ImageIcon(table[0][0] == 0 ? "yellowBlock.jpg" : (table[0][0] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c12 = new JLabel(new ImageIcon(table[0][1] == 0 ? "yellowBlock.jpg" : (table[0][1] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c13 = new JLabel(new ImageIcon(table[0][2] == 0 ? "yellowBlock.jpg" : (table[0][2] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c21 = new JLabel(new ImageIcon(table[1][0] == 0 ? "yellowBlock.jpg" : (table[1][0] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c22 = new JLabel(new ImageIcon(table[1][1] == 0 ? "yellowBlock.jpg" : (table[1][1] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c23 = new JLabel(new ImageIcon(table[1][2] == 0 ? "yellowBlock.jpg" : (table[1][2] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c31 = new JLabel(new ImageIcon(table[2][0] == 0 ? "yellowBlock.jpg" : (table[2][0] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c32 = new JLabel(new ImageIcon(table[2][1] == 0 ? "yellowBlock.jpg" : (table[2][1] == 1 ? "blueX.jpg" : "blueO.jpg")));
			JLabel c33 = new JLabel(new ImageIcon(table[2][2] == 0 ? "yellowBlock.jpg" : (table[2][2] == 1 ? "blueX.jpg" : "blueO.jpg")));
			
			c.add(c11); c.add(c12); c.add(c13);
			c.add(c21); c.add(c22); c.add(c23);
			c.add(c31); c.add(c32); c.add(c33);
			
		add(c, BorderLayout.CENTER);
	}
	
	private void changeNorth(JPanel north) {
		north.setBackground(Color.yellow);
			//Add an image to north
			JLabel banner = new JLabel(new ImageIcon("tictactoeyellow.jpg"));
			north.add(banner); //add JLabel with ImageIcon to North
		add(north, BorderLayout.NORTH); //add north JPanel to north sector of tttPane
		
	}
	
	private void changeEast(JPanel east){
		//East
		east.setLayout(new GridLayout(2,1));
		east.setBackground(Color.yellow);
			//east 1,1
			JPanel east11 = new JPanel(new GridLayout(3,3));
			east11.setBackground(Color.yellow);
				//add JButtons for move input
				east11.add(input11); east11.add(input12); east11.add(input13);
				east11.add(input21); east11.add(input22); east11.add(input23);
				east11.add(input31); east11.add(input32); east11.add(input33);
				east.add(east11);
			
			//east 2,1
			JPanel east21 = new JPanel(new GridLayout(2,1));
			east21.setBackground(Color.yellow);
				//Label that keeps track of who's turn it is
				JLabel playTurn = new JLabel(gameWon == true ? "Game Finished" : (currentPlayer == 0 ? "CPU's Turn" : (currentPlayer == 1 ? "Player 1's Turn" : "Player 2's Trun")), SwingConstants.CENTER);
				playTurn.setBackground(Color.yellow);
				east21.add(playTurn);
				
				//Grid that keeps track of score
				JPanel scorePane = new JPanel(new GridLayout(1,3));
				scorePane.setBackground(Color.yellow);
					//Label of P1's score. CPU and P2 share the same score
					JLabel p1Score = new JLabel(Integer.toString(score[0]), SwingConstants.CENTER);
					p1Score.setBackground(Color.yellow);
					scorePane.add(p1Score);
					//Label that is a dash
					JLabel dash = new JLabel("-", SwingConstants.CENTER);
					dash.setBackground(Color.yellow);
					scorePane.add(dash);
					//Label of p2 and CPU's score
					JLabel p2Score = new JLabel(Integer.toString(score[1]), SwingConstants.CENTER);
					p2Score.setBackground(Color.yellow);
					scorePane.add(p2Score);
					
				east21.add(scorePane);
				
			east.add(east21);
		add(east, BorderLayout.EAST);
		
	}
	
	private void changeSouth(JPanel south){
			south.setLayout(new GridLayout(1,5));
			south.setBackground(Color.yellow);
			//Flow panel for the first fifth
			JPanel south1 = new JPanel(new FlowLayout());
			south1.setBackground(Color.yellow);
				//Labels
				JLabel setL = new JLabel("Set", SwingConstants.CENTER);
				setL.setBackground(Color.yellow);
				south1.add(setL);
				
				JLabel newL = new JLabel("New", SwingConstants.CENTER);
				newL.setBackground(Color.yellow);
				south1.add(newL);
				
				JLabel gameL = new JLabel("Game");
				gameL.setBackground(Color.yellow);
				south1.add(gameL);
				
				JLabel parametersL = new JLabel("Parameters", SwingConstants.CENTER);
				parametersL.setBackground(Color.yellow);
				south1.add(parametersL);
			south.add(south1);
			
			//Grid panel for second south grid
			JPanel south2 = new JPanel(new GridLayout(3,1));
			south2.setBackground(Color.yellow);
				//add label
				JLabel playerSelect = new JLabel("Players", SwingConstants.CENTER);
				playerSelect.setBackground(Color.yellow);
				south2.add(playerSelect);
				//add buttons
				south2.add(pCountOne);
				south2.add(pCountTwo);
			south.add(south2);
			
			//Grid panel for third grid
			JPanel south3 = new JPanel(new GridLayout(2,1));
			south3.setBackground(Color.yellow);
				//upper grid of south3 is a flow layout
				JPanel south31 = new JPanel(new FlowLayout());
				south31.setBackground(Color.yellow);
					//JLabels
					JLabel symbolSelectP = new JLabel("Player", SwingConstants.CENTER);
					symbolSelectP.setBackground(Color.yellow);
					south31.add(symbolSelectP);
					
					JLabel symbolSelectOne = new JLabel("1", SwingConstants.CENTER);
					symbolSelectOne.setBackground(Color.yellow);
					south31.add(symbolSelectOne);
					
					JLabel symbolSelectS = new JLabel("Select", SwingConstants.CENTER);
					symbolSelectS.setBackground(Color.yellow);
					south31.add(symbolSelectS);
					
				south3.add(south31);
				//Lower grid of south 3 is grid
				JPanel south32 = new JPanel(new GridLayout(1,2));
				south32.setBackground(Color.yellow);
					//add buttons
					south32.add(p1SymbolX);
					south32.add(p1SymbolO);
				south3.add(south32);
			south.add(south3);
			
			//Grid panel for fourth south grid spot
			JPanel south4 = new JPanel(new GridLayout(2,2));
			south4.setBackground(Color.yellow);
				//south411
				JLabel south411 = new JLabel("Players:", SwingConstants.CENTER);
				south411.setBackground(Color.yellow);
				south4.add(south411);
				//south412 - depends on the input parameter selected
				JLabel south412 = new JLabel((selectPCount == 1 ? "1" : "2"), SwingConstants.CENTER);
				south412.setBackground(Color.yellow);
				south4.add(south412);
				//south421
				JLabel south421 = new JLabel("Symbol:", SwingConstants.CENTER);
				south421.setBackground(Color.yellow);
				south4.add(south421);
				//south422 - depends on the input parameter selected
				JLabel south422 = new JLabel((selectP1Symbol == 1 ? "X" : "O"), SwingConstants.CENTER);
				south422.setBackground(Color.yellow);
				south4.add(south422);
			south.add(south4);
			
			//Add newGame button to the grid
			south.add(newGame);
		add(south, BorderLayout.SOUTH);


		
	}

	private void changeAll(){
		removeAll();
		setBackground(Color.blue);
		changeNorth(new JPanel());
		changeSouth(new JPanel());
		changeEast(new JPanel());
		changeCenter(new JPanel());
		revalidate();
	}
	
	private void splashScreen(){
		//Main
		JPanel splash = new JPanel(new BorderLayout());
			
			//center
			JLabel center = new JLabel(new ImageIcon("Untitled.jpg"));
			splash.add(center, BorderLayout.CENTER);
			
			//South
			splash.add(startButton, BorderLayout.SOUTH);
		add(splash);
	}
	
	private void winCheck(int p1Symbol, int[][] table){
		winTest();
		
		if(p1Symbol == 1 && winX == true) score[0]++; //if player 1 was X and X won, add to player 1 score
		if(p1Symbol == 1 && winO == true) score[1]++; //if player 1 was x and O won, add to player 2 score
		
		if(p1Symbol == 2 && winX == true) score[1]++; //if player 1 was O and X won, add to player 2 score
		if(p1Symbol == 2 && winO == true) score[0]++; //if player 1 was x and O won, add to player 2 score
		
		if(gameWon == true)
		{
			tttFile.writeScore(score);
		}
	}
		
	public void winTest(){
		//Code to test for win
		//code for x
		if(	(table[0][0] == 1 && table[0][1] == 1 && table[0][2] == 1) ||	//first row
			(table[1][0] == 1 && table[1][1] == 1 && table[1][2] == 1) ||	//second row
			(table[2][0] == 1 && table[2][1] == 1 && table[2][2] == 1) ||	//third row
			(table[0][0] == 1 && table[1][0] == 1 && table[2][0] == 1) ||	//first column
			(table[0][1] == 1 && table[1][1] == 1 && table[2][1] == 1) ||	//second column
			(table[0][2] == 1 && table[1][2] == 1 && table[2][2] == 1) ||	//third column
			(table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1) ||	//first diagonal
			(table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 1) ){	//second diagonal
			winX = true;
			gameWon = true;
		}else{
			//draw test
			if(	table[0][0] != 0 && 
				table[0][1] != 0 && 
				table[0][2] != 0 && 
				table[1][0] != 0 && 
				table[1][1] != 0 && 
				table[1][2] != 0 && 
				table[2][0] != 0 && 
				table[2][1] != 0 && 
				table[2][2] != 0){	//If all spaces are full after checking for a win, make gameWon true, but add no score
				gameWon = true;
			}
		}
		
		//code for o
		if(	(table[0][0] == 2 && table[0][1] == 2 && table[0][2] == 2) ||	//first row
			(table[1][0] == 2 && table[1][1] == 2 && table[1][2] == 2) ||	//second row
			(table[2][0] == 2 && table[2][1] == 2 && table[2][2] == 2) ||	//third row
			(table[0][0] == 2 && table[1][0] == 2 && table[2][0] == 2) ||	//first column
			(table[0][1] == 2 && table[1][1] == 2 && table[2][1] == 2) ||	//second column
			(table[0][2] == 2 && table[1][2] == 2 && table[2][2] == 2) ||	//third column
			(table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2) ||	//first diagonal
			(table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 2) ){	//second diagonal
			winO = true;
			gameWon = true;
		}else{
			//draw test
			if(	table[0][0] != 0 && 
				table[0][1] != 0 && 
				table[0][2] != 0 && 
				table[1][0] != 0 && 
				table[1][1] != 0 && 
				table[1][2] != 0 && 
				table[2][0] != 0 && 
				table[2][1] != 0 && 
				table[2][2] != 0){	//If all spaces are full after checking for a win, make gameWon true, but add no score
				gameWon = true;
			}
		}
		
		
	}
	
	
	/*
	 * AI Protocol
	 * 1. Win if you can
	 * 2. Block if you must
	 * 3.
	 */
	public void cpuCheck(int[][] table, int currentP1Symbol, boolean gameWon){
		
		if(gameWon == false){
		
		if(currentP1Symbol == 1){	//Player 1 is X
			while(true){
				//Check for wins
					//First row
					if(table[0][0] == 0 && table[0][1] == 2 && table[0][2] == 2){
						table[0][0] = 2;
						break;
					}
					
					if(table[0][0] == 2 && table[0][1] == 0 && table[0][2] == 2){
						table[0][1] = 2;
						break;
					}
					if(table[0][0] == 2 && table[0][1] == 2 && table[0][2] == 0){
						table[0][2] = 2;
						break;
					}
					
					//second row
					if(table[1][0] == 0 && table[1][1] == 2 && table[1][2] == 2){
						table[1][0] = 2;
						break;
					}
					
					if(table[1][0] == 2 && table[1][1] == 0 && table[1][2] == 2){
						table[1][1] = 2;
						break;
					}
					if(table[1][0] == 2 && table[1][1] == 2 && table[1][2] == 0){
						table[1][2] = 2;
						break;
					}
					
					//third row
					if(table[2][0] == 0 && table[2][1] == 2 && table[2][2] == 2){
						table[2][0] = 2;
						break;
					}
					
					if(table[2][0] == 2 && table[2][1] == 0 && table[2][2] == 2){
						table[2][1] = 2;
						break;
					}
					if(table[2][0] == 2 && table[2][1] == 2 && table[2][2] == 0){
						table[2][2] = 2;
						break;
					}
					
					//First col
					if(table[0][0] == 0 && table[1][0] == 2 && table[2][0] == 2){
						table[0][0] = 2;
						break;
					}
					if(table[0][0] == 2 && table[1][0] == 0 && table[2][0] == 2){
						table[1][0] = 2;
						break;
					}
					if(table[0][0] == 2 && table[1][0] == 2 && table[2][0] == 0){
						table[2][0] = 2;
						break;
					}
					
					//second col
					if(table[0][1] == 0 && table[1][1] == 2 && table[2][1] == 2){
						table[0][1] = 2;
						break;
					}
					
					if(table[0][1] == 2 && table[1][1] == 0 && table[2][1] == 2){
						table[1][1] = 2;
						break;
					}
					if(table[0][1] == 2 && table[1][1] == 2 && table[2][1] == 0){
						table[2][1] = 2;
						break;
					}
					
					//Thrid col
					if(table[0][2] == 0 && table[1][2] == 2 && table[2][2] == 2){
						table[0][2] = 2;
						break;
					}
					
					if(table[0][2] == 2 && table[1][2] == 0 && table[2][2] == 2){
						table[1][2] = 2;
						break;
					}
					if(table[0][2] == 2 && table[1][2] == 2 && table[2][2] == 0){
						table[2][2] = 2;
						break;
					}
					
					//first diagonal
					if(table[0][0] == 0 && table[1][1] == 2 && table[2][2] == 2){
						table[0][0] = 2;
						break;
					}
					
					if(table[0][0] == 2 && table[1][1] == 0 && table[2][2] == 2){
						table[1][1] = 2;
						break;
					}
					if(table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 0){
						table[2][2] = 2;
						break;
					}
					
					//second diagonal
					if(table[0][2] == 0 && table[1][1] == 2 && table[2][0] == 2){
						table[0][2] = 2;
						break;
					}
					
					if(table[0][2] == 2 && table[1][1] == 0 && table[2][0] == 2){
						table[1][1] = 2;
						break;
					}
					if(table[0][2] == 2 && table[1][1] == 2 && table[2][0] == 0){
						table[2][0] = 2;
						break;
					}
					
			//Check loss
					//First row
					if(table[0][0] == 0 && table[0][1] == 1 && table[0][2] == 1){
						table[0][0] = 2;
						break;
					}
					
					if(table[0][0] == 1 && table[0][1] == 0 && table[0][2] == 1){
						table[0][1] = 2;
						break;
					}
					if(table[0][0] == 1 && table[0][1] == 1 && table[0][2] == 0){
						table[0][2] = 2;
						break;
					}
					
					//second row
					if(table[1][0] == 0 && table[1][1] == 1 && table[1][2] == 1){
						table[1][0] = 2;
						break;
					}
					
					if(table[1][0] == 1 && table[1][1] == 0 && table[1][2] == 1){
						table[1][1] = 2;
						break;
					}
					if(table[1][0] == 1 && table[1][1] == 1 && table[1][2] == 0){
						table[1][2] = 2;
						break;
					}
					
					//third row
					if(table[2][0] == 0 && table[2][1] == 1 && table[2][2] == 1){
						table[2][0] = 2;
						break;
					}
					
					if(table[2][0] == 1 && table[2][1] == 0 && table[2][2] == 1){
						table[2][1] = 2;
						break;
					}
					if(table[2][0] == 1 && table[2][1] == 1 && table[2][2] == 0){
						table[2][2] = 2;
						break;
					}
					
					//First col
					if(table[0][0] == 0 && table[1][0] == 1 && table[2][0] == 1){
						table[0][0] = 2;
						break;
					}
					if(table[0][0] == 1 && table[1][0] == 0 && table[2][0] == 1){
						table[1][0] = 2;
						break;
					}
					if(table[0][0] == 1 && table[1][0] == 1 && table[2][0] == 0){
						table[2][0] = 2;
						break;
					}
					
					//second col
					if(table[0][1] == 0 && table[1][1] == 1 && table[2][1] == 1){
						table[0][1] = 2;
						break;
					}
					
					if(table[0][1] == 1 && table[1][1] == 0 && table[2][1] == 1){
						table[1][1] = 1;
						break;
					}
					if(table[0][1] == 1 && table[1][1] == 1 && table[2][1] == 0){
						table[2][1] = 2;
						break;
					}
					
					//Thrid col
					if(table[0][2] == 0 && table[1][2] == 1 && table[2][2] == 1){
						table[0][2] = 2;
						break;
					}
					
					if(table[0][2] == 1 && table[1][2] == 0 && table[2][2] == 1){
						table[1][2] = 2;
						break;
					}
					if(table[0][2] == 1 && table[1][2] == 1 && table[2][2] == 0){
						table[2][2] = 2;
						break;
					}
					
					//first diagonal
					if(table[0][0] == 0 && table[1][1] == 1 && table[2][2] == 1){
						table[0][0] = 2;
						break;
					}
					
					if(table[0][0] == 1 && table[1][1] == 0 && table[2][2] == 1){
						table[1][1] = 2;
						break;
					}
					if(table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 0){
						table[2][2] = 2;
						break;
					}
					
					//second diagonal
					if(table[0][2] == 0 && table[1][1] == 1 && table[2][0] == 1){
						table[0][2] = 2;
						break;
					}
					
					if(table[0][2] == 1 && table[1][1] == 0 && table[2][0] == 1){
						table[1][1] = 2;
						break;
					}
					if(table[0][2] == 1 && table[1][1] == 1 && table[2][0] == 0){
						table[2][0] = 2;
						break;
					}
					
			//Block forks
					if((table[0][2] == 1 && table[1][1] == 2 && table[2][0] == 1) && //if condition
							(table[0][1] == 0 && table[0][0] == 0 &&
							table[1][0] == 0 && table[1][2] == 0 &&
							table[2][2] == 0 && table[2][1] == 0)){
						table[1][0] = 2;
						break;
					}
					
					if((table[0][0] == 1 && table[1][1] == 2 && table[2][2] == 1) && //if condition
							(table[0][1] == 0 && table[0][2] == 0 &&
							table[1][0] == 0 && table[1][2] == 0 &&
							table[2][0] == 0 && table[2][1] == 0)){
						table[1][0] = 2;
						break;
					}
				
			//First move
					if(table[1][1] == 0){
							table[1][1] = 2;
							break;
					}else{
						if(table[0][0] == 0){
							table[0][0] = 2;
							break;
						}else{
							if(table[1][2] == 0){
								table[1][2] = 2;
								break;
							}else{
								if(table[2][1] == 0){
									table[2][1] = 2;
									break;
								}else{
									if(table[0][1] == 0){
										table[0][1] = 2;
										break;
									}else{
										if(table[1][0] == 0){
											table[1][0] = 2;
											break;
										}else{
											if(table[0][2] == 0){
												table[0][2] = 2;
												break;
											}else{
												if(table[2][2] == 0){
													table[2][2] = 2;
													break;
												}else{
													if(table[2][0] == 0){
														table[2][0] = 2;
														break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}else{						//Computer is X
			
		}
		
		}
		
	}
	

	public void readScore(){
		score[0] = tttFile.readScoreOne();
		score[1] = tttFile.readScoreTwo();
	}
	
}
