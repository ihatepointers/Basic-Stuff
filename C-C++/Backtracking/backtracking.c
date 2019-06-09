#include <stdio.h>
#include <stdlib.h>

/*
Name: Serdar Efe
Student no: 15011005
E-Mail: efeser80@gmail.com
Compiler used: GCC
IDE: Dev-C++ 5.11
Operating System: Windows 10 64-bit

*/


int sagaKaydir(int** matris,int boyut,int satirNumarasi){
	int tmp;
	int j;
	for(j=0;j<boyut-1;j++){
		tmp=matris[satirNumarasi][j+1];
		matris[satirNumarasi][j+1]=matris[satirNumarasi][0];
		matris[satirNumarasi][0]=tmp;
	}
	
	return 0;
}

int matrisDuzenleAdimli(int** matris,int boyut,int satirNumarasi,int* hamle){
	
	int i;
	int j;
	int wrong=0;
	int yanlisSatir;
	
	for(i=1;i<=satirNumarasi;i++){
		for(j=0;j<boyut;j++){
			if(matris[satirNumarasi][j]==matris[satirNumarasi-i][j]){
				wrong=1;
			}
		}	
	}
	

	if (wrong==1){
		if(hamle[satirNumarasi]!=boyut-1){
			sagaKaydir(matris,boyut,satirNumarasi);
			hamle[satirNumarasi]++;
			
			printf("%d.satir %d. kez saga kaydirildi\n\n",satirNumarasi+1,hamle[satirNumarasi]);
			for(i=0;i<boyut;i++){
				for(j=0;j<boyut;j++){
					printf("%d ",matris[i][j]);
				}
				printf("\n");
			}
			printf("------------------------------\n");	
			
			
			matrisDuzenleAdimli(matris,boyut,satirNumarasi,hamle);
		}else{
			while(hamle[satirNumarasi]==boyut-1){
				hamle[satirNumarasi]=0;
				satirNumarasi--;
			}
			if(satirNumarasi==-1){
				return -1;
			}else{
				sagaKaydir(matris,boyut,satirNumarasi);
				hamle[satirNumarasi]++;
				
				printf("%d.satir %d. kez saga kaydirildi\n\n",satirNumarasi+1,hamle[satirNumarasi]);
				
				for(i=0;i<boyut;i++){
					for(j=0;j<boyut;j++){
						printf("%d ",matris[i][j]);
					}
					printf("\n");
				}
				printf("------------------------------\n");			
				
				
				matrisDuzenleAdimli(matris,boyut,satirNumarasi,hamle);
			}
		}
	}else{
		if(satirNumarasi!=boyut-1){
			matrisDuzenleAdimli(matris,boyut,satirNumarasi+1,hamle);
		}else{
			return 0;
		}
	}
	
}

int matrisDuzenle(int** matris,int boyut,int satirNumarasi,int* hamle){
	
	int i;
	int j;
	int wrong=0;
	int yanlisSatir;
	
	for(i=1;i<=satirNumarasi;i++){
		for(j=0;j<boyut;j++){
			if(matris[satirNumarasi][j]==matris[satirNumarasi-i][j]){
				wrong=1;
			}
		}	
	}
	
	if (wrong==1){
		if(hamle[satirNumarasi]!=boyut){
			sagaKaydir(matris,boyut,satirNumarasi);
			hamle[satirNumarasi]++;
			matrisDuzenle(matris,boyut,satirNumarasi,hamle);
		}else{
			while(hamle[satirNumarasi]==boyut){
				hamle[satirNumarasi]=0;
				satirNumarasi--;
			}
			sagaKaydir(matris,boyut,satirNumarasi);
			hamle[satirNumarasi]++;
			matrisDuzenle(matris,boyut,satirNumarasi,hamle);
		}
	}else{
		if(satirNumarasi!=boyut-1){
			matrisDuzenle(matris,boyut,satirNumarasi+1,hamle);
		}else{
			return 0;
		}
	}
}

int main() {
	int secim=-1;
	int boyut;
	int i=0;
	int j=0;
	int k=0;
	char buffer[128];
	int satirNumarasi;
	int** matris;
	int* hamle;
	
	//			       0       1      2      3		 4	     5		  6		 7
	char* renkler[]={"Sari","Mavi","Yesil","Mor","Beyaz","Siyah","Kirmizi","Gri"};
	for(i=0;i<8;i++){
		printf("%d. %s \n",i,renkler[i]);
	}
	
	printf("NxN matrisin boyutunu (N) giriniz:");
	scanf("%d",&boyut);
	matris=malloc(boyut*sizeof(int*));
	for(i=0;i<boyut;i++){
		matris[i]=malloc(boyut*sizeof(int));
	}
	
	printf("Matrisi giriniz:");
	for(i=0;i<boyut;i++){
		for(j=0;j<boyut;j++){
			scanf("%s",&buffer);
			for(k=0;k<8;k++){
				if(strcmp(renkler[k],buffer)==0){
					matris[i][j]=k;
				}
			}
			
		}
	}
	hamle=calloc(boyut,sizeof(int));
	
	while(secim){	//menu	
		system("cls");
		printf("========================================================================================================================");
		printf("Matris:\n\n");
		for(i=0;i<boyut;i++){
			for(j=0;j<boyut;j++){
				printf("%d ",matris[i][j]);
			}
			printf("\n");
		}
			printf("\n");
	
		printf("========================================================================================================================");
		printf("1.Matrisi duzenle\n");
		printf("2.Matrisi her adimi gostererek duzenle\n");
		printf("0.Cikis\n\n");
		printf("Secim: ");
		scanf("%d",&secim);
		printf("\n");
		switch(secim){
			case 1:
				satirNumarasi=0;
				if(matrisDuzenle(matris,boyut,satirNumarasi,hamle)==0){
					printf("\nDuzenlenmis matris olusturuldu.");
					printf("\nYeni matris:\n");
					for(i=0;i<boyut;i++){
						for(j=0;j<boyut;j++){
							printf("%d ",matris[i][j]);
						}
						printf("\n");
					}
				}else{
					printf("\n Verilen matrisin cozumu yok.");
				}
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;
			case 2:
				
				satirNumarasi=0;
				if(matrisDuzenleAdimli(matris,boyut,satirNumarasi,hamle)==0){
					printf("\n=================================");
					printf("\nDuzenlenmis matris olusturuldu.\n");
					printf("=================================\n");
					
					printf("\nMatrisin son hali:\n");
					for(i=0;i<boyut;i++){
						for(j=0;j<boyut;j++){
							printf("%s ",renkler[matris[i][j]]);
						}
						printf("\n");
					}
				}else{
					printf("\n=================================");
					printf("\nVerilen matrisin cozumu yok!\n");
					printf("=================================\n");
				}
				
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;
			case 0:
				exit(0);
				free(matris);
				free(hamle);
				break;
			default:
				printf("Gecersiz secim.");
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;	
		}		
	}
	free(matris);
	free(hamle);
	return 0;
}
