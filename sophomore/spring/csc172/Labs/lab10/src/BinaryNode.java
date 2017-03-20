
public class BinaryNode<T extends Comparable<T>> {
	public T data;
	public BinaryNode<T> leftChild;
	public BinaryNode<T> rightChild;
	public BinaryNode<T> parent;
	
	//Construct Null
	public BinaryNode(){
		
	}
	
	//Construct with data
	public BinaryNode(T info){
		data = info;
	}
	
	//Construct with data and parent node
	public BinaryNode(T info, BinaryNode<T> rent){
		data = info;
		parent = rent;
		
	}
	
	//Construct with everything
	public BinaryNode(T info, BinaryNode<T> rent, BinaryNode<T> left, BinaryNode<T> right){
		data = info;
		parent = rent;
		leftChild = left;
		rightChild = right;
	}
	
	public void insert(BinaryNode<T> temp, T d){
		//go to the right if compareTo returns > than 0
		//else go left
		if(d.compareTo(temp.data) > 0){	
			//System.out.print("Right, ");
			if(temp.rightChild == null){
				temp.rightChild = new BinaryNode(d, temp);		//Insert with temp as parent
			}
			else{
				insert(temp.rightChild, d);
			}
		}
		else if(d.compareTo(temp.data) < 0){
			//System.out.print("Left, ");
			if(temp.leftChild == null){
				temp.leftChild = new BinaryNode(d, temp);		//Insert with temp as parent
			}
			else{
				insert(temp.leftChild, d);
			}
		}
		//System.out.println();
	}

	public void printPreOrder(BinaryNode<T> n) {
		// TODO Auto-generated method stub
		//do (N) before anything else
		
		if(n != null){					//If node passed in is null, skip so
			System.out.print(n.data + " ");	//the function can continue with
			printPreOrder(n.leftChild);	//the rest of the nodes
			printPreOrder(n.rightChild);
		}

		
	}

	public void printInOrder(BinaryNode<T> n) {
		// TODO Auto-generated method stub
		//do (N) in between the two trees
	
		if(n != null){
			printInOrder(n.leftChild);
			System.out.print(n.data + " ");
			printInOrder(n.rightChild);
		}

		
	}

	public void printPostOrder(BinaryNode<T> n) {
		// TODO Auto-generated method stub
		//do (N) after everything else
		if(n != null){
			printPostOrder(n.leftChild);
			printPostOrder(n.rightChild);
			System.out.print(n.data + " ");
		}

		
	}

	public boolean lookup(BinaryNode root, Comparable x) {
		// TODO Auto-generated method stub
		if(root.data.compareTo(x) > 0){				//x goes to left tree if it is less
			if(root.leftChild == null){				//if there is no node, and the number is less, the number does not exist
				return false;	
			}
			else{
				return lookup(root.leftChild, x);
			}
		}
		else if(root.data.compareTo(x) < 0){		//x goes to right tree
			if(root.rightChild == null){			//if there is no node to the right,  the number does not exist
				return false;		
			}
			else{
				return lookup(root.rightChild, x);
			}
		}
		else{
			return true;							// if it is nor positive not negative compareTo, it is equal
		}
		
	}

	public void delete(BinaryNode root, Comparable x) {
		//System.out.println();
		// TODO Auto-generated method stub
		if(x.compareTo(root.data) > 0){			//left side
			//System.out.print("right ");
			delete(root.rightChild, x);
		}
		else if(x.compareTo(root.data) < 0){
			//System.out.print("left ");
			delete(root.leftChild, x);
		}
		else if(x.compareTo(root.data) == 0){
			//leaf
			if(leafNode(root)){
				if(leafRight(root)){
					root.parent.rightChild = null;
				}
				else if(leafLeft(root)){
					root.parent.leftChild = null;
				}
			}
			//single child
			else if((leafNode(root) != true) &&(twoNode(root) != true)){
				//check if root is a right or left child of its parent
				
				if(root.parent.leftChild == root){			//leftChild
					if(root.rightChild != null){			
						root.rightChild.parent = root.parent;
						root.parent.leftChild = root.rightChild;
						
					}
					if(root.leftChild != null){
						root.leftChild.parent = root.parent;
						root.parent.leftChild = root.leftChild;
					}
				}
				else if(root.parent.rightChild == root){	//right child
					if(root.rightChild != null){
						root.rightChild.parent = root.parent;
						root.parent.rightChild = root.rightChild;
					}
					if(root.leftChild != null){
						root.leftChild.parent = root.parent;
						root.parent.rightChild = root.leftChild;
					}
					
				}
			}
			//two child
			else{
				Comparable temp = leftMost(root.rightChild).data;
				delete(leftMost(root.rightChild), temp);
				root.data = temp;
			}
		}
		
	}
	
	
	//Return true if they satisfy
	public boolean leafNode(BinaryNode n){
		if((n.leftChild == null) && (n.rightChild == null)){
			return true;
		}
		return false;
	}
	public boolean leafRight(BinaryNode n){
		if(n.parent.rightChild == n){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean leafLeft(BinaryNode n){
		if(n.parent.leftChild == n){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean twoNode(BinaryNode n){
		if((n.rightChild != null) && (n.leftChild != null)){
			return true;
		}
		return false;
	}
	public boolean oneNode(BinaryNode n){	//this is "exclusive or"
		
		 //either both children are null or both children exist,
		 //then this should not work. i.e.
		 //leafNode(n) != true && twoChild(n) != true
		 
		if((leafNode(n) == false) && (twoNode(n) == false)){
			return true;
		}
		return false;
	}
	

	//returns leftMost child node of a node
	public BinaryNode leftMost(BinaryNode n){
		if(n.leftChild != null){
			return leftMost(n.leftChild);
		}
		return n;
	}
}
