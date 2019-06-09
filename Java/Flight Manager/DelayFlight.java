import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class DelayFlight extends JFrame{
	JButton delayFlightButton;
	JTextField delayFlightText;
	Flight flight;
	public DelayFlight(Flight flight) {
		super("Delay Flight");
		setLayout(new FlowLayout());
		this.flight = flight;
		
		delayFlightText = new JTextField("Delay in Minutes");
		
		HandlerClass handler = new HandlerClass();
		
		delayFlightButton = new JButton("Delay The Flight");
		delayFlightButton.setBounds(200, 300, 200, 30);
		delayFlightButton.setFont(new Font("Verdana",1,13));
		delayFlightButton.addActionListener(handler);
		
		add(delayFlightText);
		add(delayFlightButton);
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == delayFlightButton) {
				flight.addDelay(Integer.parseInt(delayFlightText.getText()));
				dispose();
				
			}
		}
	}
}
