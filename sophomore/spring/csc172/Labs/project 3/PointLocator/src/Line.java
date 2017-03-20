/*
 Borja Rojo
 Project3
 4/11/15
  
	This class creates a line out of 4 given double or String values
*/

public class Line {
	Point p1;
	Point p2;

	//constructor that takes 4 doubles as parameters
	public Line(double a, double b, double c, double d)
	{
		p1 = new Point(a, b);
		p2 = new Point(c, d);
	}

	// constructor that takes 4 strings as parameters
	public Line(String a, String b, String c, String d)
	{
		p1 = new Point(Double.parseDouble(a), Double.parseDouble(b));
		p2 = new Point(Double.parseDouble(c), Double.parseDouble(d));
	}

	 public String toString()
	 {
	 	return ("(" + p1.x + "," + p1.y +"),(" + p2.x + "," + p2.y + ")");
	 }
}