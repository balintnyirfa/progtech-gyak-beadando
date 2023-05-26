package Calendar;

import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.MonthlyCalendar;

public class MonthlyCalendarFactory implements CalendarFactory {
    @Override
    public CalendarAbstract createCalendar() {
        return new MonthlyCalendar();
    }
}
