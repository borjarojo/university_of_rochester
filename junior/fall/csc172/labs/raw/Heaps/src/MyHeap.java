import java.lang.reflect.Array;


public class MyHeap<T extends Comparable<T>> implements Heap<T>{
	
	private T[] heap;
	private int size;
	private int defaultCapacity;
	
	//Constuctors
	
	//Parameterless with default capacity 10
	public MyHeap(){
		defaultCapacity = 10;
		heap = (T[]) new Comparable[defaultCapacity];
	}
	
	//Takes an int to set the default capacity
	public MyHeap(int dCap){
		defaultCapacity = dCap;
		heap = (T[]) new Comparable[defaultCapacity];
	}

	//Heapify constructor, assumes full array
	public MyHeap(T[] items){
		size = items.length;
		heap = (T[]) new Comparable[(size + 2) * 11 / 10];
		
		int i = 1;
		for(T item : items){
			heap[i++] = item;
		}
		
		heapify();
	}
	
	private void heapify() {
		for( int i = size / 2; i > 0; i-- )
			bubbleDown(i);
	}

	public void insert(T item) {
		if(heap.length == (size +1))	//if full
			expand();					//then expand
		bubbleUp(item);					//bubble up the passed item
	}
	
	//bubbleUp() helper method
	//ADDS TO SIZE IN METHOD
	private void bubbleUp(T item){
		int hole = ++size;	//set the hole and add 1 to the size
		
		//Set top to item, while item is less than parent, move the hole up
		//and set the hole equal to the parent
		for(heap[0] = item; item.compareTo(heap[hole /2]) < 0; hole /= 2){
			heap[hole] = heap[hole / 2];
		}
		
		//when item is no longer less than the parent, set the hole to the item
		heap[hole] = item;
	}
	
	//helper method that expands the heap when called
	private void expand(){
		//Copy the old array into a new array of double the size
		//and make the old array, the new array
		T[] newHeap = (T[]) new Comparable[heap.length * 2];
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}
		
	//printHeap() helper method
 	public void printHeap(){
		int i = 1;
		//while heap[i] is not null
		while(heap[i] != null){
			//print the element in heap and add 1 to i
			System.out.println(heap[i++]);
		}
	}

	//Returns true is size is 0, false otherwise
	public boolean isEmpty() {
		if(size == 0){		//if size is 0
			return true;	//then return true
		}
		return false;		//(size is not 0) return false
	}
	
	//Returns the size of the heap
	public int size() {
		return size;	//return the dedicated int size
	}

	public T deleteMin() {
		T minItem = heap[1];
		heap[1] = heap[size--];
		bubbleDown(1);
		
		return minItem;
	}
	
	public void bubbleDown(int hole){
		int child;
		T tmp = heap[hole];
		
		for( ; hole * 2 <= size; hole = child){
			child = hole * 2;
			if(child != size && heap[child + 1].compareTo(heap[child]) < 0){
				child++;
			}
			if(heap[child].compareTo(tmp) < 0){
				heap[hole] = heap[child];
			}else{
				break;
			}
		}
		heap[hole] = tmp;
	}
}
