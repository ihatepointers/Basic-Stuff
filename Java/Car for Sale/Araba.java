import java.util.Calendar;
import java.util.Date;

public class Araba {
	private int ID;
	private int yas;
	private String yakitTipi;
	private int motorHacmi;
	private String marka;
	private double fiyat;
	final int tekerlekSayisi=4;
	
	public Araba(int ID,int Yas,String yakitTipi,int motorHacmi,String marka) {
		this.ID = ID;
		this.yas = yas;
		this.yakitTipi = yakitTipi;
		this.motorHacmi = motorHacmi;
		this.marka = marka;
		
	}
	
	public Araba(int ID) {
		this.ID = ID;
	}
	
	public int getTekerlekSayisi() {
		return this.tekerlekSayisi;
	}
	
	
	public int getID() {
		return this.ID;
	}
	
	public int getyas() {
		return this.yas;
	}
	public void getYas(int yas) {
		this.yas= yas;
	}
	
	public String getYakitTipi() {
		return this.yakitTipi;
	}
	public int getMotorHacmi() {
		return this.motorHacmi;
	}
	public String getMarka() {
		return this.marka;
	}
	
	public double getFiyat() {
		return fiyat;
	}
	public void setFiyat(double fiyat) {
		
		this.fiyat= indirimYap(fiyat);
		//this.fiyat = fiyat;
	}
	
	public double indirimYap(double fiyat) {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		
			fiyat=fiyat*0.95;
			return fiyat;
	}
	
	
	
	
	
	
}
