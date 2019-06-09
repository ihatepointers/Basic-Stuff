//Serdar Efe
//15011005

#include <stdio.h>


int main() {
	
	int i;	//loop variable
	int j;	//loop variable
	int firstList;	//size of the first list
	int secondList; //size of the second list
	int point;		//Target node to be filled in the linkC list
	int C[200];		//New linked list	
	int linkC[200]; //Order of the new linked list
	int A[100];		//first linked list
	int B[100];		//second linked list
	int linkA[100]; //Order of the first linked list
	int linkB[100]; //Order of the second linked list
	int headA;		//start position of A
	int headB;		//start position of B
	int headC;		//start position of C

	//Getting the values from the user
	
	printf("\nHow many integers are the in the first linked list?");
	scanf("%d",&firstList);
	printf("\nEnter the first list.(one element at a time)");
	for(i=0;i<firstList;i++){
		scanf("%d",&A[i]);
	}
	printf("\nEnter the order links of the first list");
	for(i=0;i<firstList;i++){
		scanf("%d",&linkA[i]);
	}
	printf("\nWhich element is the start of the list?");
	scanf("%d",&headA);
	
	printf("\nHow many integers are the in the second linked list?");
	scanf("%d",&secondList);
	printf("\nEnter the first list.(one element at a time)");
	for(i=0;i<secondList;i++){
		scanf("%d",&B[i]);
	}
	printf("\nEnter the order links of the first list");
	for(i=0;i<secondList;i++){
		scanf("%d",&linkB[i]);
	}
	printf("\nWhich element is the start of the list?");
	scanf("%d",&headB);

	
	//Step 1
	//Fusing the the lists.
	for(i=0;i<firstList;i++){
		C[i]=A[i];
	}
	for(i=firstList;i<firstList+secondList;i++){
		C[i]=B[i-firstList];
	}
	
	//Step 2
	//From now on,i and j will mark the current elements being compared in each list
	
	i=headA;
	j=headB;
	
	if(A[headA]<B[headB]){
		headC=headA;
		point=headA;
		linkC[point]=A[headA];
		i=linkA[i];	//if we start the linking with an element from the first list,we go to the second element of the said list
	}else{
		headC=headB;
		point=firstList+headB;
		linkC[point]=B[headB];
		j=linkB[j];//if we start the linking with an element from the second list,we go to the second element of the said list
	}
		
	do{
		if(C[i]<=C[firstList+j]){	//comparing the two elements
			linkC[point]=i;		//fill the current target position in the order list with the element i(which is the next lowest element)
			i=linkA[i];		//go to the next element in the first list
		}else{	
			linkC[point]=firstList+j;	//fill the current target position in the order list with the element j(which is the next lowest element)	
			j=linkB[j];		//go to the next element in the second list	
		}	
		point=linkC[point];	//go to the next element's position in the order list of C and mark it as the next place to fill
	}while((i>=0)&&(j>=0));	//this will go on untill one of the lists finish
	
	if(i>=0){			//if i is equal or greater than 0,it means the second list is finished but the first list is not.We keep adding  
		while(i>=0){	//elements from the first list until the first list finishes too
			linkC[point]=i;		
			i=linkA[i];
		}
		linkC[linkC[point]]=-1;	//marking the end
	}else{
		while(j>=0){					//if j is equal or greater than 0,it means the first list is finished but the second list is not.We keep adding
			linkC[point]=firstList+j;	//elements from the second list until the second list finishes too	
			j=linkB[j];	
		}
		linkC[linkC[point]]=-1;	//marking the end
	}
	
	printf("\n");	//displaying the final list C
	for(i=0;i<firstList+secondList;i++){
		printf(" %d ",C[i]);
	}
	
	printf("\n");	//displaying the order list of C
	for(i=0;i<firstList+secondList;i++){
		printf(" %d ",linkC[i]);
	}
	return 0;
}
