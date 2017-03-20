#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#define Maxlines 100
#define maxchar 30

char *delimiters = ",\t\n";
char *delimiters2 =" ,\t\n ";


struct City{
	char Name[maxchar];
	unsigned int zipcode;
	char statecode[2];
	unsigned int population;
	unsigned int region;
	struct City *next;	
	};
	struct City *root=NULL;

void sp_remover(char *ptr)
{
	int i=strlen(ptr)-1;
	//printf("%d\n",i);
	while(ptr[i]==' ' || ptr[i]=='\t')	
	{
		//printf("%d\n",i);
		ptr[i]='\0';	
		i--;
	}
}
void to_cap(char *ptr)
{
	int i=0;
	int c;
	while(ptr[i])
	{
		c=toupper(ptr[i]);
		ptr[i]=c;
		i++;
	}
}
void to_lower(char *ptr)
{
	int i=1;
	int c;
	while(ptr[i])
	{
		c=tolower(ptr[i]);
		ptr[i]=c;
		i++;
	}
}
int RegionNO(char *ptr)
{
	int i;
	to_cap(ptr);
	char rgn[][3]={"NE","NW","SE","SW","MW"};
	for(i=0;i<5;i++)
	{
		if(strcmp(ptr,rgn[i])==0)
			return i;			
	}
	return 5;
}			
	
void add(FILE *fi)
{
	char line[100];
	char *ptr;
    struct City *temp;
	
	while(fgets(line, Maxlines, fi)) {
		//printf("%s",line);
        temp = root;
        root = malloc(sizeof(struct City));
			if(root==NULL) printf("Oops there is no memory space in your memory to allocate\n");
			
        root->next = temp;

        ptr = strtok(line, delimiters);	//Name of the city capitalized
		sp_remover(ptr);
		//printf("%s ",ptr);
		strcpy(root->Name,ptr);
			to_cap(root->Name);
			//printf("%s	",root->Name);
		ptr = strtok(NULL, delimiters2);	// state code saved as characters
		sp_remover(ptr);		
			root->statecode[0]=ptr[0];
			root->statecode[1]=ptr[1];
			//printf("%c%c	",root->statecode[0],root->statecode[1]);	
		ptr = strtok(NULL, delimiters2);  //population of the city 
		sp_remover(ptr);
			root->population=atoi(ptr);
			//printf("%d  ",root->population);
        ptr = strtok(NULL, delimiters2);	// region of the city is found in int value 
		sp_remover(ptr);		
			root->region=RegionNO(ptr);
			//printf("%d	%d\n",root->region,root->zipcode);
		ptr = strtok(NULL, delimiters2);    //zipcode of the city 
		sp_remover(ptr);
			root->zipcode=atol(ptr);
	}
}

void read_file()
{
	FILE *fi;
   
	fi = fopen("cities.txt", "r");
	if (!fi) {
		printf("Oops, something wrong with the input file...\n");
		exit(-1);
	}	
	add(fi);
	fclose(fi);
}
	
