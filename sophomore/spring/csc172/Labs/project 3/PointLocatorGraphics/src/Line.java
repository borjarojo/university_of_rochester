/*
Borja Rojo
Project3
4/11/15

This class creates a line out of 4 given double or String values
*/

public class Line {
	Point p1;
	Point p2;

	//constructor that takes in four numbers and converts them to two points
	public Line(double a, double b, double c, double d)
	{
		p1 = new Point(a, b);
		p2 = new Point(c, d);
	}

	//another constructor that takes in strings and converts into doubles
	public Line(String a, String b, String c, String d)
	{
		p1 = new Point(Double.parseDouble(a), Double.parseDouble(b));
		p2 = new Point(Double.parseDouble(c), Double.parseDouble(d));
	}

	//useful for testing
	public String toString()
	{
		return ("(" + p1.x + "," + p1.y +") and (" + p2.x + "," + p2.y+")");
	}
}