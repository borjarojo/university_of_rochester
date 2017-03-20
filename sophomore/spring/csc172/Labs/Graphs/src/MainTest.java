
public class MainTest {
	public static void main(String[]args){
		
		//create directed graph
		Graph u = new Graph(7, true);
			
			u.insert(new Edge(0,3));
			u.insert(new Edge(0,1));
			
			u.insert(new Edge(1,3));
			u.insert(new Edge(1,4));
			
			u.insert(new Edge(2,5));
			u.insert(new Edge(2,0));
			
			u.insert(new Edge(3,2));
			u.insert(new Edge(3,5));
			u.insert(new Edge(3,6));
			u.insert(new Edge(3,4));
			
			u.insert(new Edge(4,6));
			
			u.insert(new Edge(6,5));
			
		//create undirected graph
		Graph d = new Graph(5, false);
			
			d.insert(new Edge(0,1));
			d.insert(new Edge(0,3));
			d.insert(new Edge(0,4));
			d.insert(new Edge(1,3));
			d.insert(new Edge(1,2));
			d.insert(new Edge(2,3));
			d.insert(new Edge(2,4));
		
		u.show();
		System.out.println();
		d.show();
		
	}
}
