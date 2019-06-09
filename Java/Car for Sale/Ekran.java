import java.text.ParseException;

public class Ekran {
	public static void main(String[] args) throws ParseException {
		
		/*
		 	Araba a1= new Araba(100, 3, "dizel", 1600,"Mazda");
			Araba a2= new Araba(102, 2, "benzinli", 2000, "Honda");
		 */
		
		Araba araba1 = new Araba(100, 3, "dizel", 1600,"Mazda");
		Araba araba2 = new Araba(102, 2, "benzinli", 2000, "Honda");
		
		araba1.setFiyat(36000);
		araba2.setFiyat(40000);
		
		Musteri musteri1 = new Musteri("Serdar",21,"uskudar");
		Musteri musteri2 = new Musteri("Melih",20,"celiktepe");
		
		Satis satis1 = new Satis(1,"2017-03-07",araba1,musteri1);
		Satis satis2 = new Satis(2,"2017-01-07",araba2,musteri2);
		
		System.out.println(satis1.getMusteri().getIsim() + "'ın " + satis1.getAraba().getMarka() + " marka arabasi var ve bu araba " + satis1.getAraba().getFiyat() + " TL'ye malodu");
		System.out.println(satis2.getMusteri().getIsim() + "'in " + satis2.getAraba().getMarka() + " marka arabasi var ve bu araba " + satis2.getAraba().getFiyat() + " TL'ye malodu");
	
		System.out.println("Bütün arabalarin " + satis1.getAraba().getTekerlekSayisi() + " tekerlegi var");
		
		System.out.println("Serdar'ın arabasinin yakit tipi: " + satis1.getAraba().getYakitTipi());
		System.out.println("Melih'in arabasinin yakit tipi: " + satis2.getAraba().getYakitTipi());
		
		System.out.println(satis1.getAraba().getMarka() + " markali arabanin sahibi " + satis1.getMusteri().getAdres() + "'da oturuyor");
		System.out.println(satis2.getAraba().getMarka() + " markali arabanin sahibi " + satis2.getMusteri().getAdres() + "'de oturuyor");
		
	}

}
