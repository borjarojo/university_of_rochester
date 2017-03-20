/*
 Borja Rojo
 4/19/15
 Lab Partner: Daniel Saltz
 
 */

public class Graph {
	
       private int vertexCount;
       private boolean directed; // false for undirected graphs, true for directed
       private boolean adj[][];
       public Vertex v[];
       
       public Graph(int numVerticies, boolean isDirected) 
       {
    	   directed = isDirected;
    	   vertexCount = numVerticies;
    	   adj = new boolean[numVerticies][numVerticies];
    	   v = new Vertex[numVerticies];
    	   
    	   // filling the Vertex array with Vertices
    	   // fills the data so that the shortest path can be printed
    	   for (int i = 0; i < v.length; i++)
    	   {
    		   v[i] = new Vertex();
    		   v[i].data = "" + i;
    	   }
       }
       
       public void insert(Edge e) 
       { 
    	   // checking to make sure it's not a duplicate edge
    	   if (adj[e.from][e.to] == true)
    		   return;
    	   if (directed)
    			adj[e.from][e.to] = true;
    	   else if (!directed)
    	   {
    		   adj[e.from][e.to] = true;
    		   adj[e.to][e.from] = true;
    	   }
       }
       public void delete(Edge e)
       { 
    	   if (directed)
    			adj[e.from][e.to] = false;
    	   else if (!directed)
    	   {
    		   adj[e.from][e.to] = false;
    		   adj[e.to][e.from] = false;
    	   }
       }
       
       // this method checks to see if the edge exists at the given nodes
	   	public boolean edge(int node1, int node2)
	   	{
	   		return adj[node1][node2];
	   	}
       
       public AdjList getAdjList(int vertex) 
       { 
    	   return new AdjArray(vertex);
       }
       
       // method to print out the graph using an iterator
       public void show() 
       {
           for (int s = 0; s < vertexCount; s++) 
           {
               System.out.print(s + ": ");
               AdjList A = getAdjList(s);
               for (int t = A.begin(); !A.end(); t = A.next())
               {
            	   if (t != -1)
            		   System.out.print(t + " ");
               }
               System.out.println();
           }
       }
       
       // taken from Figure 9.16 of the textbook
		public void unweighted(int s)
		{
			// Initializing distances to -1, and values to unknown
			for (int i = 0; i < v.length; i++)
			{
				v[i].distance = -1;
				v[i].known = false;
			}
			
			v[s].distance = 0; // setting the starting vertex distance to zero
			
			for (int currDist = 0; currDist < vertexCount; currDist++)
			{
				for (int i = 0; i < v.length; i++)
				{
					if (!v[i].known && v[i].distance == currDist)
					{
						v[i].known = true;
						for (int j = 0; j < v.length; j++)
						{
							if (edge(i,j))
							{
								if (v[j].distance == -1)
								{
									v[j].distance = currDist + 1;
									v[j].path = v[i];
								}
							}
						}
					}
				}
			}
		}
		
		// taken from Figure 9.30 of the textbook
		public void printPath(Vertex x)
		{
			if (x.path != null)
			{
				printPath(x.path);
				System.out.print(" to ");
			}
			System.out.print(x.data);
		}
       
       private class AdjArray implements AdjList {
		   private int v; 
		   private int i; 
		   public AdjArray(int v)
		   {
			   this.v = v;
			   i = -1;
		   }
		   public int next() 
		   { 	   
			   for (++i; i < vertexCount; i++)
			   {
				   if (edge(v,i))
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
		    	return i > vertexCount;
		    }     
       } 
      
      private interface AdjList{
		  int begin();
		  int next();
		  boolean end();
	}
}
