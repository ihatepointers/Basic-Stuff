/* 
Merge Sort

Serdar Efe
15011005

*/
#include <stdio.h>

int main() {
	int dizi[8]={6,5,3,1,8,7,2,4};
	int N=8;
	int i=0;
	int j=0;
	int min=0;
	int tmp;
	printf("Dizi: ");
	for(i=0;i<N;i++){
		printf("%d ",dizi[i]);
	}
	i=0;
	for(i=0;i<N-2;i++){
		min=i;
		for(j=i+1;j<N;j++){
			if(dizi[j]<dizi[min]){
				min=j;
			}
		}
		tmp=dizi[i];
		dizi[i]=dizi[min];
		dizi[min]=tmp;	
	}

	printf("\n
	Siralanmis Dizi:");
	for(i=0;i<N;i++){
		printf("%d ",dizi[i]);
	}
	return 0;
}
