/*
 * Author: Borja Rojo
 * Partner: Daniel Saltz
 */
public class MyTreeNode<T extends Comparable<T>> {
	public T data;
	public MyTreeNode<T> leftChild;
	public MyTreeNode<T> rightChild;
	public MyTreeNode<T> parent;
	
	//basic constuctor for only the data, used for root
	public MyTreeNode(T x){
		data = x;
	}
	
	//constuctor that uses a MyTreeNode to assign as the parent
	public MyTreeNode(T x, MyTreeNode<T> p){
		data = x;
		parent = p;
	}
	
	//recursively defined insert for the use in the MyBST class
	//adds the new data
	public void insert(T x){
		/*
		 * The recursion of this method is to keep inserting to
		 * the childs of a node until a child to be inserted on 
		 * is null. Then, create that node with the data and a 
		 * link to the parent, which is the node this method is
		 * being invoked on
		 */
		if(x.compareTo(data) > 0){	//if the data to insert is bigger, go left
			if(leftChild == null){		//if there isn't a child, make a new one and link the parent
				leftChild = new MyTreeNode<T>(x, this);
			}else{						//else, call insertion again on itself
				leftChild.insert(x);
			}
		}else{						//if it's equal or smaller, go right
			if(rightChild == null){		//if there isn't a child, make a new one and link the parent
				rightChild = new MyTreeNode<T>(x, this);
			}else{
				rightChild.insert(x);	//else, call insertion again on itself
			}
		}
	}
	
	/*
	 * Print methods
	 * they are all rather similar, the difference being when
	 * the action on the called node is invoke with respect to
	 * the recursion on it's children are invoked. 
	 * 
	 * PreOrder has the action on the node invoked before visiting the
	 * right and left child
	 * 
	 * InOrder has the action invoked between the visits
	 * 
	 * Post order has the action invoked after the visits
	 */
	public void printPreOrder() {
		System.out.println(data);
		if(leftChild != null){leftChild.printPreOrder();}
		if(rightChild != null){rightChild.printPreOrder();}
	}
	
	public void printInOrder() {
		if(leftChild != null){leftChild.printInOrder();}
		System.out.println(data);
		if(rightChild != null){rightChild.printInOrder();}
	}
	
	public void printPostOrder() {
		if(leftChild != null){leftChild.printPostOrder();}
		if(rightChild != null){rightChild.printPostOrder();}
		System.out.println(data);
	}
	
	//preorder traverse
	/*
	 * So the way this works is that every single node will
	 * have been visited and checked before it returns false once
	 */
	public boolean lookup(T x) {
		if(x.compareTo(data) > 0){			//if what you're looking up is larger, go left
			if(leftChild == null){				//if nothing is left, x is not in the tree, so return false
				return false;
			}else{								//else, lookup on the left to see if it's in that subtree
				return leftChild.lookup(x);
			}
		}else if(x.compareTo(data) < 0){	//else if what you're looking up is smaller, go right
			if(rightChild == null){				//if nothing is right, x is not in the tree, so return false
				return false;
			}else{								//else, lookup on the right to see if it's in the subtree
				return rightChild.lookup(x);
			}
		}else{								//else, it's equal, so you've found the data, so return true
			return true;
		}
	}
	
	public void delete(T x){
		if(x.compareTo(data) > 0){			//if its larger, go left
			if(leftChild == null){				//if left doesn't exists, x is not there, so return
				return;
			}else{								//else delete on the left because it might be in that subtree
				leftChild.delete(x);
			}
		}else if(x.compareTo(data) < 0){	//else if its less, go right
			if(rightChild == null){				//if right doesn't exists, x is not there, so return
				return;
			}else{								//else, delte on the right because it might be in that subtree
				rightChild.delete(x);
			}
		}else{								//else, it's equal, so it's been found, so it must be deleted
			//deletion section
			
			//Leaf
			if((leftChild == null) && (rightChild == null)){				//if it's a leaf
				//There is no huristic that allows me to inherently know of what child this leaf is
				if(parent.leftChild == this){								//if the node we are deleting on is the left child if its parent, dereference it
					parent.leftChild = null;
				}else{														//else, it's the right, so dereference the right
					parent.rightChild = null;
				}
				
			//Single child, and I know it's not a leaf
			}else if((leftChild == null) || (rightChild == null)){ 			//if it has a single child
				if(rightChild == null){										//if the left is the child
					//There is no huristic that allows me to inherently know of what child this leaf is
					if(parent.leftChild == this){							//if the node we are deleting on is the left child if its parent, dereference it
						parent.leftChild = this.leftChild;
					}else{													//else, it's the right, so dereference the right
						parent.rightChild = this.leftChild;
					}
				}else{														//if the right is the child
					//There is no huristic that allows me to inherently know of what child this leaf is
					if(parent.leftChild == this){							//if the node we are deleting on is the left child if its parent, dereference it
						parent.leftChild = this.rightChild;
					}else{													//else, it's the right, so dereference the right
						parent.rightChild = this.rightChild;
					}
				}
			}else{															//if it has two children
				//I realized my tree was built backwards in the middle of this lab.
				//it actually only matters in this section RIGHT HERE
				MyTreeNode<T> rightMost = leftChild.rightMost();			//find the RIGHTMOST member of the LEFTCHILD
				this.data = rightMost.data;									//replace the nodes data item with the data item found
				
				leftChild.delete(rightMost.data);							//delete the node that contained the found data item
																			//Note: it has to be on the leftChild subtree
			}
			
		}
	}
	
	public MyTreeNode<T> rightMost(){
		if(rightChild == null){
			return this;
		}else{
			return rightChild.rightMost();
		}
	}
}

