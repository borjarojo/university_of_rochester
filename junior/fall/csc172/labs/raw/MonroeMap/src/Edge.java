/*
 * Borja Rojo
 * Street Mapping
 * Project 4
 */
public class Edge {
	public String id;		// Name of the Edge
	public int num;			// INDEX (not hashCode) of the Edge
	public String v, w;		// Names of the intersections
	public double weight;	// Weight of the edge
	
	public Edge(String id, String v, String w){
		//Initialize
		this.id = id;		
		this.num = Graph.hash(id);
		this.v = v;
		this.w = w;
		//Distance Formula
		weight = Math.pow(Math.pow((Graph.getVertex(v).x - Graph.getVertex(w).x), 2)
				+ Math.pow((Graph.getVertex(v).y - Graph.getVertex(w).y), 2), (1/2));
	}
}
