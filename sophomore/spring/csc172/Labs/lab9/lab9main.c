#include <stdio.h>
#include <stdlib.h>

#define ARRAYSIZE 10

//Global Variables
int myArray[ARRAYSIZE];

//Interface
void bubble_sort(int m, int a[], int (*comp)(int x, int y));
int gTCmp(int x, int y);
int lTCmp(int x, int y);

int main(int argc, char *argv[])
{
	int i = 0;

	//Fill myArray with random ints
	printf("Creating and printing Array\n");
	for(i = 0; i < ARRAYSIZE; i++){
		myArray[i] = rand() % 32767;			//using rand() from stdlib for simplicity, modulo for max int size
		printf("%d\n", myArray[i]);
	}

	//gTCmp test
	printf("\n");
	printf("Sorting and printing Array with gTCmp:\n");
	bubble_sort(ARRAYSIZE, myArray, gTCmp);
	for(i = 0; i < ARRAYSIZE; i++){			
		printf("%d\n", myArray[i]);
	}


	//lTCmp test
	printf("\n");
	printf("Sorting and printing Array with lTCmp:\n");
	bubble_sort(ARRAYSIZE, myArray, lTCmp);
	for(i = 0; i < ARRAYSIZE; i++){			
		printf("%d\n", myArray[i]);
	}



	return 0;
}

void bubble_sort(int m, int a[], int (*comp)(int x, int y)){
	int i, j, tmp;

	for(i = 0; i < m-1; i++){
		for(j = 0; j < m-i-1; j++){

			if((*comp)(a[j], a[j+1])){
				tmp = a[j];
				a[j] = a[j+1];
				a[j+1] = tmp;
			}
		}
	}
}

int gTCmp(int x, int y){
	if(x > y){
		return 1;
	}
	else{
		return 0;
	}
}

int lTCmp(int x, int y){
	if(x < y){
		return 1;
	}
	else{
		return 0;
	}
}