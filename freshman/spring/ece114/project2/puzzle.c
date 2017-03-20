/*Project 2 ECE 114

This program creates a "puzzle". It is a 30x30 grid of numbers
that will have several kinds of operands done upon it. This
includes creating it, as well as other new ones, displaying it
and changes like reversing it.
*/

//Global stuff

#include <stdio.h>
#include <stdlib.h>

int puzzle[30][30]; //Global puzzle 2D array

//Make function. It will make the values of the array passed in randomized
void make(){
	//Set up systematic array run-thorugh
	int x = 0; int y = 0;
	//for every row element
	for(x = 0; x < 30; x++){
		//for every col element
		for(y = 0; y < 30; y++){
			puzzle[x][y] = rand() % 10;
		}
	}

	printf("A new puzzle has been made\n\n");
}


//Display function. Prints ints with spaces between each int and line break every 30
void display(){
	//Set up systematic array run-thorugh
	int x = 0; int y = 0;
	//for every row element
	for(x = 0; x < 30; x++){
		//for every col element
		for(y = 0; y < 30; y++){
			printf("%d ", puzzle[x][y]);
		}
		printf("\n");
	}
}

//reverse the rows of the array
void reverse(){
	//go through every row, and then RIGHT TO LEFT on each row
	//Set up systematic array run-thorugh
	int x = 0; int y = 0;
	//for every row element
	for(x = 0; x < 30; x++){
		//for every col element
		for(y = 0; y < 15; y++){
			int a = puzzle[x][y];
			puzzle[x][y] = puzzle[x][29-y];
			puzzle[x][29-y] = a;
		}
	}

	printf("Puzzle reversal has been completed\n");
}

//conversion function for int to corresponding char
char convert(int a){
	char con;
	switch(a){
		case 0: con = '0';
				break;
		case 1: con = '1';
				break;
		case 2: con = '2';
				break;
		case 3: con = '3';
				break;
		case 4: con = '4';
				break;
		case 5: con = '5';
				break;
		case 6: con = '6';
				break;
		case 7: con = '7';
				break;
		case 8: con = '8';
				break;
		case 9: con = '9';
				break;
		default: con = '*';

	}

	return con;
}

//Find function
void find(){
	int number = 0;
	//Querry and check
	int checkFind = 1;

	while(checkFind){
		printf("For which number (10-9999) ? ");
		scanf("%d", &number); printf("\n");

		if((number < 10) || (number > 9999)){
			printf("Invalid input...Please enter a new number\n");
		}else{
			checkFind = 0;
		}
	}

	//convert input to char array yo evaluate against upcomming char puzzle copy
		char charNum[4];
		if(number>999){
			charNum[0] = convert(((number)- (number%1000))/1000);
			charNum[1] = convert((((number)- (number%100))%1000)/100);
			charNum[2] = convert(((number%100)-(number%10))/10);
			charNum[3] = convert(number%10);
		}
		if((number < 1000) && (number > 99)){
			charNum[0] = convert((((number)- (number%100))%1000)/100);
			charNum[1] = convert(((number%100)-(number%10))/10);
			charNum[2] = convert(number%10);
			charNum[3] = '*';
		}
		if(number < 99){
			charNum[0] = convert(((number%100)-(number%10))/10);
			charNum[1] = convert(number%10);
			charNum[2] = '*';
			charNum[3] = '*';
		}

	//Create a Char version of the puzzle with the numbers and stars
	//
	char puzzleChar[30][30];

	int x = 0; int y = 0;
	for (x = 0; x < 30; x++){
		for(y = 0; y < 30; y++){
			puzzleChar[x][y] = convert(puzzle[x][y]);
		}
	}
	
	for (x = 0; x < 30; x++){
		for(y = 0; y < 30; y++){
			//Check to see if 4 digits work. jump to next sequance of numbers if it does
			if((y+3 < 29) &&
				(puzzleChar[x][y] == charNum[0]) && 
				(puzzleChar[x][y+1] == charNum[1]) && 
				(puzzleChar[x][y+2] == charNum[2]) &&
				(puzzleChar[x][y+3] == charNum[3])){
				puzzleChar[x][y] = charNum[0];
				puzzleChar[x][y+1] = charNum[1];
				puzzleChar[x][y+2] = charNum[2];
				puzzleChar[x][y+3] = charNum[3];
				y += 3;
			}else{
				//Check to see if 3 digits work. jump to next sequance of numbers if it does
				if((y+2 < 29) &&
					(puzzleChar[x][y] == charNum[0]) && 
					(puzzleChar[x][y+1] == charNum[1]) && 
					(puzzleChar[x][y+2] == charNum[2]) &&
					(charNum[3] == '*')){
					puzzleChar[x][y] = charNum[0];
					puzzleChar[x][y+1] = charNum[1];
					puzzleChar[x][y+2] = charNum[2];
					y += 2;
				}else{
					//Check to see if 2 digits work. jump to next sequance of numbers if it does
					if((y+1 < 29) &&
						(puzzleChar[x][y] == charNum[0]) && 
						(puzzleChar[x][y+1] == charNum[1]) && 
						(charNum[2] == '*') &&
						(charNum[3] == '*')){
						puzzleChar[x][y] = charNum[0];
						puzzleChar[x][y+1] = charNum[1];
						y += 1;
					}else{
						puzzleChar[x][y] = '*';
					}
				}
			}
		}
	}
	

	//Display the puzzleChar
	for(x = 0; x < 30; x++){
		for(y = 0; y < 30; y++){
			printf("%c ", puzzleChar[x][y]);
		}
		printf("\n");
	}
}

