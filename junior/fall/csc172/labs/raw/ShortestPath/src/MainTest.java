/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
import java.io.FileNotFoundException;


public class MainTest {
	public static void main(String[]args) throws FileNotFoundException{
		//These are the graphs asked to be made in file format and used
		//My custom graphs can be made with not crossing lines. Also,
		//ask me for a picture of them if you'd like. I just drew some up.
		System.out.println("-------------------\n"
				+ "---Shortest Path---\n"
				+ "-------------------\n");
		System.out.println("Creating textbook and custom graphs from files...");
		Graph nineTen = Graph.createFromFile("graph9.10.txt");
		Graph nineSeventeen = Graph.createFromFile("graph9.17.txt");
		Graph nineSixtyTwo = Graph.createFromFile("graph9.62.txt");
		Graph customOne = Graph.createFromFile("customGraph1.txt");
		Graph customTwo = Graph.createFromFile("customGraph2.txt");
		Graph customThree = Graph.createFromFile("customGraph3.txt");
		
		//Printing shortest path examples
		System.out.println("Printing some shortest paths...");
		System.out.println("---On Figure 9.10---");
		customThree.printShortestPath(2, 8);
		customThree.printShortestPath(9, 1);
		
		System.out.println("---On Figure 9.17---");
		customThree.printShortestPath(7, 1);
		customThree.printShortestPath(3, 0);
		
		//These notes are letters. They were entered A-E as 0-4
		System.out.println("---On Figure 9.62---");
		customThree.printShortestPath(4, 1);
		customThree.printShortestPath(3, 4);
		
		System.out.println("---On Custom Graph 1---");
		customThree.printShortestPath(0, 5);
		customThree.printShortestPath(9, 2);
		
		System.out.println("---On Custom Graph 2---");
		customThree.printShortestPath(8, 4);
		customThree.printShortestPath(3, 0);
		
		System.out.println("---On Custom Graph 3---");
		customThree.printShortestPath(2, 8);
		customThree.printShortestPath(9, 1);
	}
}
