//Puzzle part 2. This part has the function for reversing each row

#include <stdio.h>
#include <stdlib.h>

int puzzle[30][30];

//reverse the rows of the array
void reverse(int array[30][30]){
	//go through every row, and then RIGHT TO LEFT on each row
	//Set up systematic array run-thorugh
	int x = 0; int y = 0;
	//for every row element
	for(x = 0; x < 30; x++){
		//for every col element
		for(y = 0; y < 15; y++){
			int a = array[x][y];
			array[x][y] = array[x][29-y];
			array[x][29-y] = a;
		}
	}
}