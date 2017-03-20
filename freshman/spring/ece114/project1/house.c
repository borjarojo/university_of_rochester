#include <stdio.h>
#include <math.h>

//Draw N Characters function
void DrawNCharacters(int a, char c)
{
	int x;
	for(x = 0; x < a; x++){
		printf("%c", c);
	}
}


void main()
{
	//instantiate variables
	char house;
	char symbol;
	int window = 0;
	int run = 1;
	int windowRun = 1;
	int windowCheck = 1;
	
	//Print purpose of program
		printf("This program draws houses depending on some parameters you enter\n----------------------------------------------------------------\n");
	
	//continue to loop the program as long as run = 1
	while(run == 1){
	
		//Ask user for house info
			
			printf("Do you want a big or small house ? (Q: quit b: big house S: small house)  ?\n");
			
			//retieve input and display input
			scanf("%c", &house);
		
			//Check house input
			while(!(house == 66 || house == 98 || house == 83 || house == 115)){
				
				//imediately quit check if response is Q or q
				if(house == 81 || house == 113){
				printf("QUITting the program ... Bye ...");
				break;
				}
				
				if(house != 13){
					printf("Invalid response \"%c\" ... Try again \n", house);
				
					printf("Do you want a big or small house ? (Q: quit b: big house S: small house)  ?\n");
					scanf("%c", &house);
				}
				
			}
			
			//imediately quit program if response is Q or q
			if(house == 81 || house == 113){
				break;
			}
			
		//Ask user for what character they want to use to draw the house
			
			printf("Which character do you want to use for drawing ? DASH(-), STAR(*), or DOT (.) ?\n");
			
			//retrieve and display input
			scanf("%c", &symbol); 
			
			//Check input
			while(!(symbol == 45 || symbol == 42 || symbol == 46)){
				printf("Invalid character '%c' ... Try again ...\n", symbol);
				printf("Which character do you want to use for drawing ? DASH(-), STAR(*), or DOT (.) ?\n");
				scanf("%c", &symbol);
			}
			
			//print confirmation
			printf("Using %s to draw the house", (symbol == 45 ? "DASH (-)" : (symbol == 42 ? "STAR (*)" : "DOT (.)")));
	
		//Ask user for number of windows
		
			printf("How many windows do you want in your house (0-2 for Small, 0-4 for Big houses) ?");
			
			//retrieve and display info
			scanf("%d", &window);
			
			//Check input
			
				//if statement to concider big or small
				
				if(house == 66 || house == 98){
					//Run check for negative numbers and for exceeding 4 windows
					while(windowCheck == 1){
					
						while(window < 0){
							printf("Number of windows cannot be negative ... Try again ...");
							printf("How many windows do you want in your house (0-2 for Small, 0-4 for Big houses) ?");
							scanf("%d", &window);
						}
						
						while(window > 4){
							printf("Number of windows cannot exceed 4 ... Try again ...");
							printf("How many windows do you want in your house (0-2 for Small, 0-4 for Big houses) ?");
							scanf("%d", &window); printf(" %d\n", window);
						}
						
						if((window >= 0) && (window <= 4)) break;
					}
					
				}else{
					//Run check for negative numbers and for exceeding 2 windows
					while(windowCheck == 1){
					
						while(window < 0){
							printf("Number of windows cannot be negative ... Try again ...");
							printf("How many windows do you want in your house (0-2 for Small, 0-4 for Big houses) ?");
							scanf("%d", &window); printf(" %d\n", window);
						}
						
						while(window > 2){
							printf("Small houses cannot have more than two windows ... Try again ...");
							printf("How many windows do you want in your house (0-2 for Small, 0-4 for Big houses) ?");
							scanf("%d", &window); printf(" %d\n", window);
						}
						
						if((window >= 0) && (window <= 2)) break;
					}
					
				}
		
		//Draw the house
		
			//if statement for big or small house
			if((house) == 66 || (house == 98)){
				
				//check for the character used
				if(symbol == 45){
					//check window count
					switch(window){
						case 0 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 45); printf("\n");
							//body
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
								DrawNCharacters(60, 45);
							//print message
								printf("\nBIG HOUSE WITH NO WINDOWS\n\n\n");
								break;
						case 1 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 45); printf("\n");
							//body
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(27, 45); DrawNCharacters(6, 32); DrawNCharacters(27, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 1 WINDOW\n\n\n");
								break;
						case 2 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 45); printf("\n");
							//body
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n"); 
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n"); 
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n"); 
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n"); 
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n"); 
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n");
								DrawNCharacters(21, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(21, 45); printf("\n"); 
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 2 WINDOWS\n\n\n");
								break;
						case 3 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 45); printf("\n");
							//body
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(15, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(15, 45); printf("\n"); 
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 3 WINDOWS\n\n\n");
								break;
						case 4 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 45); printf("\n");
							//body
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(9, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(9, 45); printf("\n"); 
								DrawNCharacters(60, 45); printf("\n");
								DrawNCharacters(60, 45); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 4 WINDOWS\n\n\n");
								break;
					}
				}
				
				if(symbol == 42){
					//check window count
					switch(window){
						case 0 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 42); printf("\n");
							//body
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
								DrawNCharacters(60, 42);
							//print message
								printf("\nBIG HOUSE WITH NO WINDOWS\n\n\n");
								break;
						case 1 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 42); printf("\n");
							//body
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(27, 42); DrawNCharacters(6, 32); DrawNCharacters(27, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 1 WINDOW\n\n\n");
								break;
						case 2 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 42); printf("\n");
							//body
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(21, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(21, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 2 WINDOWS\n\n\n");
								break;
						case 3 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 42); printf("\n");
							//body
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(15, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(15, 42); printf("\n"); 
								DrawNCharacters(60, 42); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 3 WINDOWS\n\n\n");
								break;
						case 4 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 42); printf("\n");
							//body
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(9, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(9, 42); printf("\n"); 
								DrawNCharacters(60, 42); printf("\n");
								DrawNCharacters(60, 42); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 4 WINDOWS\n\n\n");
								break;
					}
				}
				
				if(symbol == 46){
					//check window count
					switch(window){
						case 0 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 46); printf("\n");
							//body
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
								DrawNCharacters(60, 46);
							//print message
								printf("\nBIG HOUSE WITH NO WINDOWS\n\n\n");
								break;
						case 1 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 46); printf("\n");
							//body
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(27, 46); DrawNCharacters(6, 32); DrawNCharacters(27, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 1 WINDOW\n\n\n");
								break;
						case 2 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 46); printf("\n");
							//body
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(21, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(21, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 2 WINDOWS\n\n\n");
								break;
						case 3 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 46); printf("\n");
							//body
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(15, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(15, 46); printf("\n"); 
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 3 WINDOWS\n\n\n");
								break;
						case 4 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(42, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(48, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(54, 46); printf("\n");
							//body
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(9, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(9, 46); printf("\n"); 
								DrawNCharacters(60, 46); printf("\n");
								DrawNCharacters(60, 46); printf("\n");
							//print message
								printf("\nBIG HOUSE WITH 4 WINDOWS\n\n\n");
								break;
					}
				}
				
			}else{
			
				//check for the character used
				if(symbol == 45){
					//check window count
					switch(window){
						case 0 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 45); printf("\n");
							//body
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								
							//print message
								printf("\nSMALL HOUSE WITH NO WINDOWS\n\n\n");
								break;
						case 1 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 45); printf("\n");
							//body
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(12, 45); DrawNCharacters(6, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(12, 45); DrawNCharacters(6, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(12, 45); DrawNCharacters(6, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(12, 45); DrawNCharacters(6, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(12, 45); DrawNCharacters(6, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(12, 45); DrawNCharacters(6, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
								
							//print message
								printf("\nSMALL HOUSE WITH 1 WINDOW\n\n\n");
								break;
						case 2 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 45); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 45); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 45); printf("\n");
							//body
								DrawNCharacters(30, 45); printf("\n");
								DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); printf("\n");
								DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); printf("\n");
								DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); printf("\n");
								DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); printf("\n");
								DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); printf("\n");
								DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); DrawNCharacters(6, 32); DrawNCharacters(6, 45); printf("\n");
								DrawNCharacters(30, 45); printf("\n");
							//print message 
								printf("\nSMALL HOUSE WITH 2 WINDOWs\n\n\n");
								break;
					}
				}
				
				if(symbol == 42){
					//check window count
					switch(window){
						case 0 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 42); printf("\n");
							//body
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								
							//print message
								printf("\nSMALL HOUSE WITH NO WINDOWS\n\n\n");
								break;
						case 1 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 42); printf("\n");
							//body
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(12, 42); DrawNCharacters(6, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(12, 42); DrawNCharacters(6, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(12, 42); DrawNCharacters(6, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(12, 42); DrawNCharacters(6, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(12, 42); DrawNCharacters(6, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(12, 42); DrawNCharacters(6, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
								
							//print message
								printf("\nSMALL HOUSE WITH 1 WINDOW\n\n\n");
								break;
						case 2 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 42); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 42); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 42); printf("\n");
							//body
								DrawNCharacters(30, 42); printf("\n");
								DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); printf("\n");
								DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); printf("\n");
								DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); printf("\n");
								DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); printf("\n");
								DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); printf("\n");
								DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); DrawNCharacters(6, 32); DrawNCharacters(6, 42); printf("\n");
								DrawNCharacters(30, 42); printf("\n");
							//print message 
								printf("\nSMALL HOUSE WITH 2 WINDOWs\n\n\n");
								break;
					}
				}
				
				if(symbol == 46){
					//check window count
					switch(window){
						case 0 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 46); printf("\n");
							//body
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								
							//print message
								printf("\nSMALL HOUSE WITH NO WINDOWS\n\n\n");
								break;
						case 1 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 46); printf("\n");
							//body
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(12, 46); DrawNCharacters(6, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(12, 46); DrawNCharacters(6, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(12, 46); DrawNCharacters(6, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(12, 46); DrawNCharacters(6, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(12, 46); DrawNCharacters(6, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(12, 46); DrawNCharacters(6, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
								
							//print message
								printf("\nSMALL HOUSE WITH 1 WINDOW\n\n\n");
								break;
						case 2 :
							//roof
								DrawNCharacters(9, 32); DrawNCharacters(12, 46); printf("\n");
								DrawNCharacters(6, 32); DrawNCharacters(18, 46); printf("\n");
								DrawNCharacters(3, 32); DrawNCharacters(24, 46); printf("\n");
							//body
								DrawNCharacters(30, 46); printf("\n");
								DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); printf("\n");
								DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); printf("\n");
								DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); printf("\n");
								DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); printf("\n");
								DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); printf("\n");
								DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); DrawNCharacters(6, 32); DrawNCharacters(6, 46); printf("\n");
								DrawNCharacters(30, 46); printf("\n");
							//print message 
								printf("\nSMALL HOUSE WITH 1 WINDOW\n\n\n");
								break;
					}
				}
				
			}
				
	}

}