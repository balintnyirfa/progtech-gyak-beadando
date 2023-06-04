package classes;

import Calendar.CalendarAbstract;

import javax.swing.*;
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


    public void update(Observable o, Object arg) {
        CalendarAbstract calendar = (CalendarAbstract) o;
        if(arg instanceof AddEventCommand){
            AddEventCommand addEventCommand = (AddEventCommand)arg;
            actionWhenAddEvent(calendar, addEventCommand);
        }
        else if(arg instanceof DeleteEventCommand) {
            DeleteEventCommand deleteEventCommand = (DeleteEventCommand) arg;
            actionWhenRemoveEvent(calendar, deleteEventCommand);
        }
    }

    private void actionWhenAddEvent(CalendarAbstract calendarAbstract, AddEventCommand addEventCommand) {
        JOptionPane.showMessageDialog(null, addEventCommand.getCalendar().getTitle() + " nevű naptárba "+ addEventCommand.getEvent().getTitle() + " nevű eseményt töltöttél fel!", "For Observer", JOptionPane.PLAIN_MESSAGE);
    }
    private void actionWhenRemoveEvent(CalendarAbstract calendarAbstract, DeleteEventCommand deleteEventCommand) {
        JOptionPane.showMessageDialog(null, deleteEventCommand.getCalendar().getTitle() + " nevű naptárból "+ deleteEventCommand.getEvent().getTitle() + " nevű eseményt töröltél!", "For Observer", JOptionPane.PLAIN_MESSAGE);
    }
}
