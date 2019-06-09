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

public class AddCapital extends JFrame{
	ArrayList<String> departureCities;
	JButton addNewCapitalButton;
	JTextField addNewCapitalText;
	public AddCapital(ArrayList<String> departureCities) {
		super("Add New Capital");
		setLayout(new FlowLayout());
		this.departureCities = departureCities;
		
		HandlerClass handler = new HandlerClass();
		
		addNewCapitalText = new JTextField("Capital Name");
		
		addNewCapitalButton = new JButton("Add New Capital");
		addNewCapitalButton.setBounds(200, 300, 200, 30);
		addNewCapitalButton.setFont(new Font("Verdana",1,13));
		addNewCapitalButton.addActionListener(handler);
		
		add(addNewCapitalText);
		add(addNewCapitalButton);
	}
	
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == addNewCapitalButton) {
				departureCities.add(addNewCapitalText.getText());
				dispose();
				
			}
		}
	}
}
