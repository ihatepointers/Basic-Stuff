package ndklab3;

public class Bolum {
	private String bolumAdi;
	private String adres;
	private int kurulusYili;
	private int toplamMaas;
	private Calisan[] calisanDizisi;
	private int calisanSayisi;
	private static final int max=50;
	
	public Bolum(String bolumAdi,String adres,int kurulusYili){
		this.bolumAdi=bolumAdi;
		this.adres=adres;
		this.kurulusYili=kurulusYili;
		toplamMaas=0;
		calisanDizisi = new Calisan[max];
		calisanSayisi=0;
	}
	
	public String getBolumadi() {
		return bolumAdi;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public int getKurulusYili() {
		return kurulusYili;
	}
	
	public void CalisanEkle(Calisan calisan){
		if((calisanSayisi<max)&&(!search(calisan))){
			calisanDizisi[calisanSayisi]=calisan;
			calisanSayisi++;
		}else {
			System.out.println("Bolumun kapasitesi doldugundan daha fazla calisan ekleyemezsiniz.");
		}
	}
	
	public boolean search(Calisan calisan){
		for(int i=0;i<calisanSayisi;i++)
		{
			if(calisanDizisi[i].ad.compareToIgnoreCase(calisan.ad)==0)
			{
				return true;
			}
		}
		return false;
	}
	
	public void calisanToplamMaas(){
		for(int i=0;i<calisanSayisi;i++)
		{
			toplamMaas+=calisanDizisi[i].getMaas();
		}
		System.out.println("Toplam maas : "+toplamMaas);
	}
	
	public void kendiniTanit(){
		System.out.println("Bolum ismi: "+this.bolumAdi+" , Adres: "+this.adres+" , Kurulus yili: "+this.kurulusYili+" , Calisan sayisi: "+this.calisanSayisi);
	}
	



}