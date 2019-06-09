
//15011005
//Serdar Efe

#include <stdio.h>


int main() {
	int i,j,N=7;
	int A[7]={17,13,27,47,41,11,29};
	int S[7]={0,0,0,0,0,0,0};
	
	printf("The array:\n\n");
	for(i=0;i<N;i++){
		printf("%d ",A[i]);
	}
		
	for(i=0;i<N-1;i++){
		for(j=i+1;j<N;j++){
			if(A[i]>A[j]){
				S[i]++;
			}else{
				S[j]++;
			}
		}
	}
	
	printf("\n\nThe counting order:\n");
	printf("\n");
	
	
	for(i=0;i<N;i++){
		printf("%d  ",S[i]+1);
	}
	
	printf("\n\n");
	printf("The sorted array: \n\n");
	for(i=0;i<N;i++){
		j=0;
		while(S[j]!=i){
			j++;
		}
		printf("%d ",A[j]); 
	}
	
	
	return 0;
}
