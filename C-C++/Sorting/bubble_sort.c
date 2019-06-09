#include <stdio.h>


int main() {
	int array[10]={1,5,2,8,0,3,4,6,9,7}; //assigning unsorted array
	int i,j,tmp;
	
	for(i=0;i<10;i++){ //displaying unsorted array
		printf("%d ",array[i]);
	}
	printf("\n");
	
	for(i=0;i<10;i++){
		for(j=0;j<10-i;j++){
			if(array[j]>array[j+1]){
				tmp=array[j+1];
				array[j+1]=array[j];
				array[j]=tmp;		
			}
		}
	}
	
	for(i=0;i<10;i++){ //displaying sorted array
		printf("%d ",array[i]);
	}
	return 0;
}
