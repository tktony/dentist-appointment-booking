

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import database.MainDatabase;
import users.Patient;

@SuppressWarnings("serial")// version control message
public class PatientLoginGUI extends JFrame {

    public PatientLoginGUI() {
        setTitle("Patient Login");
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

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve username and password from input fields
            	String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Authenticate the patient
                Patient authenticatedPatient = MainDatabase.getInstance().authenticatePatient(name, username, password);
                // Check if authentication was successful
                if (authenticatedPatient != null) {
                	// If authentication successful, display a success message
                	JOptionPane.showMessageDialog(PatientLoginGUI.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // If authentication successful, open the patient home window
                    PatientHomeGUI homeGUI = new PatientHomeGUI(authenticatedPatient);
                    homeGUI.setVisible(true);
                    // Close the login window
                    dispose();
                } else {
                    // If authentication failed, display an error message
                    JOptionPane.showMessageDialog(PatientLoginGUI.this, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }} });
        
        // Create a label that functions as a link to the registration page
        JLabel registerLinkLabel = new JLabel("Don't have an account? Register here");
        registerLinkLabel.setForeground(Color.BLUE);
        registerLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the registration page
                dispose(); // Close the login page
                PatientGUI patientGUI = new PatientGUI();
                patientGUI.setVisible(true);}});
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerLinkLabel); 

        add(panel);}

}
