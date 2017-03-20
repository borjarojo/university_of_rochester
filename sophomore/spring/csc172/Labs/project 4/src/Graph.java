/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 * 
 * This is my graph class. It holds methods to insert edges into the graph, which is the main way of constructing it.
 * It also holds methods to hold the MWST and find Dijkstra's for a beginning and end node.
 * With this, you can continuously get Dijkstra's paths for two nodes, as long as the method is run continuously.
 */
public class Graph {
	
	private static String name;		//Name of graph
	private static int vertexCount;	//Number of vertecies
	private int edgeCount;			//Number of edges
	boolean directed;				//Directed or Undirected
	private static boolean adj[][];	//Adjacency Array
	public static Node[] n;			//Node Array
	public static Edge[] e;			//Edge Array
	
	public static Node[] dij;		//Array holding dijkstra's ordering
	public static double dist;		//Double holding minimum distance
	
	public static Graph mwst;		//Graph that holds the minimum weight spanning tree
	
	//Constructor
	public Graph(String na, int numVerticies, boolean isDirected){
		name = na;
		directed = isDirected;
		vertexCount = numVerticies;
		edgeCount = 0;
		adj = new boolean[numVerticies][numVerticies];
		n = new Node[numVerticies];
		dij = new Node[numVerticies];
	}
	
	public Graph(String na, boolean isDirected){
		name = na;
		directed = isDirected;
	}
	
