public class Stack {
	Node first;

	public Stack(){
		first = new Node(null);
	}

	public void push(String x){
		if (first==null){
			first=new Node(x);
		}
		else if (first.data==null){
			first.data=x;
		} 
		else{
			Node temp= new Node(x);
			temp.next=first;
			first=temp;
		}
	}

	public String pop(){
		if(first == null){
			return null;
		}
		Node temp = new Node(null);
		temp = first;
		first = first.next;
		return temp.data;
	}

	public String peek(){
		if (first==null)
			return null;
		return first.data;
	}

	public boolean isEmpty() {
		return (first == null || first.data == null);
	}

	public void printList() {
		Node temp = new Node(null);
		temp = first;

		while(temp != null){
			System.out.print("" + temp.data + "");
			temp = temp.next;
		}
	}

	public int size(){
		int x = 1;
		Node temp = first;
		while(temp != null){
			x++;
			temp = temp.next;
		}
		return x;
	}

	public int lastIndexOf(String x){
		int j = size();
		Node temp = first;
		while(temp != null){
			if(temp.data.equals(x)){
				return j;
			}
			temp = temp.next;
			j--;
		}
		return 0;
	}
}
