/*
 *  Borja Rojo
 *  lab18
 *  4/12/15
 *  
 *  This is my main class
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MainTest {
	public static void main(String[] args) throws FileNotFoundException{

		Scanner scan = new Scanner(System.in);
		
		// get file 9.10.txt
		System.out.println("Enter the file path for 9.10.txt");
		File f1 = new File(scan.next());
		Graph g1 = ReadGraph(f1);
		g1.show(g1);
		g1.unweighted(3); //unweighted path
		
		// get file 9.62.txt
		System.out.println("Enter the file path for 9.62.txt");
		File f2 = new File(scan.next());
		Graph g2 = ReadGraph(f2);
		g2.show(g2);
		g2.unweighted(3);
		
		// get file graph1.txt
		System.out.println("Enter the file path for graph1.txt");
		File f3 = new File(scan.next());
		Graph g3 = ReadGraph(f3);
		g3.show(g3);
		g3.unweighted(3);
		
		// get file graph2.txt
		System.out.println("Enter the file path for graph2.txt");
		File f4 = new File(scan.next());
		Graph g4 = ReadGraph(f4);
		g4.show(g4);
		g4.unweighted(3);
	}
	
	//reads the files and creates a graph from them
	public static Graph ReadGraph(File f) throws FileNotFoundException{
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		Graph g;
		int vertices;
		char c;
		boolean b;

		try{
			vertices = Integer.parseInt(br.readLine());
			String j = br.readLine();
			c = j.charAt(0);
			if (c == 'U')
				b = false;
			else
				b = true;
			String ln = br.readLine();
			g = new Graph(vertices, b);
			while (ln != null)
			{
				String[] str = ln.split(" ");
				for (int i = 1; i < str.length; i++)
				{
					Edge e = new Edge(Integer.parseInt(str[i - 1]), Integer.parseInt(str[i]));
					g.insert(e);
				}
				ln = br.readLine();
			}
			br.close();
			return g;
		} catch(Exception e){
			System.out.println("ERROR: File not found.");
		}
		return null;
	}
}