void print_city()
{
	struct City *ptr = root;
	char rgn[][11]={"NORTH EAST","NORTH WEST","SOUTH EAST","SOUTH WEST","MID WEST","OTHER"};	
	
		printf("Name of city			ZipCode		State		Population	Region\n");
		printf("==========================================================================================\n");
	while(ptr) {
		printf("%-22s		%05d		%c%c		%d		%s\n",ptr->Name,ptr->zipcode,ptr->statecode[0],ptr->statecode[1],ptr->population,rgn[ptr->region]);
		ptr = ptr->next;
	}
		printf("==========================================================================================\n");
}
struct City *free_memory(struct City *rootptr)
{
	struct City *next;
	while(rootptr) {
		next = rootptr->next;
		free(rootptr);
		rootptr = next;
	}
	return rootptr;
}
void MainMenue(){
	printf("\n\t\t\t~~MAIN MENUE~~\nWhat would you like me to do? Search/Delete/Add/List/Advanced\nWrite 'DONE' to stop\n");
}
int searchcity(char *entry)
{
	char rgn[][11]={"NORTH EAST","NORTH WEST","SOUTH EAST","SOUTH WEST","MID WEST","OTHER"};
	struct City *ptr;
	ptr=root;
	char name[maxchar];
	int i=0;
	
	strcpy(name,ptr->Name);
	to_lower(name);	
	while(ptr){
		if(strcmp(ptr->Name,entry)==0){
			i++;
			printf("%d)\n===========================================================================\n",i);
			printf("City Name		:%s\n",ptr->Name);
			printf("Located in State	:%c%c \n",ptr->statecode[0],ptr->statecode[1]);
			printf("Zipcode			:%05d\n",ptr->zipcode);
			printf("Population		:%d\n",ptr->population);
			printf("Region			:%s\n",rgn[ptr->region]);
			printf("===========================================================================\n");
		}
	ptr=ptr->next;
	}
	if(i==0) 
		printf("The city is not in the database\n");
	return i;
}
int searchstate(char *entry,char *state)
{
	char rgn[][11]={"NORTH EAST","NORTH WEST","SOUTH EAST","SOUTH WEST","MIDWEST","OTHER"};
	struct City *ptr;
	ptr=root;
	char name[maxchar];
	strcpy(name,ptr->Name);
	to_lower(name);
	while(ptr){
		if(strcmp(ptr->Name,entry)==0){
			if(ptr->statecode[0]==state[0] && ptr->statecode[1]==state[1]){
				printf("===========================================================================\n");
				printf("City Name		:%s\n",ptr->Name);
				printf("Located in State	:%c%c \n",ptr->statecode[0],ptr->statecode[1]);
				printf("Zipcode			:%05d\n",ptr->zipcode);
				printf("Population		:%d\n",ptr->population);
				printf("Region			:%s\n",rgn[ptr->region]);
				printf("===========================================================================\n");
		return 1;
			}
		}	
		ptr=ptr->next;
	}
	return 0;
}
void addcity(char *input,char *state)
{	
		struct City *temp;
		temp=root;
		root=malloc(sizeof(struct City));
		root->next=temp;
		
			root->statecode[0]=state[0];
			root->statecode[1]=state[1];
			strcpy(root->Name,input);
			to_cap(root->Name);
		printf("Enter ZipCode: "); 
		scanf("%5s",input);
		root->zipcode=atol(input);
		printf("Enter Population : "); 
		scanf("%s",input);
		root->population=atol(input);
		printf("Enter the Region the City is found \n(NE,NW,SE,SW,MW or Other) : ");
		scanf("%5s",input);
		root->region=RegionNO(input);		
		struct City *ptr=root;		
				printf("\n=====================SUCCUSSFULLY ADDED THE CITY===========================\n");
				printf("City Name		:%s\n",ptr->Name);
				printf("Located in State	:%c%c \n",ptr->statecode[0],ptr->statecode[1]);
				printf("Zipcode			:%05d\n",ptr->zipcode);
				printf("Population		:%d\n",ptr->population);
				printf("Region			:%d\n",ptr->region);
				printf("===========================================================================\n");
}
void delete(char *city,int n)
{
	int op=0;
	//struct City *temp;
	struct City *ptr=root;	
	struct City *prv=NULL;
	while(ptr){		
		if(strcmp(ptr->Name,city)==0){
			++op;
			if(op==n){
				printf("\n=====================SUCCUSSFULLY DELETED THE CITY==========================\n");
				printf("City Name		:%s\n",ptr->Name);
				printf("Located in State	:%c%c \n",ptr->statecode[0],ptr->statecode[1]);
				printf("Zipcode			:%05d\n",ptr->zipcode);
				printf("Population		:%d\n",ptr->population);
				printf("Region			:%d\n",ptr->region);
				printf("===========================================================================\n");
					if(prv){
						
						prv->next=ptr->next;
						free(ptr);
						
					}else{
						root=ptr->next;
						free(ptr);
					}
			}
			
		}
		prv=ptr;
		ptr=ptr->next;
	}	
}
void swap(struct City *ptr, struct City *prev)
{
	struct City *temp;
	if (prev) {
		temp = ptr->next;
		ptr->next = temp->next;
		temp->next = ptr;
		prev->next = temp;
	} else {
		temp = ptr->next;
		ptr->next = temp->next;
		temp->next = ptr;
		root = temp;
	}
}
void sort(int d)
{
	int swaps;
	struct City *ptr, *prev, *temp;
	do {		
		swaps = 0;
		ptr = root;
		prev = NULL;
		while(ptr->next) {
			if(d==1){
				if (strcmp(ptr->Name, ptr->next->Name) > 0) {
					temp = ptr->next;
					swap(ptr, prev);
					swaps = 1;
					prev = temp;
				} else {
					prev = ptr;
					ptr = ptr->next;
				}
			}
			if(d==2){
				if (ptr->population > ptr->next->population) {
					temp = ptr->next;
					swap(ptr, prev);
					swaps = 1;
					prev = temp;
				} else {
					prev = ptr;
					ptr = ptr->next;
				}
			}
			if(d==3){
				if (ptr->region > ptr->next->region) {
					temp = ptr->next;
					swap(ptr, prev);
					swaps = 1;
					prev = temp;
				} else {
					prev = ptr;
					ptr = ptr->next;
				}
			}
			if(d==4){
				char x[]={ptr->statecode[0],ptr->statecode[1],0};
				char y[]={ptr->next->statecode[0],ptr->next->statecode[1],0};
				if (strcmp(x,y)>0) {
					temp = ptr->next;
					swap(ptr, prev);
					swaps = 1;
					prev = temp;
					
				} else {
					prev = ptr;
					ptr = ptr->next;
				}
			}				
		}
	} while(swaps);
}
void advanced(int ent)
{
	struct City *ptr=root;
	char temp[2];
	int a=0,b=0,c=0,d=0,e=0,f=0;
	
	if (ent==1){
		while(ptr)
		{
			if(ptr->population<100000)
				a++;
			if(ptr->population>=100000 && ptr->population<2000000)
				b++;
			if(ptr->population>=200000 && ptr->population<3000000)
				c++;
			if(ptr->population>=300000 && ptr->population<4000000)
				d++;
			if(ptr->population>4000000)
				e++;
			ptr=ptr->next;
		}
		printf("Cities with Poppulation\n==============================\n<100000			%d\n100000-2000000		%d\
				\n2000000-3000000		%d\n3000000-4000000		%d\n>4000000		%d\n",a,b,c,d,e);
	}

	if (ent==2){
		while(ptr)
		{
			if(ptr->region==0)
				a++;
			if(ptr->region==1)
				b++;				
			if(ptr->region==2)
				c++;
			if(ptr->region==3)
				d++;
			if(ptr->region==4)
				e++;
			if(ptr->region==5)
				f++;
			ptr=ptr->next;
		}
		printf("Cities in Region\n==============================\nNoth East		%d\nNorth West		%d\
		\nSouth East		%d\nSouth West		%d\nMidwest			%d\nOther			%d\n",a,b,c,d,e,f);
	}
	if(ent==3){
		printf("Cities in the state\n==============================\n");
		temp[0]= ptr->statecode[0]; 
		temp[1]= ptr->statecode[1];
		a=0;
		while(ptr)
		{
			if(ptr->statecode[0]==temp[0] && ptr->statecode[1]==temp[1]){
				a=a+1;
			}else{
			printf("%c%c			%d\n",temp[0],temp[1],a);
			temp[0]= ptr->statecode[0]; 
			temp[1]= ptr->statecode[1];
			a=1;
			}
			ptr=ptr->next;
		}
	}
}	
int main()
{	
	int d,c=0;
	int n;
	char entry[maxchar],s[3];
	read_file();
	
	do{
		MainMenue();
		gets(entry);
		to_cap(entry);
		if(strcmp(entry,"SEARCH")==0){
			printf("Which city do you want me to search? \n");
			gets(entry);
			to_cap(entry);			
			searchcity(entry);
		}
		if(strcmp(entry,"ADD")==0){				
				printf("Enter City Name (Enter 'EXIT' when you're done) : "); 
				gets(entry);
				to_cap(entry);				
					printf("Enter State Code : "); scanf("%2s",s);
					to_cap(s);
					d=searchstate(entry,s);
					if(d==1){
						printf("THIS CITY ALREADY EXITSTS\n");
					}else{
					addcity(entry,s);
					c++;
				}
		}
		if(strcmp(entry,"LIST")==0){
			sort(1);
			print_city();
		}
		if(strcmp(entry,"DELETE")==0){
			printf("What city do you want to DELETE: ");
			gets(entry);
			to_cap(entry);
			n=searchcity(entry);
			if(n){
				//printf("%d\n",n);
				if(n>1){
					printf("Enter the number of the city you want to DELETE: ");
					scanf("%d", &c);
				}else{
					c=1;
				}
				delete(entry,c);				
			}						
		}
		if(strcmp(entry,"ADVANCED")==0){
			printf("This is not developed yet\n");
			gets(entry);
			to_cap(entry);			
			if(strcmp(entry,"POPULATION")==0){
				sort(2);
				advanced(1);
			}
			if(strcmp(entry,"REGION")==0){
				sort(3);
				advanced(2);
			}
			if(strcmp(entry,"STATE")==0){
				sort(4);
				advanced(3);
			}
		}			
	}while(strcmp(entry,"DONE"));
	free_memory(root);
}
