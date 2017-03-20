import java.io.FileNotFoundException;

import javax.swing.*;

public class tttMain extends JFrame{
	
	public static void main(String[]args) throws FileNotFoundException{
		
		JFrame frame = new JFrame();
		tttPane tictactoe = new tttPane();
		
		frame.add(tictactoe);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setSize(550,550);
		
		
		
	}
	
	

}
