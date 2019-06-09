import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;


public class FlightManagementScreen extends JFrame{
	JButton addCapitalButton;
	JButton deleteCapitalButton;
	JButton addDestinationButton;
	JButton deleteDestinationButton;
	JButton pauseButton;
	JButton updateListButton;
	JButton newFlightButton;
	JButton deleteFlightButton;
	JButton updateFlightButton;
	JButton delayFlightButton;
	
	JFrame frame;
	JPanel panel;
	
	JScrollPane departurePane;
	JScrollPane destinationPane;
	JScrollPane tablePane;
	
	ArrayList<Flight> flights;
	ArrayList<String> departureCities;
	ArrayList<String> destinationCities;
	ArrayList<String> airlinesNames;
	
	JList departureCitieslist;
	JList destinationCitiesList;
	
	JTable table;
	
	JLabel pauseState;
	
	DateFormat dateFormat;
	
	Object[][] tableData;
	
	String[] columnNames = {"No", "Departure", "Destination", "Dep. Time", "Arr. Time", "ETA", "Airlines", "Flight Number", "Aircraft Model"};
	
	static Date date = new Date();
	
	Timer timer = new Timer();
	
	int pause = 0;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	
	public FlightManagementScreen(){
		super("Flight manager");
		setLayout(new FlowLayout());
		
		dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss", Locale.ENGLISH);
		
		flights = new ArrayList<Flight>();
		departureCities = new ArrayList<String>();
		destinationCities = new ArrayList<String>();
		airlinesNames = new ArrayList<String>();
		
		
		
		
		
		//ArrayList<Date> departureDates = new ArrayList<Date>();
		//ArrayList<Date> arrivalDates = new ArrayList<Date>();
		
		//ArrayList<Integer> flightNumbers = new ArrayList<Integer>();
		
		departureCities.add("New York");
		departureCities.add("Sydney");
		departureCities.add("Paris");
		departureCities.add("Tokyo");
		departureCities.add("Istanbul");
		
		destinationCities.add("Istanbul");
		destinationCities.add("Sydney");
		destinationCities.add("Paris");
		destinationCities.add("New York");
		destinationCities.add("Tokyo");
		destinationCities.add("Berlin");
		
		
		airlinesNames.add("Turkish Airlines");
		airlinesNames.add("Pegasus Airlines");
		airlinesNames.add("British Airways");
		
		Date departureDate;
		Date arrivalDate;
		String dateString;
		
		departureDate = new Date();
		arrivalDate= new Date();

		dateString = "2018/05/24/10/10/00";
		
		try {
			departureDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		dateString = "2018/05/24/13/23/00";
		try {
			arrivalDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		Flight flight1 = new Flight("Sydney", "Istanbul", departureDate, arrivalDate, "Turkish Airlines", 254821, "Boeing 747" );
		
		
		dateString = "2018/05/24/12/28/00";
		
		try {
			departureDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		dateString = "2018/05/24/13/40/00";
		try {
			arrivalDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		Flight flight2 = new Flight("Tokyo", "Berlin", departureDate, arrivalDate, "Pegasus Airlines", 548131, "Boeing 717" );
		
		try {
			departureDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		dateString = "2018/05/24/22/15/00";
		try {
			arrivalDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		Flight flight3 = new Flight("Paris", "New York", departureDate, arrivalDate, "Pegasus Airlines", 184129, "Boeing 787" );
		
		flights.add(flight1);
		flights.add(flight2);
		flights.add(flight3);
		
		Thread t1 = new Thread(flight1);
		Thread t2 = new Thread(flight2);
		Thread t3 = new Thread(flight3);
		
		t1.start();
		t2.start();
		t3.start();
		
		
		
		
		/*
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(5, 5, 400, 300);
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		*/
		
		/*
		JTextField textField = new JTextField();
		textField.setBounds(5, 5, 300, 20);
		frame.add(textField);
		*/
		
		HandlerClass handler = new HandlerClass();
		
		addCapitalButton = new JButton("Add Capital");
		addCapitalButton.setBounds(880, 250, 150, 30);
		addCapitalButton.setFont(new Font("Verdana",1,13));
		addCapitalButton.addActionListener(handler);
		
		deleteCapitalButton = new JButton("Delete Capital");
		deleteCapitalButton.setBounds(880, 290, 150, 30);
		deleteCapitalButton.setFont(new Font("Verdana",1,13));
		deleteCapitalButton.addActionListener(handler);
		
		addDestinationButton = new JButton("Add Destination");
		addDestinationButton.setBounds(1090, 250, 150, 30);
		addDestinationButton.setFont(new Font("Verdana",1,13));
		addDestinationButton.addActionListener(handler);
		
		deleteDestinationButton = new JButton("Delete Destination");
		deleteDestinationButton.setBounds(1090, 290, 150, 30);
		deleteDestinationButton.setFont(new Font("Verdana",1,10));
		deleteDestinationButton.addActionListener(handler);
		
		
		
		updateListButton = new JButton("Update Flight List");
		updateListButton.setBounds(200, 50, 200, 30);
		updateListButton.setFont(new Font("Verdana",1,13));
		updateListButton.addActionListener(handler);
		
		newFlightButton = new JButton("Add New Flight");
		newFlightButton.setBounds(200, 100, 200, 30);
		newFlightButton.setFont(new Font("Verdana",1,13));
		newFlightButton.addActionListener(handler);
		
		deleteFlightButton = new JButton("Delete Selected Flight");
		deleteFlightButton.setBounds(200, 150, 200, 30);
		deleteFlightButton.setFont(new Font("Verdana",1,13));
		deleteFlightButton.addActionListener(handler);
		
		updateFlightButton = new JButton("Update Selected Flight");
		updateFlightButton.setBounds(200, 200, 200, 30);
		updateFlightButton.setFont(new Font("Verdana",1,13));
		updateFlightButton.addActionListener(handler);
		
		delayFlightButton = new JButton("Delay Selected Flight");
		delayFlightButton.setBounds(200, 250, 200, 30);
		delayFlightButton.setFont(new Font("Verdana",1,13));
		delayFlightButton.addActionListener(handler);
		
		pauseButton = new JButton("Pause/Start Time");
		pauseButton.setBounds(200, 300, 200, 30);
		pauseButton.setFont(new Font("Verdana",1,13));
		pauseButton.addActionListener(handler);
		
		
		
		frame = new JFrame("Flight Management System");
		panel = new JPanel();
		JLabel destinations = new JLabel("Destinations");
		JLabel capitals = new JLabel("Capitals");
		JLabel flightsLabel = new JLabel("Flights");
		pauseState = new JLabel("System: Running");
		
		destinations.setFont(new Font("Verdana",1,15));
		destinations.setBounds(1113, 5, 110, 30);
		
		capitals.setFont(new Font("Verdana",1,15));
		capitals.setBounds(915, 5, 110, 30);
		
		flightsLabel.setFont(new Font("Verdana",1,18));
		flightsLabel.setBounds(550, 360, 110, 30);
		
		pauseState.setFont(new Font("Verdana",1,18));
		pauseState.setBounds(500, 700, 200, 30);
		
		
		
		departureCitieslist = new JList(departureCities.toArray()); //data has type Object[]
		departureCitieslist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		departureCitieslist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		departureCitieslist.setVisibleRowCount(-1);
		
		destinationCitiesList = new JList(destinationCities.toArray()); //data has type Object[]
		destinationCitiesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		destinationCitiesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		destinationCitiesList.setVisibleRowCount(-1);
		
		departurePane = new JScrollPane(departureCitieslist);
		destinationPane = new JScrollPane(destinationCitiesList);
		
		
		departurePane.setBounds(900, 40, 110, 200);
		destinationPane.setBounds(1110, 40, 110, 200);
		
		
		
		/*
		Object[][] data = {
				{"1","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"2","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"3","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"4","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"5","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"6","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"7","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"8","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"9","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"10","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"11","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"12","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"13","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"14","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"15","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"16","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"17","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"18","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				{"19","Turkey", "Moscow", "15:00", "16:00", "18.53", "Turkish Airlines", "064B13", "Boeing 747"},
				{"20","Berlin", "Warsaw", "18:38", "20:38", "88.27", "Pegasus Airlines", "FC84Z6", "Boeing 787"},
				
		};
		*/
		createTableData();
		table = new JTable(tableData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		tablePane = new JScrollPane(table);
		tablePane.setBounds(150, 400, 900, 300);
		
		frame.add(tablePane);
		frame.add(departurePane);
		frame.add(destinationPane);
		frame.add(destinations);
		frame.add(capitals);
		frame.add(flightsLabel);
		frame.add(pauseState);
		frame.add(addCapitalButton);
		frame.add(deleteCapitalButton);
		frame.add(addDestinationButton);
		frame.add(deleteDestinationButton);
		frame.add(pauseButton);
		frame.add(updateListButton);
		frame.add(newFlightButton);
		frame.add(deleteFlightButton);
		frame.add(updateFlightButton);
		frame.add(delayFlightButton);
		
		frame.setSize(1300, 800);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	if((flights.size() > -1 ) && (pause == 0)) {
		    		updateFlightTable();
		    	}
		    }
		}, 0, 1000);
		
		
	}
	
	public void createTableData() {
		//date = new Date();
		String secondToMinutes;
		tableData = new Object[flights.size()][9];
		for(int i = 0; i < flights.size(); i++) {
			tableData[i][0] = i+1;
			tableData[i][1] = flights.get(i).getDepartureCity();
			tableData[i][2] = flights.get(i).getDestinationCity();
			tableData[i][3] = flights.get(i).getDepartureDate();
			tableData[i][4] = flights.get(i).getArrivalDate();
			tableData[i][5] = secondFormatter((int) flights.get(i).getETA() / 1000);
			tableData[i][6] = flights.get(i).getAirlinesName();
			tableData[i][7] = flights.get(i).getFlightNumber();
			tableData[i][8] = flights.get(i).getAircraftModel();
			
		}
	}
	
	public String secondFormatter(int seconds) {
		String formattedString = new String();
		
		if(seconds < 36000) {
			formattedString += "0";
		}
		formattedString += seconds/3600;
		
		formattedString +=":";
		
		seconds = seconds % 3600;
		
		if(seconds < 600) {
			formattedString +="0";
		}
		formattedString += seconds / 60;
		
		formattedString +=":";
		
		seconds = seconds % 60;
		
		if(seconds < 10) {
			formattedString +="0";
		}
		formattedString += seconds;
		
		return formattedString;
		
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == addCapitalButton) {
				
				AddCapital AC = new AddCapital(departureCities);
				AC.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AC.setSize(250, 250);
				AC.setVisible(true);
				renewDeparture();
				
			}else if(event.getSource() == deleteCapitalButton) {
				if(departureCitieslist.getSelectedIndex() > -1) {
					
					departureCities.remove(departureCitieslist.getSelectedIndex());
					renewDeparture();
				}
				
			}else if(event.getSource() == addDestinationButton) {
				
				//destinationCities.add("Moscow");
				AddDestination AD = new AddDestination(destinationCities);
				AD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				AD.setSize(250, 250);
				AD.setVisible(true);
				renewDestination();
			}else if(event.getSource() == deleteDestinationButton) {
				if(destinationCitiesList.getSelectedIndex() > -1) {
					destinationCities.remove(destinationCitiesList.getSelectedIndex());
					renewDestination();
				}
			}else if(event.getSource() == deleteFlightButton) {
				if(table.getSelectedRow() != -1) {
					Iterator<Flight> iter = flights.iterator();
					while(iter.hasNext()) {
						Flight f = iter.next();
						if((Integer)table.getValueAt(table.getSelectedRow(), 7) == f.getFlightNumber()) {
							flights.remove(f);
							
						}
					}
					
					
					for(Flight f : flights) {
						System.out.println(f.getDepartureCity());
					}
					
					
					
					updateFlightTable();
					
					
					
				}
			}else if(event.getSource() == newFlightButton) {
				AddNewFlight ANF = new AddNewFlight(flights);
				ANF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ANF.setSize(400, 500);
				ANF.setVisible(true);
				
				updateFlightTable();
				
			}else if(event.getSource() == updateListButton) {
				updateFlightTable();
			}else if(event.getSource() == updateFlightButton) {
				for(Flight fu : flights) {
					if((Integer)table.getValueAt(table.getSelectedRow(), 7) == fu.getFlightNumber()) {
						UpdateFlight UF = new UpdateFlight(fu);
						UF.setSize(400, 500);
						UF.setVisible(true);
					}	
				}
				updateFlightTable();
			}else if(event.getSource() == pauseButton){
				/*
				for(Flight fl : flights) {
					
					fl.pauseTime();
				}
				*/
				
				if(pause == 1) {
					Flight.pause = 0;
					pause = 0;
					System.out.println("Pause " + pause);
					pauseState.setText("System: Running");
					
				}else {
					Flight.pause = 1;
					pause = 1;
					System.out.println("Pause " + pause);
					pauseState.setText("System: Paused");
				}
			}else if(event.getSource() == delayFlightButton) {
				for(Flight fd : flights) {
					if((Integer)table.getValueAt(table.getSelectedRow(), 7) == fd.getFlightNumber()) {
						DelayFlight DeF = new DelayFlight(fd);
						DeF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						DeF.setSize(250, 250);
						DeF.setVisible(true);
					}
				}
			}
			
			
			frame.revalidate();
			frame.repaint();
		}
	}
	
	public void renewFlightTable() {
		frame.remove(tablePane);
		createTableData();
		
	}
	
	public void renewDestination() {
		frame.remove(destinationPane);
		destinationCitiesList = new JList(destinationCities.toArray()); //data has type Object[]
		destinationCitiesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		destinationCitiesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		destinationCitiesList.setVisibleRowCount(-1);
		
		destinationPane = new JScrollPane(destinationCitiesList);
		destinationPane.setBounds(1110, 40, 110, 200);
		frame.add(destinationPane);
		frame.revalidate();
		frame.repaint();
	}
	
	public void renewDeparture() {
		frame.remove(departurePane);
		departureCitieslist = new JList(departureCities.toArray()); //data has type Object[]
		departureCitieslist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		departureCitieslist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		departureCitieslist.setVisibleRowCount(-1);
		
		departurePane = new JScrollPane(departureCitieslist);
		departurePane.setBounds(900, 40, 110, 200);
		frame.add(departurePane);
		frame.revalidate();
		frame.repaint();
	}
	
	
	public void updateFlightTable() {
		frame.remove(tablePane);
		createTableData();
		table = new JTable(tableData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		tablePane = new JScrollPane(table);
		tablePane.setBounds(150, 400, 900, 300);
		frame.add(tablePane);
		frame.revalidate();
		frame.repaint();
		
		renewDestination();
		renewDeparture();
		System.out.println("Renewed");
	}
	
	
	public void eraseFlightFromList(Flight flight) {
		flights.remove(flight);
		if(pause == 1) {
			updateFlightTable();
		}
		
	}
	
	
	/*
	public void eraseFlightFromList(Flight flight) {
		frame.remove(tablePane);
		flights.remove(flight);

		
		createTableData();
		table = new JTable(tableData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		tablePane = new JScrollPane(table);
		tablePane.setBounds(150, 400, 900, 300);
		frame.add(tablePane);
	}
	*/

}
