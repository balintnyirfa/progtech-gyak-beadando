package Pages;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import java.sql.Date;

public class CreateCalendar extends JDialog{

    private JPanel CreateCalendarPanel;
    private JTextField calendarTitle;

    JComboBox<String> comboBox;

    private JButton createCalendar_btn;
    private JPanel startDateChooserJP;
    private JPanel endDateChooserJP;
    private JPanel typeJP;

    Calendar cld = Calendar.getInstance(); //Aktuális dátum
    JDateChooser startDateChooser = new JDateChooser(cld.getTime()); //Dátum választó mai dátummal kezdőértékként.
    JDateChooser endDateChooser = new JDateChooser(cld.getTime());


    public CalendarAbstract cal;
    public User user;

    public CalendarTypeEnum EnumPicker(String selectedModelValue, CalendarTypeEnum type)
    {
        switch (selectedModelValue){
            case "NAPI":
                type = CalendarTypeEnum.NAPI;
            case "HETI":
                type = CalendarTypeEnum.HETI;
            case "HAVI":
                type = CalendarTypeEnum.HAVI;
        }
        return type;
    }

    public CreateCalendar(JFrame parent) {
        super(parent);
        setTitle("Új naptár létrehozása");
        setContentPane(CreateCalendarPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        comboBox = new JComboBox<>();
        startDateChooserJP.add(startDateChooser);
        endDateChooserJP.add(endDateChooser);


        createCalendar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCalendar();
            }
        });

        setVisible(true);

    }

    private void CreateCalendar()
    {
        // Naptár létrehozása

        String selectedtype = (String) comboBox.getSelectedItem();
        CalendarTypeEnum cte = null;
        CalendarTypeEnum type = EnumPicker(selectedtype, cte);

        java.util.Date startDate = startDateChooser.getDate();
        java.sql.Date from_date = new java.sql.Date(startDate.getTime());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String fromDateStr = dateFormat.format(startDate);
        //Date from_date = Date.valueOf(fromDateStr);

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
        if (type == CalendarTypeEnum.NAPI && from_date.equals(to_date))
        {
            CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
            CalendarAbstract dailyCalendar = dailyCalendarFactory.createCalendar(user.getID());
        }
        else {
            JOptionPane.showMessageDialog(this, "Nem megfelelő intervallum", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        cal = addCalendarToDatabase(user.getID(), type, from_date, to_date, title);

        if(cal != null){
            JOptionPane.showMessageDialog(this, "Sikeres létrehozás!");
            HomePage home = new HomePage(null);
            home.setVisible(true);
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Sikertelen létrehozás!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        switch (type) {
            case NAPI:
                //Napi naptár
                CalendarFactory dailyCalendarFactory = new DailyCalendarFactory();
                CalendarAbstract dailyCalendar = dailyCalendarFactory.createCalendar(user.getID());
                break;
            case HETI:
                //Heti naptár
                CalendarFactory weeklyCalendarFactory = new WeeklyCalendarFactory();
                CalendarAbstract weeklyCalendar = weeklyCalendarFactory.createCalendar(user.getID());
                break;
            case HAVI:
                //Havi naptár
                CalendarFactory monthlyCalendarFactory = new MonthlyCalendarFactory();
                CalendarAbstract monthlyCalendar = monthlyCalendarFactory.createCalendar(user.getID());
                break;
        }
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
            preparedStatement.setInt(1, user_id);
            preparedStatement.setObject(2, type);
            preparedStatement.setDate(3, from_date);
            preparedStatement.setDate(4, to_date);
            preparedStatement.setString(5, title);

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


    /*private void createUIComponents() {
        calendarTypeComboBox = new JComboBox<>(CalendarTypeEnum.values());
        Object sel = calendarTypeComboBox.getSelectedItem();
        if (sel == "NAPI")
        {
            sel = CalendarTypeEnum.NAPI;
        }
        else if (sel == "HETI")
        {
            sel = CalendarTypeEnum.HETI;
        }
        else if (sel == "HAVI") {
            sel = CalendarTypeEnum.HAVI;
        }
    }*/
}
