/*
Name: Serdar Efe
Student no: 15011005
E-Mail: efeser80@gmail.com
Compiler used: GCC
IDE: Dev-C++ 5.11
Operating System: Windows 10 64-bit

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>



int found=0;
int anlikHash=1;
int queueSize=1;
int visitedSize=1;
int aramaLeveli=0;

typedef struct Node{
	int value;
	char title[256];
	int level;
	int parent;
}Node;

int baglantiVarMi(int anlikHash,int arananHash,int** adjacencyList,Node* nodeArray,int aramaLeveli){
	
	int k=0;
	//printf("%d ile %d baglantili mi?",anlikHash,arananHash);
	
	while((adjacencyList[anlikHash][k]!=arananHash)&&(adjacencyList[anlikHash][k]!=0)){
		k++;
	}
	
	if(adjacencyList[anlikHash][k]!=arananHash){
		//printf(" | Hayir.\n");
		return 0;
	}else{
		nodeArray[arananHash].parent=anlikHash;
		//printf(" | Evet.\n");
		found=1;
		return 1;
	}
}


int hashAra(int anlikHash,int arananHash,int** adjacencyList,int aramaLeveli,Node* nodeArray,int* queue,int* visited,int queueMax){
	int i,j;
	int k=0;
	int queueIndex=1;
	int flag=0;
	
	for(i=0;i<visitedSize;i++){	
		if(visited[i]==queue[0]){
			flag=1;
		}
	}
		
	if(flag==0){
		visitedSize++;
		visited=(int*) realloc(visited,(visitedSize)*sizeof(int));
		visited[visitedSize-1]=queue[0];
	}
		
	flag=0;
	
	found=baglantiVarMi(anlikHash,arananHash,adjacencyList,nodeArray,aramaLeveli);
	
	if(found==1){
		nodeArray[arananHash].parent=1;
		return arananHash;
	}else{
		while(adjacencyList[anlikHash][k]!=0){
			k++;
		}
		
		for(i=0;i<k;i++){
				//printf("Komsular %d || ",anlikHash);
				baglantiVarMi(nodeArray[adjacencyList[anlikHash][i]].value,arananHash,adjacencyList,nodeArray,aramaLeveli);
				if(found==1){
					free(visited);
					free(queue);
					
					nodeArray[adjacencyList[anlikHash][i]].parent=anlikHash;
					nodeArray[adjacencyList[anlikHash][i]].level=aramaLeveli+1;
					nodeArray[arananHash].level=aramaLeveli+2;
					return arananHash;	
				}else{
					
					for(j=0;j<visitedSize;j++){	
						if(visited[j]==adjacencyList[anlikHash][i]){
							flag=1;
						}
					}
					
					for(j=0;j<queueSize;j++){	
						if(queue[j]==adjacencyList[anlikHash][i]){
							flag=1;
						}
					}
					
					if(flag==0){
						queueSize++;
						queue= (int*) realloc(queue,(queueSize+1)*sizeof(int));
						queue[queueSize-1]=nodeArray[adjacencyList[anlikHash][i]].value;
						nodeArray[queue[queueSize-1]].parent=anlikHash;		
						nodeArray[queue[queueSize-1]].level=aramaLeveli+1;
					}

					/*
					if(nodeArray[adjacencyList[anlikHash][i]].parent==0){
						nodeArray[adjacencyList[anlikHash][i]].parent=anlikHash;
					}
					*/
				}
				flag=0;
			}	
		}
		
		flag=0;
		/*
		for(i=1;i<queueSize;i++){
			nodeArray[queue[i]].parent=queue[0];
		}
		*/
		
		if(queue[0]==queueMax){
			aramaLeveli++;
			queueMax=queue[queueSize-1];
		}
		
		queue=(int*) realloc(queue,(queueSize+1)*sizeof(int));
		for(i=0;i<queueSize-1;i++){
			queue[i]=queue[i+1];
		}
		if(queueSize==0){
			return -1;
		}else{
			queue[i]=0;
			queueSize--;	
		}
		
		
		/*
		flag=0;
		if(aramaLeveli)
		
		for(i=0;i<queueSize;i++){
			if(nodeArray[queue[i]].level==-1){
				nodeArray[queue[i]].level=aramaLeveli;
				flag=1
			}	
		}
		if(flag==1){
			aramaLeveli++;
		}
		*/
		
		
		
		
		hashAra(queue[0],arananHash,adjacencyList,aramaLeveli,nodeArray,queue,visited,queueMax);

		
		
	}



