package Calendar;

import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.DailyCalendar;

import java.sql.Date;

public class DailyCalendarFactory implements CalendarFactory {
    @Override
    public CalendarAbstract createCalendar(int user_id) {
        //return new DailyCalendar(user_id);
        CalendarAbstract calendar = new DailyCalendar(user_id);
        calendar.addCalendarToDatabase(calendar.getUser_id(), CalendarTypeEnum.NAPI.toString(),
                (Date) calendar.getFrom_date(), (Date) calendar.getTo_date(), calendar.getTitle());
        return calendar;
    }


}
