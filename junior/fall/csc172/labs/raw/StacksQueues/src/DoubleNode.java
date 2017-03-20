/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public class DoubleNode<T> {
	public T data;
	public DoubleNode<T> next;
	public DoubleNode<T> prev;
	
	public DoubleNode(){
		data = null;
		next = null;
		prev = null;
	}
	
	public DoubleNode(T x){
		data = x;
		next = null;
		prev = null;
	}
}
