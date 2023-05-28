package Calendar;

import classes.Event;

import java.util.Calendar;

public class EventCommand implements ICommand {
    private Calendar calendar;
    private Event event;

    public EventCommand(Calendar calendar, Event event) {
        this.calendar = calendar;
        this.event = event;
    }

    @Override
    public void AddEvent() {
        //valami.addEvent(event);
    }

    @Override
    public void DeleteEvent() {
        //valami.deleteEvent(event);
    }
}
