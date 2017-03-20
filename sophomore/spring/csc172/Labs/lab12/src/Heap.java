
public class Heap implements HeapInterface {
	
	public int size;
	public static final int DEFAULT_SIZE = 10;
	
	public Comparable[] heap;
	
	//Constructors
	
		//Default. Creates heap of size DEFAULT_SIZE
		public Heap(){
			size = DEFAULT_SIZE;
			heap = new Comparable[DEFAULT_SIZE];
		}
		//Constructs with the size of the heap as the parameter
		public Heap(int s){
			size = s;
			heap = new Comparable[s];
		}
	
	//Inserts and item into the heap
	public void insert(Comparable item) {
		// TODO Auto-generated method stub
		

	}
	
	//assumes the something has just been added to the end of the list
	//all this does is bubble up the last item in the heap
	private void bubbleUp(){
		//
		int last = 1;
		while(last < heap.length && heap[last] != null){
			last++;
		}
		last--;
		
		int hole = last;
		Comparable item = heap[hole];
		Comparable parent;
		int parentInd;
		
		while(parentInd != ){
			if(hole%2 == 1){
				parent = heap[(hole - 1) /2];
				parentInd = (hole-1)/2;
			}
			else{
				parent = heap[(hole)/2];
				parentInd = (hole)/2;
			}
			
			if(parent.compareTo(item) == -1){	//if parent is less than item, break
				break;
			}
			else{
				heap[hole] = parent;
				heap[parentInd] = item;
			}
		}
		
	}
	
	private void expand(){
		if(heap[heap.length - 1] != null){
			size = size*2 +1;
		}
		
		Comparable[] newHeap = new Comparable[size];
		
		for(int i = 0; i < heap.length; i++){
			newHeap[i] = heap[i];
		}
		
		heap = newHeap;
		
		
	}
	
	//Checks if the heap is empty
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for(int i = 0; i < size; i++){
			if(heap[i] != null){
				return false;
			}
		}
		return true;
	}
	
	//Returns the size of the heap
	public int size() {
		return size;
	}

	public Comparable deleteMin() {
		// TODO Auto-generated method stub
		/*
		 * Like insert method, this bubbleDown()
		 * method should be written in the deleteMin()
		 * method.
		 */
		
		/*
		 * The steps behind this method go as follows:
		 * 1. Save root of tree.
		 * 2. check to see if last item in array fits in 
		 * 		hole by checking if it is smaller than both
		 * 		its children
		 * 3. if not, bubble the smallest child of hole up
		 * 4. repeat 3 and 4 until no more children
		 */
		
		Comparable min = heap[1];
		
		int i = 1;
		
		int lastInd = 1;
		while(lastInd < size && heap[lastInd] != null){
			lastInd++;
		}
		lastInd--;
		
		//Save root and last
		Comparable last = heap[lastInd];
		
		//remove root and last
		heap[1] = null;
		heap[lastInd] = null;
		
		//while a hole has two children
		
		/*these statements use the technique of 
		 *evaluating boolean statements from the
		 *left. this way, I can prevent evaluations 
		 *from happening that may cause errors.
		 */
		
		while(true){
			
			System.out.println("bubble: " + i);
		}
		
		return min;
		
		/*
		while(((2*i) < size && (2*i + 1) < size) &&
				(heap[2*i] != null && heap[2*i +1] != null)){	//while there are two children
			if(last.compareTo(heap[2*i]) == -1 && 
					last.compareTo(heap[2*i + 1]) == -1){	//if last item is less than both children
				heap[i] = last;
				return min;
			}
			else{
				if(heap[2*i].compareTo(heap[2*i +1]) == -1){		//if left child is smaller
					heap[i] = heap[2*i];							//make hole equal to left child
					heap[2*i] = null;								//then make left child a hole
					i = 2*i;										//then make hole index equal to left child index
				}
				else if(heap[2*i +1].compareTo(heap[2*i]) == -1){	//if right child is smaller
					heap[i] = heap[2*i + 1];							//make hole equal to right child
					heap[2*i + 1] = null;								//then make right child a hole
					i = 2*i + 1;										//then make hole index equal to right child index
				}
				else if(heap[2*i].compareTo(heap[2*i + 1]) == 0){	//if they are equal, go with left child
					heap[i] = heap[2*i];							//make hole equal to left child
					heap[2*i] = null;								//then make left child a hole
					i = 2*i;	
				}
			}
			System.out.println("bubble: " + i);
		}
		
		//at this point, the hole has either no or one child
		//a heap fills up left to right, so if there is one child,
		//it is the left child
		if(((2*i) < size && (2*i + 1) < size) &&
				(heap[2*i] == null && heap[2*i +1] == null)){			// if no children, make last item the hole											
			heap[i] = last;
		}
		else if(((2*i) < size && (2*i + 1) < size) &&
				heap[2*i] != null){								//if left is the child
			heap[i] = heap[2*i];
			heap[2*i] = last;
		}
		
		return min;
		*/
	}
	
	private void bubbleDown(int hole){
		
	}

	public void printHeap() {
		// TODO Auto-generated method stub
		int i = 1;
		while(heap[i] != null){
			System.out.println(heap[i]);
			i++;
		}
	}

}
