public class WeeklyCalendarFactory implements CalendarFactory{
    @Override
    public CalendarAbstract createCalendar() {
        return new WeeklyCalendar();
    }
}
