#include <stdio.h>
#include <stdlib.h>

int puzzle[30][30];
char action = "";
int run = 1;


//Function that returns a random 30x30 array
int[][] makePuzzle(){
	//Create array
	int puzzleMake[30][30];
	int x = 0;
	int y = 0;
	//For loop to fill array
	for(x = 0; x < 30; x++){
		for(y = 0; y < 30; y++){
			//Assign elements of the row to a random # between 0 and 9, % 10 is a filter
			puzzleMake[x][y] = (rand() % 10);
		}
	}

	return puzzleMake;
}

//Function to display a 2d array
void displayPuzzle(int[][] array){
	int x = 0;
	int y = 0;
	//Run through every row
	for(x = 0; x < 30; x++){
		//Run through each row element
		for(y = 0; y < 30; y++){
			//Print every element
			print(array[x][y] + " ");
		}
		//Line down every time a row is complete
		print("\n");
	}
}

//Function for reversing the puzzle
int[][] reversePuzzle(){
	//allocate space for a reverse copy of the puzzle
	int puzzleReverse[30][30];
	//Copy the reverse or each row. 
	//Go through each row ascending, but decending for each column element
	int x = 0;
	int y = 0;
	if(x = 0; x < 30; x++){
		//decending
		if(y = 29; y >= 0; y--){
			puzzleReverse[x][y] = puzzle[x][29-y]; //Puzzle is accessed forward
		}
	}

	return puzzleReverse;
}

//Functionto prompt for input that returns a char
char actionPrompt(){
	char input = "";
	print("(M) Make (D) Display (R) Reverse (F) Find (S) Sum-Match (Q) Quit || Action:");
	scanf("%c", &input); print("\n");
	return input;
}

//Run corresponding funtion with corresponding input value
void actionEvaluate(){
	//Evaluate M or m
	if((action == 77)||(action == 109)){
		puzzle = makePuzzle();
	}
	//Evaluate D or d
	if((action == 68)||(action == 100)){
		displayPuzzle();
	}
	//evaluate R or r
	if((action == 82)||(action == 114)){
		puzzle = puzzleReverse();
	}
	//evaluate F or f
	if((action == 70)||(action == 102)){

	}
	//evaluate S or s
	if((action == 83)||(action == 114)){

	}
	//Evaluate Q or q
	if((action == 81)||(action == 113)){
		run = 0;
	}
}

void main(){
	print("Welcome to PuzzleMaster 3000!\n\n");
	print("Make: Make a new puzzle\nDisplay: Display current puzzle\n
		Reverse: Mirror puzzle\nFind: \nSum-Match: \n Quit: Quits program\n\n");
	while(run){
		action = actionPrompt();
		actionEvaluate();
	}
}