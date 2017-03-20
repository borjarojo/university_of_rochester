
public class Test {
	
	public static void main(String[]args){
		
		//Create Stack
		Stack stack = new Stack();
		
		//Check isEmpty when just made
		if(stack.isEmpty() == true){
			System.out.println("Stack is empty");
		}else{
			System.out.println("Stack is not empty");
		}
		
		//Insert
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		//Check isEmpty after insert
		if(stack.isEmpty() == true){
			System.out.println("Stack is empty");
		}else{
			System.out.println("Stack is not empty");
		}
		
		//Peek at stack
		System.out.println("Peeking at stack:");
		System.out.println(stack.peek());
		
		//Pop stack
		System.out.println("Popping stack:");
		System.out.println(stack.pop());

		System.out.println("Popping stack again:");
		System.out.println(stack.pop());

		System.out.println("Popping the rest:");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		
	}

}
