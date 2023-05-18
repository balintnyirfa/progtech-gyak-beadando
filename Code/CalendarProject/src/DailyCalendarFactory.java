public class DailyCalendarFactory implements CalendarFactory{
    @Override
    public CalendarAbstract createCalendar() {
        return new DailyCalendar();
    }
}
