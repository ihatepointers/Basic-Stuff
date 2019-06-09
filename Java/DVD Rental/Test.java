import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.print("Kiralayacak kisinin ismini girin: ");
		String name = input.nextLine();
		Customer aCustomer = new Customer(name);
		
		
		Movie avatar = new Movie("Avatar");
		avatar.setPrice(20);
		
		Movie kolpacino = new Movie("Kolpacino");
		kolpacino.setPrice(30);
		
		Movie yalanveemanet = new Movie("Yalan ve Emanet");
		yalanveemanet.setPrice(100);
		
		System.out.print("Kiralanacak filmin ismini girin ");
		String title=input.nextLine();
		System.out.print(title+" adli film araniyor...");
		if(title.equals("Avatar")) {
			Rental aRental = new Rental(avatar);
			System.out.print("Kac gunlugune kiralayacaksin? ");
			int daysRented=input.nextInt();
			aCustomer.addRental(aRental);
			aRental.setDaysRented(daysRented);
		}else if(title.equals("Kolpacino")){
			Rental aRental = new Rental(kolpacino);
			System.out.print("Kac gunlugune kiralayacaksin? ");
			int daysRented=input.nextInt();
			aCustomer.addRental(aRental);
			aRental.setDaysRented(daysRented);
			
		}else if(title.equals("Yalan ve Emanet")){
			Rental aRental = new Rental(yalanveemanet);
			System.out.print("Kac gunlugune kiralayacaksin? ");
			int daysRented=input.nextInt();
			aCustomer.addRental(aRental);
			aRental.setDaysRented(daysRented);
		}else {
			System.out.print("Kiralanacak film bulunamadi ");
		}
		System.out.print(aCustomer.getName()+" , "+ aCustomer.getRental().getMovie().getTitle() + " filmini" + aCustomer.getRental().getDaysRented() + "gunlugune kiraladi.");
	}

}
