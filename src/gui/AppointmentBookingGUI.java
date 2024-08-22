
package gui;
import database.MainDatabase;
import users.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial") // version control problem just ignore not important
public class AppointmentBookingGUI extends JFrame {

    private JLabel dentistLabel;
    private JCalendar calendar;
    private JComboBox<String> timeComboBox;
    private JButton bookButton;
    
    private boolean isSunday(Date date) {//checking if Sunday
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SUNDAY;}
    

    public AppointmentBookingGUI() {
        setTitle("Appointment Booking");
        setSize(600, 470);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for date and time selection
        JPanel dateTimePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        dateTimePanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // padding
        calendar = new JCalendar();
        dateTimePanel.add(new JLabel("Date:"));
        dateTimePanel.add(calendar);
        dateTimePanel.add(new JLabel("Time:"));
        timeComboBox = new JComboBox<>(getTimeSlots());
        dateTimePanel.add(timeComboBox);
        panel.add(dateTimePanel, BorderLayout.NORTH);

        // Display the dentist name
        dentistLabel = new JLabel("Dentist: " + MainDatabase.getInstance().getDentistName());
        dentistLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        panel.add(dentistLabel, BorderLayout.CENTER);

        // Add button for booking appointment
        bookButton = new JButton("Book Appointment");
        
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = calendar.getDate();
                String selectedTime = (String) timeComboBox.getSelectedItem();
                // check if Sunday
                if (isSunday(selectedDate)) {
                    JOptionPane.showMessageDialog(AppointmentBookingGUI.this, "Appointments cannot be booked on Sundays.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method without booking the appointment
                }
                
                //Create an Appointment object with the selected date, time
                Appointment appointment = new Appointment(selectedDate, selectedTime);
                System.out.print(appointment);
         
                String authenticatedPatientName = AuthenticationHelper.getAuthenticatedPatientName();
                String authenticatedPatientUsername = AuthenticationHelper.getAuthenticatedPatientUsername();
                String authenticatedPatientPassword = AuthenticationHelper.getAuthenticatedPatientPassword();

                Patient patient = MainDatabase.getInstance().getPatient(authenticatedPatientName, authenticatedPatientUsername, authenticatedPatientPassword);

                if (patient != null) {
                    boolean bookingSuccess = MainDatabase.getInstance().bookAppointment(patient, appointment);
                    if (bookingSuccess) {
                        JOptionPane.showMessageDialog(AppointmentBookingGUI.this, "Appointment booked for " + authenticatedPatientName + " on " + formatDate(selectedDate) + " at " + selectedTime, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(AppointmentBookingGUI.this, "Failed to book appointment. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AppointmentBookingGUI.this, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }}});

        panel.add(bookButton, BorderLayout.SOUTH);
        add(panel);}
    
    //toggle time slots for appointments with 30 minute intervals
    private String[] getTimeSlots() {
        String[] timeSlots = new String[16];
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        for (int i = 0; i < timeSlots.length; i++) {
            timeSlots[i] = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            calendar.add(Calendar.MINUTE, 30);}
        return timeSlots;}

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);}}
