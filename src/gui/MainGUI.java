
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") // version control problem just ignore not important
public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Dental Appointment System");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // color scheme 
        Color buttonColor = new Color(0, 50, 185); 
        Color textColor = Color.WHITE; 
        Color panelColor = new Color(0, 250, 0); 

        JButton registerPatientButton = new JButton("Register as Patient");
        JButton loginPatientButton = new JButton("Login as Patient");
        JButton bookAppointmentButton = new JButton("Book Appointment");
        JButton loginDentistButton = new JButton("Login as Dentist");
        JButton loginStaffButton = new JButton("Login as Staff");

        // Apply colors to buttons
        registerPatientButton.setBackground(buttonColor);
        registerPatientButton.setForeground(textColor);
        loginPatientButton.setBackground(buttonColor);
        loginPatientButton.setForeground(textColor);
        bookAppointmentButton.setBackground(buttonColor);
        bookAppointmentButton.setForeground(textColor);
        loginDentistButton.setBackground(buttonColor);
        loginDentistButton.setForeground(textColor);
        loginStaffButton.setBackground(buttonColor);
        loginStaffButton.setForeground(textColor);

        registerPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open registration window for patients
                PatientGUI patientGUI = new PatientGUI();
                patientGUI.setVisible(true);}});

        loginPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open login window for patients
                PatientLoginGUI patientLoginGUI = new PatientLoginGUI();
                patientLoginGUI.setVisible(true);}});

        bookAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open appointment booking window
                AppointmentBookingGUI appointmentBookingGUI = new AppointmentBookingGUI();
                appointmentBookingGUI.setVisible(true);}});

        loginDentistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open login window for dentists
                DentistLoginGUI dentistLoginGUI = new DentistLoginGUI();
                dentistLoginGUI.setVisible(true);}});

        loginStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open login window for staff
                StaffLoginGUI staffLoginGUI = new StaffLoginGUI();
                staffLoginGUI.setVisible(true);}});

        panel.add(registerPatientButton);
        panel.add(loginPatientButton);
        panel.add(bookAppointmentButton);
        panel.add(loginDentistButton);
        panel.add(loginStaffButton);

        // panel background color
        panel.setBackground(panelColor);
        // Add padding to the panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panel);}

    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }
}
