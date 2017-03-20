import java.util.Random;


public class Test {
	public static void main(String[]args){
		//Creating heaps with different constructors
		System.out.println("---Heaps---");
		
		System.out.println("Creating default heap...");
		MyHeap<Integer> defaultHeap = new MyHeap<Integer>();
		System.out.println("Creating custom heap of size 20...");
		MyHeap<Integer> customHeap = new MyHeap<Integer>(20);
		
		//Testing size() and isEmpty()
		System.out.println();
		System.out.println(">Testing size() method");
		System.out.println("\tsize() of defaultHeap: " + defaultHeap.size());
		System.out.println("\tsize() of customHeap: " + customHeap.size());
		
		System.out.println();
		System.out.println(">Testing isEmpty() method");
		System.out.println("\tisEmpty() of defaultHeap: " + defaultHeap.isEmpty());
		System.out.println("\tisEmpty() of customHeap: " + customHeap.isEmpty());

		System.out.println();
		System.out.println(">Working with defaultHeap");
		
		System.out.println("Inserting some Integers...");
		System.out.println("The default array starts with 10, so this will expand"
				+ " twice by inserting 25 items");
		for(int i = 0; i < 25; i++){
			defaultHeap.insert(new Integer(new Random().nextInt(100)));
		}
		
		System.out.println("Printing heap:" + defaultHeap.size());
		defaultHeap.printHeap();
		
		System.out.println();
		System.out.println("Deleting 3 items from the heap...");
		System.out.println("\tDeleted, Size: " + defaultHeap.deleteMin() 
				+ ", " + defaultHeap.size());
		System.out.println("\tDeleted, Size: " + defaultHeap.deleteMin() 
				+ ", " + defaultHeap.size());
		System.out.println("\tDeleted, Size: " + defaultHeap.deleteMin() 
				+ ", " + defaultHeap.size());
		
		System.out.println();
		System.out.println("Printing heap: " + defaultHeap.size());
		defaultHeap.printHeap();
		
		System.out.println();
		System.out.println(">Testing heapify()");
		System.out.println("Creating array to be heapified...");
		System.out.println("Printing array:");
		int i = 15;
		Integer[] array = new Integer[i];
		for(int j = 0; j < i; j++){
			array[j] = new Integer(new Random().nextInt(100));
			System.out.println(array[j]);
		}
		
		System.out.println("Creating heap with array...");
		MyHeap<Integer> heapifyHeap = new MyHeap<Integer>(array);
		System.out.println("Printing heap: " + heapifyHeap.size());
		heapifyHeap.printHeap();
	}
}
