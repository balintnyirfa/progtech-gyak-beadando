package Calendar;

import classes.Event;

public class AddEventCommand implements ICommand {
    private CalendarAbstract calendar;
    private Event event;

    public AddEventCommand(CalendarAbstract calendar, Event event) {
        this.calendar = calendar;
        this.event = event;
    }

    @Override
    public void ExecuteEvent() {
        calendar.addEvent(event);
    }

}
