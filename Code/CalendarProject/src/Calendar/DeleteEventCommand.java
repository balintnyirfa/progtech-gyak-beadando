package Calendar;

import classes.Event;

import java.util.Calendar;

public class DeleteEventCommand  implements IDeleteCommand{
    private CalendarAbstract calendar;
    private Event event;

    public DeleteEventCommand(CalendarAbstract calendar, Event event) {
        this.calendar = calendar;
        this.event = event;
    }

    @Override
    public void DeleteEvent() {
        calendar.removeEvent(event);
    }
}
