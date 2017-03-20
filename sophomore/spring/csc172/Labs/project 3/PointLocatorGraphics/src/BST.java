 /*
Borja Rojo
Project3
4/11/15
 
 */

public class BST {
	public Node root; //root node of the BST

	// if the root is empty then the root is the given line. Otherwise, insert the line.
	public void insert(Line a)
	{
		Node n = new Node(a);
		if(root == null)
			root = n;
		else
			Node.insert(root, a);
	}

	 //counts the number of leaf nodes in the BST
	 public int leafCount(Node first)
	 {
	 	int j = 0;
	 	if (first == null)
	 	{
	 		j++;
	 		return j;
	 	}
	 	if (first != null)
	 	{
	 		j = leafCount(first.leftchild);
	 		j = j + leafCount(first.rightchild);
	 	}
	 	return j;
	 }

	 //returns the average height of the BST
	 public double averageHeight()
	 {
	 	return (double) sumLength(root, 0) / leafCount(root);
	 }

	 //recursive method that calculates the sum of the lengths of the leafnodes
	 //length = depth; so length of a single node is zero
	 //length counts from the root to each leafnode
	 public int sumLength(Node first, int j)
	 {
	 	if (first == null)
	 		return j;
	 	else
	 		return sumLength(first.leftchild, j + 1) + sumLength(first.rightchild, j + 1);
	 }
}