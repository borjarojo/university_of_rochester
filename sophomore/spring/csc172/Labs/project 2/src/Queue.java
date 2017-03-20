public class Queue {
	Node first;

	public Queue(){
		first = new Node(null);
	}

	public void insert(String x){
		if(first.data == null){
			first.data = x;
		} else{
			Node temp = first;
			first.next = temp.next;
			while(temp.next != null){
				temp = temp.next;
			}
			temp.next = new Node(x);
		}
	}

	public String dequeue(){
		if(first.data == null){
			return null;
		} else{
			String s = first.data;
			first = first.next;
			return s;
		}
	}
	
	public boolean isEmpty() {
		if (first.data == null || first == null)
			return true;
		return false;
	}

	public String peek(){
		return first.data;
	}

	public int size(){
		int x = 0;
		Node temp = first;
		while(temp != null){
			x++;
			temp = temp.next;
		}
		return x;
	}

	public void printList() {
		Node temp = new Node(null);
		temp = first;

		while(temp != null){
			System.out.print("" + temp.data + " ");
			temp = temp.next;
		}
	}
}
