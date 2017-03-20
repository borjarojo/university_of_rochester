/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public class MainTest {
	public static void main(String[]args){
		//Create Double Linked List
		System.out.println("Creating list...");
		MyDoublyLinkedList<String> strDList = new MyDoublyLinkedList<String>();
		System.out.println("Empty check: " + strDList.isEmpty());
		
		//Insert
		System.out.println("\n-Insertion-");
		strDList.insert("Borja");
		strDList.insert("Mike");
		strDList.insert("Peter");
		strDList.insert("Chou");
		strDList.insert("Vahag");
		strDList.insert("Luke");
		strDList.insert("Owen");
		strDList.insert("Will");
		strDList.insert("Billy");
		
		System.out.println("Empty check: " + strDList.isEmpty());
		
		//List Print forward and reverse
		System.out.println("\n-Print-");
		System.out.print("Printing list in order: "); strDList.printList();
		System.out.print("Printing list in reverse order: "); strDList.printListRev();
		
		//Lookup Test
		System.out.println("\n-Lookup-");
		String x = "Borja"; System.out.println("Looking up \"" + x + "\": " + strDList.lookup(x));
		x = "Will"; System.out.println("Looking up \"" + x + "\": " + strDList.lookup(x));
		x = "George"; System.out.println("Looking up \"" + x + "\": " + strDList.lookup(x));
		x = "Sean"; System.out.println("Looking up \"" + x + "\": " + strDList.lookup(x));
		
		//deletion
		System.out.println("\n-Deletion-");
		strDList.delete("Peter");
		strDList.delete("Owen");
		strDList.delete("George");
		strDList.delete("Billy");
		strDList.delete("Andrew");
		strDList.delete("Nathan");
		
		System.out.println("\n-Final List-");
		strDList.printList();
	}
}
