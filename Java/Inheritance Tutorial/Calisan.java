package ndklab3;

public abstract class Calisan {
	protected String ad;
	protected int yas;
	protected int kidem;
	protected int maas;
	public Calisan(String ad,int yas,int kidem,int maas){
		this.ad=ad;
		this.yas=yas;
		this.kidem=kidem;
		this.maas=maas;
	}
	public Calisan() {}
	
	public String getAd() {
		return ad;
	}
	
	public int getYas() {
		return yas;
	}
	
	public int getKidem() {
		return kidem;
	}
	public int getMaas() {
		return maas;
	}
	
	public abstract void kendiniTanit();
}
