package Calendar;

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

    List<Observer> observerList = new ArrayList<>();
    List<Event> eventList = new ArrayList<>();

    public void addObserver(Observer observer){
        observerList.add(observer);
    }
    public void removeObserver(Observer observer){
        observerList.remove(observer);
    }

    //Ezt a kettőt teszzük majd command-ba!
    public void addEvent(Event event){
        eventList.add(event);
    }
    public void removeEvent(Event event){
        eventList.remove(event);
    }

    //public abstract Calendar createCalendar();    ---> CalendarFactoryben lesz

}