	public static String name(){
		return name;
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
	
	public boolean[][] getAdjArray(){
		return adj;
	}
	
	public void insertNode(Node node){
		n[node.index()] = node;
	}
	
	/*
	 * Edge Insertion
	 * 
	 * This method adds marks to the adjacency array
	 * This method adds nodes to the node n array
	 * This method adds edges to the edge e array
	 */
	public void insertEdges(Edge[] ed){
		edgeCount = ed.length;
		e = new Edge[edgeCount];
		for(int i = 0; i < ed.length; i++){
			e[i] = ed[i];
			insertEdge(ed[i]);
		}
		
	}

	public void insertEdge(Edge e){
		n[e.v.index()] = e.v;
		n[e.w.index()] = e.w;
		if(isDirected()){
			adj[e.v.index()][e.w.index()] = true;
		}
		else{
			adj[e.v.index()][e.w.index()] = true;
			adj[e.w.index()][e.v.index()] = true;
		}
		
		edgeCount++;
	}
	
	public void deleteEdge(Edge e){
		if(isDirected()){
			adj[e.v.index()][e.w.index()] = false;
		}
		else{
			adj[e.v.index()][e.w.index()] = false;
			adj[e.w.index()][e.v.index()] = false;
		}
		edgeCount--;
	}
	
	//returns a boolean value to see if two node's are connected
	public boolean connected(Node node1, Node node2){
		return adj[node1.index()][node2.index()];
	}
	
	public AdjList getAdjList(Node vertex){
		return new AdjArray(vertex);
	}
	
	//prints an adjacency list of the adjacency array
	public void show(){
		for(int s = 0; s < vertices(); s++){
			System.out.print(n[s].name() + ": ");
			AdjList A = getAdjList(n[s]);
			for(int t = A.begin(); !A.end(); t = A.next()){
				System.out.print(n[t].name() + " ");
			}
			System.out.println();
		}
	}
	
	//hold the class to implement getAdjList()
	private class AdjArray implements AdjList{
		private int v;
		private int i;
		
		public AdjArray(Node v1){
			v = v1.index();
			i = -1;
		}
		
		public int next(){
			for(++i; i < vertices(); i++){
				if(connected(n[v], n[i]) == true) return i;
			}
			return -1;
		}
		
		public int begin(){
			i = -1;
			return next();
		}
		
		public boolean end(){
			if(i < vertices()) return false;
			return true;
		}
	}

	//Runs dijkstra's algorithm on the graph and sets dij and dist accordingly
	public static void dijkstra(Node start, Node end){
		//set up, all things that need to be kept track of
		boolean[] visited = new boolean[vertexCount];
		double[] distance = new double[vertexCount];
		Node[] path	= new Node[vertexCount];
		Node at = start;
		
		//set all distance to -1 to represent infinity
		for(int i = 0; i < vertexCount; i++){
			distance[i] = -1;
		}
		
		//preliminary, set up the first node
		visited[start.index()] = true;
		distance[start.index()] = 0;
		path[start.index()] = start;
		
		//algorithm
		//as long as the end has not been visited
		while(visited[end.index()] == false){
			visited[at.index()] = true;												//set the node being evaluated to true
			for(int i = 0; i < vertexCount; i++){									//for every edge from the intersection being checked
				if(adj[at.index()][i] == true){										//as long as their is an edge
					if(distance[i] == -1){											//if the distance is infinity
						distance[i] = distance[at.index()] + distance(at, n[i]);	//then set the distance equal to the distance from start plus the distance between both nodes
						path[i] = at;												//and set the parent as the node being evaluated
					}
					else if(distance[i] > distance[at.index()] + distance(at, n[i])){	//otherwise, if the distance at the node connected to the one we are at is longer
						distance[i] = distance[at.index()] + distance(at, n[i]);		//set the new distance
						path[i] = at;													//and reset the parent to correspond with the shorter path
					}
				}
			}
			
			
			//evaluate for the smallest distance calculated that has not been visited
			double min = -1;
			int ind = -1;
			for(int i = 0; i < distance.length; i++){			//for every node
				if(visited[i] == false && distance[i] > 0){		//if it has been visited and has a distance other than infinity or zero
					if(min == -1){								//then if the minimum distance hasn't been set yet
						min = distance[i];						//mark the distance
						ind = i;								//and keep track of what node it is
					}
					else if(min > distance[i]){					//otherwise, if the marked distance is not smaller than the node being checked
						min = distance[i];						//mark the new, smaller distance
						ind = i;								//and keep track of what node it is
					}
				}
			}
			at = n[ind];
			
		}
		
		//organize into dij, with null's filling the top
		at = end;
		int count = path.length - 1;
		while(path[at.index()] != start){
			dij[count--] = at;
			at = path[at.index()];
		}
		dij[count--] = at;
		at = path[at.index()];
		dij[count] = at;
		
		//save the distance
		dist = distance[end.index()];
		
	}
	
	//prints Dijkstra's ordering and the minimum distance for the path run through Dijkstra's
	public static void printDij(){
		System.out.print("Shortest Path: ");
		for(int i = 0; i < vertexCount; i++){
			if(dij[i] != null){
				System.out.print(dij[i].name() + " ");
			}
		}
		System.out.println();
		
		System.out.println("Distance: " + dist);
	}
	
	//Calculates the distance from one node to the other
	public static double distance(Node one, Node two){
		return Math.sqrt(Math.pow((one.lati() - two.lati()), 2) + Math.pow((one.longi() - two.longi()), 2));
	}
	
	//Calculates the distance an Edge has between it's two node
	public static double distance(Edge ed){
		return Math.sqrt(Math.pow((ed.v.lati() - ed.w.lati()), 2) + Math.pow((ed.v.longi() - ed.w.longi()), 2));
	}

	//Returns a minimum weight spanning tree of this graph
	public static Graph mwst(Node start){
		Graph tree = new Graph("MWST of " + name(), vertexCount, false);
		
		Edge small = null;
		int count = 0;
		
		//start
		for(int i = 0; i < vertexCount; i++){
//			System.out.println(start.index() + " : " + adj[start.index()][i]);
			if(adj[start.index()][i] == true){
//				System.out.println("check");
				if(small == null){
					small = findEdge(start, n[i]);
					
				}
				else if(distance(small) > distance(findEdge(start, n[i]))){
					small = findEdge(start, n[i]);
				}
			}
		}
		
		//System.out.println(small.name);
		
		//tree.insertEdge(small);
		
//		//find smallest edge
//		while(count < vertexCount){
//			for(int i = 0; i < vertexCount; i++){
//				if(adj[start.index()][i]){
//					for(int j = 0; j < vertexCount; j++){
//						
//					}
//				}
//			}
//		}
		
		return tree;
	}

	private static Edge findEdge(Node start, Node no) {
		
		for(int i = 0; i < e.length; i++){
			if((start.name().compareTo(e[i].v.name()) == 0 && no.name().compareTo(e[i].w.name()) == 0) ||
					(start.name().compareTo(e[i].w.name()) == 0 && no.name().compareTo(e[i].w.name()) == 0)){
				return e[i];
			}
		}
		return null;
	}
	
	public static void printAdjArray(){
		for(int i = 0; i < vertexCount; i++){
			for(int j = 0; j < vertexCount; j++){
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
