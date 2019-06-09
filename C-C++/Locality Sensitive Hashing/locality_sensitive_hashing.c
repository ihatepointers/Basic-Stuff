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
#include <time.h>
#include <windows.h>


void shingleSetOlustur(FILE *fp,char ***shingleSet,int fileCount,int shingleLength,int* shingleAmount){//dosya okuma fonksiyonu
	int index=0;	//dosya imleci
	char buffer[1024];	//gecici olarak cumlenin tutuldugu yer
	int shingleCount=0;	//shingle sayaci
	//dongu sayaclarý
	int i=0;	
	int j=0;
	int k=0;
	
	int sameFlag=0;	//daha once ayni shingle yazilmissa 1 olan flag
	
	int oldIndex; //4 shingle ilerledikten sonra bir sonraki shingle icin baslangic adresine dönmemizi saglayan imlec
	
	if(fp != NULL){
		while(fgets(buffer,sizeof buffer,fp) != NULL){
			shingleSet[fileCount]=realloc(shingleSet[fileCount],(shingleCount+1)*sizeof(char*)); //yeni shingle icin yer acma
			shingleSet[fileCount][shingleCount]=malloc((shingleLength+1)*sizeof(char));
			while(index <= strlen(buffer)-shingleLength){	//satir bitene kadar okumaya devam et
				oldIndex=index;
				while((i<shingleLength)&&(index <= strlen(buffer))){
					if(((buffer[index] >= 65)&&(buffer[index] <= 90))||((buffer[index] >= 97)&&(buffer[index] <= 122))){	//yeni char istenen aralikta mi
						if((buffer[index] >=65)&&buffer[index]<=90){
							buffer[index] += 32;
						}		
						shingleSet[fileCount][shingleCount][i]=buffer[index];
					}else if(buffer[index] == 32){	//birden fazla bosluk var mi
						if(buffer[index+1] != 32){
							shingleSet[fileCount][shingleCount][i]=buffer[index];
						}else{
							i--;
						}
					}else{
						i--;	//yeni char istenen aralikta degilse geri git
					}
				index++;	//dosyadaki imleci artir
				i++;	//yeni shingle imlecini artir
				}
				if(i!=shingleLength){// shingle dolmadan satýrýn sonuna gelinmiþse
					while(i<=shingleLength){
						shingleSet[fileCount][shingleCount][i]=32;	//shingle'in kalan bolumunu bosluk ata
						i++;
					}
					
				}
				k=0;
				sameFlag=0;
				shingleSet[fileCount][shingleCount][shingleLength]='\0';
				while((sameFlag==0)&&(k<shingleCount)){	//shingle daha onceden kaydedilmis mi
					if(strcmp(shingleSet[fileCount][k],shingleSet[fileCount][shingleCount])==0){
						sameFlag=1;
					}
					k++;
				}
				if(sameFlag==0){	//shingle yeni ise
					shingleCount++;
					shingleSet[fileCount]=realloc(shingleSet[fileCount],(shingleCount+1)*sizeof(char*));
					shingleSet[fileCount][shingleCount]=malloc((shingleLength+1)*sizeof(char));
				
					if(index > strlen(buffer)){	//sondaki karakteri sýfýrla
						j=1;
						for(i=index;i>strlen(buffer);i--){
							shingleSet[fileCount][shingleCount-1][shingleLength-j]='\0';
							j++;
						}
					}	
				}
				i=0;
				index=oldIndex+1;	
			}
			shingleAmount[fileCount]=shingleCount;
		}
	}
}

void shingleYazdir(int fileCount,int* shingleAmount,char*** shingleSet){
	int i;
	fileCount--;
	printf("%d.txt isimli dosyanin %d adet shingle'i var\n",fileCount+1,shingleAmount[fileCount]);
	printf("Shingle'lar:\n");
	for(i=0;i<shingleAmount[fileCount];i++){
		printf("%s|",shingleSet[fileCount][i]);
	}
	printf("\n");
}

float jaccardBenzerligiHesapla(int dosya1,int dosya2,int *shingleAmount,char ***shingleSet){
	dosya1--;	//1.txt 0.dosya,2.txt 1.dosya oldugu icin
	dosya2--;
	int i,j;
	int overlapCount=0;	//kesisim sayaci
	int totalUniqueShingle;	//kendini tekrar etmeyen shingle kumesinin buyuklugu
	float jaccardSimiliarity;
	for(i=0;i<shingleAmount[dosya1];i++){
		for(j=0;j<shingleAmount[dosya2];j++){
			if(strcmp(shingleSet[dosya1][i],shingleSet[dosya2][j])==0){
				overlapCount++;
			}
		}
	}
	totalUniqueShingle=shingleAmount[dosya1]+shingleAmount[dosya2]-overlapCount;	//aUb = a+b-(anb)
	jaccardSimiliarity=(float)overlapCount/(float)totalUniqueShingle;
	return jaccardSimiliarity;
}

