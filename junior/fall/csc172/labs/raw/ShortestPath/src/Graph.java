/*
 * Borja Rojo
 * CSC 172
 * Partner: Daniel Saltz
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graph {
	private int vertexCount, edgeCount;
	boolean directed;
	private boolean adj[][];
	
	public Graph(int numVerticies, boolean isDirected){
		vertexCount = numVerticies;
		directed = isDirected;
		adj = new boolean[numVerticies][numVerticies];
	}
	
	public static Graph createFromFile(String filename) throws FileNotFoundException{
		Scanner in = new Scanner(new File(filename));
		
		int v = Integer.valueOf(in.nextLine());		//first line
		boolean d;
		if(in.nextLine().compareTo("D") == 0){		//Second line
			d = true;
		}else{
			d = false;
		}
		
		Graph g = new Graph(v, d);				//Create the graph
		String s;
		while(in.hasNext()){					//rest of the lines
			s = in.next();
			g.insert(new Edge(Integer.valueOf(s.substring(0, 1)),
					Integer.valueOf(s.substring(2, 3))));
		}
		in.close();
		return g;
		
		
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
	
	public Vertex[] unweighted(int i){
		return unweighted(new Vertex(i, false, -1));
	}
	
	
	// A method that returns an array of Vertex in with path information
	// on the vertex passed
	public Vertex[] unweighted(Vertex s){
		//Vertex array to have the path information
		Vertex[] path = new Vertex[vertices()];
		
		//Instantiate a 
		for (int j = 0; j < path.length; j++) {
			path[j] = new Vertex(j, false, -1);	//Unweighted, assumes no negatives
		}
		
		path[s.num].dist = 0;
		
		//One heuristic present is that a node with be visited in order of distance anyways
		//so checking for only infinite distance, ie it hasn't been visited before, is enough
		
		for(int currDist = 0; currDist < vertices(); currDist++){	//For each distance, starting at no distance and ending at the vertex count
			for(Vertex v : path){										//for every vertex
				if(!v.known && v.dist == currDist){						//where a path is not known but has a distance equal to the one being looked at
					v.known = true;											//Make that path known, because now it is.
					for(Vertex w : path){									//And in comparison to every other vertex,
						if(connected(v.num, w.num) && w.dist == -1){		//if there is a path from the first to the second and the 
																			//distance is considered infinite
							w.dist = currDist + 1;							//Make the distance this one +1
							w.p = v;										//and set the path to be FROM the first TO the second
						}
					}
				}
			}
		}
		
		return path;
	}
	
	public void printShortestPath(int s, int e){
		Vertex[] path = unweighted(s);
		Vertex v = new Vertex(path[e].num, path[e].known, path[e].dist);
		v.p = path[e].p;
		
		System.out.print("Shortest path from vertex " + s + " to " + e + ": ");
		recursivePrint(v);
		System.out.println();
		
	}
	
	private void recursivePrint(Vertex v) {
		// TODO Auto-generated method stub
		if(v.p == null){
			System.out.print(v.num);
		}else{
			recursivePrint(v.p);
			System.out.print(", " + v.num);
		}
	}

	private class AdjArray implements AdjList{

		private int v;	// interested
		private int i;	// where we are
		
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
