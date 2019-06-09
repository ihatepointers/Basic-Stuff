#include <stdio.h>

int main(){
	
	int array[10]={1,5,2,8,0,3,4,6,9,7}; //assigning unsorted array
	int i,j,min,tmp;
	
	for(i=0;i<10;i++){ //displaying unsorted array
		printf("%d ",array[i]);
	}
	printf("\n");
	
	for (i=0;i<10;i++){
		min=i;
		for (j=i+1;j<10;j++){
			if (array[j]<array[min]){
				min=j;
				}
		}
		if (i!=min){
		tmp=array[i];
		array[i]=array[min];
		array[min]=tmp;		
    	}
	}
	
	for(i=0;i<10;i++){ //displaying sorted array
		printf("%d ",array[i]);
	}
	return 0;
}
