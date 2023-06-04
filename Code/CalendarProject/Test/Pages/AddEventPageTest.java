package Pages;

import Calendar.CalendarAbstract;
import Calendar.DailyCalendar;
import junit.framework.TestCase;

import java.sql.Timestamp;
import java.util.Date;

public class AddEventPageTest extends TestCase {
    public void testAddEvent_WithValidData(){
        java.sql.Timestamp start = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        java.sql.Timestamp end = java.sql.Timestamp.valueOf("2007-09-23 12:10:10.0");
        CalendarAbstract calendar = new DailyCalendar(1);
        calendar.setID(14);
        AddEventPage addEventPage = new AddEventPage(null, calendar);
        addEventPage.eventTitle.setText("Title");
        addEventPage.eventContent.setText("Content");
        addEventPage.startDateChooser.setDate(start);
        addEventPage.endDateChooser.setDate(end);
        addEventPage.AddEventToDatabase(calendar);
    }
    public void testAddEvent_WithEmptyField(){
        java.sql.Timestamp start = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        java.sql.Timestamp end = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
        CalendarAbstract calendar = new DailyCalendar(1);
        calendar.setID(14);
        AddEventPage addEventPage = new AddEventPage(null, calendar);
        addEventPage.eventTitle.setText("Title");
        addEventPage.eventContent.setText("");
        addEventPage.startDateChooser.setDate(start);
        addEventPage.endDateChooser.setDate(end);
        addEventPage.AddEventToDatabase(calendar);
    }
}