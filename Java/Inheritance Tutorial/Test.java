package ndklab3;

public class Test {
	
	public static void main(String[] args) {
		
		Bolum bol1 = new Bolum("Bilgisayar Muhendisligi","Davutpasa",1974);
		
		ArastirmaGorevlisi ars1 = new ArastirmaGorevlisi("Serdar",21,2,4500,"Lisans");
		ArastirmaGorevlisi ars2 = new ArastirmaGorevlisi("Melih",20,1,3800,"Doktora");
		OgretimUyesi ogr1 = new OgretimUyesi("Mertcan",50,1,8800,"Docent");
		OgretimUyesi ogr2 = new OgretimUyesi("Hasan",35,3,6500,"Yar.Doc");
		
		bol1.CalisanEkle(ars1);
		bol1.CalisanEkle(ars2);
		bol1.CalisanEkle(ogr1);
		bol1.CalisanEkle(ogr2);
		
		bol1.kendiniTanit();
		
		ars1.kendiniTanit();
		ars2.kendiniTanit();
		ogr1.kendiniTanit();
		ogr2.kendiniTanit();
		
		System.out.println();
		bol1.calisanToplamMaas();
		
		
	}

}
