package csc172lab3;

/*
 * Henry Le
 * 
 * February 1, 2014
 * 
 * CSC 172
 * 
 * Lab 3
 * 
 * Lab Partner : Roy Eldar & Murray Wan
 * 
 * TA : Ian Davison
 */

public class MainTest {
	public static void main(String[] args){
		//creates list
		LinkedList list = new LinkedList();
		
		//tests empty
		System.out.println("Empty : " + list.isEmpty());
		
		//adds items to list
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);
		
		//prints the list forward and backwards
		list.printList();
		System.out.println("");
		list.printListRev();
		
		//checks lookup method
		System.out.println("\nEmpty : " + list.isEmpty());
		System.out.println("Is 9 in the list? : " + list.lookup(9));
		System.out.println("Is 4 in the list? : " + list.lookup(4));
		
		//delete method
		System.out.println("Delete 4");
		list.delete(4);
		
		//prints again
		list.printList();
		System.out.println("");
		list.printListRev();
	}
}