int searchShingle(char* targetShingle,int dosya,int *shingleAmount,char ***shingleSet){	//istenilen shingle dosyada geciyor mu kontrol et
	int i=0;
	int foundFlag=0;
	while((i<shingleAmount[dosya])&&(foundFlag==0)){
		if(strcmp(shingleSet[dosya][i],targetShingle)==0){
			foundFlag=1;
		}
		i++;
	}
	return foundFlag;
}

float minHashHesapla(int dosya1,int dosya2,int *shingleAmount,char ***shingleSet,int shingleLength){
	dosya1--;	//1.txt 0.dosya,2.txt 1.dosya oldugu icin
	dosya2--;
	int sameFlag=0;
	int i,j,k;
	char **uniqueShingles;	//kendini tekrar etmeyen shinglelar matrisi
	int **matrix;	//hash degerlerinin tutuldugu matrix
	int signatureMatrix[100][2];	//boyutlari bilinen imza matrisi
	int *aDizisi;	//(a*x+1)mod M seklindeki hash fonksiyonundaki rastgele a degerlerini tutan dizi
	int sameCount=0;	//benzerligi hesaplamamizi saglayan eslesme sayaci
	float hashDifference;	//hesaplayacagimiz deger
	int M; //hash fonksiyonundaki M degeri
	srand(time(NULL));
	
	for(i=0;i<100;i++){
		for(j=0;j<2;j++){
			signatureMatrix[i][j]=-1;
		}
	}
	
	int uniqueShingleCount=shingleAmount[dosya1];
	uniqueShingles=malloc(uniqueShingleCount*sizeof(char*));
	
	for(i=0;i<shingleAmount[dosya1];i++){	
		uniqueShingles[i]=malloc(shingleLength*sizeof(char*));
	}	
	
	for(i=0;i<shingleAmount[dosya1];i++){ //dosya 1 deki her sey unique kabul et
		strcpy(uniqueShingles[i],shingleSet[dosya1][i]);
	}
	j=0;
	for(i=0;i<shingleAmount[dosya2];i++){	//dosya2 deki kelimeler daha once bulunmamissa ekle
		sameFlag=0;
		while((j<shingleAmount[dosya1])&&(sameFlag==0)){	
			if(strcmp(shingleSet[dosya2][i],shingleSet[dosya1][j])==0){
				sameFlag=1;
			}
			j++;
		}
		
		if(sameFlag == 0){	//daha once bulunmamissa
			uniqueShingleCount++;
			uniqueShingles=realloc(uniqueShingles,uniqueShingleCount*sizeof(char*));
			uniqueShingles[uniqueShingleCount-1]=malloc(shingleLength*sizeof(char));
			strcpy(uniqueShingles[uniqueShingleCount-1],shingleSet[dosya2][i]);
		}
		j=0;
	}
	
	matrix=malloc(uniqueShingleCount*sizeof(int*));	//her shingle icin hash degerlerinin saklanacagi matris
	for(i=0;i<uniqueShingleCount;i++){
		matrix[i]=malloc(100*sizeof(int));
	}
	for(i=0;i<uniqueShingleCount;i++){	
		if(i<shingleAmount[dosya1]){
			matrix[i][0]=1;
		}else{
			matrix[i][0]=searchShingle(uniqueShingles[i],dosya1,shingleAmount,shingleSet);
		}
		matrix[i][1]=searchShingle(uniqueShingles[i],dosya2,shingleAmount,shingleSet);
		
	}
	
	M=uniqueShingleCount;
	aDizisi=malloc(100*sizeof(int));	//hash fonksiyonu icin random sayilar ata
	for(i=0;i<100;i++){
		aDizisi[i]=rand()%M;	
	}
	
	//(a*x+1)mod M
	for(i=0;i<uniqueShingleCount;i++){	//hash degerlerini hesapla
		for(j=2;j<102;j++){
			matrix[i][j]=(aDizisi[j-2]*i+1) % M;
		}
	}
	for(i=1;i<uniqueShingleCount;i++){	//signature matrix olustur
		for(j=0;j<2;j++){
			if(matrix[i][j]==1){
				for(k=0;k<100;k++){
					if((signatureMatrix[k][j]==-1)||(matrix[i][k+2]<signatureMatrix[k][j])){
						signatureMatrix[k][j]=matrix[i][k+2];
					}
				}
			}
		}
	}
	for(i=0;i<100;i++){
		if(signatureMatrix[i][0]==signatureMatrix[i][1]){	//1.sutun ve 2.sutun ayni ise eslesme var
			sameCount++;
		}
		hashDifference= (float)sameCount/100;
	}
	free(matrix);
	free(uniqueShingles);
	return hashDifference;
	
	
}

