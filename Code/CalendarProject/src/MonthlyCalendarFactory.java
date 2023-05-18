public class MonthlyCalendarFactory implements CalendarFactory{
    @Override
    public CalendarAbstract createCalendar() {
        return new MonthlyCalendar();
    }
}
