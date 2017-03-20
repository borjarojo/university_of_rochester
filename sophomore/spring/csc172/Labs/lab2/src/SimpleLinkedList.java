/*
 * Borja Rojo
 * 
 * Due February 1, 2015
 * 
 * CSC 172
 * 
 * Lab 3
 */
public interface SimpleLinkedList<AnyType> {
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
}
