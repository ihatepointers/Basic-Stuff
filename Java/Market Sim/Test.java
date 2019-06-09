
public class Test {
	public static void main(String[] args) {
		
		
		
		
		ElektronikUrun telefon = new ElektronikUrun("Telefon",123,1300,1.18,2);
		Yiyecek peynir = new Yiyecek("Peynir",456,5,1.08,20);
		Yiyecek tavuk = new Yiyecek("Tavuk",789,10,1.08,2);
		ElektronikUrun televizyon = new ElektronikUrun("Televizyon",741,2000,1.18,4);
		
		
		System.out.println("Urun: "+telefon.getAd()+" , Barkod:"+telefon.getBarkod()+ " , Fiyat:"+telefon.getFiyat()+"TL , Garanti suresi:"+telefon.getGarantiSuresi()+" yil");
		System.out.println("Urun: "+peynir.getAd()+" , Barkod:"+peynir.getBarkod()+" , Fiyat:"+peynir.getFiyat()+"TL , Tuketim suresi:"+peynir.getTuketimSuresi()+" gun");
		System.out.println("Urun: "+tavuk.getAd()+" , Barkod:"+tavuk.getBarkod()+" , Fiyat:"+tavuk.getFiyat()+"TL , Tuketim suresi:"+tavuk.getTuketimSuresi()+" gun");
		
		Sepet sepet1 = new Sepet("Serdar");
		
		sepet1.ekleUrun(telefon);
		sepet1.ekleUrun(peynir);
		sepet1.ekleUrun(tavuk);
		sepet1.ekleUrun(televizyon);
		
		sepet1.sepetYazdir();
		
		/*
		if(sepet1.araUrun(telefon)){
			System.out.println(telefon.getAd()+" sepette mevcut.");
		}else {
			System.out.println(telefon.getAd()+" sepette mevcut degil.");
		}
		
		if(sepet1.araUrun(televizyon)){
			System.out.println(televizyon.getAd()+" sepette mevcut.");
		}else {
			System.out.println(televizyon.getAd()+" sepette mevcut degil.");
		}
		*/
		System.out.println("Urunun eski fiyati: "+televizyon.getFiyat());
		televizyon.uzatGaranti(3);
		System.out.println("Urunun yeni fiyati: "+televizyon.getFiyat());
		
		sepet1.silUrun(telefon);
		sepet1.ekleUrun(televizyon);
		
		sepet1.sepetYazdir();
		
		System.out.println("Sepet tutari: "+sepet1.getTutar());
		System.out.println("KDV tutari: "+sepet1.getKDV());
		System.out.println("Odenecek toplam tutar: "+sepet1.getOdenecek());

		
		
		
		
		
	}
}
