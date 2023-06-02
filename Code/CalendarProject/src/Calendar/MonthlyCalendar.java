package Calendar;

import Calendar.CalendarAbstract;

import java.sql.*;

import static Pages.SignInPage.user;

public class MonthlyCalendar extends CalendarAbstract {
    public MonthlyCalendar(int user_id) {
        super(user_id);
    }

    @Override
    public CalendarAbstract addCalendarToDatabase(int user_id, String type, Date from_date, Date to_date, String title) {
        CalendarAbstract cal = null;
        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO calendar(user_id, type, from_date, to_date, title) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setObject(2, type);
            preparedStatement.setDate(3, from_date);
            preparedStatement.setDate(4, to_date);
            preparedStatement.setString(5, title);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                cal = new MonthlyCalendar(user_id){};
                cal.setUser_id(user_id);
                cal.setType(CalendarTypeEnum.valueOf(type));
                cal.setFrom_date(from_date);
                cal.setTo_date(to_date);
                cal.setTitle(title);
            }
            stm.close();
            conn.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return cal;
    }

}
