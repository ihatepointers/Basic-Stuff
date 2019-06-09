
public class Yiyecek extends Urun{
	private int tuketimSuresi;
	
	public Yiyecek(String ad,int barkod,double fiyat,double kdv_oran,int tuketimSuresi) {
		super(ad,barkod,fiyat,kdv_oran);
		this.tuketimSuresi=tuketimSuresi;
	}
		
	public int getTuketimSuresi() {
		
		return this.tuketimSuresi;
	}

}
