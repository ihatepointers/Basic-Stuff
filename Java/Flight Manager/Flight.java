import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Flight implements Runnable{
	private String departureCity;
	private String destinationCity;
	private String airlinesName;
	private String aircraftModel;
	private Date departureDate;
	private Date arrivalDate;
	private long ETA;
	static int pause = 0;

	private int flightNumber;
	Timer timer = new Timer();
	
	public Flight(String departureCity, String destinationCity, Date departureDate,
			Date arrivalDate, String airlinesName, int flightNumber, String aircraftModel) {
		this.departureCity = departureCity;
		this.destinationCity = destinationCity;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.airlinesName = airlinesName;
		this.flightNumber = flightNumber;
		this.aircraftModel = aircraftModel;
		ETA = arrivalDate.getTime() - departureDate.getTime();
		
	}
	
	public void deleteFlight()
	{
		if(ETA == 0) {
			//System.out.println(this.getClass().getName() + " is deleted");
			Test.deleteFlight(this);
		}
	}
	
	@Override
	public void run() {
		
		
		
		timer.schedule(new TimerTask() {
		    @Override
		    
		    public void run() {
		    	if(pause == 0) {
			    	if(ETA >= 60000) {
			    		ETA -= 60000;
			    		timeOn();
			    	}else{
			    		ETA = 0;
			    		deleteFlight();
			    		return;
			    	}
		    	}else {
		    		timeOff();
		    	}
		    	System.out.println("Flight pause value: " + pause);
		    }
		    
		}, 0, 1000);
		return;
	}
	
	public void pauseTime() {
		if(pause == 1) {
			pause = 0;
		}else {
			pause = 1;
		}
		System.out.println(this.getDepartureCity() + " is : " + pause);
	}
	
	public void timeOn() {
		//System.out.println(this.getClass().getName() + " is running, ETA : " + ETA/1000);
	}
	
	public void timeOff() {
		//System.out.println(this.getClass().getName() + " is stopped");
	}
	
	public void addDelay(int minutes) {
		this.arrivalDate.setTime(this.arrivalDate.getTime() + (minutes * 60000));
		this.ETA += minutes * 60000;
	}
	
	
	
	
	
	
	
	public void updateETA() {
		ETA = arrivalDate.getTime() - departureDate.getTime();
	}
	
	public long getETA() {
		return this.ETA;
	}
	
	

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getAirlinesName() {
		return airlinesName;
	}

	public void setAirlinesName(String airlinesName) {
		this.airlinesName = airlinesName;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getAircraftModel() {
		return aircraftModel;
	}
	
	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}
	

	public void printflightDetails() {
		System.out.println("Departure city: " + departureCity + " Destination City: " + destinationCity + " Departure date: " + departureDate + " Arrival Date: "
				+ arrivalDate + " Airlines name: " + airlinesName + " Flight number: " + flightNumber);
	}



	

}