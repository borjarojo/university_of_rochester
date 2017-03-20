/*
 Borja Rojo
 Project3
 4/11/15

	This is my main method class. It controls the flow of the program.
	I collaborated with Daniel Saltz in making this project.

 */


import java.io.*;
import java.util.ArrayList;

public class PointLocator {
	//arraylist to hold the lines that intersect the point
	private static ArrayList<Line> array = new ArrayList<Line>();

	//counter for the number of initial lines used to divide the sections
	public static int lineCounter;

	public static void main(String[] args) throws FileNotFoundException, IOException{

		BST bst = new BST(); //creates a new BST object

		// creates files
		File f = new File("in.txt");
		File f2 = new File("outfile.txt");

		//reads the file
		BufferedReader br = new BufferedReader(new FileReader(f));

		// writes the output file
		BufferedWriter bw = new BufferedWriter(new FileWriter(f2));

		try{
			//reads the number of lines
			lineCounter = Integer.parseInt(br.readLine());

			//goes through each line and then splits points by whitespace
			// then inserts the line into the BST
			for (int i = 0; i < lineCounter; i++)
			{
				String s2 = br.readLine();
				String[] str2 = s2.split("\\s+");

				Line l = new Line(str2[0], str2[1], str2[2], str2[3]);
				bst.insert(l);
			}
			br.readLine(); // reads empty line	
			
			//goes through each line and then splits it by the spaces and then inserts it into an arraylist to be used for output
			while (true)
			{
				String s = br.readLine();
				if (s == null)
					break;
				
				// prints the points
				String[] s2 = s.split("\\s+");
				for (String j : s2)
					System.out.print(j + " ");
				System.out.println();

				//creates a line to find any lines intersecting between the points
				Line l = new Line(s2[0], s2[1], s2[2], s2[3]);

				//checks if any line intersect between the points and adds to the arraylist if so
				intersect(bst.root, l);

				// determines what string to output
				String str = writeString(l);

				bw.write(str);
				array.clear();
				bw.newLine();
			}
			
			System.out.println("\nNumber of external nodes: "+bst.leafCount(bst.root));
			System.out.println("Average height: "+bst.averageHeight()+" nodes");
			
			br.close();
			bw.close();
			
		} catch (IOException e){
			System.out.println("File ERROR");
		}
	}
	
	public static String writeString(Line l)
	{
		String result = "";
		
		//writes our string out depending on # of intersecting lines
		if (array.size() == 0)
			result = "Points " + l.toString() + " are in the same region";
		else if (array.size() == 1)
			result = "Points " + l.toString() + " are separated by line " + array.get(0);
		else
		{
			result = "Points " + l.toString() + " are separated by lines " + array.get(0);
			for (int j = 1; j < array.size(); j++)
				result += " & " + array.get(j);
		}
		System.out.println("RESULT: "+result);
		return result;
	}

	//method that checks if there are any lines intersecting the two points(that are in the line parameter)
	public static void intersect(Node first, Line l)
	{
		Node n = first;
		int s = Node.ccw(first.line.p1, first.line.p2, l.p1);
		int t = Node.ccw(first.line.p1, first.line.p2, l.p2);
		
		if (s != t)
		{
			//checks if the arraylist already contains the line so there will be no repeats
			if (!array.contains(n.line))
				array.add(n.line);
		}

		//recursive call to the left and right child if able
		if (first.leftchild != null)
		{
			n = first.leftchild;
			intersect(n, l);
		}
		if (first.rightchild != null)
		{
			n = first.rightchild;
			intersect(n, l);
		}
	}
}