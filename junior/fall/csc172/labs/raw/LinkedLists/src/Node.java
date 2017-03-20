/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public class Node<T> {
	public T data;
	public Node<T> next;
	
	//empty constructor
	public Node(){
		data = null;
		next = null;
	}
	
	//data constructor
	public Node(T x){
		data = x;
		next = null;
	}
}
