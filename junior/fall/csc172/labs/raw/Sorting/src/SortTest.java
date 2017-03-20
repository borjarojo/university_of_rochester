import java.util.Arrays;

public class SortTest{
	private static int count;
	
	public static void main(String[]args){
		for(int x = 0; x < 1; x++){
			long startTime, endTime, elapsedTime;
			int size = 2000;
			
			Integer[] a = new Integer[size];
			Integer[] b = new Integer[size];
			
			for(int i = 0; i < size; i++){
				a[i] = b[i] = new Integer((int) (Math.random() * 100));
			}
			
			
			System.out.println(Arrays.toString(a));
			count = 0;
			startTime = System.currentTimeMillis();
			Arrays.sort(a);
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println(Arrays.toString(a));
			System.out.println("Arrays.sort() took " + count + " moves to sort "
					+ size + " itmes");
			System.out.println("\t in : " + elapsedTime + " millesec");
			
			// Reset count and array
			count = 0;
			for(int i = 0; i < size; i++){
				a[i] = b[i];
			}
		}
	}
	
	public static <T extends Comparable<? super T>>
	void bubblesort(T[] a){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length - 1; j++){
				if(a[j].compareTo(a[j+1]) > 0){
					T tmp = a[j];
					count++;
					a[j] = a[j + 1];
					count++;
					a[j + 1] = tmp;
					count++;
				}
			}
		}
	}
	
	public static <T extends Comparable<? super T>>
	void insertionSort(T[] a){
		int j;
	    for( int p = 1; p < a.length; p++ ){
	        T tmp = a[p];
	        count++;
	        for( j = p; j > 0 && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
	            a[ j ] = a[ j - 1 ];
	        	count++;
	        a[ j ] = tmp;
	        count++;
	    }
	}
	
	//shell sort with hibbard's increments
	public static <T extends Comparable<? super T>>
	void shellSort(T[] a){
		int j;
		int gap = 1;
		
		for(int x = 1; gap < a.length; x++){
			gap = (int) (Math.pow(2, x) + 1);
			for(int i = gap; i < a.length; i++){
				T tmp = a[i];
				count++;
				for(j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap){
					a[j] = a[j - gap];
					count++;
				}
				a[j] = tmp;
				count++;
				
			}
		}
	}
	
	public static <T extends Comparable<? super T>>
	void quickSort(T[] a, int lo, int hi){
		if(lo == hi){
			return;
		}
		int i = lo;				//lowest index
		int j = hi;	//highest index
		T pivot = a[lo + ((hi - lo) / 2)];	//middle number
		
		while(i <= j){	//proceed with algorithm's primary condition
			while(a[i].compareTo(pivot) <= 0){	//while i is less than pivot
				i++;
			}
			while(a[j].compareTo(pivot) >= 0){	//while j is more than pivot
				j--;
			}
			if(i <= j){
				//exchange numbers
				T tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}
		}
		//recursive calls for quickSort
		if(lo < j){
			quickSort(a, lo, j);
		}
		if(i < hi){
			quickSort(a, i, hi);
		}
	}
}