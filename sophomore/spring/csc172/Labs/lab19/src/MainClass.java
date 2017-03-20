/*
 Borja Rojo
 4/19/15
 Lab Partner: Daniel Saltz
 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException
	{	
		System.out.println("Testing example 9.10:");
		File f = new File("9.10.txt");
		Graph g = readFile(f);
		g.show();
		g.unweighted(2);
		System.out.print("Shortest path from 2 to 6 is: ");
		g.printPath(g.v[6]);

		
		System.out.println("\n\nTesting example 9.17:");
		File f1 = new File("9.17.txt");
		Graph g1 = readFile(f1);
		g1.unweighted(0);
		System.out.print("Shortest path from 0 to 3 is: ");
		g1.printPath(g1.v[3]);
		
		System.out.println("\n\nTesting example 9.62:");
		File f2 = new File("9.62.txt");
		Graph g2 = readFile(f2);
		g2.show();
		g1.unweighted(4);
		System.out.print("Shortest path from 4 to 2 is: ");
		g1.printPath(g2.v[2]);
		
		System.out.println("\n\nTesting test1.txt:");
		File f3 = new File("test1.txt");
		Graph g3 = readFile(f3);
		g3.show();
		g3.unweighted(2);
		System.out.print("Shortest path from 2 to 1 is: ");
		g3.printPath(g3.v[1]);
		
		System.out.println("\n\nTesting test2.txt:");
		File f4 = new File("test2.txt");
		Graph g4 = readFile(f4);
		g4.show();
		g4.unweighted(5);
		System.out.print("Shortest path from 5 to 7 is: ");
		g4.printPath(g4.v[7]);
		
		System.out.println("\n\nTesting test3.txt:");
		File f5 = new File("test3.txt");
		Graph g5 = readFile(f5);
		g5.show();
		g5.unweighted(0);
		System.out.print("Shortest path from 0 to 3 is: ");
		g5.printPath(g5.v[3]);
	}
	
	public static Graph readFile(File f) throws FileNotFoundException
	{
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		boolean isDirected = false;
			
		try {
			int numVertices = Integer.parseInt(br.readLine());
			char graphType = br.readLine().charAt(0);
			if (graphType == 'D')
				isDirected = true;
			
			Graph graph = new Graph(numVertices,isDirected);

			String s = br.readLine();
			while (s != null)
			{
				String[] s1 = s.split(" ");
				Edge e = new Edge(s1[0],s1[1]);
				graph.insert(e);
				s = br.readLine();
			}
			br.close();
			return graph;

		} catch (IOException e) {
			System.out.println("ERROR: IOException.");
			return null;
		}
	}
}