
public class Rental {
	Movie movie;
	int daysRented;
	
	public Rental(Movie movie) {
		this.movie = movie;
	}
	
	Movie getMovie() {
		return this.movie;
	}
	
	int getDaysRented(){
		return daysRented;
	}
	
	void setDaysRented(int daysRented) {
		this.daysRented=daysRented;
	}
	
	/*
	int getRentalCount() {
		return rentalCount;
	}
	
	void getRental(int no) {
		return rental;
	}
	*/
	
	
}
