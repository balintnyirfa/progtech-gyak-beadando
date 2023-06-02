package Calendar;

import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.WeeklyCalendar;

import java.sql.Date;

public class WeeklyCalendarFactory implements CalendarFactory {
    @Override
    public CalendarAbstract createCalendar(int user_id) {
        //return new WeeklyCalendar(user_id);
        CalendarAbstract calendar = new WeeklyCalendar(user_id);
        calendar.addCalendarToDatabase(calendar.getUser_id(), CalendarTypeEnum.HETI.toString(),
                (Date) calendar.getFrom_date(), (Date) calendar.getTo_date(), calendar.getTitle());
        return calendar;
    }
}
