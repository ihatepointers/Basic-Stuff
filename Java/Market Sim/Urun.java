
abstract public class Urun {
	private String ad;
	private int barkod;
	private double fiyat;
	private double kdv_oran;
	
	public Urun(String ad,int barkod,double fiyat,double kdv_oran) {
		this.ad=ad;
		this.barkod=barkod;
		this.fiyat=fiyat;
		this.kdv_oran=kdv_oran;
	}
	
	public Urun(){
		
	}
	
	public double hesaplaKDV() {
		
		return this.kdv_oran;
	}
	
	public void setFiyat(double fiyat) {
		this.fiyat=fiyat;
	}
	
	public String getAd() {
		return this.ad;
	}
	
	public int getBarkod() {
		return this.barkod;
	}
	
	public double getFiyat() {
		return this.fiyat;
	}
	
	
	public double getKdv_Oran() {
		return this.kdv_oran;
	}
	
	public double kdvHesapla() {
		double kdv;
		kdv=(this.fiyat*(this.kdv_oran-1.0));
		return kdv;
	}
	


}
