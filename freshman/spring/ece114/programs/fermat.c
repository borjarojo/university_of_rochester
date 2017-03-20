/*This program is used to try an disprove Fermat's Conjecture
 *It uses for-loops to run through every scenario
*/

#include <stdio.h>
#include <math.h>

main()
{
	//instanciate the the variables
		unsigned long long a, b, c;
		unsigned int n;

	//run the analysis

		for(n = 3; n < 64; n++){
			for(a = 2; (pow(a, n)) < 2000000000; a++){
				printf("\nCalculating %d^%d + %d^%d = %d^%d", a, n, b, n, c, n);
				for(b = 2; (pow(b, n)) < 2000000000; b++){
					for(c = 2; (pow(c, n)) < 2000000000; c++){
						if((pow(a, n)) + (pow(b, n)) == (pow(b, n))){
							printf("\nFERMATS CONJECTURE IS WRONG!!! a=%d, b=%d, c=%d, n=%d", a, b, c, n);
						}
						print("Calculating...");
					}
				}
			}
		}
	printf("\nAnalysis complete.");
}