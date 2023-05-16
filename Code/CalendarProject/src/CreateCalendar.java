public class CreateCalendar {

    //Napi naptár
    CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
    Calendar dailyCalendar = dailyCalendarFactory.createCalendar();


    //Heti naptár
    CalendarFactory weeklyCalendarFactory = new WeeklyCalendarFactory();
    Calendar weeklyCalendar = weeklyCalendarFactory.createCalendar();


    //Havi naptár
    CalendarFactory monthlyCalendarFactory = new MonthlyCalendarFactory();
    Calendar monthlyCalendar = monthlyCalendarFactory.createCalendar();

}
