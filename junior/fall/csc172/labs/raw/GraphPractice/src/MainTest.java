/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
public class MainTest {
	public static void main(String[]args){
		System.out.println("------------\n"
				+ "---Graphs---\n"
				+ "------------\n");
		
		//Building graph 9.10
		System.out.println("Constructing graph 9.10 from Weiss:");
		System.out.println("Instantiating graph...");
		Graph directed = new Graph(7, true);
		// vertices are indexed at 0
		// Inserting edges from book to this graph
		System.out.println("Inserting edges...");
		directed.insert(new Edge(0, 1));	//v1 to v2
		directed.insert(new Edge(0, 3));	//v1 t0 v4
		directed.insert(new Edge(1, 3));	//v2 to v4
		directed.insert(new Edge(1, 4));	//v2 to v5
		directed.insert(new Edge(2, 0));	//v3 to v0
		directed.insert(new Edge(2, 5));	//v3 to v6
		directed.insert(new Edge(3, 2));	//v4 to v3
		directed.insert(new Edge(3, 4));	//v4 to v5
		directed.insert(new Edge(3, 5));	//v4 to v6
		directed.insert(new Edge(3, 6));	//v4 to v7
		directed.insert(new Edge(4, 6));	//v5 to v7
		directed.insert(new Edge(6, 5));	//v7 to v6
		System.out.println();
		System.out.println("---Weiss Graph figure 9.10---");
		directed.show();
		
		//Building graph 9.62
		System.out.println();
		System.out.println("Constructing graph 9.62 from Weiss: ");
		System.out.println("Instantiating graph...");
		Graph undirected = new Graph(5, false);
		// Vertices are indexed at 0
		// Inserting edges from book to this graph
		System.out.println("Inserting Edges...");
		
		/* The safest way to do this is to make inserts
		 * on the graph for every edge connecting to every
		 * node and rely on the repitition catcher to make
		 * sure there aren't any issues. I didn't do this
		 * because there are so few edges, so I didn't
		 * think I'd miss one.
		 */
		undirected.insert(new Edge(0, 1));	// A to B
		undirected.insert(new Edge(0, 3));	// A to D
		undirected.insert(new Edge(0, 4));	// A to E
		undirected.insert(new Edge(1, 3));	// B to D
		undirected.insert(new Edge(1, 2));	// B to C
		undirected.insert(new Edge(2, 3));	// C to D
		undirected.insert(new Edge(2, 4));	// C to E
		System.out.println("---Weiss Graph figure 9.62---");
		undirected.show();
		
	}
}
