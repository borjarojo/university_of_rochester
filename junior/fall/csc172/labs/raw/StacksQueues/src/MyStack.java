/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */

//This List runs through with the start node empty.
public class MyStack<T> implements Stack<T>{
	Node<T> start;
	
	//empty constructor
	public MyStack(){
		start = new Node<T>();
	}
	
	//Insert data. Constant time O(1). Inserts at beginning
	public void insert(T x) {
		//if lookup is false
		if(!lookup(x)){
			System.out.println("Item not found. Inserting...");
			Node<T> node = new Node<T>(x);
			node.next = start.next;
			start.next = node;
		}
		else{
			System.out.println("Item found. No insertion.");
		}
	}

	public void delete(T x) {
		if(lookup(x)){
			System.out.println("Item found. Deleting");
			Node<T> at = start;
			while(at.next != null){
				if(at.next.data == x){
					at.next = at.next.next;
					break;
				}
				at = at.next;
			}
		}
		else{
			System.out.println("Item not found. By Pass Deletion");
		}
		
	}

	public boolean lookup(T x) {
		Node<T> at = start;
		while(at.next != null){
			at = at.next;
			if(at.data == x) return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		if(start.next == null) return true;
		return false;
	}
	
	//EXPECTED RUNTIME O(n)
	//Begins at the start of the list, moves into a data point,
	//prints, moves to the next, repeats until last data point
	//is reached. then print last data point
	public void printList() {
		Node<T> at = start;
		while(at.next != null){
			at = at.next;
			System.out.print(at.data + " ");
		}
		System.out.println();	
	}

	public void push(T x) {
		Node<T> node = new Node<T>(x);
		node.next = start.next;
		start.next = node;
		
	}

	public T pop() {
		if(isEmpty()){
			return null;
		}else{
			T data = start.next.data;	//get data at top node
			start.next = start.next.next;	//set start to point to the item after the top of the list; remove top of list
			return data;				//return data
		}
	}

	public T peek() {
		return start.next.data;
	}
	
	
}
