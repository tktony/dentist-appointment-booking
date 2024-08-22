
package gui;

import javax.swing.*;
import database.MainDatabase;
import users.Dentist;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial") // version control problem just ignore not important
public class DentistLoginGUI extends JFrame {

    public DentistLoginGUI() {
        setTitle("Dentist Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate the dentist
                Dentist authenticatedDentist = MainDatabase.getInstance().authenticateDentist(username, password);

                if (authenticatedDentist != null) {
                    // If the dentist is authenticated successfully, retrieve the name
                    String dentistName = authenticatedDentist.getName();
                    // Display a welcome message
                    JOptionPane.showMessageDialog(DentistLoginGUI.this, "Welcome, " + dentistName + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    // Open the dentist home GUI 
                    DentistHomeGUI dentistHomeGUI = new DentistHomeGUI(dentistName);
                    dentistHomeGUI.setVisible(true);
                    dispose(); // Close the login window
                } else {
                    // If authentication fails, show an error message
                    JOptionPane.showMessageDialog(DentistLoginGUI.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }}});

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        add(panel);}
}
