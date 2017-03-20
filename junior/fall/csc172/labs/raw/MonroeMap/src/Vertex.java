/*
 * Borja Rojo
 * Street Mapping
 * Project 4
 */
public class Vertex {
	String id;		// Name of intersection
	double x, y;	// Coordinate of intersection
	int index;		// INDEX (not hashCode) of intersection
	
	//Variables reserved for dijkstra's
	boolean known;	
	double dist;
	Vertex p;
	
	public Vertex(String id, double x, double y){
		// Initialize
		this.id = id;
		this.index = Graph.hash(id);
		this.x = x;
		this.y = y;
		
		// Set for dijkstra's
		this.known = false;
		this.dist = -1;
		this.p = null;
	}
}
