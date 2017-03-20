/*
 * Borja Rojo
 * Street Mapping
 * Project 4
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graph {
	private static Vertex vertex[];
	private static Edge edge[];
	private int numVerticies;
	
	
	public Graph(int numVerticies, boolean isDirected){
		vertex = new Vertex[60013];
		edge = new Edge[60013];
	}
	
	public static int hash(String s){
		int code = s.hashCode();
		if(code < 0){
			code *= -1;
		}
		return code % 60013;
	}
	
	public static Graph monroeFile(String filename) throws FileNotFoundException {
		
		int itemCount = 30000;
		
		Scanner input = new Scanner(new File(filename));
		String[] line;
		Graph monroe = new Graph(itemCount, false);
		
		while(input.hasNext()){
			line = input.nextLine().split("\t");
			switch(line[0].charAt(0)){
				case 'i':
					Vertex v = new Vertex(line[1], Double.valueOf(line[2]), Double.valueOf(line [3]));
					monroe.insertVertex(v);
					break;
				case 'r':
					Edge e = new Edge(line[1], line[2], line[3]);
					monroe.insertEdge(e);
					break;
			}
		}
		
		input.close();
		
		return monroe;
		
		
	}
	
	/*
	 * HELP FUNCTIONS
	 * SELF EXPLANITORY
	 * 
	 * Insertion and getting is done using the HashTable way
	 */
	public static void insertVertex(Vertex v){
		int start = v.index;
		int at = v.index;
		
		while(vertex[at] != null){
			if(at >= vertex.length){
				at = at % vertex.length;
				at += start;
			}else{
				at += start;
			}
			if(at >= vertex.length)
				at = at % vertex.length;
		}
		
		vertex[at] = v;
		vertex[at].index = at;
		
	}
	
	public static boolean hasVertex(Vertex v){
		int start = v.index;
		int at = v.index;
		
		while(vertex[at] != null){
			if(at >= vertex.length){
				at = at % vertex.length;
				if(vertex[at].id.compareTo(v.id) == 0){
					return true;
				}
				at += start;
			}else{
				if(vertex[at].id.compareTo(v.id) == 0){
					return true;
				}
				at += start;
			}
			if(at >= vertex.length)
				at = at % vertex.length;
		}
		
		return false;
	}
	
	public static boolean hasVertex(String s){
		int start = hash(s);
		int at = hash(s);
		
		while(vertex[at] != null){
			if(at >= vertex.length){
				at = at % vertex.length;
				if(vertex[at].id.compareTo(s) == 0){
					return true;
				}
				at += start;
			}else{
				if(vertex[at].id.compareTo(s) == 0){
					return true;
				}
				at += start;
			}
			if(at >= vertex.length)
				at = at % vertex.length;
		}
		
		return false;
	}
	
	public static Vertex getVertex(String s){
		int start = hash(s);
		int at = hash(s);
		
		while(vertex[at] != null){
			if(at >= vertex.length){
				at = at % vertex.length;
				if(vertex[at].id.compareTo(s) == 0){
					return vertex[at];
				}
				at += start;
			}else{
				if(vertex[at].id.compareTo(s) == 0){
					return vertex[at];
				}
				at += start;
			}
			if(at >= vertex.length)
				at = at % vertex.length;
		}
		
		return null;
	}
	
	public static void insertEdge(Edge e){
		int start = e.num;
		int at = e.num;
		
		while(edge[at] != null){
			if(at >= edge.length){
				at = (at + start) % edge.length;
			}else{
				at = at + start;
			}
			if(at >= vertex.length)
				at = at % vertex.length;
		}
		
		
		edge[at] = e;
		edge[at].num = at;
	}
	
	public static boolean hasEdge(Edge e){
		int start = e.num;
		int at = e.num;
		
		while(edge[at] != null){
			if(at >= edge.length){
				at = at % edge.length;
				if(edge[at].id.compareTo(e.id) == 0){
					return true;
				}
				at += start;
			}else{
				if(edge[at].id.compareTo(e.id) == 0){
					return true;
				}
				at += start;
			}
			if(at >= vertex.length)
				at = at % vertex.length;
		}
		
		return false;
	}

	
	public Vertex[] dijkstra(String start, String end){
		return dijkstra(getVertex(start), getVertex(end));
	}
	
	public Vertex[] dijkstra(Vertex start, Vertex end){
		/*
		 * This follows the implementation of from the lab
		 * It mirrors it pretty well
		 * 
		 * I had to use specific new way of accessing stuff because of 
		 * my data structure limitations. Due to the high memory costs,
		 * I had to get rid of the adacency arrays for path and weight.
		 * All I use is Edge and Vertex arrays, defined in other files
		 */
		
		/*
		 * Every vertex is set to inf and false at initialization (Check constructor of Vertex)
		 * Copy a fresh path set
		 * Set the start distance to 0
		 */
		Vertex[] path = this.vertex;
		path[start.index].dist = 0;
		
		/*
		 * While there is an unknown distance vertex
		 */
		while(hasUnknownDistVertex(path) && (path[end.index].known == false)){
			
			Vertex v = smallestUnknownDistVertex(path);	//Vertex v = Smallest unknown distance vertex
			path[v.index].known = true;					//v.known = true;
			
			for(Edge w: edgesFrom(path[v.index])){		//For each Vertex w adjacent to v
				//Validate existence
				if(w != null){
					//Extract w
					Vertex other = (w.v.compareTo(v.id) == 0 ? getVertex(w.w) : getVertex(w.v));
					if(!path[other.index].known){		//if(!w.known)
						double cvw = w.weight;			//Cost of edge from v to w
						if((path[v.index].dist + cvw < path[other.index].dist)	//if the new distance is better
								|| (path[other.index].dist < 0)){
							//update
							path[other.index].dist = path[v.index].dist + cvw;
							path[other.index].p = path[v.index];
						}
					}
				}
			}
		}
		
		return path;
	}
	
	/*
	 * Failed Prim's
	 * 
	 * I don't understand why they wont work. I tried implementing my
	 * own Prim's and also one based on the book recomendations,
	 * but the runtime was so long I never was able to see it finish
	 */
	
	public Vertex[] prim(String s){
		return prim(getVertex(s));
	}
	
