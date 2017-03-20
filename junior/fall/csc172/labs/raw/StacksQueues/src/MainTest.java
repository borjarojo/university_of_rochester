/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public class MainTest {
	public static void main(String[]args){
		System.out.println("Creating new stack...");
		MyStack<String> stack = new MyStack<String>();
		
		System.out.println("Pushing onto stack...");
		stack.push("Charlie");
		stack.push("Sammy");
		stack.push("Josh");
		stack.push("Brian");
		stack.push("Mathew");
		
		System.out.println("Is the stack empty? " + stack.isEmpty());
		System.out.print("Printing Stack: "); stack.printList();
		
		System.out.println("Peeking at the top of the stack: " + stack.peek());
		System.out.println("Popping from the stack...");
		
		stack.pop();
		stack.pop();
		stack.pop();
		
		System.out.print("Printing new stack: "); stack.printList();
		
		System.out.println("New top of stack: " + stack.peek());
		
		System.out.println("\nCreating new queue...");
		MyQueue<String> queue = new MyQueue<String>();
		
		System.out.println("Enqueueing...");
		queue.enqueue("Charlie");
		queue.enqueue("Sammy");
		queue.enqueue("Josh");
		queue.enqueue("Brian");
		queue.enqueue("Mathew");
		
		System.out.println("Is the queue empty? " + queue.isEmpty());
		System.out.print("Printing queue..."); queue.printList();
		
		System.out.println("Peeking the queue: " + queue.peek());
		System.out.println("Dequeueing...");
		
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		
		System.out.print("Printing new queue: "); queue.printList();
		
		System.out.println("New top of queue: " + queue.peek());
	}
}
