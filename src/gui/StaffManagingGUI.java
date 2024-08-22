
package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import database.MainDatabase;
import users.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("serial")//VC warning
public class StaffManagingGUI extends JFrame {

    @SuppressWarnings("unused")
	private String staffName;

    // constructor
    public StaffManagingGUI() {
        setTitle("Manage Appointments");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Fetch booked appointments from the database
        List<Appointment> appointments = MainDatabase.getInstance().getAllAppointments();

        // Create a JTextArea to display appointments
        JTextArea appointmentsTextArea = new JTextArea();
        appointmentsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(appointmentsTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Retrieve appointments for the current dentist from the database
        List<String> appointments1 = MainDatabase.getInstance().getDentistAppointments();

        // Populate the appointmentsTextArea with appointment data
        StringBuilder sb = new StringBuilder();
        sb.append("Appointments:\n\n");
        for (Appointment appointment : appointments) {
            sb.append("Date: ").append(appointment.getDate()).append("\n");
            sb.append("Time: ").append(appointment.getTime()).append("\n");
            sb.append(appointments1).append("\n\n");
        }
        appointmentsTextArea.setText(sb.toString());

        // Create buttons for actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open dialogs to get appointment details
                String dateString = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter appointment date (YYYY-MM-DD):");
                String time = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter appointment time (HH:MM):");

                // Retrieve patient information
                String name = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter patient name:");
                String username = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter patient username:");
                String password = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter patient password:");

                // Get patient from database
                Patient patient = MainDatabase.getInstance().getPatient(name, username, password);

                // Check if patient exists
                if (patient != null) {
                    // Parse the date string into a Date object
                    Date date = parseDate(dateString);

                    // Create an Appointment object
                    Appointment appointment = new Appointment(date, time);

                    // Add the appointment to the patient's list of booked appointments
                    MainDatabase.getInstance().addAppointment(patient, appointment);

                    // Refresh appointment list
                    refreshAppointmentList();
                } else {
                    // Patient not found
                    JOptionPane.showMessageDialog(StaffManagingGUI.this, "Patient not found. Please register the patient first.");
                }}});;
        
        buttonPanel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a dialog to delete an appointment by date and time
                String date = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter appointment date to delete (YYYY-MM-DD):");
                String time = JOptionPane.showInputDialog(StaffManagingGUI.this, "Enter appointment time to delete (HH:MM):");
                MainDatabase.getInstance().deleteAppointment(date, time);
                refreshAppointmentList();
            }
        });
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);}

    	// Method to parse date from string
    	private Date parseDate(String dateString) {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		try {
    			return dateFormat.parse(dateString);
    		} catch (ParseException e) {
    			e.printStackTrace();
    			return null; // Return null if parsing fails
    		}}
    
    	// Method to refresh the displayed appointment list
    	private void refreshAppointmentList() {
    		// Fetch updated booked appointments from the database
    		List<Appointment> appointments = MainDatabase.getInstance().getAllAppointments();

    		// Retrieve updated appointments for the current dentist from the database
    		List<String> appointments1 = MainDatabase.getInstance().getDentistAppointments();

    		// Create a new StringBuilder to build the appointment text
    		StringBuilder sb = new StringBuilder();
    		sb.append("Appointments:\n\n");
    		for (Appointment appointment : appointments) {
    			sb.append("Date: ").append(appointment.getDate()).append("\n");
    			sb.append("Time: ").append(appointment.getTime()).append("\n");
    			sb.append(appointments1).append("\n\n");}

        // Find the JTextArea within the nested components
        Container contentPane = getContentPane();
        for (Component component : contentPane.getComponents()) {
            if (component instanceof JPanel) {
                for (Component innerComponent : ((JPanel) component).getComponents()) {
                    if (innerComponent instanceof JScrollPane) {
                        Component viewportView = ((JScrollPane) innerComponent).getViewport().getView();
                        if (viewportView instanceof JTextArea) {
                            JTextArea appointmentsTextArea = (JTextArea) viewportView;
                            appointmentsTextArea.setText(sb.toString());
                            return; // Exit the method once text is updated
                        }}}}}

        // If no JTextArea within a JScrollPane is found, display an error message
        JOptionPane.showMessageDialog(this, "Error: Unable to refresh appointment list.");
    }
}
