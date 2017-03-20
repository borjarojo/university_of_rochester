/*
 * Author:Borja Rojo
 * Partner: Daniel Saltz
 */
public interface Queue<T> {
	public boolean isEmpty();
	public void enqueue(T x);
	public T dequeue();
	public T peek();
}
