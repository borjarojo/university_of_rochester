/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public interface LinkedList<T> {
	public void insert(T x);
	public void delete(T x);
	public boolean lookup(T x);
	public boolean isEmpty();
	public void printList();
}
