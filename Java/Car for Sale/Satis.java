import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Satis {
	private int kayitNo;
	private Date tarih;
	private Araba araba;
	private Musteri musteri;
	static int satisSayisi=0;
	
	public Satis(int kayitNo,String tarih,Araba araba,Musteri musteri) throws ParseException {
		this.kayitNo = kayitNo;
		this.tarih=new SimpleDateFormat("yyyy-MM-dd").parse(tarih);
		this.araba = araba;
		this.musteri = musteri;
		satisSayisi = satisSayisi +1;
	}
	
	public int getKayitNo() {
		return this.kayitNo;
	}
	public void setKayitNo(int kayitNo) {
		this.kayitNo = kayitNo;
	}
	
	public Date getTarih() {
		return this.tarih;
	}
	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public Araba getAraba() {
		return this.araba;
	}
	
	public void setAraba(Araba araba) {
		this.araba = araba;
	}
	
	public Musteri getMusteri() {
		return this.musteri;
	}
	public void setMusteri() {
		this.musteri = musteri;
	}
	
	

}
