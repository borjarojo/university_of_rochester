/*
Borja Rojo
Partner: Daniel Saltz
*/

#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
typedef int BOOLEAN;

struct Node
{
	int value;
	struct Node *next;
};

//This takes an int to insert and a pointer to a list, which is itself a pointer to a node
void insert(int x, struct Node **pL)
{
	if((*pL) == NULL)
	{
		(*pL) = malloc(sizeof(struct Node));
		(*pL)->value = x;
		(*pL)->next = NULL;
	}
	else
	{
		insert(x, &((*pL)->next));
	}
}

//printList takes a pointer to a Node and prints its chain
void printList(struct Node * L)
{
	printf("%i\n", (*L).value);
	if((*L).next == NULL)
		return;
	printList((*L).next);
}

//lookup takes an int and a pointer to a Node and 
//returns TRUE or FALSE if the int is contained or
//not contained in the list
BOOLEAN lookup(int x, struct Node * L)
{
	if((*L).value == x)
	{
		return TRUE;
	}
	if((*L).next == NULL)
	{
		return FALSE;
	}
	return lookup(x, (*L).next);
}

void delete(int x, struct Node **pL)
{
	if((*pL)->value == x)
	{
		(*pL) = (*pL)->next;
	}
	else if((*pL)->next == NULL)
	{
		return;
	}
	delete(x, &((*pL)->next));
}

int main(int argc, char const *argv[])
{
	//Create my Node pointer
	printf("Creating Node pointer...\n");
	struct Node *L;

	//Insert some values into my list
	printf("Inserting values into list...\n");
	for(int i = 1; i < 20; i += 2)
	{
		insert(i, &L);
	}

	//Print the list
	printf("Print the list...\n");
	printList(L);

	//Checking lookup()
	printf("Testing lookup()...\n");
	for(int i = 0; i < 20; i++)
	{
		printf("%d %s FOUND\n",i,((lookup(i,L) == TRUE) ? "" : "NOT"));
	}

	//Deletion
	printf("Deleting...\n");
	for(int i = 0; i < 20; i += 3)
	{
		delete(i,&L);
	}

	//Print final list
	printf("Printing final list...\n");
	printList(L);
}