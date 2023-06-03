package Pages;

import Calendar.CalendarAbstract;
import classes.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalendarPage extends  JDialog {
    private JButton addEvent;
    private JPanel calendarPage;
    private JTable eventsTable;
    private CalendarAbstract calendarAbstract;

    public CalendarPage(JFrame parent, CalendarAbstract calendar){
        super(parent);
        setTitle("Új esemény létrehozása");
        setContentPane(calendarPage);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        calendarAbstract = calendar;

        ListEvents(calendarAbstract.getID());
        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddEventPage addEventPage = new AddEventPage(null, calendarAbstract);
                addEventPage.setVisible(true);
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
            String sql = "SELECT * FROM event WHERE calendar_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, calendarId);

            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) eventsTable.getModel();
            int cols = rsmd.getColumnCount()-2;
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);
            String title, content;
            Date from, to;

            while (rs.next()) {
                title = rs.getString("title");
                content = rs.getString("content");
                from = rs.getDate("from_date");
                to = rs.getDate("to_date");
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
