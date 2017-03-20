/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
public class Graph {
	private int vertexCount, edgeCount;
	boolean directed;
	private boolean adj[][];
	
	public Graph(int numVerticies, boolean isDirected){
		vertexCount = numVerticies;
		directed = isDirected;
		adj = new boolean[numVerticies][numVerticies];
	}
	
	public boolean isDirected(){
		return directed;
	}
	
	public int vertices(){
		return vertexCount;
	}
	
	public int edges(){
		return edgeCount;
	}
	
	public void insert(Edge e){
		if(!connected(e)){				// If there isn't an edge there at least one way
			adj[e.v][e.w] = true;		// Set it to go the first way
			if(!isDirected())			// and if it's not directed
				adj[e.w][e.v] = true;	//	Make the other way true too
			edgeCount++;				// edgeCount incremented once for either kind of edge
		}
	}
	
	public void delete(Edge e){
		if(connected(e)){				// If there is an edge there at least one way
			adj[e.v][e.w] = false;		// Set it to not go the first way
			if(!isDirected())			// and if it's not directed
				adj[e.w][e.v] = false;	//Make the other way false too
			edgeCount--;				// edgeCount decremented once for either kind of edge
		}
	}
	
	public boolean connected(int node1, int node2){
		return adj[node1][node2];
	}
	
	public boolean connected(Edge e){
		return adj[e.v][e.w];
	}
	
	public AdjList getAdjList(int vertex){
		return new AdjArray(vertex);
	}
	
	public void show(){
		for(int s = 0; s < vertices(); s++){
			System.out.print(s + ": ");
			AdjList A = getAdjList(s);
			for(int t = A.begin(); !A.end(); t = A.next())
				System.out.print(t + " ");
			System.out.println();
		}
	}
	
	private class AdjArray implements AdjList{

		private int v;	// interested
		private int i;	//where we are
		
		public AdjArray(int v){
			this.v = v;
			i = -1;
		}
		
		public int begin() {
			i = -1;
			return next();
		}

		public int next() {
			for(++i; i < vertices(); i++){
				if(connected(v, i)) return i;
			}
			return -1;
		}

		public boolean end() {
			if(i < vertices()) return false;
			return true;
		}
	}
}
