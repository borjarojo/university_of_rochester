#include <stdio.h>

main(int argc, char * argv[])
{
  for(int x = 0; x < argc; ++x){
    if(argv[x] == "Borja" || argv[x] "Daniel"){
      printf("Hello, ");
    }
    printf("%s\n", argv[x]);
  }

}

int mystrcmp(char *s, char *t){
  for( ; *s == *t; s++, t++){
    if(*s == '\0')
      return 0;
  }
  return *s - *t;
}

void mystrcat(char *dest, char *source){
  
  while(*dest != '\0'){
    dest++;
  }
  while(*source != '\0'){
    *dest++ = *source++;
  }
  *dest = *source;
}

void strcpy(char *s, char *t)
{
  int i;

  i =0;
  while((*s = *t) != '\0')
    i++;
}

