/*
 * Author: Borja Rojo
 * Partner: Daniel Saltz
 */
public class MyBST<T extends Comparable<T>> implements BST<T>{

	public MyTreeNode<T> root;
	
	public void insert(T x) {
		if(root == null){	//if there is no root, make one
			root = new MyTreeNode<T>(x);
		}else if(lookup(x) == true){
			System.out.println("Sorry, can't insert! " + x.toString()
					+" is already a member of this tree.");
		}else{				//else, insert on the root (recursively defined)
			root.insert(x);
		}
	}

	public void delete(T x) {
		// TODO Auto-generated method stub
		if(lookup(x) == false){
			System.out.println("Item not found in the tree! Nothing to be deleted.");
		}else{
			root.delete(x);	
		}
	}

	public boolean lookup(T x) {
		// TODO Auto-generated method stub
		return root.lookup(x);
	}
	
	
	//Print methods that check for an existing roots and then
	//call a recursive MyTreeNode print method if there is one
	public void printPreOrder() {
		// TODO Auto-generated method stub
		if(root == null){
			System.out.println("Sorry! This tree has no members.");
		}else{
			root.printPreOrder();
		}
	}

	public void printInOrder() {
		// TODO Auto-generated method stub
		if(root == null){
			System.out.println("Sorry! This tree has no members.");
		}else{
			root.printInOrder();
		}
	}

	public void printPostOrder() {
		// TODO Auto-generated method stub
		if(root == null){
			System.out.println("Sorry! This tree has no members.");
		}else{
			root.printPostOrder();
		}
	}

}
