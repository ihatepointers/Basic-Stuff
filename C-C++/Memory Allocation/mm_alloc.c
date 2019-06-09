#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include "mm_alloc.h"

#define BLOCK_SIZE 28 //metadata'nin buyuklugu data kismi haric hesaplandigi icin
		      //elle girilmek zorundadir
void *base = NULL;

int nearest4(int x){  //verilen buyuklugu en yakin 4'un kati olan sayiya esitleme
    x += x%4;
	return x;
}

t_block get_block(void *p){ //verisinin pointer'i verilen block'un metadata adresini donduren fonksiyon
    return (p - BLOCK_SIZE);
}

void copy_block(t_block dst, t_block src){  //kaynak block'un verisini hedef block'a kopyalama fonksiyonu

    int *sdata,*ddata;
	int dataCount;
    size_t i;
    sdata = src->data;
    ddata = dst->data;
	dataCount=(src->size/4);    //her bir veri 4 byte oldugu icin
    for(i=0;i<dataCount;i++){   //block buyuklugunun 4'e bolunmesiyle eleman sayisi bulunur
        ddata[i] = sdata[i];
    }

}

void split_block(t_block last,size_t size){ //verilen block'u verilen buyukluk ve kalan olarak 2'ye bolen fonksiyon

    t_block b;
    b=(last->data+size);    //memoryde bulunulan block'un veri adresinden size kadar ileri gidilir
    b->size=last->size-size-BLOCK_SIZE;     //bolunmus block'un yeni buyuklugu hesaplanir
    b->next=last->next;     //burdan asagisi linkli liste islemleri
    if(b->next != NULL){
        b->next->prev=b;
    }
    b->prev=last;
    last->next=b;
    b->free=1;
    last->free=0;
    last->size = size;
}

t_block extend_heap(t_block last, size_t s){    //heap buyutme fonksiyonu

    t_block b = sbrk(0);    //Yeni block olustur.Adres olarak heap'in sonunu ver
    sbrk(s + BLOCK_SIZE);   //heap'i ilerlet
    b->size = s;            //blok buyuklugunu size ata
    b->next = NULL;         //yeni block'un next'i NULL ata
    if(last != NULL){
        while(last->next != NULL){
            last=last->next;
        }
        b->prev=last;
        last->next = b;
    }else{
        b->prev = NULL;     //Eger yeni blok base ise prev'i de NULL ata
    }
    b->free = 0;            //b'yi dolu olarak isaretle
    return (b);             //Yeni b'yi dondur
}

t_block find_block(t_block last, size_t size){  //musait block arama fonksiyonu
    t_block b = base;
    while ((b != NULL)&& !((b->free == 1)&&(b->size >= size))){   //istenilen block linkli listede ilerleyerek ara
        last = b;
        b = b->next;
    }
    if(b == base){
        if ((b->free == 1)&&(b->size >= size)){
           return (b);
        }else{
            return NULL;
        }
    }else{
        return (b);
    }

}

void *mm_malloc(size_t size){
    t_block b,last;
    size_t s;
    s = nearest4(size);
    int i;

    if(size <=0){
        return (NULL);
    }

    if(base != NULL){
        last = base;
        b = find_block(last,s);
        if(b != NULL){
            if((size+BLOCK_SIZE+4) <= b->size){
                split_block(b,size); //eger malloc buyuklugu bulunan block'tan cok kucukse bol
            }
            b->free = 0;    //block bolunemeyecek buyuklukteyse block'u oldugu gibi kullan
        }else{
            b = extend_heap(last,s); //musait block yoksa heap buyut.Yeni block'un adresini b'ye ata
            if(b == NULL){
                return(NULL);
            }
        }
    }else{
        b = extend_heap(NULL,s); //base yoksa(ilk malloc kullanimiysa) heap buyut.ilk adresi b ye ata
        if(!b){
            return(NULL);
        }
        base = b;   //b'deki ilk adresi base olarak ata
    }
    return(b->data);    //block'un verisinin basladigi adresi dondur

}




void mm_free(void *p){

    t_block b;
    b = get_block(p);
    if(b != NULL){
        //b yi free ettikten sonra prev ve next block'lara bakılarak gerekliyse bos bloklar birlestirilir
        if((b->prev != NULL )&&(b->prev->free==1)&&(b->next != NULL)&&(b->next->free==1)){ //prev ve next block free ise
                b->prev->size=b->prev->size+b->size+b->next->size;

                if(b->next != NULL){
                    if(b->next->next != NULL){  //b'nin 2 ilerisinde bir block mevcutsa
                        b->next->next->prev=b->prev;
                        if(b->prev != NULL){
                            b->prev->next=b->next->next;
                        }
                    }
                }

        }else if((b->prev != NULL)&&(b->prev->free==1)){  //sadece prev block free ise
                b->prev->size=b->prev->size+b->size;
                b->next->prev=b->prev;
                b->prev->next=b->next;
        }else if((b->next != NULL)&&(b->next->free==1)){  //sadece next block free ise
                 b->size=b->size+b->next->size;

                 if(b->next->next != NULL){ //b'nin 2 ilerisinde bir block mevcutsa
                    b->next->next->prev=b;
                    b->next=b->next->next;
                 }

        }
        b->free = 1; //b'yi free etme
    }

    if(b->next == NULL){
        brk(b); //free edilmis block'un sonrasi bos ise fazla heap'i geri birak.
    }

}

void *mm_realloc(void *p, size_t size){

    size_t s;
    t_block b,newblock;
    void *newp;
    if(!p){
        return (malloc(size));
    }
    s = nearest4(size);
    b = get_block(p);
    if(s <= b->size){    //yeni buyukluk mevcut buyuklukten kucukse
        if((s+BLOCK_SIZE+4) <= b->size){    //buyuklukler arasindaki fark 2 block olusturacak kadar buyukse
                if(s < b->size){            //arta kalan kismis free et
                    split_block(b,s);
                }
            return b->data;           //yeni block'u dondur
        }else{
            b->size = s;
            return b->data;
        }
    }else{
        if(b->next != NULL){    //b'nin komsusu var mi
            if((b->next->free == 1)&&(b->size+b->next->size >= s)){
                if(b->size+b->next->size >= s + BLOCK_SIZE + 4){    //komsu free block cok buyukse
                    split_block(b->next,s-b->size);
                }
                if(b->next->next != NULL){  //b block'u ile bos olan komsu block'u birlestirme
                    b->next->next->prev=b;
                    b->next=b->next->next;
                }
                return b;
            }
        }else{
            newp = mm_malloc(s);    //yeni boyut daha buyukse yeni boyuta uygun yer malloc et
            if(!newp){
                return(NULL);
            }
            newblock = get_block(newp);
            copy_block(newblock,b); //eski block'daki bilgileri yeni block'a aktar
            mm_free(p);             //eski block'u free et
            return newp;            //yeni block'u dondur
        }
    }


}
