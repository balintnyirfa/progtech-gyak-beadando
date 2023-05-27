package classes;

import Calendar.CalendarAbstract;

import java.util.Observable;
import java.util.Observer;

public class User implements Observer {
    private int ID;
    private String Username;
    private String Firstname;
    private String Lastname;
    private String Email;
    private String Password;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public void update(Observable o, Object arg) {
        CalendarAbstract calendar = (CalendarAbstract) o;
    }
}
