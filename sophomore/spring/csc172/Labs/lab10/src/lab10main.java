
public class lab10main {
	
	public static void main(String[]args){
		
		BinarySearchTree tree = new BinarySearchTree();
		
		//Inserting
		
			tree.insert(12);
			tree.insert(20);
			tree.insert(14);
			tree.insert(22);
			tree.insert(23);
			tree.insert(21);
			tree.insert(5);
			tree.insert(10);
			tree.insert(11);
			tree.insert(7);
			tree.insert(3);
			tree.insert(2);
			
		//Lookup
		System.out.println("-Lookup-");
			
			//11
			System.out.print("Looking up 11: ");
			if(tree.lookup(11)){
				System.out.println("Exists");
			}
			else{
				System.out.println("Does not Exist");
			}
			
			//6
			System.out.print("Looking up 6: ");
			if(tree.lookup(6)){
				System.out.println("Exists");
			}
			else{
				System.out.println("Does not Exist");
			}
			
			//22
			System.out.print("Looking up 22: ");
			if(tree.lookup(22)){
				System.out.println("Exists");
			}
			else{
				System.out.println("Does not Exist");
			}
		
		//Printing
		System.out.println("\n-Printing-");
			//PreOrder
			System.out.print("Printing PreOrder: ");
			tree.printPreOrder();
			System.out.println();
			//InOrder
			System.out.print("Printing In Order: ");
			tree.printInOrder();
			System.out.println();
			//PostOrder
			System.out.print("Printing Post Order: ");
			tree.printPostOrder();
			System.out.println();
		
		//deletion
		System.out.println("\n-Deletions-");
		System.out.print("Original: ");
		tree.printPostOrder();
		
			//double
			tree.delete(20);	//should have been replaced by 21
			System.out.println();
			System.out.print("Deleting 20: ");
			tree.printPostOrder();
			
			//double
			tree.delete(21);	//should have been replaced by 22, and 22 was single child deletion
			System.out.println();
			System.out.print("Deleting 21: ");
			tree.printPostOrder();
			
			//single
			tree.delete(3);		//Should have cut it out
			System.out.println();
			System.out.print("Deleting 3: ");
			tree.printPostOrder();
			
			//leaf
			tree.delete(2);		//simple removal
			System.out.println();
			System.out.print("Deleting 2: ");
			tree.printPostOrder();
			
			//check
			tree.delete(10);
			System.out.println();
			System.out.print("Deleting 10: ");
			tree.printPostOrder();
		
	}
}
