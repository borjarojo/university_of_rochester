/*
 * Borja Rojo's Custom QueueInterface for Queue
 * 
 * Class: CSC 172
 * Lab: Redo of Queue lab. Much cleaner
 */

public interface QueueInterface<AnyType> {
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
	
	public void enqueue(AnyType x);
	public AnyType dequeue();
	public AnyType peek();
}
