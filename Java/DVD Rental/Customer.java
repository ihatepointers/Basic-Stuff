
public class Customer {
	String name;
	Rental rental;
	
	public Customer(String name) {
		this.name = name;
	}
	
	String getName() {
		return this.name;
	}
	
	void addRental(Rental rental) {
		this.rental = rental;
	}
	
	Rental getRental() {
		return this.rental;
	}

}
