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

public class AddDestination extends JFrame{
	ArrayList<String> destinationCities;
	JButton addNewDestinationButton;
	JTextField addNewDestinationText;
	public AddDestination(ArrayList<String> destinationCities) {
		super("Add New Destination");
		setLayout(new FlowLayout());
		this.destinationCities = destinationCities;
		
		HandlerClass handler = new HandlerClass();
		
		addNewDestinationText = new JTextField("Capital Name");
		
		addNewDestinationButton = new JButton("Add New Destination");
		addNewDestinationButton.setBounds(200, 300, 200, 30);
		addNewDestinationButton.setFont(new Font("Verdana",1,13));
		addNewDestinationButton.addActionListener(handler);
		
		add(addNewDestinationText);
		add(addNewDestinationButton);
	}
	
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == addNewDestinationButton) {
				destinationCities.add(addNewDestinationText.getText());
				dispose();
				
			}
		}
	}
}
