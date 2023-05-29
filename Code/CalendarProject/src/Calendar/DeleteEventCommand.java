package Calendar;

import classes.Event;

import java.util.Calendar;

public class DeleteEventCommand  implements ICommand{
    private CalendarAbstract calendar;
    private Event event;

    public DeleteEventCommand(CalendarAbstract calendar, Event event) {
        this.calendar = calendar;
        this.event = event;
    }

    @Override
    public void ExecuteEvent() {
        calendar.removeEvent(event);
    }
}
