#include <assert.h>
#include <dlfcn.h>
#include <stdio.h>
#include <stdlib.h>
#include "mm_alloc.h"

int main()
{
    int i;
    int *a;
    int *b;
    int *c;
    int *d;
    a=mm_malloc(5*sizeof(int));
    printf("Malloc (5) \n");
    printf("-------------\n");
    a[0]=1;
    a[1]=2;
    a[2]=3;
    a[3]=4;
    a[4]=5;

    for(i=0;i<5;i++){
        printf("%d ",a[i]);
    }
    printf("\n\n");

    printf("Realloc(1)\n");
    printf("-------------\n");
    a=mm_realloc(a,1*sizeof(int));
    for(i=0;i<1;i++){
        printf("%d ",a[i]);
    }

    printf("\n\nRealloc(6)\n");
    printf("-------------\n");

    a=mm_realloc(a,6*sizeof(int));
    a[5]=6;
    for(i=0;i<6;i++){
        printf("%d ",a[i]);
    }

    b=mm_malloc(5*sizeof(int));
    c=mm_malloc(5*sizeof(int));
    mm_free(a);
    mm_free(c);
    mm_free(b);
    b=mm_malloc(15*sizeof(int));
    printf("\n\nRealloc(15)\n");
    printf("-------------\n");

    for(i=0;i<15;i++){
        printf("%d ",b[i]);
    }
    printf("\n");

    return 0;
}
