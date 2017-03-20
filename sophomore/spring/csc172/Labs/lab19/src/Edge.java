/*
 Borja Rojo
 4/19/15
 Lab Partner: Daniel Saltz
 
 */

public class Edge {
         
	public final int from, to; // an edge from v to w
	
    public Edge(int from, int to)
    {
    	this.from = from;
    	this.to = to;
    }
    
    // Another constructor to take in Strings and convert to ints
    public Edge(String from, String to)
    {
    	this.from = Integer.parseInt(from);
    	this.to = Integer.parseInt(to);
    }
}