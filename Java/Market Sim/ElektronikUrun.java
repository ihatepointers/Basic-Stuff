public class ElektronikUrun extends Urun{
		private int garantiSuresi;
		
		public ElektronikUrun(String ad,int barkod,double fiyat,double kdv_oran,int garantiSuresi) {
			super(ad,barkod,fiyat,kdv_oran);
			this.garantiSuresi=garantiSuresi;
		}
		
		public void uzatGaranti(int yil) {
			System.out.println(super.getAd()+" adli urunun garanti suresi "+yil+" yil uzatildi");
			this.garantiSuresi=garantiSuresi+yil;
			super.setFiyat(super.getFiyat()+(50*yil));
			//this.fiyat=fiyat+(yil*50);
		}
		
		public int getGarantiSuresi() {
			
			return this.garantiSuresi;
		}

}
