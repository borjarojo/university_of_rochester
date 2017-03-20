
public interface StackInterface<AnyType> {
	public void insert(AnyType x);
	public void delete(AnyType x);
	public boolean lookup(AnyType x);
	public boolean isEmpty();
	public void printList();
	
	public void push(AnyType x);
	public AnyType pop();
	public AnyType peek();
}
