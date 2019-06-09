/*
Name: Serdar Efe
Student no: 15011005
E-Mail: efeser80@gmail.com
Compiler used: GCC
IDE: Dev-C++ 5.11
Operating System: Windows 10

*/


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
			
#define tableSize 267581

int hangisienKucuk(int x,int y,int z){	//verilen uc ifadeden hangisinin en kucuk oldugunu bulur
	int min=0;							//minimum deger x ise 0,y ise 1,z ise 2 dondurur.
	min =x;
	if (y<min){
		min=y;
	}
	if(z<min){
		min=z;
	}
	if(min==x){
		return 0;
	}else if(min==y){
		return 1;
	}else{
		return 2;
	}
}
int dosyayafarkYazdirma(char* arananKelime,char** dictionary,int enyakinkelimeAdresi,FILE *f){
	int** matrix;
	int i=0;
	int j=0;
	int k=0;
	int M;	//satir sayisi
	int N;	//sutun sayisi
	int satirAdresi=enyakinkelimeAdresi;	//en yakin kelimenin hash adresi
	int hamle;		//matriste hangi yone dogru gittigimizin bilgisini tutan int degeri
	char farkDizisi[128];	//dolu matristen sonundan basa dogru kelimeyi yazdigimiz dizi
	int  hamleDizisi[128];	//insert delete change islemlerinden hangisini hangi sirayla yaptigimizi tutan dizi
	
	for(i=0;i<128;i++){		//dizi sifirlama
		farkDizisi[i] = '\0';
	}
	
	for(i=0;i<128;i++){	//dizi sifirlama
		hamleDizisi[i]==-1;
	}
	
	M=strlen(dictionary[satirAdresi])+1;	// satir ve sutun degerlerini belirleme
	N=strlen(arananKelime)+1;
			
	matrix = (int**) malloc (M * sizeof(int*));	//memory islemleri
			
	for(i=0;i<M;i++){
		matrix[i] = malloc (N * sizeof(int*));	//memory islemleri
	}
	for(i=0;i<M;i++){		//matris sifirlama
		for(j=0;j<N;j++){
			matrix[i][j]=0;
		}
	}
			
	for(i=0;i<M;i++){		//insert remove change matrisini olusturma
		for(j=0;j<N;j++){
			
			if(i==0){
				matrix[i][j]=j;
			}else if(j==0){
				matrix[i][j]=i;
			}else if(dictionary[satirAdresi][i-1]== arananKelime[j-1]){
				matrix[i][j]=matrix[i-1][j-1];
			}else{
				matrix[i][j]= min(matrix[i][j-1]+1,  matrix[i-1][j]+1,  matrix[i-1][j-1]+2);
                                 //delete			 //insert			//change
			}
		}
	}
	
	i=M-1;	//matrisin satir ve sutununu sondan baslatma
	j=N-1;
	
	if(M<N){	//fark dizisine sondan baslayacagimiz icin sonuncu karakterin 
		k=N-1;	//kacinci gozde olacagini belirleme
	}else{
		k=M-1;
	}
	
	fprintf(f,"%s ",arananKelime);
	fprintf(f,"%s ",dictionary[satirAdresi]);	
	
	while((i!=0)&&(j!=0)){
		hamle=hangisienKucuk(matrix[i][j-1],  matrix[i-1][j],  matrix[i-1][j-1]);	//matriste hangi yone ilerleyecegimizi bulma
        if(hamle==0){
        	farkDizisi[k]=arananKelime[j-1];		//delete isleminde kullanicinin girdigi kelimeden silinen harf diziye alinir
        	if(matrix[i][j-1]!=matrix[i][j]){
        		hamleDizisi[k]=hamle+1;
			}else{
				hamleDizisi[k]=0;
			}
        	j--;
		}else if(hamle==1){
        	farkDizisi[k]=dictionary[satirAdresi][i-1];	//insert isleminde sozlukteki kelimeden insert edilen harf diziye alinir
        	if(matrix[i-1][j]!=matrix[i][j]){
        		hamleDizisi[k]=hamle+1;
			}else{
				hamleDizisi[k]=0;
			}
			i--;
		}else{
			farkDizisi[k]=dictionary[satirAdresi][i-1];	//change isleminde sozlukteki cevrilmis harf diziye alinir
			if(matrix[i-1][j-1]!=matrix[i][j]){
        		hamleDizisi[k]=hamle+1;
			}else{
				hamleDizisi[k]=0;
			}
			i--;
			j--;
		}
		k--;	
	}
	i=0;
	while(farkDizisi[i]=='\0'){	//eger farkdizisinde bosluk olusmussa
		i++;					//diziyi kaydirarak boslugu doldurma
	}
	j=i;
	while(farkDizisi[i]!='\0'){
		farkDizisi[i-j]=farkDizisi[i];
		hamleDizisi[i-j]=hamleDizisi[i];
		i++;
	}
	
	/*
	for(i=0;i<N;i++){		//dosyaya yazilacak bilgilerin aynisini ekrana yazdirma.Debug icin
		printf("%c",farkDizisi[i]);
		if((hamleDizisi[i])!=0){
			if((hamleDizisi[i])==1){
				printf("_delete");
			}else if((hamleDizisi[i])==2){
				printf("_insert");
			}else{
				printf("_change");
			}
		}
		printf(" ");
	}
	*/
	
	for(i=0;i<N;i++){		//fark dizisi dosyaya yazdirilir
		fprintf(f,"%c",farkDizisi[i]);
		if((hamleDizisi[i])!=0){		//eger hamleDizisi'nin degeri 0'dan farkli ise delete,insert ya da change islemlerinden
			if((hamleDizisi[i])==1){	//biri gerceklesmistir.hamleDizisi'nin degerine gore hangi islemin gerceklestirildigi
				fprintf(f,"_delete");	//bilgisi harfin yanina yazilir
			}else if((hamleDizisi[i])==2){
				fprintf(f,"_insert");
			}else{
				fprintf(f,"_change");
			}
		}
		fprintf(f," ");
	}

	fprintf(f,"\n");
	
	for(i=0;i<M;i++){	//olusturdugumuz matrisler free edilir
		free(matrix[i]);	
	}
	free(matrix);
	
}
int min(int x,int y,int z){	//3 tam sayidan en kucuk olaninin degerini donduren fonksiyon
	int min;
	min=x; //insert
				
	if(y<min){	//remove
		min=y;
	}
	if(z<min){ //replace
		min=z;
	}
	return min;		
}

