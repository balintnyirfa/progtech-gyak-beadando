package Pages;

import Calendar.CalendarAbstract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CalendarPage extends  JDialog {
    private JButton addEvent;
    private JPanel calendarPage;
    private JTable eventsTable;
    private JLabel titleText;
    private JButton backButton;
    private CalendarAbstract calendarAbstract;

    public CalendarPage(JFrame parent, CalendarAbstract calendar){
        super(parent);
        setTitle("Naptár eseményei");
        setContentPane(calendarPage);
        setMinimumSize(new Dimension(800, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        calendarAbstract = calendar;
        titleText.setText(calendarAbstract.getTitle());

        ListEvents(calendarAbstract.getID());
        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddEventPage addEventPage = new AddEventPage(null, calendarAbstract);
                addEventPage.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ListCalendarsPage lp = new ListCalendarsPage(null);
                lp.setVisible(true);
            }
        });
    }

    private void ListEvents(int calanderId){
        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        int calendarId = calanderId;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            String sql = "SELECT title, content, from_date, to_date FROM event WHERE calendar_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, calendarId);

            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            DefaultTableModel model = (DefaultTableModel) eventsTable.getModel();
            String[] colName = new String[]{"Cím", "Leírás", "Kezdeti dátum", "Vég dátum"};
            model.setColumnIdentifiers(colName);

            String title, content;
            Timestamp from, to;

            while (rs.next()) {
                title = rs.getString("title");
                content = rs.getString("content");
                from = rs.getTimestamp("from_date");
                to = rs.getTimestamp("to_date");
                Object[] row = {title, content, from, to};
                model.addRow(row);
            }

            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