int main() {
	int secim=-1;
	
	char buffer[2048];
	int hamle[10];
	char* asd;
	int value=1;
	char* token;
	Node* nodeArray =(Node *) calloc (1,sizeof(Node));
	int flag=0;
	int i;
	int j;
	int k;
	int l;
	int m=0;
	int n;
	int tmpValue;
	int arananHash;
	int arananYedek;
	int bulunanHash;
	int headHash=1;
	int hamleSayisi=0;
	int** adjacencyList;
	int adjacencyMax=0;
	int tmpOldMax=0;
	int* valueList=(int *) calloc(1,sizeof(int));
	int* queue=(int *)malloc(2*sizeof(int));
	int* visited=(int*)malloc(2*sizeof(int));
	int frekans[10]={0};
	int sonsuzKisi;
	frekans[0]=1;
	visited[0]=1;
	queue[0]=1;
	
	adjacencyList = (int**) calloc ((adjacencyMax+2),sizeof(int*));
	
	for(i=0;i<(adjacencyMax+1);i++){
		adjacencyList[i]=(int*) calloc(1,sizeof(int));
	}
	adjacencyList[1]= (int*) realloc(adjacencyList[1],2*sizeof(int));
	
		
	FILE *fp = fopen("input-3.txt","r");
	
	if ( fp != NULL ){	
    	while(fgets ( buffer, sizeof buffer, fp) != NULL) {
    		k=0;
			token=strtok(buffer,"/");
			printf("Film ismi: %s\n",token);
			token=strtok(NULL,"/");
			
			
			while(token != NULL){
				
				if(token[strlen(token)-1]=='\n'){
				token[strlen(token)-1]='\000';
			}
					
				if(strcmp(token,"\n")!=0){
					for(i=1;i<value;i++){
						if(strcmp(token,nodeArray[i].title)==0){
							flag=1;	
							tmpValue=nodeArray[i].value;
						}
					}
				
					
					
					
					if(flag==0){
						struct Node newNode;
						newNode.value=value;
						strcpy(newNode.title,token);
						newNode.level=-1;
						
						nodeArray =(Node *) realloc (nodeArray,(value+1)*sizeof(Node));
						
						nodeArray[value]=newNode;	
						valueList[k]=value;
						valueList= (int*) realloc (valueList,(k+2)*sizeof(int));
						k++;
						value++;
						
											
					}else{
						valueList= (int*) realloc (valueList,(k+2)*sizeof(int));			
						valueList[k]=tmpValue;
						k++;	
						tmpValue=nodeArray[i].value;
						flag=0;
					}
				}
				token=strtok(NULL,"/");		
			}
				
			if(value>adjacencyMax){
						tmpOldMax=adjacencyMax;
						adjacencyMax=value;
						adjacencyList = (int**) realloc (adjacencyList,(adjacencyMax+1)*sizeof(int*));
						for(i=tmpOldMax;i<adjacencyMax+1;i++){
							adjacencyList[i]= (int*)calloc(1,sizeof(int));
						}
					}
				
			flag=0;	
			j=0;
			l=0;
			
			
			for(j=0;j<k-1;j++){
				for(l=j+1;l<k;l++){
					n=0;
					while((adjacencyList[valueList[j]][n]>0)&&(adjacencyList[valueList[j]][n]!=valueList[l])){
						n++;
					}
					
					
					
					adjacencyList[valueList[j]]= (int*) realloc(adjacencyList[valueList[j]],(n+2)*sizeof(int));		
					adjacencyList[valueList[j]][n]=valueList[l];
					adjacencyList[valueList[j]][n+1]=0;
					
					n=0;
					while((adjacencyList[valueList[l]][n]>0)&&(adjacencyList[valueList[l]][n]!=valueList[j])){
						n++;
					}
					if((valueList[j]==304)&&(valueList[l]==102)){
						printf(asd);
					}
					adjacencyList[valueList[l]]= (int*) realloc(adjacencyList[valueList[l]],(n+2)*sizeof(int));
					
					adjacencyList[valueList[l]][n]=valueList[j];
					adjacencyList[valueList[l]][n+1]=0;
				}
			}

			free(valueList);
			valueList=(int *) calloc(1,sizeof(int));
			m++;
			
			}
		}		
		printf("Filmlerin yuklenmesi tamamlandi.\n");
		printf("\n--------------------------------- ");
		printf("\nDevam etmek icin bir tusa basiniz...");
		getch();
		
		
	char aranan[64];
	int sonsuzFlag=0;
		
	while(secim){	//menu	
		sonsuzFlag=0;
		system("cls");
		printf("========================================================================================================================\n");
		printf("1.Kevin Bacon ara\n");
		printf("2.Kayitli Kevin Bacon numarasi sorgula\n");
		printf("3.Her Kevin Bacon sayisinda kac kisi oldugunu goruntule\n");
		printf("4.Input dosyasindaki herkesi sorgula\n");
		printf("0.Cikis\n\n");
		printf("Secim: ");
		scanf("%d%*c",&secim);
		//secim=1;
		printf("\n");
		switch(secim){
			case 1:
				printf("Hedef kisinin ismini Soyadi, Adi seklinde giriniz: ");
				fgets(aranan,100,stdin);
				aranan[strlen(aranan)-1]='\0';
				
				
				flag=0;
				while((strcmp(nodeArray[arananHash].title,aranan)!=0)&&(flag==0)){
					arananHash++;
					if(arananHash==value){
						printf("Aranan kisi bulunamadi.");
						flag=1;
						
					}
				}
				
				if(flag==0){
					arananHash=nodeArray[arananHash].value;
					printf("Aranan kisinin hash degeri: %d\n",arananHash);
					arananYedek=arananHash;
					
					
					hamleSayisi=0;
					if(strcmp(nodeArray[arananHash].title,"Bacon, Kevin")==0){
						printf("Kevin Bacon'un Kevin Bacon degeri 0.");
					}else{
						bulunanHash=hashAra(queue[0],arananHash,adjacencyList,0,nodeArray,queue,visited,1);
						sonsuzFlag=bulunanHash;
						bulunanHash=arananHash;
						if(sonsuzFlag==-1){
							printf("Verilen ismin Kevin Bacon baglantisi sonsuzdur.");
							nodeArray[arananHash].level=-2;
						}else{
							while(nodeArray[bulunanHash].value!=1){
								printf("%s -> ",nodeArray[bulunanHash].title);
								//printf("%d",nodeArray[bulunanHash].parent);
								//printf("%s",nodeArray[2].title);
								
								//printf("%s \n",nodeArray[nodeArray[bulunanHash].parent].title);
								hamleSayisi++;
								//printf("%d hashli node yazdirilacak:",nodeArray[bulunanHash].parent);
								bulunanHash=nodeArray[bulunanHash].parent;
								if(nodeArray[bulunanHash].parent==1){
									//printf("%d hashli node yazdirilacak:",nodeArray[bulunanHash].level);
									//bulunanHash=nodeArray[bulunanHash].parent;
									hamleSayisi++;
									printf("%s -> ",nodeArray[bulunanHash].title);
									bulunanHash=1;
								}
							}
							printf(" Bacon, Kevin");
							if(nodeArray[arananYedek].level==-1){
								nodeArray[arananYedek].level=1;
							}
							printf("\nKevin Bacon degeri: %d",nodeArray[arananYedek].level);
							//frekans[nodeArray[arananYedek].level]++;
							//nodeArray[arananYedek].level=hamleSayisi;
						
					
						}

					}					
				}
				visitedSize=1;
				queueSize=1;
				visited=(int*)calloc(2,sizeof(int));
				visited[0]=1;
				queue=(int *)calloc(2,sizeof(int));
				queue[0]=1;
				arananHash=1;
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;
			case 2:
				
				printf("Hedef kisinin ismini Soyadi, Adi seklinde giriniz: ");
				fgets(aranan,100,stdin);
				aranan[strlen(aranan)-1]='\0';
				
				while((strcmp(nodeArray[arananHash].title,aranan)!=0)&&(flag==0)){
					arananHash++;
					if(arananHash==value){
						printf("Aranan kisi bulunamadi.");
						flag=1;
						
					}
				}
				if(flag==0){
					if(nodeArray[arananHash].level==-1){
						printf("Aranan kisinin Kevin Bacon numarasi henuz hesaplanmamis.");
						
					}else if(nodeArray[arananHash].level==-2){
						printf("Aranan kisinin Kevin Baon numarasi sonsuz.");
					}else{
						printf("Aranan kisinin Kevin Bacon sistemde kayitli numarasi: %d",nodeArray[arananHash].level);
					}
					
				}
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;	

			case 3:
				for(i=2;i<=value;i++){
					if(nodeArray[i].level==-2){
						sonsuzKisi++;
					}else{
						frekans[nodeArray[i].level]++;	
					}					
				}
				
				for(i=0;i<10;i++){
					if(i==0){
						frekans[i]=1;
					}
					printf("Kevin Bacon sayisi %d olan kisi sayisi: %d\n",i,frekans[i]);
					
				}
				printf("Kevin Bacon sayisi sonsuz olan kisi sayisi: %d\n",sonsuzKisi);
				
				for(i=1;i<=value;i++){
					frekans[nodeArray[i].level]=0;
				}
				sonsuzKisi=0;
				
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;	
			
			case 4:
				
				printf("Input dosyasindaki butun isimler araniyor...\n");
				
				for(i=2;i<=value;i++){
					if(nodeArray[i].level==-1){
						printf("%s sorgulaniyor...\n",nodeArray[i].title);
						arananHash=nodeArray[i].value;
						
							printf("Aranan kisinin hash degeri: %d\n",arananHash);
							arananYedek=arananHash;
							
							
							hamleSayisi=0;
							if(strcmp(nodeArray[arananHash].title,"Bacon, Kevin")==0){
								printf("Kevin Bacon'un Kevin Bacon degeri 0.");
							}else{
								
								bulunanHash=hashAra(queue[0],arananHash,adjacencyList,0,nodeArray,queue,visited,1);
								sonsuzFlag=bulunanHash;
								if(sonsuzFlag==-1){
									printf("Verilen ismin Kevin Bacon baglantisi sonsuzdur.\n");
									nodeArray[arananHash].level=-2;
								
								}else{
									bulunanHash=arananHash;
									while(nodeArray[bulunanHash].value!=1){
										hamleSayisi++;
										bulunanHash=nodeArray[bulunanHash].parent;
										if(nodeArray[bulunanHash].parent==1){
											hamleSayisi++;
											bulunanHash=1;
										}
									}
									if(nodeArray[arananYedek].level==-1){
										nodeArray[arananYedek].level=1;
									}
								}
									
							}
					
						}
						visitedSize=1;
						queueSize=1;
						visited=(int*)calloc(2,sizeof(int));
						visited[0]=1;
						queue=(int *)calloc(2,sizeof(int));
						queue[0]=1;
						arananHash=1;
						
					
					}

				
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;	
			case 0:
				exit(0);
				break;
			default:
				printf("Gecersiz secim.");
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;	
		}		
	}	
	
	
	return 0;
}
