//test
#include <stdio.h>

char decide;

/*
void menu(){
	printf("\n(M) Make (D) Display (R) Reverse (F) Find (S) Sum-Match (Q) Quit Action: ");
	scanf("%c", &decide);

	
	if(!((input == 'M') || (input == 109) || (input == 68 )|| 
			(input == 100) || (input == 82) || (input == 114) || 
			(input == 70) || (input == 102) || (input == 83) || 
			(input == 115) || (input == 81) || (input == 113))){

		printf("Invalid input... please enter a new command\n");
		printf("(M) Make (D) Display (R) Reverse (F) Find (S) Sum-Match (Q) Quit Action: ");
		scanf("%c", &input);
		printf("\n");
	}
	*/

//}

int main(){
	int run = 1;
	while(run){
		printf("\n(M) Make (D) Display (R) Reverse (F) Find (S) Sum-Match (Q) Quit Action: ");
		scanf("%c", &decide);
	}
	return 0;
}