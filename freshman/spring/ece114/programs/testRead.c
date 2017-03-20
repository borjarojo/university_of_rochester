//This file tests the reading of cities.txt

#include <stdio.h>
#include <stdlib.h>

int main(){
	char *city;
	char *state;
	
	FILE *cities;
	cities = fopen("cities.txt", "r");

	if(cities == NULL){
		printf("Sorry!");
	}else{
		fscanf(cities, "%s,%s", city, state);
		printf("%s %s\n", city, state);
	}

}