
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")//VC warning
public class StaffHomeGUI extends JFrame {

    public StaffHomeGUI() {
        setTitle("Staff Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 5));

        JLabel welcomeLabel = new JLabel("Welcome, Staff!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel);

        
        JButton manageAppointmentsButton = new JButton("Manage Appointments");
        manageAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the StaffManagingGUI window
                StaffManagingGUI staffManagingGUI = new StaffManagingGUI();
                staffManagingGUI.setVisible(true);}});
        
        panel.add(manageAppointmentsButton);

        JButton managePatientsButton = new JButton("Manage Patients");
        managePatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the StaffManagingP GUI
                StaffManagingP staffManagingP = new StaffManagingP();
                staffManagingP.setVisible(true);}});
        
        panel.add(managePatientsButton);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logout logic
                int choice = JOptionPane.showConfirmDialog(StaffHomeGUI.this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose(); // Close staff home window
                    StaffLoginGUI staffLoginGUI = new StaffLoginGUI();
                    staffLoginGUI.setVisible(true); // Open staff login window
                }}});
        panel.add(logoutButton);
        add(panel);}

}
