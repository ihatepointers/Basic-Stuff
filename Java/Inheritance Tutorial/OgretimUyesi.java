package ndklab3;

public class OgretimUyesi extends Calisan {
	private String unvan;
	public OgretimUyesi(String ad,int yas,int kidem,int maas,String unvan) {
		super(ad,yas,kidem,maas);
		this.unvan=unvan;
	}
	
	public OgretimUyesi() {}
	
	public String getUnvan() {
		return unvan;
	}
	
	public void kendiniTanit(){
		System.out.println("Ad: "+super.ad+" , Yas: "+super.yas+" , Kidem: "+super.kidem+" , Maas: "+super.maas+"TL , Unvaný : "+unvan);
	}
	 
	public void bolumBaskaniYap() {
		OgretimUyesi bolumbaskani= new BolumBaskani();
	}
	
	

}
