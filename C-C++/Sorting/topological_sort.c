#include <stdio.h>
#include <stdlib.h>
int visitedIndex=0;
int finishedIndex=0;

void dfs(int bulunan,char* harfler,int** adjacencyList,int* visited,int* finished){
	int i=1;
	int j=1;
	int flag=0;
	while(adjacencyList[bulunan][i]!=0){
		for(j=0;j<visitedIndex;j++){
			if(visited[j]==adjacencyList[bulunan][i]){
				flag=1;
			}
		}
		if(flag==0){
			visited[visitedIndex]=adjacencyList[bulunan][i];
			visitedIndex++;
			dfs(adjacencyList[bulunan][i],harfler,adjacencyList,visited,finished);
		}
		i++;
	}
	finished[finishedIndex]=bulunan;
	finishedIndex++;
}

int main() {
	
	char* harfler;
	harfler=malloc(6*sizeof(char));
	harfler[1]='A';
	harfler[2]='B';
	harfler[3]='C';
	harfler[4]='D';
	harfler[5]='E';
	int i,j;
	int flag=0;
	i=j=0;
	
	int** adjacencyList;
	
	adjacencyList=calloc(6,sizeof(char*));
	
	for(i=0;i<6;i++){
		adjacencyList[i]=calloc(10,sizeof(char));
	}
	for(i=0;i<5;i++){
		for(j=0;j<9;j++){
			adjacencyList[i][j]=0;
		}
	}
	
	int* visited=calloc(10,sizeof(int));

	int* finished=calloc(10,sizeof(int));
	
	adjacencyList[1][1]=2;
	adjacencyList[1][2]=4;
	
	adjacencyList[2][1]=5;
	
	adjacencyList[3][1]=1;
	adjacencyList[3][2]=4;
	
	adjacencyList[4][1]=2;
	adjacencyList[4][2]=5;
	int bulunan=3;
	visited[0]=3;
	visitedIndex++;
	dfs(bulunan,harfler,adjacencyList,visited,finished);
	
	printf("Topological sort:\n\n");
	
	for(i=visitedIndex-1;i>=0;i--){
		printf("%c",harfler[finished[i]]);
		if(i!=0){printf("->");
		}
	}
		
	return 0;
}