int levenshteinMatrix(char* arananKelime,char** dictionary,int enyakinkelimeAdresi){
	int** matrix;
	int i=0;
	int j=0;
	int k=0;
	int M;
	int N;
	int satirAdresi=enyakinkelimeAdresi;
	
	M=strlen(dictionary[satirAdresi])+1;	// satir ve sutun degerlerini belirleme
	N=strlen(arananKelime)+1;
			
	matrix = (int**) malloc (M * sizeof(int*));	//memory islemleri
			
	for(i=0;i<M;i++){
		matrix[i] = malloc (N * sizeof(int*));	//memory islemleri
	}
	for(i=0;i<M;i++){		//matris sifirlama
		for(j=0;j<N;j++){
			matrix[i][j]=0;
		}
	}
			
	for(i=0;i<M;i++){		//insert remove change matrisini olusturma
		for(j=0;j<N;j++){
			
			if(i==0){
				matrix[i][j]=j;
			}else if(j==0){
				matrix[i][j]=i;
			}else if(dictionary[satirAdresi][i-1]== arananKelime[j-1]){
				matrix[i][j]=matrix[i-1][j-1];
			}else{
				matrix[i][j]= min(matrix[i][j-1]+1,  matrix[i-1][j]+1,  matrix[i-1][j-1]+2); 
                                 //insert			 //remove			//change
			}
		}
	}
	
	printf("\n    ");	//levensthein matrisini ekrana yazdirma
	for(i=0;i<N;i++){
		printf("%c ",arananKelime[i]);
	}
	printf("\n");
	
	for(i=0;i<M;i++){
		if(i!=0){
			printf("%c ",dictionary[satirAdresi][i-1]);
		}else{
			printf("  ");
		}	
		for(j=0;j<N;j++){
			printf("%d ",matrix[i][j]);
		}
	printf("\n");
	}
	printf("\nEn yakin kelimenin levenshtein uzakligi: %d",matrix[M-1][N-1]);
	
	
	for(i=0;i<M;i++){	//matris free etme
		free(matrix[i]);
	}
	free(matrix);
	
	
}

