package Calendar;

import Calendar.CalendarAbstract;

public interface CalendarFactory {
    CalendarAbstract createCalendar(int user_id);

}
