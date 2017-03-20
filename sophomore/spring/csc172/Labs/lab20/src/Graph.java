/*
 Borja Rojo
 4/19/15
 Lab Partner: Daniel Saltz
 
 */

public class Graph {
	
       private int vertexCount;
       private boolean directed; // false for undirected graphs, true for directed
       private boolean adj[][];
       private int weight[][];
       public Vertex v[];
       
       public Graph(int numVerticies, boolean isDirected) 
       {
    	   directed = isDirected;
    	   vertexCount = numVerticies;
    	   adj = new boolean[numVerticies][numVerticies];
    	   weight = new int[numVerticies][numVerticies];
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
    	   if (directed){
    			adj[e.from][e.to] = true;
    			weight[e.from][e.to] = e.weight;
    	   }
    	   else if (!directed)
    	   {
    		   adj[e.from][e.to] = true;
    		   adj[e.to][e.from] = true;
    		   weight[e.from][e.to] = e.weight;
    		   weight[e.to][e.from] = e.weight;
    	   }
       }
       public void delete(Edge e)
       { 
    	   if (directed){
    			adj[e.from][e.to] = false;
    			weight[e.from][e.to] = 0;
    	   }
    	   else if (!directed)
    	   {
    		   adj[e.from][e.to] = false;
    		   adj[e.to][e.from] = false;
    		   weight[e.from][e.to] = 0;
    		   weight[e.from][e.to] = 0;
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
		
		public void dijkstra(int s)
		{
			//Distanced set to inf and values to unknown
			for(int i = 0; i < v.length; i++)
			{
				v[i].distance = -1;
				v[i].known = false;
			}

			
			v[s].distance = 0;
			v[s].known = true;
			
			int w = s;
			
			//if there is an unknown distance vertex
			while(hasUnknownDistVert(v)){
				//set w vertex known = true
				v[w].known = true;
				
				
				//for every vertex
				for(int i = 0; i < v.length; i++)
				{
					//check if there is an edge between current vertex and adjacent vertex
					if(edge(w, i)){
						//if !i.known
						if(!v[i].known){
							
							//cost of edge from w to i
							int cwi = weight[w][i];
							if(v[i].distance == -1){
								v[i].distance = cwi;
								v[i].path = v[w];
								break;
							}
							if(v[w].distance + cwi < v[i].distance){
								v[i].distance = v[w].distance + cwi;
								v[i].path = v[w];
							}
						}
						
					}
				}
				w = smallestDistanceVert();
				
			}
			
			
		}
		
		public boolean hasUnknownDistVert(Vertex ver[]){
			
			for(int i = 0; i < ver.length; i++)
			{
				if(!ver[i].known)
					return true;
			}
			return false;
			
		}
		
		public int smallestDistanceVert()
		{
			int num = 0;
			int dist = -1;
			for(int i = 0; i < v.length; i++)
			{
				if(!v[i].known){
					if(v[i].distance >= 0){

						System.out.println("check");
						if(dist == -1){
							dist = v[i].distance;
							num = i;
						}
						if(v[i].distance < dist)
						{
							dist = v[i].distance;
							num = i;
						}
						System.out.println(num);
					}
				}
			}
			return num;
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
