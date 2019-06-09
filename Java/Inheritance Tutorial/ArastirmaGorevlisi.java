package ndklab3;

public class ArastirmaGorevlisi extends Calisan {
	private String lisansDurumu;
	public ArastirmaGorevlisi(String ad,int yas,int kidem,int maas,String lisansDurumu){
		super(ad,yas,kidem,maas);
		this.lisansDurumu = lisansDurumu;
	}
	
	public ArastirmaGorevlisi() {}
	
	public String getLisansDurumu(String durum){
		return lisansDurumu;
	}

	public void kendiniTanit(){
		System.out.println("Ad: "+super.ad+" Yas: "+super.yas+" , Kidem : "+super.kidem+" , Maas: "+super.maas+" , Durumu: "+lisansDurumu);
	}
	
	public void arastirmaGorevlisiTemsilcisiYap() {
		ArastirmaGorevlisi temsilci = new ArastirmaGorevlisiTemsilcisi();
	}
	
}
