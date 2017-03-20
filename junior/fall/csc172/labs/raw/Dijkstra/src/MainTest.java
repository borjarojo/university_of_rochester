/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
import java.io.FileNotFoundException;


public class MainTest {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Graph textGraph = Graph.createFromFile("graph9.20.txt");
//		Graph customGraphOne = Graph.createFromFile("customGraph1.txt");
//		Graph customGraphTwo = Graph.createFromFile("customGraph2.txt");
//		Graph customGraphThree = Graph.createFromFile("customGraph3.txt");
//		
//		System.out.print("Graph 9.20 - ");
//		textGraph.printShortestPath(0, 4);
//		
////		System.out.print("Custom Graph 1 - ");
////		customGraphOne.printShortestPath(4, 7);
//		
//		System.out.print("Custom Graph 2 - ");
//		customGraphTwo.printShortestPath(3, 5);
//		
//		System.out.print("Custom Graph 3 - ");
//		customGraphThree.printShortestPath(0, 9);
		
		textGraph = Graph.monroeFile("monroe-county.tab");
	}

}
