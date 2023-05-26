package Calendar;

import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.MonthlyCalendar;

public class MonthlyCalendarFactory implements CalendarFactory {
    @Override
    public CalendarAbstract createCalendar(int user_id) {
        return new MonthlyCalendar(user_id);
    }

}
