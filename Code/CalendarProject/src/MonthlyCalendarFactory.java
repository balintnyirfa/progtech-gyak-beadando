public class MonthlyCalendarFactory implements CalendarFactory{
    @Override
    public Calendar createCalendar() {
        return new MonthlyCalendar();
    }
}
