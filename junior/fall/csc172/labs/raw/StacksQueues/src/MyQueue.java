/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public class MyQueue<T> implements Queue<T>{
	
	DoubleNode<T> start;
	DoubleNode<T> end;
	
	public MyQueue(){
		start = new DoubleNode<T>();
		end = new DoubleNode<T>();
		start.next = end;
		end.prev = start;
	}
	
	//insertion at the beginning
	public void insert(T x) {
		if(lookup(x)){
			System.out.println("Item found. No insertion.");
		}
		else{
			System.out.println("Item not found. Inserting.");
			DoubleNode<T> node = new DoubleNode<T>(x);	//Create node
			node.next = start.next;						//Node's next points to start's next
			node.prev = start;							//Nodes prev points to start
			//So far, this is safe, as start and start.next still point to each other
			start.next.prev = node;						//make start's next point back to node, before start no longer points to it
			start.next = node;							//make start point to node	
		}
	}

	public void delete(T x) {
		if(!lookup(x)){
			System.out.println("Item not found. No Deletion");
		}
		else{
			System.out.println("Item found. Deleting");
			DoubleNode<T> at = start;
			while(at.next != end){
				at = at.next;
				if(at.data == x){
					at.prev.next = at.next;
					at.next.prev = at.prev;
				}
			}
		}
		
	}

	public boolean lookup(T x) {
		DoubleNode<T> at = start;
		while(at.next != end){
			at = at.next;
			if(at.data == x) return true;
		}
		return false;
	}
	
	//returns true if start points to end
	public boolean isEmpty() {
		if(start.next == end) return true;
		return false;
	}
	
	//Expected Run time: O(n)
	public void printList() {
		DoubleNode<T> at = start;
		while(at.next != end){
			at = at.next;
			System.out.print(at.data + " ");
		}
		System.out.println();
		
	}

	//Expected Run time: O(n)
	public void printListRev() {
		DoubleNode<T> at = end;
		while(at.prev != start){
			at = at.prev;
			System.out.print(at.data + " ");
		}
		System.out.println();
	}

	public void enqueue(T x) {
		// TODO Auto-generated method stub
		DoubleNode<T> node = new DoubleNode<T>(x);
		node.prev = end.prev;
		node.next = end;
		end.prev = node;
		node.prev.next = node;
		
	}

	public T dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()){
			return null;
		}else{
			T data = start.next.data;
			start.next = start.next.next;
			start.next.prev = start;
			return data;
		}
		
	}

	public T peek() {
		// TODO Auto-generated method stub
		return start.next.data;
	}

}
