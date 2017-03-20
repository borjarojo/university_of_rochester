/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 */

/*
 * Edge class
 * Holds the name of the edge
 * Holds start and finish
 * and index
 */
public class Edge {
	public String name;
	public Node v, w;
	public int index;

	public Edge(String n, Node i, Node j){
		name = n;
		v = i;
		w = j;
	}
	
	public Edge(String n, Node i, Node j, int ind){
		name = n;
		v = i;
		w = j;
		index = ind;
	}
	
	public String name(){
		return name;
	}
	
	public Node start(){
		return v;
	}
	
	public Node end(){
		return w;
	}
}
