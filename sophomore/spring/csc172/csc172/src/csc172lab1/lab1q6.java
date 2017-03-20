package csc172lab1;

import java.util.Comparator;

public class lab1q6 {

	public static <AnyType>
	AnyType findMax(AnyType [] arr, Comparator<? super AnyType> cmp){
		int maxIndex = 0;

		for(int i = 1; i < arr.length; i++){
			if(cmp.compare(arr[1],  arr[maxIndex]) > 0)
				maxIndex = i;
		}

		return arr[maxIndex];
	}


	public static void main(String[]args){
		String [] arr = {"ZEBRA", "alligator", "crocodile"};
		Character [] arr1 = {'z', 'a', 'd', 't', 'y', 'l'};
		System.out.println(findMax(arr, new CaseInsensitiveCompare()));
	}
}

