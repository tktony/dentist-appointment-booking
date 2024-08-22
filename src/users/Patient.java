
package users;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private List<Appointment> bookedAppointments;

    public Patient(String name, String username, String password) {
        super(name, username, password);
        this.bookedAppointments = new ArrayList<>();}

    public List<Appointment> getBookedAppointments() {
        return bookedAppointments;}

    public void addBookedAppointment(Appointment appointment) {
        bookedAppointments.add(appointment);}
}
