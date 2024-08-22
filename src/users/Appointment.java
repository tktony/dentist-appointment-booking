

package users;

import java.util.Date;

public class Appointment {
    private Date date;
    private String time;

    public Appointment(Date date, String time) {
        this.date = date;
        this.time = time;}

    public Date getDate() {
        return date;}

    public String getTime() {
        return time;}
}
