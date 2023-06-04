package classes;

import Calendar.CalendarAbstract;
import classes.Event;
import classes.ICommand;

public class AddEventCommand implements ICommand {
    private CalendarAbstract calendar;
    public Event event;

    public AddEventCommand(CalendarAbstract calendar, Event event) {
        this.calendar = calendar;
        this.event = event;
    }

    @Override
    public void ExecuteEvent() {
        calendar.addEvent(event);
    }

}
