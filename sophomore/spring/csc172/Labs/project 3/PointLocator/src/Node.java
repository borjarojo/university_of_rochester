 /*
Borja Rojo
Project3
4/11/15

Node class for the BST class that contains a left child and right child
 
 */

public class Node {
	public Line line;
	public Node leftchild;
	public Node rightchild;

	//constructor that creates a new node with a line
	public Node(Line node)
	{
		line = node;
	}

	//recursively calls the insert method on the left and/or right child if they are not empty
	public static void insert(Node m, Line a)
	{
		// determines the location of each of the line's points
		int p1 = ccw(m.line.p1, m.line.p2, a.p1); 
		int p2 = ccw(m.line.p1, m.line.p2, a.p2);

		if ((p1 == 1) && (p2 == 1)) // if both are counter-clockwise, insert to the left
		{
			if (m.leftchild == null)
				m.leftchild = new Node(a);
			else
				insert(m.leftchild, a);
		}
		else if ((p1 == -1) && (p2 == -1)) // if both are clockwise, insert to the right
		{
			//both are clockwise
			if (m.rightchild == null)
				m.rightchild = new Node(a);
			else
				insert(m.rightchild, a);
		}
		else //one is clockwise and the other is counter-clockwise
		{
			if (p1 == 1) // if first point is counter-clockwise, insert that point to the left
			{
				if (m.leftchild == null)
					m.leftchild = new Node(a);
				else
					insert(m.leftchild, a);
			}
			else // if first point is clockwise, insert that point to the right
			{
				if (m.rightchild == null)
					m.rightchild = new Node(a);
				else
					insert(m.rightchild, a);
			}
			if (p2 == 1) // if second point is counter-clockwise, insert that point to the left
			{
				if (m.leftchild == null)
					m.leftchild = new Node(a);
				else
					insert(m.leftchild, a);
			}
			else // if second point is clockwise, insert that point to the right
			{
				if (m.rightchild == null)
					m.rightchild = new Node(a);
				else
					insert(m.rightchild, a);
			}
		}
	}

	// returns the direction of a new point relative to a line
	// counter-clockwise returns -1, clockwise returns 1 and collinear returns 0
	public static int ccw(Point a1, Point a2, Point b)
	{
		double direction = (a2.x - a1.x)*(b.y - a1.y) - (a2.y - a1.y)*(b.x - a1.x);
		if (direction < 0)
			return -1; //clockwise
		else if (direction > 0)
			return 1; // counter-clockwise
		return 0; //collinear
	}
}
