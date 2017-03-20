package project2;

import javax.swing.JFrame;

public class tttMain extends JFrame{
	public static void main(String[]args){
		JFrame frame = new JFrame();
		tttPanel panel = new tttPanel();

		//aim to have Center as 500x500
		frame.add(panel);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(700,550);
	}

}
