package Pages;

import Calendar.*;
import Pages.CreateCalendar;
import classes.User;
import junit.framework.TestCase;
import org.junit.Test;

import static Pages.SignInPage.user;

public class CreateCalendarPageTest extends TestCase {

    @Test
    public void testCreateDailyCalendar() {
        User u = new User();
        CalendarFactory factory = new DailyCalendarFactory();

        CalendarAbstract dailyCalendar = factory.createCalendar(u.getID());

        assertTrue(dailyCalendar instanceof DailyCalendar);
    }

    @Test
    public void testCreateWeeklyCalendar() {
        User u = new User();
        CalendarFactory factory = new WeeklyCalendarFactory();

        CalendarAbstract weeklyCalendar = factory.createCalendar(u.getID());

        assertTrue(weeklyCalendar instanceof WeeklyCalendar);
    }

    @Test
    public void testCreateMonthlyCalendar() {
        User u = new User();
        CalendarFactory factory = new MonthlyCalendarFactory();

        CalendarAbstract monthlyCalendar = factory.createCalendar(u.getID());

        assertTrue(monthlyCalendar instanceof MonthlyCalendar);
    }
}
