
public class BinarySearchTree implements BST{
	
	public BinaryNode root;

	public void insert(Comparable x) {
		//Construct the root node if it is empty
		if(root == null){
			root = new BinaryNode(x);
			return;
		}
		if(lookup(x) == false){
			root.insert(root, x);
		}
	}
	
	public void delete(Comparable x) {
		// TODO Auto-generated method stub
		//if it is true, do something, else, will do nothing
		if(lookup(x) == true){ 
			root.delete(root, x);
		}
	}
	
	
	public boolean lookup(Comparable x) {
		// TODO Auto-generated method stub
		if(root == null){
			//System.out.println("Root does not exist");
			return false;
		}
		else{
			//System.out.println("entering");
			return root.lookup(root, x);
		}
	}
	
	public void printPreOrder() {
		// TODO Auto-generated method stub
		//do (N) before anything else
		if(root == null){
			System.out.print("Root does not exsist");
		}
		else{
			root.printPreOrder(root);
		}
		
	}

	public void printInOrder() {
		// TODO Auto-generated method stub
		//do (N) in between everything else
		if(root == null){
			System.out.print("Root does not exsist");
		}
		else{
			root.printInOrder(root);
		}
		
	}

	public void printPostOrder() {
		// TODO Auto-generated method stub
		//do (N) after everything else
		if(root == null){
			System.out.print("Root does not exsist");
		}
		else{
			root.printPostOrder(root);
		}
		
		
	}
	

	

}
