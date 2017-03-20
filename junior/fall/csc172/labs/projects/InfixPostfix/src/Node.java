/*
 * Borja Rojo's Custom Node for Stack
 * 
 * Class: CSC 172
 * Lab: Redo of Stack lab. Much cleaner
 */

public class Node<AnyType> {
	
	public AnyType data;
	public Node<AnyType> next;
	
	public Node(){
		
	}
	
	public Node(AnyType x){
		data = x;
	}
}