//Sum-Match Function
void summatch(){
	char sumDis[30][30];

	//query and check user
	int sum = 0;
	int checkSum = 1;

	while(checkSum){
		printf("Match which sum (1-20) ? ");
		scanf("%d", &sum); printf("\n");

		if((sum < 1) || (sum > 20)){
			printf("Invalid input...Please enter a new number\n");
		}else{
			checkSum = 0;
		}
	}

	//systematicall run through every element of puzzle
		//Variables needed to eep track of what's found
		int two[100][2]; int twoCount = -1; int sumDisTwo[30];
		int three[100][3]; int threeCount = -1; int sumDisThree[30];
		int four[100][4]; int fourCount = -1;  int sumDisFour[30];
		int five[100][5]; int fiveCount = -1; int sumDisFive[30];
		int six[100][6]; int sixCount = -1; int sumDisSix[30];

		//x and y are used to run through each element. 
		//i represents the sequence type being searched for
		//at each element. j is used to run through the 
		//elements and save them in the allocated arrays
		int x = 0; int y = 0; int i = 0; int j = 0;

		for(x = 0; x < 30; x++){
			for(y = 0; y < 30; y++){
				sumDis[x][y] = 42;
			}
		}
		
		for(x = 0; x < 30; x++){
			for(y = 0; y < 30; y++){
				//Used to see if the sum of the elements is equal 
				//to the sum asked by the user
				int sumCheck = 0;

				//squence check
				for(i = 0; i < 6; i++){
						
						sumCheck += puzzle[x][y+i];
						//Check to see if a sum has reach 15. if yes, then i+1 represents the sequence amount
						if((sumCheck == sum) && (i > 0)){
							sumCheck = 0;

							//depending on the sequence type, add to sequence amount
							switch(i){
								case 1: twoCount++;
										sumDisTwo[x]++;
										break;
								case 2: threeCount++;
										sumDisThree[x]++;
										break;
								case 3: fourCount++;
										sumDisFour[x]++;
										break;
								case 4: fiveCount++;
										sumDisFive[x]++;
										break;
								case 5: sixCount++;
										sumDisSix[x]++;
										break;
							}

							//save the entries
							for(j = 0; j <= i; j++){
								int a = puzzle[x][y+j];

								switch(i){
									case 1: two[twoCount][j] = a;
											//sumDis[x][y-1] = 91;
											sumDis[x][y+j] = convert(a);
											//sumDis[x][y+j+1] = 93;
											break;
									case 2: three[threeCount][j] = a;
											//sumDis[x][y-1] = 91;
											sumDis[x][y+j] = convert(a);
											//sumDis[x][y+j+1] = 93;
											break;
									case 3: four[fourCount][j] = a;
											//sumDis[x][y-1] = 91;
											sumDis[x][y+j] = convert(a);
											//sumDis[x][y+j+1] = 93;
											break;
									case 4: five[fiveCount][j] = a;
											//sumDis[x][y-1] = 91;
											sumDis[x][y+j] = convert(a);
											//sumDis[x][y+j+1] = 93;
											break;
									case 5: six[sixCount][j] = a;
											//sumDis[x][y-1] = 91;
											sumDis[x][y+j] = convert(a);
											//sumDis[x][y+j+1] = 93;
											break;
								}


							}
							//Make the for loop start at the next element
							y += i;
							break;
						}
				}
			}
		}

		int r = 0; int s = 0;
		//Print out the results
			//two
			if(twoCount+1 == 0){
				printf("No entries with size-2-sequences");
			}else{
				if(twoCount+1 == 1){
					printf("1 entry with size-2-sequences : ");
				}else{
					printf("%d entries with size-2-sequences : ", twoCount+1);
				}
			}
			for(r = 0; r < twoCount+1; r++){
				printf("(%d %d) ", two[r][0], two[r][1]);
			}
			printf("\n");

			//three
			if(threeCount+1 == 0){
				printf("No entries with size-3-sequences");
			}else{
				if(threeCount+1 == 1){
					printf("1 entry with size-3-sequences : ");
				}else{
					printf("%d entries with size-3-sequences : ", threeCount+1);
				}
			}
			for(r = 0; r < threeCount+1; r++){
				printf("(%d %d %d) ", three[r][0], three[r][1], three[r][2]);
			}
			printf("\n");

			//four
			if(fourCount+1 == 0){
				printf("No entries with size-4-sequences");
			}else{
				if(fourCount+1 == 1){
					printf("1 entry with size-4-sequences : ");
				}else{
					printf("%d entries with size-4-sequences : ", fourCount+1);
				}
			}
			for(r = 0; r < fourCount+1; r++){
				printf("(%d %d %d %d) ", four[r][0], four[r][1], four[r][2], four[r][3]);
			}
			printf("\n");

			//five
			if(fiveCount+1 == 0){
				printf("No entries with size-5-sequences");
			}else{
				if(fiveCount+1 == 1){
					printf("1 entry with size-5-sequences : ");
				}else{
					printf("%d entries with size-5-sequences : ", fiveCount+1);
				}
			}
			for(r = 0; r < fiveCount+1; r++){
				printf("(%d %d %d %d %d) ", five[r][0], five[r][1], five[r][2], five[r][3], five [r][4]);
			}
			printf("\n");

			//six
			if(sixCount+1 == 0){
				printf("No entries with size-6-sequences");
			}else{
				if(sixCount+1 == 1){
					printf("1 entry with size-6-sequences : ");
				}else{
					printf("%d entries with size-6-sequences : ", sixCount+1);
				}
			}
			for(r = 0; r < sixCount+1; r++){
				printf("(%d %d %d %d %d %d) ", six[r][0], six[r][1], six[r][2], six[r][3], six[r][4], six[r][5]);
			}
			printf("\n\n");

	//display
	for(x = 0; x < 30; x++){
		for(y = 0; y < 30; y++){
			printf("%c ", sumDis[x][y]);
		}
		printf("\n");
	}

}


