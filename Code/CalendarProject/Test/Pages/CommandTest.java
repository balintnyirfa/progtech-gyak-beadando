package Pages;

import classes.AddEventCommand;
import classes.Event;
import junit.framework.TestCase;
import Calendar.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class CommandTest extends TestCase {
    public void testAddDailyEventCommandExecution() {
        java.sql.Timestamp start = java.sql.Timestamp.valueOf("2023-06-23 10:10:10.0");
        java.sql.Timestamp end = java.sql.Timestamp.valueOf("2023-06-23 10:10:10.0");

        CalendarAbstract calendar = new DailyCalendar(1);

        Event event = new Event();
        event.setID(1);
        event.setCalendar_id(1);
        event.setTitle("Prog. tech. vizsga");
        event.setContent("Lorem ipsum dolor cetli valami.");
        event.setFrom(start);
        event.setTo(end);

        AddEventCommand addEventCommand = new AddEventCommand(calendar, event);

        addEventCommand.ExecuteEvent();

        assertTrue(calendar.hasEvent(event));
    }

    public void testAddWeeklyEventCommandExecution() {
        java.sql.Timestamp start = java.sql.Timestamp.valueOf("2023-06-23 10:10:10.0");
        java.sql.Timestamp end = java.sql.Timestamp.valueOf("2023-06-30 10:10:10.0");

        CalendarAbstract calendar = new WeeklyCalendar(1);

        Event event = new Event();
        event.setID(1);
        event.setCalendar_id(1);
        event.setTitle("Balatoni kiruccanás");
        event.setContent("Lorem ipsum dolor cetli valami.");
        event.setFrom(start);
        event.setTo(end);

        AddEventCommand addEventCommand = new AddEventCommand(calendar, event);

        addEventCommand.ExecuteEvent();

        assertTrue(calendar.hasEvent(event));
    }

    public void testAddMonthlyEventCommandExecution() {
        java.sql.Timestamp start = java.sql.Timestamp.valueOf("2023-06-23 10:10:10.0");
        java.sql.Timestamp end = java.sql.Timestamp.valueOf("2023-07-23 10:10:10.0");

        CalendarAbstract calendar = new MonthlyCalendar(1);

        Event event = new Event();
        event.setID(1);
        event.setCalendar_id(1);
        event.setTitle("VILÁGURALOM");
        event.setContent("Lorem ipsum dolor cetli valami.");
        event.setFrom(start);
        event.setTo(end);

        AddEventCommand addEventCommand = new AddEventCommand(calendar, event);

        addEventCommand.ExecuteEvent();

        assertTrue(calendar.hasEvent(event));
    }
}
