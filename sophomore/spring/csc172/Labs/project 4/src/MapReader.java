/*
 * Author: Borja Rojo
 * Project 4
 * CSC 172
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * This class reads the files and holds a large amount of information
 * the maps given. It can get the amount of vertecies, amount of edges,
 * and store the nodes and edges in arrays. It also stores the file that
 * the map is based from.
 */
public class MapReader {
	private static int vertCount;
	private static int edgeCount;
	private static Node[] node;
	private static Edge[] edge;
	private static File mapFile;
	
	//blank constructor
	public MapReader(){
		
	}
	
	//constructor that takes a file and evaluates all properties of MapReader
	public MapReader(File f) throws FileNotFoundException{
		mapFile = f;
		setAll();
	}
	
	//Evaluated and sets all properties of MapReader
	public static void setAll() throws FileNotFoundException{
		int vCount = 0;		//Count verticies
		int eCount = 0;		//count edges
		Scanner s = new Scanner(mapFile);	//create a scanner with the Map file
		String[] token;		//a string array that will hold the seperate pieces of information in each line
		
		//while there is another line in the file
		while(s.hasNextLine()){
			token = s.nextLine().split("[\\t]");	//take that line and split up, using tab as the delimiter
			if(token[0].compareTo("i") == 0){		//if it's an intersection
				vCount++;							//add to the vertex count
				
			}
			else if(token[0].compareTo("r") == 0){	//if it's and edge
				eCount++;							//add to the edge count
			}
		}
		s.close();									//close the scanner
		
		vertCount = vCount;							//set the vertex count
		edgeCount = eCount;							//set the edge count
		node = new Node[vCount];					//initialize the node array
		edge = new Edge[eCount];					//initialize the edge array
		
		vCount = 0;									//reset both to zero to keep track
		eCount = 0;
		
		s = new Scanner(mapFile);

		while(s.hasNextLine()){
			token = s.nextLine().split("[\\t]");
			if(token[0].compareTo("i") == 0){
				//Add a node at the index of the vertex count, with a name, latitude, longitude, and index
				node[vCount] = new Node(token[1], Float.parseFloat(token[2]), Float.parseFloat(token[3]), vCount);
				vCount++;
				
			}
			else if(token[0].compareTo("r") == 0){
				//Add an edge at the index of the edge count, with a name, start node, end node, and index
				edge[eCount] = new Edge(token[1], findNode(token[2]), findNode(token[3]), eCount);
				eCount++;
			}
		}
		//close the scanner
		s.close();
		
		//ALL PARTS SET
	}
	
	//finds a node given it's name
	public static Node findNode(String name){
		for(int i = 0; i < node.length; i++){
			if(node[i].name().compareTo(name) == 0){
				return node[i];
			}
		}
		return null;
	}
	//retrieve methods
	public int getVert(){
		return vertCount;
	}
	
	public int getEdge(){
		return edgeCount;
	}
	
	public Node[] getNodes(){
		return node;
	}
	
	public Edge[] getEdges(){
		return edge;
	}
}