int main(){
	//Preliminary info
	printf("\nThis program runs a puzzle of sorts. It allows you to create and manipulate a \ngrid of numbers from 0-10\n\n");
	printf("To start, use the 'Make' command\n");

	char command;
	int run = 1;
	while(run){
		//Query and check user
		int check = 1;
		while(check){
			printf("------------------------------------------------------------------------------\n\n");
			printf("(M) Make (D) Display (R) Reverse (F) Find (S) Sum-Match (Q) Quit Action: ");
			scanf("%s", &command);
			printf("\n");

			if((command != 'm') && (command != 'M') &&
				(command != 'd') && (command != 'D') && 
				(command != 'r') && (command != 'R') &&
				(command != 'f') && (command != 'F') &&
				(command != 's') && (command != 'S') &&
				(command != 'q') && (command != 'Q')){

				printf("Invalid input...Please try again\n");
			}else{
				check = 0;
			}
		}

		//make new puzzle if command is M or m
		if((command == 'm') || (command == 'M')){
			make();
		}
		//Display if d or D
		if((command == 'd') || (command == 'D')){
			display();
			printf("\n");
		}
		//reverse if r or R
		if((command == 'r') || (command == 'R')){
			reverse();
			printf("\n");
		}
		//find consecutive numbers if command is f or F
		if((command == 'f') || (command == 'F')){
			find();
			printf("\n");
		}
		if((command == 's') || (command == 'S')){
			summatch();
			printf("\n");
		}
		//Run if q or Q
		if((command == 'q') || (command == 'Q')){
			printf("GoodBye\n");
			break;
		}
	}

return 0;
}



