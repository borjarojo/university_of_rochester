/*
 Borja Rojo
 Project3
 4/11/15

	This is my main method class. It controls the flow of the program.
	I collaborated with Daniel Saltz in making this project.

 */

import java.io.*;
import java.util.ArrayList;

public class LocatePoint {
	//arraylist to hold the lines that intersect the point
	private static ArrayList<Line> array = new ArrayList<Line>();

	//counter for the number of initial lines used to divide the sections
	public static int lineCounter;
	private static Line q;
//	private static String g = "";
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		// creates files
		File f = new File("in.txt");

		//reads the file
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		//creates a new BST object
		BST bst = new BST();

		try{
			lineCounter = Integer.parseInt(br.readLine()); //reads the number of lines
			Line[] linesToDraw = new Line[lineCounter]; // array of lines draw lines

			//goes through each line and then splits points by whitespace
			// then inserts the line into the BST, and adds the line to linesToDraw
			for (int i = 0; i < lineCounter; i++)
			{
				String s2 = br.readLine();
				String[] str2 = s2.split("\\s+");

				Line l = new Line(str2[0], str2[1], str2[2], str2[3]);
				bst.insert(l);
				linesToDraw[i] = l;
			}
			br.close();
			
			// calls MainFrame, which draws the GUI
			// sends in the number of lines to draw, and an array of Lines to draw
			Drawings d = new Drawings(linesToDraw);

			getClicks(bst);
			
		} catch (IOException e){
			System.out.println("File ERROR");
		}
	}
	
	public static void getClicks(BST bst) throws IOException
	{
		
		String g = "";
		
		while(true)
		{
			//get that line
			q = MainFrame.getLine();
			intersect(bst.root, q);
			String display = writeString();
			MainFrame.resetDisplay(display);
			array.clear();
			
			
//			if (!g.equals(display))
//			{
//				System.out.println(display);
//				bw.append(display+"\n");
////				bw.newLine();
//			}
//			g = display;
			
//			bw.close();
		}
	} 
	
	public static String writeString()
	{
		String result = "";
		
		//writes our string out depending on # of intersecting lines
		if (array.size() == 0)
			result = "Points " + q.toString() + " are in the same region";
		else if (array.size() == 1)
			result = "<html>Points " + q.toString() + " are separated by line<br>" + array.get(0)+"</html>";
		else
		{
			result = "<html>Points " + q.toString() + " are separated by lines <br>" + array.get(0);
			for (int j = 1; j < array.size(); j++)
				result += "<br>" + array.get(j);
			result += "</html>";
		}
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
			if(!array.contains(n.line))
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