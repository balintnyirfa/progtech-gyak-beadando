package Pages;

import classes.AddEventCommand;
import classes.Event;
import Calendar.CalendarAbstract;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;

public class AddEventPage extends JDialog{
    private JTextField eventTitle;
    private JTextField eventContent;
    private JButton eventAdd;
    private JPanel createEvent;
    private JButton visszaButton;
    private JPanel startDateJP;
    private JPanel endDateJP;

    //CALENDAR kiválasztástól!
    private CalendarAbstract calendar;

    Calendar cld = Calendar.getInstance(); //Aktuális dátum
    JDateChooser startDateChooser2 = new JDateChooser(cld.getTime()); //Dátum választó mai dátummal kezdőértékként.
    JDateChooser endDateChooser2 = new JDateChooser(cld.getTime());

    public AddEventPage(JFrame parent){
        super(parent);
        setTitle("Új esemény létrehozása");
        setContentPane(createEvent);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            startDateJP.add(startDateChooser2);
            endDateJP.add(endDateChooser2);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        eventAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEventToDatabase();
            }
        });
        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ListCalendarsPage lp = new ListCalendarsPage(null);
                lp.setVisible(true);
            }
        });
        setVisible(true);
    }

    private void AddEventToDatabase(){
        Event event = null;
        String title = eventTitle.getText();
        String content = eventContent.getText();

        java.util.Date startDate = startDateChooser2.getDate();
        java.sql.Date from = new java.sql.Date(startDate.getTime());

        java.util.Date endDate = endDateChooser2.getDate();
        java.sql.Date to = new java.sql.Date(endDate.getTime());
        //int calendar.ID;

        if(title.isEmpty() || content.isEmpty()){
            JOptionPane.showMessageDialog(this, "Az összes mezőt ki kell tölteni!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO user(title, content, calendar_id, from, to) VALUES (?,?,?,?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            //preparedStatement.setString(3, cal_id);
            preparedStatement.setDate(4, from);
            preparedStatement.setDate(5, to);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                event = new Event();
                event.setTitle(title);
                event.setContent(content);
                //event.setFrom(from);
                //event.setTo(to);
                AddEventCommand a = new AddEventCommand(calendar, event);
                a.ExecuteEvent();
            }
            stm.close();
            conn.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
