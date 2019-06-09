/*

Serdar Efe
15011005

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct kuyruk {	//kuyruk yapisi
    int sira;
    struct kuyruk *sonraki;
} kuyruk;

kuyruk *ilk = NULL, *son, *yeni, *sil;

void kuyrukEkle(int sira,char** kelimeler) {	//kuyruga eleman ekleme
    yeni = (kuyruk*) malloc(sizeof (kuyruk));
    yeni->sira = sira;
    yeni->sonraki = NULL;
 
    if (ilk == NULL) {
        ilk = (kuyruk *) malloc(sizeof (kuyruk));
        ilk = yeni;
        son = ilk;
    } else {
        son->sonraki = yeni;
        son = son->sonraki;
    }
    //printf("\n%s Kuyruga eklendi",kelimeler[sira]);
 
}
 
void kuyrukSil(char** kelimeler) {	//kuyruktan eleman cekme
    if (ilk == NULL) {
        printf("Kuyruk bos\n");
    } else {
        sil = (kuyruk*) malloc(sizeof (kuyruk));
        sil = ilk;
        ilk = ilk->sonraki;
       // printf("\n%s Kuyruktan cikarildi\n", kelimeler[sil->sira]);
        free(sil);
    }
}

void kuyrukYazdir(char** kelimeler) {	//kuyrugu ekrana yazdirma
	printf("Kuyruk: ");
    if (ilk == NULL) {
        printf("Kuyruk Bos\n");
    } else {
        kuyruk *tmp = ilk;
        while (tmp != NULL) {
            printf("%d <-- ", tmp->sira);
            tmp = tmp->sonraki;
        }
        printf("\n");
        printf("\n");
        
        tmp = ilk;
        while (tmp != NULL) {
            printf("%s", kelimeler[tmp->sira]);
            tmp = tmp->sonraki;
        }
    }
    
    
 
}

void kuyrukSifirla(){	//kuyruk sifirlama
	while(ilk!=NULL){
		sil = (kuyruk*) malloc(sizeof (kuyruk));
        sil = ilk;
        ilk = ilk->sonraki;
        free(sil);
	}
}
int** komsulukMatrisiOlustur(char** kelimeler,int kelimeSayisi,int** matris){
	int i=0;
	int j=0;
	int k=0;
	int eslesme=0;
	for(i=0;i<2415;i++){
		for(j=0;j<2415;j++){
			if(i!=j){
				for(k=0;k<5;k++){
					if(kelimeler[i][k]==kelimeler[j][k]){
						eslesme++;
					}
				}
			if(eslesme==4){
				matris[i][j]=1;
			}
			eslesme=0;	
			}
		}
	}
	
	return matris;
}

int siraBul(char* aranan,char** kelimeler){		//verilen kelimenin indisini bulma
	int bulundu=0;
	int i=0;
	int j=0;
	int eslesme=0;
	int sira=-1;
	while((bulundu != 1)&&(j<2415)){
		for(i=0;i<5;i++){
			if(aranan[i]==kelimeler[j][i]){
			eslesme++;
			}	
		}
		if(eslesme==5){
			sira=j;
			bulundu++;
		}
		eslesme=0;
		j++;
	}
	return sira;
}

void gecisSorgula(int ilkKelimeSira,int sonKelimeSira,int** matris){	//tek adimda gecisin varligina bakma
	if(matris[ilkKelimeSira][sonKelimeSira]==1){
		printf("\nTek adimda gecis var\n");
	}else{
		printf("\nTek adimda gecis yok\n");
	}
}

void gecisBul(int ilkKelimeSira,int sonKelimeSira,int** matris,int kuyruktakiler[1000],char** kelimeler,int kuyruk,int* yolDizisi,int bulundu){
						//en önemli fonksiyon
	int i=0;	//matrisde gezinme indisi
	int j=0;	//son kelime indisi
	int adimSayisi=0;
	while((bulundu==0)&&(i<2415)){
		if(matris[ilkKelimeSira][i]==1){
			if(i == sonKelimeSira){
				kuyrukEkle(i,kelimeler);
				kuyruktakiler[kuyruk]=i;
				yolDizisi[i]=ilkKelimeSira;
				kuyruk++;
				bulundu=1;
				j=sonKelimeSira;
				printf("\n\n#Gecis Yolu: ");
				while(yolDizisi[j]!=-1){			//yol dizisinde her elemanin
					printf("%s <- ",kelimeler[j]);	//hangi elemandan geldigine bakarak
					j=yolDizisi[j];					//gecis yolu cizme
					adimSayisi++;
					
				}
				printf("%s",kelimeler[j]);
				printf("\n");
				printf("\n%d adimda tamamlandi.",adimSayisi);
				
			}else if((i != sonKelimeSira)&&(kuyruktaVarMi(i,kuyruktakiler))){
				kuyrukEkle(i,kelimeler);
				yolDizisi[i]=ilkKelimeSira;
				kuyruktakiler[kuyruk]=i;
				kuyruk++;	

			}

			
		}
		i++;		
	}
	if(ilk!=NULL){
			kuyrukSil(kelimeler);
	}
	if((ilk==NULL)&&(bulundu==0)){
		printf("\niki kelime arasi gecis yok\n");
	}else if(bulundu==0){
		gecisBul(ilk->sira,sonKelimeSira,matris,kuyruktakiler,kelimeler,kuyruk,yolDizisi,bulundu);
	}
	
}

int kuyruktaVarMi(int sayi,int kuyruktakiler[2415]){	//verilen indisin kuyrukta olup olmadigina bakma
	int i=0;											//kuyruk yapisinda ayni kuyruga tekrar girmemek icin onemli
	int found=1;
	while(kuyruktakiler[i]!=-1){
		if(sayi==kuyruktakiler[i]){
			found=0;
		}
	i++;
	}
	return found;
}

int main() {
	int** matris;		//komsuluk matrisi	
	int adimSayisi=0;	
	char* kelimeler[2416];	//okunan kelimeler
	int yedekDizi[1000];	//indisin daha once kullanilamis olmasina bakan degisken
	int yolDizisi[2416];	//hedefe ulasildiktan sonra gidis yolunu cizmeye yarayan degisken
	char ilkKelime[6];		//baslangic kelimesi
	char sonKelime[6];		//hedef kelimesi
	int secim=1;			//menu araci
	int ilkKelimeSira;		//baslangic kelimesinin indisi
	int sonKelimeSira;		//hedef kelimesinin indisi
	char line[128];			//txt'den okunan satirlani tutan gecici degisken
	int bulundu=0;			//hedefe ulasildigini gosteren degisken
	int kelimeSayisi=0;		//txt'nin kac kelimeden olustugunu gosteren degisken
	int kuyruk=0;			//yedek dizinin kacinci indisinde kaldigimizi gosteren degisken
	int i,j,k;				//dongu degiskenleri
	
	for(i=0;i<1000;i++){	//dizileri sifirlama
		yedekDizi[i]=-1;
	}
	
	for(i=0;i<2416;i++){
		yolDizisi[i]=-1;
	}
	FILE * fp = fopen("kelime.txt","r");	//kelimeleri okutma
	i =0;
	if ( fp != NULL ){	
    	while ( fgets ( line, sizeof line, fp ) != NULL ){
				kelimeler[i]=malloc(sizeof(line));
				j = strlen(line)-1;
				if (line[j] == '\n'){
					line[j] = '\0';
				}

    			strcpy(kelimeler[i],line);
    			
 				if ((j > 0) && (kelimeler[j] == '\n'))	//kelimenin sonundaki \n isaretini silme
  				kelimeler[j] = '\0';
    			i++;
    			kelimeSayisi++;
    		}
    			
	matris=(int**)malloc(kelimeSayisi*sizeof(int*));	//komsuluk matrisi allocate etme
		for(i=0;i<kelimeSayisi;i++){
				matris[i]=calloc(kelimeSayisi,sizeof(int));		
		}
	}
	
	komsulukMatrisiOlustur(kelimeler,kelimeSayisi,matris);	//komsuluk matrisi isleme
	while(secim){	//menu
		system("cls");
		printf("- HOS GELDINIZ -");
		printf("\n\n");
		printf("1.Kelime donusumu yap \n");
		printf("2.Tek adimda gecis sorgula\n");
		printf("0.exit\n");
		printf("\nSecim: ");
		scanf("%d",&secim);
		printf("\n");
		switch(secim){
			case 1:	//gecisBul
				printf("\nIlk kelimeyi giriniz:");
				do{
					scanf("%s",&ilkKelime);
					if(strlen(ilkKelime)!=5){
						printf("\nLutfen 5 harfli bir kelime giriniz.\n");
					}else if(siraBul(ilkKelime,kelimeler)==-1){
						printf("\nKelime sozlukte yok.\n");
					}
				}while((strlen(ilkKelime)!=5)||(siraBul(ilkKelime,kelimeler)==-1));
				
				printf("Son kelimeyi giriniz:");
				do{
					scanf("%s",&sonKelime);
					if(strlen(sonKelime)!=5){
						printf("\nLutfen 5 harfli bir kelime giriniz.\n");
					}else if(siraBul(sonKelime,kelimeler)==-1){
						printf("\nKelime sozlukte yok.\n");
					}
				}while((strlen(sonKelime)!=5)||(siraBul(sonKelime,kelimeler)==-1));
				
				ilkKelimeSira=siraBul(ilkKelime,kelimeler);
				sonKelimeSira=siraBul(sonKelime,kelimeler);
				
				printf("ilk kelimenin sirasi: %d\n",ilkKelimeSira);
				printf("son kelimenin sirasi: %d\n",sonKelimeSira);
				
				
				kuyrukEkle(ilkKelimeSira,kelimeler);	//baslangic kelimesinin girisini elle yapiyoruz
				yedekDizi[0]=ilkKelimeSira;
				kuyruk++;
				gecisBul(ilkKelimeSira,sonKelimeSira,matris,yedekDizi,kelimeler,kuyruk,yolDizisi,bulundu);
				
				kuyrukSifirla();		//bir sonraki arama icin butun degiskenleri sifirlama
				for(i=0;i<1000;i++){
				yedekDizi[i]=-1;
				}
	
				for(i=0;i<2416;i++){
					yolDizisi[i]=-1;
				}
				bulundu=0;
				kuyruk=0;
				
				printf("\n-----Devam etmek icin bir tusa basin.-----");
				getch();
				break;
				
			case 2:	//gecisSorgula
				printf("ilk kelimeyi gir:");
				scanf("%s",&ilkKelime);
				printf("son kelimeyi gir:");
				scanf("%s",&sonKelime);
				
				ilkKelimeSira=siraBul(ilkKelime,kelimeler);
				sonKelimeSira=siraBul(sonKelime,kelimeler);
				
				gecisSorgula(ilkKelimeSira,sonKelimeSira,matris);
				printf("\nDevam etmek icin bir tusa basin.");
				getch();
				break;
			case 0:
				exit(0);
				break;
			default:
				printf("Gecersiz secim.");
				printf("\n--------------------------------- ");
				printf("\nDevam etmek icin bir tusa basin.");
				getch();
				break;	
		}	
		
	}
		
	return 0;
}

