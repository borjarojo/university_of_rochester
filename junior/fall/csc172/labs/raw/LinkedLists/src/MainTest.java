/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public class MainTest {

	public static void main(String[]args){
		//Create List
		System.out.println("Creating MyList strList1...");
		MyList<String> strList1 = new MyList<String>();
		System.out.println("Checking to see if empty: " + strList1.isEmpty());
		
		//Insert Items
		System.out.println("Inserting...");
		strList1.insert("Borja");
		strList1.insert("Mike");
		strList1.insert("Peter");
		strList1.insert("Chou");
		strList1.insert("Vahag");
		strList1.insert("Luke");
		strList1.insert("Billy");
		strList1.insert("Owen");
		strList1.insert("Thomas");
		strList1.insert("Will");
		System.out.println("Checking to see if empty: " + strList1.isEmpty());
		
		//Print list
		System.out.print("Printing strList1: "); strList1.printList();
		
		//Lookup Test
		System.out.println("\nTesting Lookup:");
		String x = "Borja";
		System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		x = "George"; System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		x = "Martin"; System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		x = "Thomas"; System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		x = "Andrew"; System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		x = "Will"; System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		x = "Owen"; System.out.println("Looking up \"" + x + "\": " + strList1.lookup(x));
		
		//Insert no duplicate
		
		//Create List
		System.out.println();
		System.out.println("Creating MyList strList2...");
		MyList<String> strList2 = new MyList<String>();
		
		//Insert Items
		System.out.println("Inserting...");
		strList2.insert("Borja");
		strList2.insert("Peter");
		strList2.insert("Chou");
		strList2.insert("Luke");
		strList2.insert("Billy");
		strList2.insert("Thomas");
		System.out.println("\nNow attempting more insertion with some repititions:");
			//repeat
		strList2.insert("Borja");
		strList2.insert("Mike");
		strList2.insert("Peter");
		strList2.insert("Chou");
		strList2.insert("Vahag");
		strList2.insert("Luke");
		strList2.insert("Billy");
		strList2.insert("Owen");
		strList2.insert("Thomas");
		strList2.insert("Will");
		
		//Print list
		System.out.print("Printing strList2: "); strList2.printList();
		
		//Deletion Test
		System.out.println("\nTesting Delete:");
		x = "Borja"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		x = "George"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		x = "Martin"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		x = "Thomas"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		x = "Andrew"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		x = "Will"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		x = "Owen"; System.out.print("Deleting \"" + x + "\": "); strList1.delete(x);
		
		System.out.print("New srtList1: "); strList1.printList();
		
		
	}
}
