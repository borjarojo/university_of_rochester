package csc172lab3;

/*
 * Henry Le
 * 
 * February 1, 2014
 * 
 * CSC 172
 * 
 * Lab 3
 * 
 * Lab Partner : Roy Eldar & Murray Wan
 * 
 * TA : Ian Davison
 */

//part3 of lab3 -- class
public class LinkedList implements MyDoubleLinkedList{
	private MyDoubleNode head;
	private MyDoubleNode tail;
	private int n;


	//part3 of lab3 -- constructor
	public LinkedList(){
		head = new MyDoubleNode();
		tail = new MyDoubleNode();
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		n = 0;
	}

	//part3 of lab3 -- insert
	public void insert(Object x) {
		if(!lookup(x)){
			MyDoubleNode last = tail.prev;
			MyDoubleNode node = new MyDoubleNode();
			node.data = x;
			node.next = tail;
			node.prev = last;
			tail.prev = node;
			last.next = node;
			n++;
		} else
			System.out.println("Item is already in list");
	}

	//part6 of lab3 
	public void delete(Object x) {
		if (lookup(x)){
			MyDoubleNode curNode = new MyDoubleNode();
			MyDoubleNode prevNode = new MyDoubleNode();
			curNode = head.next;
			prevNode = head;

			while (curNode.next != null){
				if (curNode.data.equals(x)){
					curNode = curNode.next;
					prevNode.next = curNode;
					curNode.prev = prevNode;
				} else {
					curNode = curNode.next;
					prevNode = curNode.prev;
				}
			}
		} else
			System.out.println("Item is not in the list.");
	}

	//part5 of lab3
	public boolean lookup(Object x) {
		MyDoubleNode temp = new MyDoubleNode();
		temp = head.next;

		while (temp.next != null){
			if(temp.data == x){
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	//part3 of lab3 -- isEmpty?
	public boolean isEmpty() {
		return (n == 0);
	}

	//part4 of lab3
	//Runtime == BigO(n) where n is the number of items in the list
	public void printList() {
		MyDoubleNode temp = new MyDoubleNode();
		temp = head;

		while (temp.next != null){

			if (temp.data != null){
				System.out.print("" + temp.data + " ");
			}
			temp = temp.next;
		}
	}

	//part4 of lab3
	//Runtime == BigO(n) where n is the number of items in the list
	public void printListRev() {
		MyDoubleNode temp = new MyDoubleNode();
		temp = tail;

		while (temp.prev != null){
			if(temp.data != null){
				System.out.print("" + temp.data + " ");
			}
			temp = temp.prev;
		}
	}
}
