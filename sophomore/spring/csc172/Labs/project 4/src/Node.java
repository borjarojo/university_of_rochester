/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 */

/*
 * Node class.
 * Holds a name for each node
 * Holds latitude and longitude
 * Holds a reference index
 */
public class Node {
	private String name;
	private float la, lo;
	private int index;

	public Node(String s, float lati, float longi, int i){
		name = s;
		la = lati;
		lo = longi;
		index = i;
	}
	
	public String name(){
		return name;
	}
	
	public float lati(){
		return la;
	}
	
	public float longi(){
		return lo;
	}
	
	public int index(){
		return index;
	}
}