void jaccardYazdir(int* shingleAmount,char*** shingleSet){
	int i,j;
		
	printf("\n");
	printf("Jaccard benzerlik matrisi");
	printf("\n-----------------------------\n\n     ");
	for(i=1;i<=20;i++){
		printf("%d    ",i);
		if(i<10){
			printf(" ");
		}
	}
	printf("\n   ------------------------------------------------------------------------------------------------------------------------");
	printf("\n");	
	for(i=1;i<=20;i++){
		printf("%d.",i);
		if(i<10){
			printf(" ");
		}
		printf("|");
		for(j=1;j<=20;j++){
			printf("%.3f ",jaccardBenzerligiHesapla(i,j,shingleAmount,shingleSet));
		}
		printf("\n");
	}	
}

void signatureYazdir(int* shingleAmount,char*** shingleSet,int mode,float threshold,int **benzerlikMatrisi){
	int shingleLength;
	int i,j;
	printf("\n\n");
	printf("Signature matrix benzerlik matrisi");
	printf("\n-----------------------------------\n\n     ");
	printf("\n");
	if(mode==0){	//mode 0 ise thresholdsuz yazdir
		printf("     ");
		for(i=1;i<=20;i++){
			printf("%d    ",i);
			if(i<10){
				printf(" ");
			}
		}
		printf("\n   ------------------------------------------------------------------------------------------------------------------------\n");
		for(i=1;i<=20;i++){
			printf("%d.",i);
			if(i<10){
				printf(" ");
			}
			printf("|");
			for(j=1;j<=20;j++){
				printf("%.3f ",minHashHesapla(i,j,shingleAmount,shingleSet,shingleLength));
			}
			printf("\n");
		}		
	}else{		//mode 1 ise thresholdlu yazdir
	
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 12);				//renk degistir
		printf("Kirmizi ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 7);
		printf("- benzer.\n");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 10);				//renk degistir
		printf("Yesil: ");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 7);
		printf("- benzer degil.\n\n");
		printf("     ");
		for(i=1;i<=20;i++){
			printf("%d    ",i);
			if(i<10){
				printf(" ");
			}
		}
		printf("\n   ------------------------------------------------------------------------------------------------------------------------\n");
		for(i=1;i<=20;i++){
			printf("%d.",i);
			if(i<10){
				printf(" ");
			}
			printf("|");
			for(j=1;j<=20;j++){
				if(minHashHesapla(i,j,shingleAmount,shingleSet,shingleLength) > threshold){
					SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 12);				//renk degistir
					printf("%.3f ",minHashHesapla(i,j,shingleAmount,shingleSet,shingleLength));
					SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 7);
					benzerlikMatrisi[i-1][j-1]=1;
				}else{
					SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 10);
					printf("%.3f ",minHashHesapla(i,j,shingleAmount,shingleSet,shingleLength));
					SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE ), 7);
				}	
			}
			printf("\n");
		}
		
		printf("\n");
		printf("Benzer dosyalar listesi:\n");
		for(i=0;i<20;i++){
			for(j=i+1;j<20;j++){
				if(benzerlikMatrisi[i][j]==1){
					printf("|%d.txt <-> %d.txt|",i+1,j+1);
				}
			}
			printf("\n");
		}
		
	}

	
}


