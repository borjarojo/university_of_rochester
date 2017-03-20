/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
public class Vertex {
	boolean known;
	int num, dist;
	Vertex p;
	
	public Vertex(int n, boolean k, int d){
		num = n;
		known = k;
		dist = d;
		p = null;
	}
}
