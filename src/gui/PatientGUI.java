
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import database.MainDatabase;
import users.Patient;

@SuppressWarnings("serial") //ignore version control message
public class PatientGUI extends JFrame {

    public PatientGUI() {
        setTitle("Patient Registration");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 4));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
    
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve patient information from input fields
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Create a new patient
                Patient newPatient = new Patient(name, username, password);
                // Register the new patient in the database
                boolean registrationSuccess = MainDatabase.getInstance().registerPatient(newPatient);

                // Show message dialog based on registration success
                if (registrationSuccess) {
                    JOptionPane.showMessageDialog(PatientGUI.this, "Registration successful for patient: " + name, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PatientGUI.this, "Failed to register patient: " + name + ". Patient already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                }}});

        // Create a label that functions as a link to the login page like hyperlink
        JLabel loginLinkLabel = new JLabel("Already have an account? Login here");
        loginLinkLabel.setForeground(Color.BLUE);
        loginLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the login page
                dispose(); // Close the registration page
                PatientLoginGUI patientLoginGUI = new PatientLoginGUI();
                patientLoginGUI.setVisible(true);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(loginLinkLabel); 
        add(panel);}

}
