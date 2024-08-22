
package gui;

import javax.swing.JOptionPane;

public class AuthenticationHelper {
    public static String getAuthenticatedPatientName() {
        // asking the user to input their name
        return JOptionPane.showInputDialog("Enter your name:");
    }

    public static String getAuthenticatedPatientUsername() {
        // asking the user to input their username
        return JOptionPane.showInputDialog("Enter your username:");
    }

    public static String getAuthenticatedPatientPassword() {
        // asking the user to input their password
        return JOptionPane.showInputDialog("Enter your password:");
    }
}
