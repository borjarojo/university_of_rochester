/*
 * Borja Rojo
 * lab18
 * 4/12/15
 * 
 * This is my graphs class
 */

import java.awt.List;

public class Graph {
	//vertices and edges counters
	private int Vcnt, Ecnt;
	boolean digraph;
	private boolean adj[][];
	//array of vertex's to keep track of all the vertex's
	public Vertex[] v;

	//constructor
	public Graph(int numVerticies, boolean isDirgraph)
	{
		Vcnt = numVerticies;
		digraph = isDirgraph;
		adj = new boolean[Vcnt][Vcnt];
		v = new Vertex[Vcnt];
		for(int i = 0; i < v.length; i++)
		{
			v[i] = new Vertex();
			v[i].name = "" + i;
		}
	}

	public boolean directed()
	{
		for(int i = 1; i < Vcnt; i++)
		{
			if (adj[i][i - 1] && adj[i - 1][i])
				return false;
		}
		return true;
	}

	//number of vertices
	public int V()
	{
		return Vcnt;
	}

	//number of edges
	public int E()
	{
		return Ecnt;
	}

	//inserts the edges
	public void insert(Edge e)
	{
		Ecnt++;
		if(edge(e.v, e.w)){
			
		}else if(digraph){
			adj[e.v][e.w] = true;
			adj[e.w][e.v] = false;
		} else{
			adj[e.v][e.w] = true;
			adj[e.w][e.v] = true;
		}
	}

	//deletes the edges
	public void delete(Edge e)
	{
		Ecnt--;
		if(!edge(e.v, e.w)){
			
		} else if(digraph == true){
			adj[e.v][e.w] = false;
			adj[e.w][e.v] = false;
		} else if(digraph == false){
			adj[e.v][e.w] = false;
			adj[e.w][e.v] = false;
		}
	}

	//checks if two vertices are an edge
	public boolean edge(int node1, int node2)
	{
		if(adj[node1][node2] == true)
		{
			return true;
		}
		else
			return false;
	}

	public AdjList getAdjList(int vertex)
	{
		return new AdjArray(vertex);
	}
	
	private class AdjArray implements AdjList{
		private int v;
		private int i;
		
		AdjArray(int v)
		{
			this.v = v;
			i = -1;
		}
		
		public int next()
		{
			for(i++; i < V(); i++)
			{
				if (edge(v,i) == true)
					return i;
			}
			return -1;
		}

		public int begin()
		{
			i = -1;
			return next();
		}

		public boolean end() 
		{
			if (Vcnt > i)
				return false;
			return true;
		}
	}
	
	static void show(Graph G)
	{
		for (int s = 0; s < G.V(); s++)
		{
			System.out.print(s + ": ");
			AdjList A = G.getAdjList(s);
			for (int t = A.begin(); !A.end(); t = A.next())
				System.out.print(t + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	//takes in an int b/c we are using an array that is all the vertices
	public void unweighted(int s)
	{
		for(int i = 0; i < v.length; i++)
		{
			v[i].dist = -1;
			v[i].known = false;
		}
		
		v[s].dist = 0;
		
		for (int currDist = 0; currDist < Vcnt; currDist++)
		{
			for (int i = 0; i < v.length; i++)
			{
				if (!v[i].known && v[i].dist == currDist)
				{
					v[i].known = true;
					for (int j = 0; j < v.length; j++)
					{
						if (edge(i, j))
						{
							if (v[j].dist == -1)
							{
								v[j].dist = currDist + 1;
								v[j].path = v[i];
							}
						}
					}
				}
			}
		}
	}
}

class Edge{
	int v, w; //an edge from v to w

	public Edge(int v, int w)
	{
		this.v = v;
		this.w = w;
	}

	public void setV(int v)
	{
		this.v = v;
	}

	public void setW(int w)
	{
		this.w = w;
	}

	public int getV()
	{
		return v;
	}

	public int getW()
	{
		return w;
	}
}

//new object known as Vertex that contains the info we need for the algorithm
class Vertex{
	public boolean known;
	public int dist;
	public Vertex path;
	public List adj;
	public String name;
}

interface AdjList{
	int begin();
	int next();
	boolean end();
}