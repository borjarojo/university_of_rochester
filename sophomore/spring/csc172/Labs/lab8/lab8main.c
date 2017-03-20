#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
typedef int BOOLEAN;

struct Node {
	int value;
	struct Node *next;
};

void insert(int x, struct Node **pl){
	if((*pl) == NULL){
		(*pl) = malloc(sizeof(struct Node));
		(*pl)->value = x;
		(*pl)->next = NULL;
	}
	else{
		insert(x, &((*pl)->next));
	}


}

void printList(struct Node *pl){
	printf("value = %d\n", ((*pl).value));
	if((*pl).next){
		printList((*pl).next);
	}
}

BOOLEAN lookup(int x, struct Node *pl){
	if(x == (*pl).value){
		return TRUE;
	}
	else if((*pl).next != NULL){
		return lookup(x, (*pl).next);
	}
	else{
		return FALSE;
	}
}

void delete(int x, struct Node **pl){

	if(x == (*pl)->value){
		(*pl) = (*pl)->next;
	}
	else{
		delete(x, &((*pl)->next));
	}
}

int main(){
	struct Node **head;

	printf("Inserting values...\n\n");
	int i = 0;
	for(i = 1; i < 20; i += 2){
		insert(i, &(*head));
	}
	printf("Printing list:\n");
	printList(*head);
	
	printf("\nPrinting lookup():\n");
	for (i = 0; i < 20; i++)
		printf("%d %s FOUND\n",i,(lookup(i, *head) ? "": "NOT"));

	printf("\nCurrent List:\n");
	printList(*head);

	printf("\nDeleteing 3:\n");
	delete(3, &(*head));
	printList(*head);

	printf("\nDeleteing 9:\n");
	delete(9, &(*head));
	printList(*head);

	printf("\nDeleteing 17:\n");
	delete(17, &(*head));
	printList(*head);

	printf("\nDeleteing 1:\n");
	delete(1, &(*head));
	printList(*head);

	printf("\nDeleteing 19:\n");
	delete(19, &(*head));
	printList(*head);

}
