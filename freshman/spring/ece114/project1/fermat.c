#include <stdio.h>
#include <math.h>

//this program checks fermat's postulate

//YOU CAN CHANGE MAX TO ALTER HOW MUCH THE PROGRAM WILL CALCULATE
#define MAX 100000

main()
{
	//tell the user what the program is doing
	
	printf("This program tests Fermat's Last Theorem");
	
	//create variables
	unsigned long long int a, b, c;
	unsigned int n;
	
	for(n = 3; n <=64; n++){
		printf("N increased by 1\n");
		if((pow(a, n) > MAX) || (pow(b, n) > MAX) || (pow(c, n) > MAX)){
			return break;
		}
		for(c = 1; pow(c, n) < MAX; c++){
			printf("C increased by 1\n");
			for(b = 1; pow(b, n) < MAX; b++){
				printf("B increased by 1\n");
				for(a = 1; pow(a, n) < MAX; a++){
					if( (pow(a, n) + pow(b, n) == pow(c, n)){
						printf("FERMATS CONJECTURE IS WRONG !!! a=%d , b=%d , c=%d, n=%d\n");
					}
				}
			
			}
		
		}
	
	}
	
	
}