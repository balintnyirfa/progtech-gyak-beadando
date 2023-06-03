package Pages;

import Calendar.*;
import classes.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListCalendarsPage extends JDialog {
    private JPanel ListCalendarsPanel;
    private JTable CalendarsTable;
    private JButton visszaButton;
    private JButton selectedCalendarButton;
    private JPanel CalendarsPanel;
    private int userId;

    public ListCalendarsPage(JFrame parent) {
        super(parent);
        setTitle("Naptáraim");
        setContentPane(ListCalendarsPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);
        userId = SignInPage.user.getID();
        System.out.println(userId);
        ListCalendars();

        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage h = new HomePage(null);
                h.setVisible(true);
            }
        });
    }
    private void ListCalendars(){

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String query = "SELECT title, type, ID FROM `calendar` WHERE user_id = ?"; //A bejelentkezett user adati kellenenek inkább

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) CalendarsTable.getModel();

            int cols = rsmd.getColumnCount() - 1;
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);
            String type, title;
            int id;
            List<CalendarAbstract> calendars = new ArrayList<>();

            while (rs.next()) {
                title = rs.getString("title");
                type = rs.getString("type");
                id = rs.getInt("ID");

                if(CalendarTypeEnum.valueOf(type) == CalendarTypeEnum.NAPI){
                    DailyCalendar d = new DailyCalendar(userId);
                    d.setID(id);
                    d.setTitle(title);
                    calendars.add(d);
                }
                else if(CalendarTypeEnum.valueOf(type) == CalendarTypeEnum.HETI){
                    WeeklyCalendar w = new WeeklyCalendar(userId);
                    w.setID(id);
                    w.setTitle(title);
                    calendars.add(w);
                }
                else{
                    MonthlyCalendar m = new MonthlyCalendar(userId);
                    m.setID(id);
                    m.setTitle(title);
                    calendars.add(m);
                }

                Object[] row = {title, type};
                model.addRow(row);
            }
            stm.close();
            conn.close();

            RowListener rl = new RowListener();
            CalendarsTable.addMouseListener(new TableRowClickListener(CalendarsTable, rl));
            selectedCalendarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = rl.GetSelectedRow();
                    dispose();
                    CalendarPage calendarPage = new CalendarPage(null, calendars.get(row));
                    calendarPage.setVisible(true);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
