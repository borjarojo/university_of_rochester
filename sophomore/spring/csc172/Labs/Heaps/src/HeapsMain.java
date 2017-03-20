/*
 * Author: Borja Rojo
 * Partner: Daniel Saltz
 * 
 */
public class HeapsMain {
	
	public static void main(String[]args){
		Heap defaultTest = new Heap();
		Heap sizeTest = new Heap(20);
		System.out.println("Size Test:");
		System.out.println(defaultTest.size());
		System.out.println("isEmpty() test");
		System.out.println(defaultTest.isEmpty());
		System.out.println();
		System.out.println(defaultTest.heap.length);
		
		System.out.println(sizeTest.size());
		
		Heap test = new Heap(5);
		System.out.println("Inserting...");
		test.insert(new Integer(19));
		test.insert(new Integer(12));
		test.insert(new Integer(15));
		test.insert(new Integer(16));
		test.insert(new Integer(13));
		test.insert(new Integer(11));
		test.insert(new Integer(17));
		test.insert(new Integer(14));
		
		test.insert(new Integer(7));
		test.insert(new Integer(5));
		test.insert(new Integer(8));
		test.insert(new Integer(3));
		test.insert(new Integer(2));
		test.insert(new Integer(9));
		test.insert(new Integer(6));
		test.insert(new Integer(6));
		
		
		System.out.println();

		System.out.println("Printing...");
		test.printHeap();
		System.out.println();
		
		System.out.println("deleteMin():");
		System.out.println(test.deleteMin());
		System.out.println("deleteMin():");
		System.out.println(test.deleteMin());
		System.out.println("deleteMin():");
		System.out.println(test.deleteMin());
		System.out.println("deleteMin():");
		System.out.println(test.deleteMin());
		System.out.println("deleteMin():");
		System.out.println(test.deleteMin());
		
	}
}
