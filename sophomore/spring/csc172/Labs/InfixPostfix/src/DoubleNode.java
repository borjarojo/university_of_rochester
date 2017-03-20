/*
 * Borja Rojo's Custom DoubleNode for Queue
 * 
 * Class: CSC 172
 * Lab: Redo of Queue lab. Much cleaner
 */

public class DoubleNode<AnyType> {
	public AnyType data;
	public DoubleNode<AnyType> next;
	public DoubleNode<AnyType> prev;
	
	public DoubleNode(){
		
	}
	
	public DoubleNode(AnyType x){
		data = x;
	}
}