int addrBul(char* buffer,char** dictionary){	//verilen kelimenin adresini bulma
	int i;
	int wordLength;
	int addr=0;
	wordLength=strlen(buffer);

	
	for(i=0;i<wordLength;i++){
		addr += (buffer[i]-'a')*26*i;
	}
	
	if(addr<0){
		addr = addr + tableSize;
	}
	
	addr = addr % tableSize;
	
	while(dictionary[addr] != NULL){
		addr++;
		addr=(addr) % tableSize;
	}
	return addr;
}

int kelimeAra(char* arananKelime,char** dictionary){	//verilen kelimenin sozlukte olup olmadigini bulan fonksiyon
	int i,j;
	int wordLength;
	int addr=0;
	wordLength=strlen(arananKelime);	
	for(i=0;i<wordLength;i++){
		addr += (arananKelime[i]-'a')*26*i;
	}
	
	if(addr<0){
		addr = addr + tableSize;
	}
	
	addr = addr % tableSize;	
	
	i=0;
	j=addr;
	
	if(dictionary[j]!=NULL){
		while((i<tableSize)&&(strcmp(dictionary[j],arananKelime)!=0)){
			if(dictionary[j+1]!=NULL){
				j++;
				j=(j) % tableSize;
			}
			i++;	
		}
	}else{
		i=tableSize;
	}
	
	
	if(i==tableSize){
		return -1;
	}else{
		return j;
	}
	
}

int levenshteinDistance(char* arananKelime,char** dictionary){	//levensthein matrisi olusturarak en kisa uzakligi bulan fonksiyon
	int** matrix;
	int satirAdresi=0;
	int i=0;
	int j=0;
	int k=0;
	int M;
	int N;
	int enkisaUzaklik=128; //levenshtein uzakligi.sonradan min islemine girecegi icin buyuk bir degerle baslatilmistir.
	int enyakinkelimeAdresi;	//en benzer kelimenin hash adresi
	for(satirAdresi=0;satirAdresi<tableSize;satirAdresi++){
		if(dictionary[satirAdresi]!=NULL){
			
			M=strlen(dictionary[satirAdresi])+1;	// satir ve sutun degerlerini belirleme
			N=strlen(arananKelime)+1;
			
			matrix = (int**) malloc (M * sizeof(int*));	//memory islemleri
			
			for(i=0;i<M;i++){
				matrix[i] = malloc (N * sizeof(int*));	//memory islemleri
			}
			
			for(i=0;i<M;i++){		//matris sifirlama
				for(j=0;j<N;j++){
					matrix[i][j]=0;
				}
			}
			
			for(i=0;i<M;i++){		//insert remove change matrisini olusturma
				for(j=0;j<N;j++){
					
					if(i==0){
						matrix[i][j]=j;	//kelimelerden birinin uzunlugu 0 ise
					}else if(j==0){
						matrix[i][j]=i; //diger kelime mecburen harf degerini alir
					}else if(dictionary[satirAdresi][i-1]== arananKelime[j-1]){ //harfler ayni ise bir islem gerceklesmez.
						matrix[i][j]=matrix[i-1][j-1];
					}else{
						matrix[i][j]= min(matrix[i][j-1]+1,  matrix[i-1][j]+1,  matrix[i-1][j-1]+2);  //harfler farkli ise 3 islemden biri gerceklesir.
		                                 //insert			 //remove			//change
					}
				}
			}
			
			
		if(matrix[M-1][N-1]<enkisaUzaklik){	//minimum degeri bulma
			enyakinkelimeAdresi=satirAdresi;
			enkisaUzaklik=matrix[M-1][N-1];
		}
		k++;
		}		
	}
	
	for(i=0;i<M;i++){	//matris free etme
		free(matrix[i]);
	}
	free(matrix);
	
	if (enkisaUzaklik>8){	//benzerlik threshold'u.8 den buyuk benzerlikler benzer kelime olarak kabul edilmez
		
		enyakinkelimeAdresi=-1;
		
	}
	return enyakinkelimeAdresi;
}
			

