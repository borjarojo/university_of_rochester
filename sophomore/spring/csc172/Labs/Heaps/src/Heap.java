/*
 * Author: Borja Rojo
 * Partner: Daniel Saltz
 * 
 */
public class Heap implements HeapInterface {
	
	private static final int DEFAULT_SIZE = 10;
	public int size;
	public Comparable[] heap;
	
	public Heap(){
		size = DEFAULT_SIZE;
		heap = new Comparable[DEFAULT_SIZE];
	}
	
	public Heap(int s){
		size = s;
		heap = new Comparable[s];
	}
	
	public Heap(Comparable[] c){
		size = c.length;
		heap = c;
		heap = heapify(heap);
	}

	private Comparable[] heapify(Comparable[] heap2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Comparable item) {
		// TODO Auto-generated method stub
		for(int i = 1; i <= heap.length; i++){
			if(i == heap.length){	//before trying to access a nonexsistent index, expand
				expand();
			}
			if(heap[i] == null){
				heap[i] = item;
				break;
			}
		}
		
		bubbleUp();
		
	}
	
	//bubbles up last element in the heap
	private void bubbleUp(){
		//get index of last item
		int item = 0;
		for(int i = 1; i < heap.length; i++){
			if(heap[i] == null){
				item = i;
				item--;
				break;
			}
		}
		
		int rent = 0;
		Comparable temp = heap[item];
		
		while(true){
			//get index of parent
			if((item%2) == 0){	//if even
				rent = item/2;
			}
			else{
				rent = (item - 1)/2;
			}
			
			if(rent == 0){
				break;
			}
			
			
			//check to see if they should switch
			if(heap[item].compareTo(heap[rent]) == -1){	//if item is less than parent
				heap[item] = heap[rent];
				heap[rent] = temp;
				item = rent;
			}
			else{										//if not, it's done, so break
				break;
			}
		}
	}

	private void expand(){
		Comparable[] newHeap = new Comparable[size*2 + 1];
		
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		
		heap = newHeap;
		size = newHeap.length;
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for(int i = 1; i < size; i++){
			 if(heap[i] != null){
				 return false;
			 }
		}
		return true;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public Comparable deleteMin() {
		// TODO Auto-generated method stub
		Comparable min = heap[1];
		heap[1] = null;
		
		bubbleDown();
		
		return min;
	}
	
	//assumes a heap that is empty at the top
	private void bubbleDown(){
		// last
		int bubble = 1;
		int leftChild = bubble*2;
		int rightChild = bubble*2 + 1;
		Comparable last = heap[1];
		
		for(int i = 1; i <= heap.length; i++){
			if(i == heap.length){
				i = i - 1;
				last = heap[i];
				break;
			}
			if(heap[i] == null){
				i = i - 1;
				last = heap[i];
				break;
			}
		}
		
		while(true){
			//check which child is smaller
			if((leftChild >= size || rightChild >=size) || heap[leftChild] == null || heap[rightChild] == null){
				break;
			}
			if(heap[leftChild].compareTo(heap[rightChild]) == -1){	//if left is smaller
				heap[bubble] = heap[leftChild];
				heap[leftChild] = null;
				bubble = leftChild;
				
			}
			else if(heap[leftChild].compareTo(heap[rightChild]) == 1){	//if right is smaller
				heap[bubble] = heap[rightChild];
				heap[rightChild] = null;
				bubble = rightChild;
			}
			else{	//if equal, go left
				heap[bubble] = heap[leftChild];
				heap[leftChild] = null;
				bubble = leftChild;
			}
			leftChild = bubble*2;
			rightChild = bubble*2 + 1;
		}
		
		if(leftChild >= size || heap[leftChild] == null){
			if(leftChild >= size ){
				heap[bubble] = last;
			}
		}
		else{
			heap[bubble] = heap[leftChild];
			heap[leftChild] = null;
			bubble = leftChild;
			heap[bubble] = last;
		}
		
		
	}
	
	public void printHeap(){
		String toPrint = "";
		for(int i = 1; i < heap.length; i++){
			if(heap[i] != null){
				toPrint = toPrint + heap[i] + " ";
			}
		}
		System.out.println(toPrint);
	}
}
