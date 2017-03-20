/*
 * Borja Rojo's Custom Queue
 * 
 * Class: CSC 172
 * Lab: Redo of Queue lab. Much cleaner
 */


public class Queue implements QueueInterface{
	
	public DoubleNode first;
	public DoubleNode last;
	
	public Queue(){
		first = new DoubleNode();
		last = new DoubleNode();
		first.prev = null;
		first.next = last;
		last.prev = first;
		last.next = null;
		
	}
	
	//I implemented a constructor in my DoubleNode class to allow for a node to be 
	//created with the data inside it when declaring the node. I also Overloaded the
	//constructor to allow me to create a node without any data
	
	//I am adding nodes after the first node and always to the beginning of the list
	public void insert(Object x) {
		// TODO Auto-generated method stub
		if(lookup(x) != true){
			
			DoubleNode obj = new DoubleNode(x);
			
			//Add the item like a queue, FIFO
			obj.next = last;				//Assign new node pointers to the correct place
			obj.prev = last.prev;			//at the end of the list
	
			last.prev.next = obj;			//assign second to last node to point to the new node
			last.prev = obj;				//assign last node to point to the new node
			System.out.println("New item added: " + x);
			
		}
		
		//The method is meant to organize the new node before anything else
		//and then organize the nodes that are meant to be around the new node to 
		//to point at the new node. This insert() method is meant to need only the 
		//beginning of the list in order to work
	}

	public void delete(Object x) {
		// TODO Auto-generated method stub
		DoubleNode current = first.next;
		
		if(lookup(x) == true){
			while(current != last){
				
				if(current.data == x){
					current.next.prev = current.prev;
					current.prev.next = current.next;
					System.out.println("Object " + x + " was deleted.");
					return;
				}
				current = current.next;
			}
		}
	}

	public boolean lookup(Object x) {
		// TODO Auto-generated method stub
		DoubleNode current = first.next;		//start at second node
		
		while(current != last){
			if(current.data == x){
				System.out.println("Object \"" + x +"\" in list.");
				return true;
			}else{
				current = current.next;
			}
		}
		
		return false;
	}

	public void printList() {
		// TODO Auto-generated method stub
		
		//Due to the need to go through the entire list, this method is O(n)
		
		DoubleNode current = first.next;			//Start at second node
		String stringList = "";
		
		while(current != last){						//Enter if the node is not last
			stringList = stringList + current.data + ", ";
			current = current.next;
		}
		
		System.out.println(stringList);
	}

	public void printListRev() {
		// TODO Auto-generated method stub
		
		//Due to the need to go through the entire list, this method is O(n)
		
		DoubleNode current = last.prev;			//Start at second to last node
		String stringList = "";
		
		while(current != first){						//Enter if the node is not last
			stringList = stringList + current.data + ", ";
			current = current.prev;
		}
				
		System.out.println(stringList);
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		/*Here, I check if the list has any nodes in between the first and last node
		//I can do this by checking if both the node after first and the node before
		//last are last and first, respectively
		 */
		if(first.next == last && last.prev == first){
			return true;
		}else{
			return false;
		}
		
		/*this works due to the fact that if either the beginning or the end of my list
		//are not pointing to each other, then the list has something in it. This logic also
		//works because if the list were not meant to be empty and I want to check if it is
		//empty, then it is effectively empty if both the first and last nodes point to each other
		 */
	}

	public void enqueue(Object x) {
		DoubleNode newNode = new DoubleNode(x);		//Create a new node
		newNode.prev = last.prev;					//set the prev pointer node to current last node
		newNode.next = last;						//set the next pointer to the last node
		last.prev.next = newNode;					//set the "was" second to last node to point its next to new node
		last.prev = newNode;						//set last node to point its prev to the new node
		
		//first, this method makes the new node point to the corresponding places
		//then, i have the surrounding nodes point to the correct places in an
		//order that prevents loss of information before the method is ready for it
	}

	public Object dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()){							//If empty, return null
			return null;
		}else{									//else
			DoubleNode front = first.next;		//make a newNode equal to the first Queue node
			first.next = first.next.next;		//set first node to point to second
			return front.data;					//return the data of the first node
		}
	}

	public Object peek() {
		// TODO Auto-generated method stub
		return first.next.data;					//returns the data of the first item in the queue
	}

}
