
public class Test {
	
	public static void main(String[]args){
		Queue test = new Queue();
		
		if(test.isEmpty() == true){
			System.out.println("The queue is empty");
		}else{
			System.out.println("The queue is not empty");
		}
		
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		test.enqueue(4);
		test.enqueue(5);
		
		if(test.isEmpty() == true){
			System.out.println("The queue is empty");
		}else{
			System.out.println("The queue is not empty");
		}
		
		System.out.println("peeking:" + test.peek());
		
		System.out.println("Dequeuing: \n" + test.dequeue());
		test.printList();
		
		System.out.println("Dequeuing again:\n" + test.dequeue());
		
		System.out.println("enqueuing some new items");
		
		test.enqueue(88);
		test.enqueue(34);
		test.enqueue(66);
		
		test.printList();
		
		
	}
}
