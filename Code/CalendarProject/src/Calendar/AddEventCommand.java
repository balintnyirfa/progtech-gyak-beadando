package Calendar;

import classes.Event;

import java.util.Calendar;

public class AddEventCommand implements IAddCommand {
    private CalendarAbstract calendar;
    private Event event;

    public AddEventCommand(CalendarAbstract calendar, Event event) {
        this.calendar = calendar;
        this.event = event;
    }

    @Override
    public void AddEvent() {
        calendar.addEvent(event);
    }
}
