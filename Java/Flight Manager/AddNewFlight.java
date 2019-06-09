import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;



public class AddNewFlight extends JFrame{
	DateFormat dateFormat;
	private JTextField departureCityText;
	private JTextField destinationCityText;
	private JTextField departureDateText;
	private JTextField arrivalDateText;
	private JTextField airlinesNameText;
	private JTextField flightNumberText;
	private JTextField aircraftModelText;
	ArrayList<Flight> flights;
	
	
	JButton addNewFlightButton;
	HandlerClass handler;
	public AddNewFlight(ArrayList<Flight> flights) {
		
		super("Add New Flight");
		setLayout(new FlowLayout());
		handler = new HandlerClass();
		this.flights = flights;
		dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss", Locale.ENGLISH);
		departureCityText = new JTextField("departureCity");
		departureCityText.setBounds(150, 5 ,20, 30);
		add(departureCityText);
		
		destinationCityText = new JTextField("destinationCity");
		add(destinationCityText);
		departureCityText.setBounds(150, 35 ,20, 30);
		
		departureDateText = new JTextField("departureDate");
		add(departureDateText);
		departureCityText.setBounds(150, 65 ,20, 30);
		
		arrivalDateText = new JTextField("arrivalDate");
		add(arrivalDateText);
		departureCityText.setBounds(150, 95 ,20, 30);
		
		airlinesNameText = new JTextField("airlinesName");
		add(airlinesNameText);
		departureCityText.setBounds(150, 125 ,20, 30);
		
		flightNumberText = new JTextField("flightNumber");
		add(flightNumberText);
		departureCityText.setBounds(150, 155 ,20, 30);
		
		aircraftModelText = new JTextField("aircraftModelText");
		add(aircraftModelText);
		departureCityText.setBounds(150, 185 ,20, 30);
		
		addNewFlightButton = new JButton("Add New Flight");
		addNewFlightButton.setBounds(200, 300, 200, 30);
		addNewFlightButton.setFont(new Font("Verdana",1,13));
		addNewFlightButton.addActionListener(handler);
		
		add(addNewFlightButton);
		
		
		
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == addNewFlightButton) {
				dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss", Locale.ENGLISH);
				Date departureDate;
				Date arrivalDate;
				String dateString;
				
				departureDate = new Date();
				arrivalDate= new Date();

				dateString = departureDateText.getText();
				
				try {
					departureDate = dateFormat.parse(dateString);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				
				dateString = arrivalDateText.getText();
				try {
					arrivalDate = dateFormat.parse(dateString);
				} catch (ParseException e) {
				    e.printStackTrace();
				}
				Flight newFlight = new Flight(departureCityText.getText(), destinationCityText.getText(), departureDate, arrivalDate, airlinesNameText.getText(), Integer.parseInt(flightNumberText.getText()), aircraftModelText.getText());
				Thread flightThread = new Thread(newFlight);
				flightThread.start();
				flights.add(newFlight);
				dispose();
				
			}
		}
	}
}