int main() {
	
	
	int shingleLength;	//kullanicidan alinan shingle uzunlugu
	int* shingleAmount;	//shingle sayisi
	char*** shingleSet;	//her dosyanin shingle matrisini tutan 3 boyutlu veri
	int shingleCount=0;	//shingle sayaci
	int index=0;		//dosya indisi
	int i,j;
	int a;	//hash fonksiyoundaki a degeri
	float hashDifference;
	float threshold;	//disaridan alinan esik degeri
	char fileName[6];	//acilacak txt ismini tutan char dizisi
	int fileCount=0;	//dosya sayaci
	int secim=-1;
	int **benzerlikMatrisi;
	FILE* fp;
	benzerlikMatrisi=malloc(20*sizeof(int*));
	for(i=0;i<20;i++){
		benzerlikMatrisi[i]=malloc(20*sizeof(int));
	}

				
	

	while(secim){	//menu	
		system("cls");
		printf("========================================================================================================================\n\n");
		printf("1.Jaccard matrix yazdir\n");
		printf("2.Signature matrix yazdir\n");
		printf("3.Esik degeri ile yazdir\n");
		printf("4.Iki matrisi alt alta yazdir\n");
		printf("0.Cikis\n\n");
		
		shingleSet=malloc(1*sizeof(char**));
		shingleSet[0]=malloc(1*sizeof(char*));
		shingleSet[0][0]=malloc(1*sizeof(char));
		shingleAmount=malloc(1*sizeof(int));
		
		printf("Secim: ");
		scanf("%d",&secim);
		printf("\n");
		for(i=0;i<20;i++){
			for(j=0;j<20;j++){
				benzerlikMatrisi[i][j]=0;
			}
		}	
		switch(secim){
			case 1:
				printf("Shingle giriniz:");
				scanf("%d",&shingleLength);
				for(i=1;i<=20;i++){	//dosyalari oku
					sprintf(fileName, "%d.txt", fileCount+1);
					fp=fopen(fileName,"r");	
					//printf("-----------------------------------------------------------------------------------------------------------------------\n");
					printf("%s okunuyor...",fileName);
					//printf("------------------\n");
					shingleSetOlustur(fp,shingleSet,fileCount,shingleLength,shingleAmount);
					printf(" Shingle sayisi: %d",shingleAmount[fileCount]);
					fileCount++;
					shingleAmount=realloc(shingleAmount,(fileCount+1)*sizeof(int));
					shingleSet=realloc(shingleSet,(fileCount+1)*sizeof(char**));
					shingleSet[fileCount]=malloc(1*sizeof(char*));
					shingleSet[fileCount][0]=malloc(1*sizeof(char));
					printf("\n");		
				}
				jaccardYazdir(shingleAmount,shingleSet);
				getch();
				break;
			case 2:
				printf("Shingle giriniz:");
				scanf("%d",&shingleLength);
				for(i=1;i<=20;i++){	//dosyalari oku
					sprintf(fileName, "%d.txt", fileCount+1);
					fp=fopen(fileName,"r");	
					//printf("-----------------------------------------------------------------------------------------------------------------------\n");
					printf("%s okunuyor...",fileName);
					//printf("------------------\n");
					shingleSetOlustur(fp,shingleSet,fileCount,shingleLength,shingleAmount);
					printf(" Shingle sayisi: %d",shingleAmount[fileCount]);
					fileCount++;
					shingleAmount=realloc(shingleAmount,(fileCount+1)*sizeof(int));
					shingleSet=realloc(shingleSet,(fileCount+1)*sizeof(char**));
					shingleSet[fileCount]=malloc(1*sizeof(char*));
					shingleSet[fileCount][0]=malloc(1*sizeof(char));
					printf("\n");		
				}
				signatureYazdir(shingleAmount,shingleSet,0,0,benzerlikMatrisi);
				getch();
				break;
			case 3:
				printf("Shingle giriniz:");
				scanf("%d",&shingleLength);
				printf("Threshold girin:");
				scanf("%f",&threshold);
					
				for(i=1;i<=20;i++){	//dosyalari oku
					sprintf(fileName, "%d.txt", fileCount+1);	//fileName'i guncelle
					fp=fopen(fileName,"r");	
					//printf("-----------------------------------------------------------------------------------------------------------------------\n");
					printf("%s okunuyor...",fileName);
					//printf("------------------\n");
					shingleSetOlustur(fp,shingleSet,fileCount,shingleLength,shingleAmount);
					printf(" Shingle sayisi: %d",shingleAmount[fileCount]);
					fileCount++;
					shingleAmount=realloc(shingleAmount,(fileCount+1)*sizeof(int));
					shingleSet=realloc(shingleSet,(fileCount+1)*sizeof(char**));	//yeni dosya icin memoryde yer ac
					shingleSet[fileCount]=malloc(1*sizeof(char*));
					shingleSet[fileCount][0]=malloc(1*sizeof(char));
					printf("\n");		
				}
				signatureYazdir(shingleAmount,shingleSet,1,threshold,benzerlikMatrisi);
				printf("\n");	
				getch();
				break;
			case 4:
				printf("Shingle giriniz:");
				scanf("%d",&shingleLength);
				for(i=1;i<=20;i++){	//dosyalari oku
					sprintf(fileName, "%d.txt", fileCount+1);
					fp=fopen(fileName,"r");	
					//printf("-----------------------------------------------------------------------------------------------------------------------\n");
					printf("%s okunuyor...",fileName);
					//printf("------------------\n");
					shingleSetOlustur(fp,shingleSet,fileCount,shingleLength,shingleAmount);
					printf(" Shingle sayisi: %d",shingleAmount[fileCount]);
					fileCount++;
					shingleAmount=realloc(shingleAmount,(fileCount+1)*sizeof(int));
					shingleSet=realloc(shingleSet,(fileCount+1)*sizeof(char**));
					shingleSet[fileCount]=malloc(1*sizeof(char*));
					shingleSet[fileCount][0]=malloc(1*sizeof(char));
					printf("\n");		
				}
				jaccardYazdir(shingleAmount,shingleSet);
				signatureYazdir(shingleAmount,shingleSet,0,0,benzerlikMatrisi);
								
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
		free(shingleSet);
		free(shingleAmount);
		strcpy(fileName,"1.txt");		
		fileCount=0;
	}
		
	return 0;
}
