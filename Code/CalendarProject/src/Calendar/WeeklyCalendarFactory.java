package Calendar;

import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.WeeklyCalendar;

public class WeeklyCalendarFactory implements CalendarFactory {
    @Override
    public CalendarAbstract createCalendar() {
        return new WeeklyCalendar();
    }
}
