package database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import users.*;

public class MainDatabase {
    private static MainDatabase instance;
    private List<Patient> patients;
    private Dentist dentist;
    private Staff staff;
    
    private MainDatabase() {
        // Initialize lists
        patients = new ArrayList<>();
        // Initialize dentist and staff
        dentist = new Dentist("John Doe", "dentist", "password");
        staff = new Staff("Emily Brown", "staff", "password");}

	 	// Method to get patient information
	    public String getPatientInfo(String name, String username, String password) {
	        Patient patient = getPatient(name, username, password);
	        if (patient != null) {
	            StringBuilder info = new StringBuilder();
	            info.append("Patient Name: ").append(patient.getName()).append("\n");
	            info.append("Patient Username: ").append(patient.getUsername()).append("\n");
	            info.append("Patient Password: ").append(patient.getPassword()).append("\n");
	            // Add any other relevant patient information here
	            return info.toString();
	        } else {
	            return "Patient not found.";}}
	    
	    // Singleton getInstance method
	    public static MainDatabase getInstance() {
	        if (instance == null) {
	            instance = new MainDatabase();}
	        return instance;}

	    // Retrieve the name of the dentist
	    public String getDentistName() {
	        return dentist.getName();}

	    // Retrieve all appointments for all dentists
	    public List<String> getDentistAppointments() {
	        List<String> appointments = new ArrayList<>();
	        for (Patient patient : patients) {
	            List<Appointment> bookedAppointments = patient.getBookedAppointments();
	            for (Appointment appointment : bookedAppointments) {
	                appointments.add("Appointment for " + patient.getName() + " on " + appointment.getDate() + " at " + appointment.getTime());}}
	        return appointments;}

	    // Book an appointment for a patient
	    public boolean bookAppointment(Patient patient, Appointment appointment) {
	        // Check if the patient is not null
	        if (patient != null && appointment != null) {
	            // Check if the appointment date and time slot are available
	            if (!isAppointmentBooked(appointment)) {
	                // If the appointment is available, add it to the patient's booked appointments
	                patient.addBookedAppointment(appointment);
	                return true;}}
	        return false;}

	 	// Check if the given appointment date and time slot are already booked
	    private boolean isAppointmentBooked(Appointment appointment) {
	        for (Patient patient : patients) {
	            List<Appointment> bookedAppointments = patient.getBookedAppointments();
	            for (Appointment bookedAppointment : bookedAppointments) {
	                // Check if the booked appointment date matches the given appointment's date
	                // and the booked appointment time matches the given appointment's time
	                if (isSameDate(bookedAppointment.getDate(), appointment.getDate()) &&
	                    bookedAppointment.getTime().equals(appointment.getTime())) {
	                    // If the appointment date and time slot are already booked, return true
	                    return true;}}}
	        // If the appointment date and time slot are available, return false
	        return false;}

	    // Check if two dates have the same date part (ignoring time)
	    private boolean isSameDate(Date date1, Date date2) {
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
	               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);}
	    
	    // Retrieve all booked appointments
	    public List<Appointment> getAllAppointments() {
	        List<Appointment> allAppointments = new ArrayList<>();
	        for (Patient patient : patients) {
	            allAppointments.addAll(patient.getBookedAppointments());}
	        return allAppointments;}

	    // Retrieve booked appointments for a patient
	    public List<Appointment> getBookedAppointments(Patient patient) {
	        if (patient != null) {
	            return patient.getBookedAppointments();}
	        return null;}

    	// Register a new patient
    	public boolean registerPatient(Patient patient) {
        	if (patients.contains(patient)) {
            	// Patient already exists
            	return false;}
        	patients.add(patient);
        	return true;}

    	// Get a patient by name, user-name, and password
    	public Patient getPatient(String name, String username, String password) {
        	for (Patient patient : patients) {
            	if (patient.getName().equals(name) && patient.getUsername().equals(username)&& patient.getPassword().equals(password)) {
                	return patient; // Return the patient if found
            	}}
        	return null; // Return null if patient not found
    		}

    	// Authenticate a patient
    	public Patient authenticatePatient(String name, String username, String password) {
    		for (Patient patient : patients) {
    			if (patient.getName().equals(name) && patient.getUsername().equals(username)&& patient.getPassword().equals(password)) {
    				return patient; // Authentication successful
            }}
        return null; // Authentication failed
    	}

    	// Authenticate a dentist
    	public Dentist authenticateDentist(String username, String password) {
    		if (dentist.getUsername().equals(username) && dentist.getPassword().equals(password)) {
    			return dentist; // Authentication successful
    		}
    		return null; // Authentication failed
    	}

    	// Authenticate a staff member
    	public Staff authenticateStaff(String username, String password) {
    		if (staff.getUsername().equals(username) && staff.getPassword().equals(password)) {
    			return staff; // Authentication successful
    		}
    		return null; // Authentication failed
    	}
    
    	// Add Appointment Method
    	public void addAppointment(Patient patient, Appointment appointment) {
    		if (patient != null && appointment != null) {
    				patient.addBookedAppointment(appointment);}}
    
    	// Method to parse date from string
    	private Date parseDate(String dateString) {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    		try {
    			return dateFormat.parse(dateString);
    		} catch (ParseException e) {
    			e.printStackTrace();
    			return null; // Return null if parsing fails
        }}

    	// Delete Appointment Method
    	public void deleteAppointment(String date, String time) {
    		// Convert date string to Date object
    		Date appointmentDate = parseDate(date);
    		if (appointmentDate == null) {
    			JOptionPane.showMessageDialog(null, "Error: Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
    			return; // Exit method if date parsing fails
    		}

        boolean appointmentFound = false;
        for (Patient patient : patients) {
            List<Appointment> bookedAppointments = patient.getBookedAppointments();
            for (int i = 0; i < bookedAppointments.size(); i++) {
                Appointment bookedAppointment = bookedAppointments.get(i);
                // Check if the appointment date and time match
                if (bookedAppointment.getDate().equals(appointmentDate) &&
                    bookedAppointment.getTime().equals(time)) {
                    bookedAppointments.remove(i);
                    appointmentFound = true;
                    break; // Exit loop once appointment is deleted
                }}
            if (appointmentFound) {
                break; // Exit outer loop if appointment is found and deleted
            }}

        if (appointmentFound) {
            JOptionPane.showMessageDialog(null, "Appointment deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "Error: Appointment not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;}}
}
