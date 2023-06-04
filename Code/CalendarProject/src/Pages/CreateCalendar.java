package Pages;

import javax.swing.*;
import java.awt.*;
//import org.jdatepicker.JDatePicker;
import classes.User;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import static Pages.SignInPage.user;

public class CreateCalendar extends JDialog{

    private JPanel CreateCalendarPanel;
    private JTextField calendarTitle;

    JComboBox<String> comboBox;

    private JButton createCalendar_btn;
    private JPanel startDateChooserJP;
    private JPanel endDateChooserJP;
    private JPanel typeJP;
    private JButton visszaButton;

    Calendar cld = Calendar.getInstance(); //Aktuális dátum
    JDateChooser startDateChooser = new JDateChooser(cld.getTime()); //Dátum választó mai dátummal kezdőértékként.
    JDateChooser endDateChooser = new JDateChooser(cld.getTime());



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

        createCalendar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateACalendar();
            }
        });

        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage h = new HomePage(null);
                h.setVisible(true);
            }
        });
    }

    private void CreateACalendar()
    {
        // Naptár létrehozása

        java.util.Date startDate = startDateChooser.getDate();
        java.sql.Date from_date = new java.sql.Date(startDate.getTime());

        java.util.Date endDate = endDateChooser.getDate();
        java.sql.Date to_date = new java.sql.Date(endDate.getTime());

        String title = calendarTitle.getText();


        if (title.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "A cím nem maradhat üresen!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (from_date.getTime() > to_date.getTime())
        {
            JOptionPane.showMessageDialog(this, "Nem megfelelő intervallum", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long daysBetween = (to_date.getTime() - from_date.getTime()) / (1000 * 60 * 60 * 24);
        if (comboBox.getModel().getSelectedItem() == "NAPI" && from_date.equals(to_date))
        {
            CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
            CalendarAbstract dailyCalendar = dailyCalendarFactory.createCalendar(user.getID());
            dailyCalendar.addObserver(user);
            dailyCalendar.addCalendarToDatabase(
                    user.getID(),
                    comboBox.getModel().getSelectedItem().toString(),
                    from_date,
                    to_date,
                    title
            );
            createSuccessOrFail(dailyCalendar);

        }
        else if (comboBox.getModel().getSelectedItem() == "HETI" && daysBetween == 7)
        {
            CalendarFactory weeklyCalendarFactory = new WeeklyCalendarFactory();
            CalendarAbstract weeklyCalendar = weeklyCalendarFactory.createCalendar(user.getID());
            weeklyCalendar.addObserver(user);
            weeklyCalendar.addCalendarToDatabase(
                    user.getID(),
                    comboBox.getModel().getSelectedItem().toString(),
                    from_date,
                    to_date,
                    title
            );
            createSuccessOrFail(weeklyCalendar);
        }
        else if (comboBox.getModel().getSelectedItem() == "HAVI" && (daysBetween == 30 || daysBetween == 31))
        {
            CalendarFactory monthlyCalendarFactory = new MonthlyCalendarFactory();
            CalendarAbstract monthlyCalendar = monthlyCalendarFactory.createCalendar(user.getID());
            monthlyCalendar.addObserver(user);
            monthlyCalendar.addCalendarToDatabase(
                    user.getID(),
                    comboBox.getModel().getSelectedItem().toString(),
                    from_date,
                    to_date,
                    title
            );
            createSuccessOrFail(monthlyCalendar);

        }
        else {
            JOptionPane.showMessageDialog(this, "Nem megfelelő intervallum", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        //cal = addCalendarToDatabase(user.getID(), comboBox.getModel().getSelectedItem().toString(), from_date, to_date, title);
    }

    private void createSuccessOrFail(CalendarAbstract calendar)
    {
        if (calendar != null) {
            JOptionPane.showMessageDialog(this, "Sikeres létrehozás!");
            HomePage home = new HomePage(null);
            home.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sikertelen létrehozás!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*private CalendarAbstract addCalendarToDatabase(int user_id, String type, Date from_date, Date to_date, String title) {
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
                cal = new CalendarAbstract(user_id){};
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
    }*/

}
