package classes;

import Calendar.CalendarAbstract;
import classes.Event;
import classes.ICommand;

public class DeleteEventCommand  implements ICommand {
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

    public CalendarAbstract getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarAbstract calendar) {
        this.calendar = calendar;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
