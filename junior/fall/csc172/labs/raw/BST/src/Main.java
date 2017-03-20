/*
 * Author: Borja Rojo
 * Partner: Daniel Saltz
 */
public class Main {
	public static void main(String[]args){
	
		MyBST<Integer> tree = new MyBST<Integer>();
		
		//test some insertion
		System.out.println("---insertion---");
		tree.insert(6);
		tree.insert(2);
		tree.insert(9);
		tree.insert(7);
		tree.insert(1);
		tree.insert(11);
		tree.insert(3);
		
		//Print tests
		//printPreOrder
		System.out.println("\n---printPreOrder---");
		tree.printPreOrder();
		//printInOrder
		System.out.println("\n---printInOrder---");
		tree.printInOrder();
		//printPostOrder
		System.out.println("\n---printPostOrder---");
		tree.printPostOrder();
		
		//Lookup test
		System.out.println("\n---lookup---");
		for(int x = 0; x < 15; x ++){
			System.out.println("Looking up " + x + ": " + tree.lookup(x));
		}
		
		//Deletion tests
		System.out.println("\n---deletion---");
		System.out.println("\nDeleting 2...");
		tree.delete(2);
		System.out.println("InOrder print...");
		tree.printInOrder();
		System.out.println("\nDeleting 9...");
		tree.delete(9);
		System.out.println("InOrder print...");
		tree.printInOrder();
		System.out.println("\nDeleting 10...");
		tree.delete(10);
		System.out.println("InOrder print...");
		tree.printInOrder();
	}
}
