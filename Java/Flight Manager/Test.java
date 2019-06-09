import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class Test{
	
	
	static FlightManagementScreen FMS;
	public static void main(String[] args) {
		FMS = new FlightManagementScreen();
	}
	
	public static void deleteFlight(Flight flight) {
		FMS.eraseFlightFromList(flight);
	}
}
