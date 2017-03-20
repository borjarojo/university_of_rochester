
public class Lab12Main {

	public static void main(String[]args){
		Heap test = new Heap(5);
		
		System.out.println(test.isEmpty());
		System.out.println(test.size());
		
		test.insert(new Integer(12));
		test.insert(new Integer(4));
		test.insert(new Integer(8));
		test.insert(new Integer(11));
		test.insert(new Integer(9));
		test.insert(new Integer(3));
		test.insert(new Integer(6));
		test.insert(new Integer(9));
		test.insert(new Integer(3));
		test.insert(new Integer(6));
		test.insert(new Integer(12));
		test.insert(new Integer(4));
		test.insert(new Integer(8));
		test.insert(new Integer(11));
		test.insert(new Integer(9));
		test.insert(new Integer(3));
		test.insert(new Integer(6));
		test.insert(new Integer(9));
		test.insert(new Integer(3));
		test.insert(new Integer(6));
		
		System.out.println(test.isEmpty());
		System.out.println(test.size() + "\n");
		
		test.printHeap();
		System.out.println();
		
		System.out.println(test.deleteMin() + "\n");
		test.printHeap();
	}
}
