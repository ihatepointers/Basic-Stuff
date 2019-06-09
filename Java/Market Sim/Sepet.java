
public class Sepet {
	private String sepetSahibi;
	private Urun[] urunler = new Urun[3];
	private int sayac_urun;
	private boolean flag;
	private int i;
	private double tutar=0;
	private double kdvTutar=0;
	private double odenecekTutar=0;
	final static int MAX_URUN=3;
	
	public Sepet(String sepetSahibi){
		this.sepetSahibi=sepetSahibi;
	}
	
	public String getSepetSahibi() {
		return sepetSahibi;
	}
	
	public int getSayac_urun() {
		return sayac_urun;
	}
	
	public void ekleUrun(Urun urun) {
		if(sayac_urun==MAX_URUN) {
			System.out.println("Sepetiniz dolu." +urun.getAd()+" sepete eklenemedi.");
		}else {
			urunler[sayac_urun]=urun;
			System.out.println(urun.getAd()+" sepete eklendi");
			sayac_urun++;
			tutar=tutar+urun.getFiyat();
			kdvTutar+=urun.kdvHesapla();
		}
		
	}
	
	public boolean araUrun(Urun urun) {
		flag=false;
		for(i=0;i<sayac_urun;i++) {
			if(urunler[i]==urun) {
				flag=true;
			}
		}
		return flag;
	}
	
	public void silUrun(Urun urun) {
		if(araUrun(urun)){
			
			i=0;
			while(urunler[i]!=urun) {
				i++;
			}
			for(i=0;i<sayac_urun-1;i++) {
				urunler[i]=urunler[i+1];
			}
			urunler[i]=null;
			System.out.println(urun.getAd()+" sepetten silindi.");
			tutar=tutar-urun.getFiyat();
			kdvTutar-=urun.kdvHesapla();
			sayac_urun--;
		}else {
			System.out.println(urun.getAd()+" sepette mevcut degil.");
		}
	}
	
	public void sepetYazdir() {
		System.out.print("Sepetinizde "+sayac_urun+" adet urun var.Urunleriniz: ");
		for(i=0;i<sayac_urun;i++) {
			System.out.print(urunler[i].getAd()+" ");
		}
		System.out.println();
	}
	
	public double getTutar() {
		return this.tutar;
	}
	
	public double getKDV() {
		return this.kdvTutar;
	}
	
	public double getOdenecek() {
		
		odenecekTutar= this.tutar+this.kdvTutar;
		if(odenecekTutar>1000) {
			odenecekTutar=odenecekTutar*0.9;
		}
		return odenecekTutar;
	}
	
	
	

}
