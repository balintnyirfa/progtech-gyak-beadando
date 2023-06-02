package Calendar;

import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.MonthlyCalendar;

import java.sql.Date;

public class MonthlyCalendarFactory implements CalendarFactory {
    @Override
    public CalendarAbstract createCalendar(int user_id) {
        //return new MonthlyCalendar(user_id);
        CalendarAbstract calendar = new MonthlyCalendar(user_id);
        calendar.addCalendarToDatabase(calendar.getUser_id(), CalendarTypeEnum.HAVI.toString(),
                (Date) calendar.getFrom_date(), (Date) calendar.getTo_date(), calendar.getTitle());
        return calendar;
    }

}
