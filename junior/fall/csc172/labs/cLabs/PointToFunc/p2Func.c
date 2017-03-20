#include <stdio.h>
#define MAX 10

int myrand();
void bubble_sort();
int gtcmp(int x, int y);
int ltcmp(int x, int y);

int a[MAX];

int main(int argc, char const *argv[])
{
	int i,t,x,y;

	printf("---This program exibits the use of pointers to functions---\n\n");

	//Fill array
	printf("Filling array...\n");
	printf("Printing array...\n");
	for(int i = 0; i < MAX; i++)
	{
		a[i] = myrand();
		printf("%d\n", a[i]);
	}

	printf("\nSorting with bubble_sort(MAX, a, gtcmp)...\n");
	bubble_sort(MAX, a, gtcmp);

	printf("Printing array...\n");
	for(int i = 0; i < MAX; i++)
	{
		printf("%d\n", a[i]);
	}

	printf("\nSorting with bubble_sort(MAX, a, ltcmp)...\n");
	bubble_sort(MAX, a, ltcmp);

	printf("Printing array...\n");
	for(int i = 0; i < MAX; i++)
	{
		printf("%d\n", a[i]);
	}

	return 0;
}

int rand_seed = 10;
int myrand()
{
	rand_seed = rand_seed * 1103515245 + 12345;
	return (rand_seed / 65536) % 32768;
}

void bubble_sort(int m, int a[], int (*comp)(int x, int y))
{
	int x,y,t;
	for(int x = 0; x < m - 1; x++)
	{
		for(int y = 0; y < m - x - 1; y++)
		{
			if((*comp)(a[y], a[y + 1]))
			{
				t = a[y];
				a[y] = a[y + 1];
				a[y + 1] = t;
			}
		}
	}
}

int gtcmp(int x, int y)
{
	if(x > y)
		return 1;
	return 0;
}

int ltcmp(int x, int y)
{
	if(x < y)
		return 1;
	return 0;
}