int main() {
	
	//Kelime sayisi=133758
	//Kelime sayisi*2'ye en yakýn asal sayý=267581

	int i,j;	//sayac degiskeni
	int secim=-1; //menu degiskeni
	int addr=0;	//adres bilgisi
	int enkucukUzaklik;	//minimum levenshthein uzakligi
	int wordLength;	//kbuffer uzunlugunu tutan degisken
	int enyakinkelimeAdresi;	//en benzer kelimenin hash adresi
	char arananKelime[128];	//aranan kelimeyi tutacak buffer
	char **dictionary;		//sozluk matris pointeri
	
	dictionary = (char**) malloc (tableSize * sizeof(char*));	//memory islemleri
	
	for(i=0;i<tableSize;i++){	//matris sifirlama
		dictionary[i]='\0';
	}

	
	char buffer[128];	//dosyadan okunan kelimeyi tutan buffer
	
	FILE* file_dictionary;
	file_dictionary = fopen("lastDict.txt","r");	//dosya okuma islemleri
	if (file_dictionary == NULL){
        printf("Failed to load dictionary");
    } 
	i=0;
    while (fscanf(file_dictionary, "%s", buffer) > 0){	//okunan kelimelerin sozluge hashlenmesi
    	addr=addrBul(buffer,dictionary);
    	wordLength=strlen(buffer);
    	dictionary[addr]= malloc (wordLength * sizeof(char*));
    	strcpy(dictionary[addr],buffer);
    	printf("%d.) %s kelimesi %d adresine eklendi\n",i,buffer,addr);
    	i++;
    }
    
    fclose(file_dictionary); //dosyayi okuduktan sonra kapatma
    printf("Bitti");
    
    printf("\nMenuye gitmek icin bir tusa basiniz...");
    
    
	getch();
    
    FILE *f = fopen("testout.txt", "w");
    FILE *test_file = fopen("test.txt", "r");
    while(secim){	//menu	
		system("cls");
		printf("========================================================================================================================");
		printf("1.Kelime gir\n");
		printf("2.Test dosyasini testout dosyasina cikar\n");
		printf("0.Cikis\n\n");
		printf("Secim: ");
		scanf("%d",&secim);
		printf("\n");
		switch(secim){
			case 1:	
				printf("Sadece kucuk harf kullanarak kelimenizi girin: ");
				scanf("%s",&arananKelime);
				addr=kelimeAra(arananKelime,dictionary);
				if(addr==-1){
					printf("Kelime sozlukte yok.\n\n");
					
					enyakinkelimeAdresi=levenshteinDistance(arananKelime,dictionary);
					
					if(enyakinkelimeAdresi!=-1){//en yakin kelime thresholdun altindaysa kabul edilir
						printf("Verdiginiz kelimeye en yakin olan kelime: %d. adreste sakli ",enyakinkelimeAdresi);
						printf("\nen yakin kelime: %s",dictionary[enyakinkelimeAdresi]);
						printf("\n========================================================================================================================");
						printf("En yakin kelimeyle olan matris karsilastirmasi:\n");
						levenshteinMatrix(arananKelime,dictionary,enyakinkelimeAdresi);
						printf("\n========================================================================================================================");						
					}else{//thresholdun ustundeki kelimeler benzer kabul edilmez
						printf("Verdiginiz kelime sozlukteki hicbir kelimeye benzememektedir(NONE).");
					}

				}else{	//kelimenin aynisi sozlukte bulunmussa
					printf("Kelime sozlukte %d . satirda var.",addr);
				}
				printf("\nDevam etmek icin bir tusa basiniz...");
				getch();
				break;
			
			case 2:
				while (fscanf(test_file, "%s", buffer) > 0){//test dosyasindaki kelimeleri okuma
						strcpy(arananKelime,buffer);
						addr=kelimeAra(arananKelime,dictionary);
						if(addr==-1){//kelimenin aynisi sozlukte yoksa
							enyakinkelimeAdresi=levenshteinDistance(arananKelime,dictionary); //en ykain kelimenin adresini bulma
							if(enyakinkelimeAdresi!=-1){	//uzaklik thresholdun altindayse
								//levenshteinMatrix(arananKelime,dictionary,enyakinkelimeAdresi);
								dosyayafarkYazdirma(arananKelime,dictionary,enyakinkelimeAdresi,f);	
							}else{
								fprintf(f, "%s NONE\n", arananKelime);
							}
						}else{
							fprintf(f, "%s OK\n", arananKelime);
						}
				}
				printf("\nDosyaya yazdirma islemi bitti.");
				getch();
				exit(0);
			
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
