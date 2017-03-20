/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
public class Edge {
	public String id;
	public Vertex v, w;
	public int weight;
	
	public Edge(String id, Vertex v, Vertex w){
		this.v = v;
		this.w = w;
	}
}
