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
	private int weight[][];
	
	public Graph(int numVerticies, boolean isDirected){
		vertexCount = numVerticies;
		directed = isDirected;
		adj = new boolean[numVerticies][numVerticies];
		weight = new int[numVerticies][numVerticies];
	}
	
	public static Graph createFromFile(String filename) throws FileNotFoundException{
		/*
		 * <numVertices>
		 * <directionality> (d,u)
		 * <edge with weight> (v,w,weight)
		 * 
		 * Check my Vertex class for variables
		 */
		Scanner in = new Scanner(new File(filename));
		
		int v = Integer.valueOf(in.nextLine());		//first line
		boolean d;
		if(in.nextLine().compareTo("D") == 0){		//Second line
			d = true;
		}else{
			d = false;
		}
		
		Graph g = new Graph(v, d);				//Create the graph
		String[] t;
		while(in.hasNext()){					//rest of the lines
			t = in.next().split(",");
			g.insert(new Edge(Integer.valueOf(t[0]),
					Integer.valueOf(t[1]),
					Integer.valueOf(t[2])));
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
			weight[e.v][e.w] = e.weight;
			if(!isDirected())			// and if it's not directed
				adj[e.w][e.v] = true;	//	Make the other way true too
				weight[e.w][e.v] = e.weight;
			edgeCount++;				// edgeCount incremented once for either kind of edge
		}
	}
	
	public void delete(Edge e){
		if(connected(e)){				// If there is an edge there at least one way
			adj[e.v][e.w] = false;		// Set it to not go the first way
			weight[e.v][e.w] = 0;
			if(!isDirected())			// and if it's not directed
				adj[e.w][e.v] = false;	//Make the other way false too
				weight[e.w][e.v] = 0;
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
	
	public Vertex[] dijkstra(int s){
		/*
		 * Create a Vertex array to hold the pathing info
		 * and set the path as a blank slate. Then, set
		 * the distance of the starting node to 0 
		 */
		Vertex[] path = new Vertex[vertices()];
		for(int i = 0; i < vertices(); i++){
			path[i] = new Vertex(i, false, -1);
		}
		
		path[s].dist = 0;
		//Setup complete
		
		/*
		 * This is the rest of the implemenation using the
		 * structure and implementation of the graph that
		 * I've created.
		 * 
		 * Using the sudo-code given in the lab, I've
		 * incorporated the nessicary conditions asked.
		 * I will comment more on portions that may not 
		 * be as clear.
		 */
		
		//hasUnknownDisVertex is defined below as a helper function
		while(hasUnknownDistVertex(path)){
			//smallest UknownDistVertex is defined below
			/*
			 * I am using only the int given so that I work
			 * with the master path I have set up. I do the
			 * same for the starting vertex, as you can see.
			 * This is nice, as I can locate the corresponding
			 * vertex with the index in my path.
			 */
			int v = smallestUnknownDistVertex(path).num;	//Store which vertex has the smallest unknown distance
			path[v].known = true;							//now it is known to have that as it's smallest path, so set it to true
			
			//For each vertex w adjacent to v
			/*
			 * Here, I use the adjacency matrix and iterate through.
			 * When I find a path marked connected to v, the code
			 * proceeds into the if-statement.
			 * 
			 * w is an iterator used to mark the index of w
			 */
			int w = 0;
			for(boolean j : adj[v]){
				if(j){										//if it's connected
					int cvw = weight[v][w];					//Cost of path given by the weight[][] adjacency matrix
					//System.out.println(cvw);
					if(((path[v].dist + cvw) < path[w].dist) || (path[w].dist < 0)){//v.dist + cvw < w.dist
						path[w].dist = path[v].dist + cvw;	//decrease w.dist to v.dist + cvw
						path[w].p = path[v];
					}
				}
				w++;										//increments with j, meant to match what index j would be accessing
			}
			
//			for(Vertex v1 : path){
//				System.out.println(v1.num + " " + v1.known + " " + v1.dist);
//			}
//			System.out.println();
		}
		
		
		
		return path;
	}
	
	//Helper method
	//Returns true is there is Vertex.known == false
	public boolean hasUnknownDistVertex(Vertex[] path){
		//run through every vertex in the path given
		for(Vertex v : path){
		//if any have a known value that is false, return true to indicate its presence
			if(!v.known){
				return true;
			}
		}
		//Otherwise, if none is found, return false to indicate its absence
		return false;
	}
	
	public static Vertex smallestUnknownDistVertex(Vertex[] path){
		Vertex s = path[0];
		for(Vertex v : path){
			/*
			 * Conditions to be met:
			 * 1. known == false
			 * 2. smallest distance that is greater than -1, as -1 is infinity
			 */
			if((!v.known) &&
					(v.dist < s.dist || ((s.dist <= 0) && (v.dist > -1)))){
				s = v;
			}
		}
//		System.out.println(s.num);
		return s;
	}
	
	public void printShortestPath(int s, int e){
	//	Vertex[] path = unweighted(s);
		Vertex[] path = dijkstra(s);
		Vertex v = new Vertex(path[e].num, path[e].known, path[e].dist);
		v.p = path[e].p;
		
		System.out.print("Dijkstra's path from vertex " + s + " to " + e + ": ");
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
