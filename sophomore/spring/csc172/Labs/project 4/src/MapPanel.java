import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 * 
 * Holds the panel to draw the graph on
 */

public class MapPanel extends JPanel{
	private Graph graph;
	
	public MapPanel(Graph g){
		graph = g;
	}
	
	public void paintComponent(Graphics g){

		for(int i = 0; i < graph.vertices(); i++){
			for(int j = 0; j < graph.vertices(); j++){
				g.drawLine((int) (1000000 * graph.n[i].lati()),
						(int) (1000000 * graph.n[i].longi()),
						(int) (1000000 * graph.n[j].lati()), 
						(int) (1000000 * graph.n[j].longi()));
				
			}
		}
		
	}
}
