public class WeeklyCalendarFactory implements CalendarFactory{
    @Override
    public Calendar createCalendar() {
        return new WeeklyCalendar();
    }
}
