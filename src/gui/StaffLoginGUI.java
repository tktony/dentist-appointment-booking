

package gui;

import javax.swing.*;

import database.MainDatabase;
import users.Staff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")//VC warning
public class StaffLoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public StaffLoginGUI() {
        setTitle("Staff Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //login logic for staff
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Call method to validate staff login
                boolean loggedIn = validateStaffLogin(username, password);
                
                if (loggedIn) {
                    // If login successful, open the staff home window
                    JOptionPane.showMessageDialog(StaffLoginGUI.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close login window
                    // Open staff home window
                    StaffHomeGUI staffHomeGUI = new StaffHomeGUI();
                    staffHomeGUI.setVisible(true);
                } else {
                    // If login failed, show error message
                    JOptionPane.showMessageDialog(StaffLoginGUI.this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }}});

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);}

    private boolean validateStaffLogin(String username, String password) {
        // Query the database to authenticate staff login
        // call the authenticateStaff method from the MainDatabase class
        MainDatabase mainDatabase = MainDatabase.getInstance();
        Staff staff = mainDatabase.authenticateStaff(username, password);
        
        // Check if the authentication was successful
        return staff != null;}

}
