
public class Musteri {
	
	private String isim;
	private int yas;
	private String adres;
	static int musteriNo=0;
	
	public Musteri(String isim,int yas,String adres) {
		this.isim = isim;
		this.yas = yas;
		this.adres = adres;
		musteriNo = musteriNo + 1;
	}
	
	
	public String getIsim(){
		return this.isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	
	public int getYas(){
		return this.yas;
	}
	public void setYas(int yas) {
		this.yas = yas;
	}
	
	public String getAdres(){
		return this.adres;
	}
	
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	
}
