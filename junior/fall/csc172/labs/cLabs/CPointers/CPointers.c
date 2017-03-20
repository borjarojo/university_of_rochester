/*
Borja Rojo
Partner: Daniel Saltz
*/
#include <stdio.h>

//Part 2: strcmp
int mystrcmp(char *s, char *t){
	for ( ; *s == *t; s++, t++) 
		if (*s == '\0')
	        return 0;
	return *s - *t;
}

//Part 3: strcat
void mystrcat(char *dest, char *source){
	while(*++dest != '\0')
		;
	while((*dest++ = *source++) != '\0')
		;
}

//Part 4: strcpy
void mystrcpy(char *s, char *dest){
	while((*dest++ = *s++) != '\0')
		;
}

//Part 1: main declaration
int main(int argc, char * argv[]){
	//Part 5: for-loop constrtuction
	for(int i = 1; i < argc; i++){	//i starts at 1 to not include ./CPointers in print

		/*	check to see if the argument is my name or my partner's name
			Uses multiplication to check if either are 0
		*/
		if(mystrcmp(argv[i], "Borja") * mystrcmp(argv[i], "Daniel") == 0){
			char hello[30];				//Create 30 character char array
			mystrcpy("Hello, ", hello);	//Copy "Hello, " to char array
			mystrcat(hello, argv[i]);	//Concatinate command line argument
			printf("%s\n", hello);		//Print concatinated hello array
		}else{
			printf("%s\n", argv[i]);
		}
	}
	//Part 6: int array
	int a[10];							//Declare an array of int at least 10 elements long
	for(int i = 0; i < 10; i++){		
		a[i] = i * i;					//Fill in the array with the square of it's index using array syntax, a[i] = i * i
		printf("%i\n", *(a + i));		//Print out the array using pointer syntax *(a + i)
	}


}