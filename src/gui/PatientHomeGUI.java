
package gui;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import users.Appointment;
import users.Patient;

@SuppressWarnings("serial")//VC warning Ignore
public class PatientHomeGUI extends JFrame {

    private Patient authenticatedPatient;

    public PatientHomeGUI(Patient authenticatedPatient) {
        this.authenticatedPatient = authenticatedPatient;

        setTitle("Patient Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(210, 220, 215)); 

        JLabel nameLabel = new JLabel("Welcome, " + authenticatedPatient.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel usernameLabel = new JLabel("Username: " + authenticatedPatient.getUsername());
        JLabel passwordLabel = new JLabel("Password: " + authenticatedPatient.getPassword());

        JPanel userInfoPanel = new JPanel(new GridLayout(3, 1));
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(passwordLabel);

        panel.add(userInfoPanel, BorderLayout.NORTH);
        // Display booked appointments
        displayBookedAppointments(panel);
        add(panel);}

    private void displayBookedAppointments(JPanel panel) {
        List<Appointment> bookedAppointments = authenticatedPatient.getBookedAppointments();
        if (bookedAppointments != null && !bookedAppointments.isEmpty()) {
            JPanel appointmentPanel = new JPanel(new BorderLayout());
            appointmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

            JLabel appointmentsLabel = new JLabel("Booked Appointments:");
            appointmentsLabel.setFont(new Font("Arial", Font.BOLD, 14)); // the font dosnt work
            appointmentPanel.add(appointmentsLabel, BorderLayout.NORTH);

            JTextArea appointmentsTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(appointmentsTextArea);
            appointmentsTextArea.setEditable(false);
            appointmentsTextArea.setFont(new Font("Arial", Font.PLAIN, 12));// font doesnt work

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Appointment appointment : bookedAppointments) {
                appointmentsTextArea.append(dateFormat.format(appointment.getDate()) + " at " + appointment.getTime() + "\n");}

            appointmentPanel.add(scrollPane, BorderLayout.CENTER);
            panel.add(appointmentPanel, BorderLayout.CENTER);}}
}