//	public Vertex[] prim(Vertex start){
//		Vertex[] path = this.vertex;
//		
//		path[start.index].dist = 0;
//		
//		int i = 0;
//		while(hasUnknownDistVertex(path)){
//			System.out.println(i++);
//
//			Vertex v = smallestUnknownDistVertex(path);
//			path[v.index].known = true;
//			
//			for(Edge w: edgesFrom(path[v.index])){
//				if(w != null){
//					Vertex other = (w.v.compareTo(v.id) == 0 ? getVertex(w.w) : getVertex(w.v));
//					if(!path[other.index].known){
//						double cvw = w.weight;
//						if((cvw < path[other.index].dist) 
//								|| (path[other.index].dist < 0)){
//							path[other.index].dist = Math.min(path[v.index].dist, cvw);
//							path[other.index].p = path[v.index];
//						}
//					}
//				}
//			}
//		}
//		
//		return path;
//	}
	
	public Vertex[] prim(Vertex start){
		Vertex[] path = this.vertex;
		path[start.index].known = true;
		path[start.index].dist = 0;
		
		int i = 0;
		//While there are more points to reach, and
		while(hasUnknownDistVertex(path)){
			Edge small = firstNotNullEdge(edge);
			small.weight = Double.MAX_VALUE;
			Vertex beg = getVertex(small.v);
			Vertex end = getVertex(small.w);
			//If only one of the intersections is known in the graph, then
			
				//For every edge,
				for(Edge e : edge){
					if((e != null)){
						Vertex v = getVertex(e.v);
						Vertex w = getVertex(e.w);
						//If the edge exists and is shorter than the current, replace and reset
						if((e.weight < small.weight)
								&& (path[v.index].known || path[w.index].known) 
								&& !(path[v.index].known && path[w.index].known)){
							System.out.println(i++);
							small = e;
							beg = getVertex(small.v);
							end = getVertex(small.w);
						}
					}
				}
			
				//For which ever intersection is known
				if(path[beg.index].known){
					
					path[end.index].known = true;
					path[end.index].p = beg;
					path[end.index].dist = small.weight;
					
				}else if(path[end.index].known){
					
					path[beg.index].known = true;
					path[beg.index].p = end;
					path[beg.index].dist = small.weight;
					
				}
			}
		
		return path;
	}
	
	/*
	 * MORE HELPER METHODS
	 */
	
	//Helper method
	//Returns true is there is Vertex.known == false
	public boolean hasUnknownDistVertex(Vertex[] path){
		//run through every vertex in the path given
		for(Vertex v : path){
			if((v != null)){
		//if any have a known value that is false, return true to indicate its presence
				if((!v.known)){
					return true;
				}
			}
		}
		//Otherwise, if none is found, return false to indicate its absence
		return false;
	}
	
	public static Vertex firstNotNullVertex(Vertex[] path){
		for(Vertex p : path){
			if(p != null) return p;
		}
		
		return null;
	}
	
	public static Edge firstNotNullEdge(Edge[] edges){
		for(Edge e : edges){
			if(e != null) return e;
		}
		
		return null;
	}
	
	public static Vertex smallestUnknownDistVertex(Vertex[] path){
		Vertex s = firstNotNullVertex(path);
		for(Vertex v : path){
			/*
			 * Conditions to be met:
			 * 1. known == false
			 * 2. smallest distance that is greater than -1, as -1 is infinity
			 */
			if((v != null) 
					&& (!v.known)
					&& (v.dist >= 0)
					&& ((v.dist < s.dist) || ((s.dist <= 0)))
					){
				s = v;
			}
		}
		return s;
	}
	
	public static Edge[] edgesFrom(Vertex v){
		Edge adj[] = new Edge[100];
		
		int i = 0;
		for(Edge e : edge){
			if((e != null) && (v.id.compareTo(e.v) == 0 || v.id.compareTo(e.w) == 0)){
				adj[i++] = e;
			}
		}
		
		return adj;
	}
	
	public void printShortestPath(String s, String e){
		Vertex[] path = dijkstra(s, e);
		Vertex v = getVertex(e);
		v.p = path[v.index].p;
		
		System.out.println("Dijkstra's path from vertex " + s + " to " + e + ": ");
		
		System.out.print("Intersections: ");
		recursivePrintInt(v, path);
		System.out.println();
		
		System.out.print("Roads: ");
		recursivePrintRoad(v, path);
		System.out.println();
		
	}
	
	private void recursivePrintRoad(Vertex v, Vertex[] path) {
		// TODO Auto-generated method stub
		if(v.p == null){
			
		}else{
			Edge[] edges = edgesFrom(v);
			for(Edge e : edges){
				if(e != null && (e.v.compareTo(v.p.id) == 0 || e.w.compareTo(v.p.id) == 0)){
					recursivePrintRoad(path[v.p.index], path);
					System.out.print(", " + e.id);
				}
			}
			
		}
	}
	
	private void recursivePrintInt(Vertex v, Vertex[] path) {
		// TODO Auto-generated method stub
		if(v.p == null){
			System.out.print(v.id);
		}else{
			recursivePrintInt(path[v.p.index], path);
			System.out.print(", " + v.id);
		}
	}

}
