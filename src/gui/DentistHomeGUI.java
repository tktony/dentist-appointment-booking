


package gui;

import javax.swing.*;
import database.MainDatabase;
import java.awt.*;
import java.util.List;

@SuppressWarnings("serial") //version control just ignore not important
public class DentistHomeGUI extends JFrame {
    
    @SuppressWarnings("unused") //unused
    private String dentistName;

    public DentistHomeGUI(String dentistName) {
        setTitle("Welcome, " + dentistName);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close dentist home without closing the whole prog

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + dentistName + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // Add a JTextArea to display appointments
        JTextArea appointmentsTextArea = new JTextArea();
        appointmentsTextArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(appointmentsTextArea); // Add a scroll pane for long appointment lists
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size
        panel.add(scrollPane, BorderLayout.CENTER);

        // Retrieve appointments for the current dentist from the database
        List<String> appointments = MainDatabase.getInstance().getDentistAppointments();
        
        // Display appointments in the appointmentsTextArea
        StringBuilder appointmentsText = new StringBuilder();
        appointmentsText.append("Appointments for ").append(dentistName).append(":\n");
        for (String appointment : appointments) {
            appointmentsText.append(appointment).append("\n");}
        appointmentsTextArea.setText(appointmentsText.toString());
        add(panel);
    }
}
