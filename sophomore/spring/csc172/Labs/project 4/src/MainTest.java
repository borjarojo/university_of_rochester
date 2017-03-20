import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;

/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 */
public class MainTest{
	public static void main(String[]args) throws FileNotFoundException{
		
		//Create Graph
		MapReader map = new MapReader(new File(args[0]));
		Graph graph = new Graph("U of R Campus", map.getVert(), false);
		graph.insertEdges(map.getEdges());
		
		int dirFlag = 0;
		
		while(args[dirFlag].compareTo("-directions") != 0){
			dirFlag++;
		}
		
		graph.dijkstra(map.findNode(args[dirFlag + 1]), map.findNode(args[dirFlag + 2]));
		graph.printDij();
		graph.mwst(graph.n[30]);
	}
}
