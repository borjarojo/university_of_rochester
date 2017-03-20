/*
 * Author: Borja Rojo
 * University of Rochester
 * CSC 172
 * 
 * Partner: Daniel Saltz
 */

import java.util.Arrays;
import java.util.Arrays;
import java.util.ArrayList;

public class SortTest{
	private static double count;
	
	public static void main(String[]args){
		for (int bob=25000; bob<=100000; bob+=2500)
		{
			for (int arnold=0; arnold<3; arnold++)
			{
				long startTime, endTime, elapsedTime;
				int size = bob;
				
				Integer[] a = new Integer[size];
				Integer[] b = new Integer[size];
				
				for(int i = 0; i < size; i++){
					a[i] = b[i] = new Integer((int) (Math.random() * 100));
				}
				
				//System.out.println(Arrays.toString(a));
				count = 0.0;
				
				startTime = System.currentTimeMillis();
				//bubblesort(a);
				//insertionSort(a);
				//shellSort(a);
				Arrays.sort(a);
				endTime = System.currentTimeMillis();
				elapsedTime = endTime - startTime;
				//System.out.println(Arrays.toString(a));
				//System.out.println("bubblesort took " + count + " moves to sort "
				//		 + size + " items");
				if (arnold<2)
				{System.out.print(elapsedTime + "," + count + ",");}
				if (arnold==2)
				{
					System.out.print(elapsedTime + "," + count);
					System.out.println("");
				}
				
				//reset count and Array
				count = 0;
				for(int i = 0; i < size; i++)
					a[i] = b[i];
			}
		}
		
	}
	
	public static <AnyType extends Comparable<? super AnyType>>
	void bubblesort(AnyType[] a){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length - 1; j++){
				if(a[j].compareTo(a[j + 1]) > 0){
					AnyType tmp = a[j];
					count++;
					a[j] = a[j + 1];
					count++;
					a[j +1] = tmp;
					count++;
				}
			}
		}
	}
	
	public static <AnyType extends Comparable<? super AnyType>>
	void insertionSort(AnyType[] a){
		int j;
		
		for(int p = 1; p < a.length; p++){
			AnyType tmp = a[p];
			count++;
			for(j = p; j > 0 && tmp.compareTo(a[j-1]) < 0; j--){
				a[j] = a[j-1];
				count++;
			}
			a[j] = tmp;
			count++;
		}
	}
	
	public static <AnyType extends Comparable<? super AnyType>>
	void shellSort(AnyType[] a){
		//Generating array with Hibbard's increments
		ArrayList<Integer> gap= new ArrayList<Integer>();
		int k=1;
		while (Math.pow(2, k)-1 < a.length)
		{
			Integer v= (int) (Math.pow(2, k)-1);
			gap.add(0, v);
			k+=1;
			
		}
		int j;
		
		//continues until no more gaps available
		while(gap.size() > 0){ //Hibbard's gap
			int gp=gap.remove(0);//Select the next gap
			for(int i = gp; i < a.length; i++){	//sort with gap
				AnyType tmp = a[i];
				count++;
				for(j = i; j >= gp &&
						tmp.compareTo(a[j - gp]) < 0; j -= gp){
					a[j] = a[j - gp];
					count++;
				}
				a[j] = tmp;
				count++;
			}
		}	
	}
}