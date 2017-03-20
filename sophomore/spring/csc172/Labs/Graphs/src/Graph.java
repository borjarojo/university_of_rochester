
public class Graph {
	private int vertexCount, edgeCount;
	boolean directed;
	private boolean adj[][];
	
	public Graph(int numVerticies, boolean isDirected){
		directed = isDirected;
		vertexCount = numVerticies;
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
		if(isDirected()){
			adj[e.v][e.w] = true;
		}
		else{
			adj[e.v][e.w] = true;
			adj[e.w][e.v] = true;
		}
		edgeCount++;
	}
	
	public void delete(Edge e){
		if(isDirected()){
			adj[e.v][e.w] = false;
		}
		else{
			adj[e.v][e.w] = false;
			adj[e.w][e.v] = false;
		}
		edgeCount--;
	}
	
	public boolean connected(int node1, int node2){
		return adj[node1][node2];
	}
	
	public AdjList getAdjList(int vertex){
		return new AdjArray(vertex);
	}
	
	public void show(){
		for(int s = 0; s < vertices(); s++){
			System.out.print(s + ": ");
			AdjList A = getAdjList(s);
			for(int t = A.begin(); !A.end(); t = A.next()){
				System.out.print(t + " ");
			}
			System.out.println();
		}
	}
	
	private class AdjArray implements AdjList{
		private int v;
		private int i;
		
		public AdjArray(int v1){
			v = v1;
			i = -1;
		}
		
		public int next(){
			for(++i; i < vertices(); i++){
				if(connected(v, i) == true) return i;
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
}
