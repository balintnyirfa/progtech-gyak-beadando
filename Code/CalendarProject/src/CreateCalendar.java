import javax.swing.*;
import java.awt.*;
//import org.jdatepicker.JDatePicker;
import classes.User;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.mysql.cj.xdevapi.SessionImpl;
import com.toedter.calendar.JDateChooser;
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;
import Calendar.CalendarAbstract;
import Calendar.CalendarFactory;
import Calendar.CalendarTypeEnum;
import Calendar.DailyCalendarFactory;
import Calendar.WeeklyCalendarFactory;
import Calendar.MonthlyCalendarFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.security.AuthProvider;
import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;
//import java.util.Date;
import java.sql.Date;

public class CreateCalendar extends JDialog{

    private JPanel CreateCalendarPanel;
    private JTextField calendarTitle;
    private JComboBox<CalendarTypeEnum> calendarTypeComboBox;
    private JButton createCalendar_btn;
    private JPanel startDateChooserJP;
    private JPanel endDateChooserJP;

    Calendar cld = Calendar.getInstance(); //Aktuális dátum
    JDateChooser startDateChooser = new JDateChooser(cld.getTime()); //Dátum választó mai dátummal kezdőértékként.
    JDateChooser endDateChooser = new JDateChooser(cld.getTime());

    public CalendarAbstract cal;
    public User user;


    public CreateCalendar(JFrame parent) {
        super(parent);
        setTitle("Új naptár létrehozása");
        setContentPane(CreateCalendarPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        startDateChooserJP.add(startDateChooser);
        endDateChooserJP.add(endDateChooser);

        // Naptár létrehozása
        CalendarTypeEnum type = Enum.valueOf(CalendarTypeEnum.class, calendarTypeComboBox.getName());
        Date from_date = Date.valueOf(startDateChooserJP.getName());
        Date to_date = Date.valueOf(endDateChooserJP.getName());
        String title = calendarTitle.getText();

        if(cal != null){
            JOptionPane.showMessageDialog(this, "Sikeres létrehozás!");
            HomePage home = new HomePage(null);
            home.setVisible(true);
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Sikertelen létrehozás!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (title.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "A cím nem maradhat üresen!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        createCalendar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cal = addCalendarToDatabase(user.getID(), type, from_date, to_date, title);

                switch (type) {
                    case NAPI:
                        //Napi naptár
                        CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
                        CalendarAbstract dailyCalendar = dailyCalendarFactory.createCalendar(cal.getUser_id());
                        break;
                    case HETI:
                        //Heti naptár
                        CalendarFactory weeklyCalendarFactory = new WeeklyCalendarFactory();
                        CalendarAbstract weeklyCalendar = weeklyCalendarFactory.createCalendar(cal.getUser_id());
                        break;
                    case HAVI:
                        //Havi naptár
                        CalendarFactory monthlyCalendarFactory = new MonthlyCalendarFactory();
                        CalendarAbstract monthlyCalendar = monthlyCalendarFactory.createCalendar(cal.getUser_id());
                        break;
                }
            }
        });

        setVisible(true);
    }


    private void createUIComponents() {
        calendarTypeComboBox = new JComboBox<>(CalendarTypeEnum.values());
    }

    private CalendarAbstract addCalendarToDatabase(int user_id, CalendarTypeEnum type, Date from_date, Date to_date, String title) {
        CalendarAbstract cal = null;
        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO calendar(user_id, type, from_date, to_date, title) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, user_id);
            preparedStatement.setObject(3, type);
            preparedStatement.setDate(4, from_date);
            preparedStatement.setDate(5, to_date);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                cal = new CalendarAbstract(user_id){};
                cal.setUser_id(user_id);
                cal.setType(type);
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
