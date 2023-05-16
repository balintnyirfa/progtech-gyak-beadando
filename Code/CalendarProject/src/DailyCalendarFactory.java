public class DailyCalendarFactory implements CalendarFactory{
    @Override
    public Calendar createCalendar() {
        return new DailyCalendar();
    }
}
