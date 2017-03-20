/*
 * Borja Rojo's Custom Stack
 * 
 * Class: CSC 172
 * Lab: Redo of Stack lab. Much cleaner
 */

public class Stack<AnyType> implements StackInterface {
	
	Node first = new Node();
	
	public Stack(){
		Node first = new Node();
	}

	public void insert(Object x) {
		if(lookup(x) == false){
			Node newNode = new Node(x);
			newNode.next = first.next;
			first.next = newNode;
		}else{
			System.out.println("Ojbect alredy in list");
		}
	}

	public void delete(Object x) {
		Node prev = first;
		Node current = first.next;
		
		while(current != null){
			if(current.data == x){
				prev.next = current.next;
				return;
			}
			prev = prev.next;
			current = current.next;
		}
		
	}

	public boolean lookup(Object x) {
		Node current = first.next;
		
		while(current != null){
			if(current.data == x){
				return true;
			}else{
				current = current.next;
			}
		}
		return false;
	}

	public void printList() {
		Node current = first.next;
		String toPrint = "";
		
		while(current != null){
			toPrint = toPrint + current.data + " ";
			current = current.next;
		}
		
		System.out.println(toPrint);
	}
	
	//Stack LAB6

	public boolean isEmpty() {
		if(first.next == null){
			return true;
		}else{
			return false;
		}
	}

	public void push(Object x) {
		Node newNode = new Node(x);			//Make new Node with data
		newNode.next = first.next;			//Make new node point to top of Stack
		first.next = newNode;				//Make First point to new node
		
	}

	public Object pop() {
		if(this.isEmpty() == true){			//If empty, return null
			return null;
		}else{								//else...
			Node popped = first.next;		//Set popped node to top of stack
			first.next = popped.next;		//make first node point to node after top of stack
			return popped.data;					//return popped node
		}
	}

	public Object peek() {
		if(first.next != null){
			return first.next.data;
		}
		else{
			return null;
		}
	}


}
