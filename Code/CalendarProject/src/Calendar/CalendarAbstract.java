package Calendar;

import classes.AddEventCommand;
import classes.Event;

import java.util.*;

public abstract class CalendarAbstract extends Observable {

    private int ID;
    private String title;
    private int user_id;
    private CalendarTypeEnum type;
    private Date from_date;
    private Date to_date;

    public CalendarAbstract(int user_id)
    {
        this.user_id = user_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public CalendarTypeEnum getType() {
        return type;
    }

    public void setType(CalendarTypeEnum type) {
        this.type = type;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public abstract CalendarAbstract addCalendarToDatabase(int user_id, String type, java.sql.Date from_date, java.sql.Date to_date, String title);

    //List<Observer> observersList = new ArrayList<>();
    List<Event> eventList = new ArrayList<>();

    public void addEvent(Event event){
        eventList.add(event);
        setChanged();
        notifyObservers(new AddEventCommand(this, event));
    }
    public void removeEvent(Event event){
        eventList.remove(event);
        //setChanged();
        //notifyObservers();
    }

    //public abstract Calendar createCalendar();    ---> CalendarFactoryben lesz

}
