public class Edge {
         
	public final int from, to, weight; // an edge from v to w
	
    public Edge(int from, int to, int weight)
    {
    	this.from = from;
    	this.to = to;
    	this.weight = weight;
    }
    
    // Another constructor to take in Strings and convert to ints
    public Edge(String from, String to, String weight)
    {
    	this.from = Integer.parseInt(from);
    	this.to = Integer.parseInt(to);
    	this.weight = Integer.parseInt(weight);
    }
}