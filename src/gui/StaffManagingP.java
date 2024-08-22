

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")//VC warning
public class StaffManagingP extends JFrame {

    public StaffManagingP() {
        setTitle("Manage Patients");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        
        JLabel titleLabel = new JLabel("Manage Patients");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel);

        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // logic to add a new patient
                JOptionPane.showMessageDialog(StaffManagingP.this, "Feature under development", "Info", JOptionPane.INFORMATION_MESSAGE);
            }});
        panel.add(addPatientButton);

        JButton viewPatientsButton = new JButton("View Patients");
        viewPatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // logic to view existing patients
                JOptionPane.showMessageDialog(StaffManagingP.this, "Feature under development", "Info", JOptionPane.INFORMATION_MESSAGE);
            }});
        panel.add(viewPatientsButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                dispose();}});
        panel.add(backButton);
        
        add(panel);}
}
