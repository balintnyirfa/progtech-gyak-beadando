package Pages;

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
    private User user;

    public ListCalendarsPage(JFrame parent) {
        super(parent);
        setTitle("Naptáraim");
        setContentPane(ListCalendarsPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);
        ListCalendars();

        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage h = new HomePage(null);
                h.setVisible(true);
            }
        });
        setVisible(true);
    }
    private void ListCalendars(){

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String query = "SELECT title, type, ID FROM `calendar` WHERE user_id = 4"; //A bejelentkezett user adati kellenenek inkább
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            //preparedStatement.setInt(1, id);
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
            List<Integer> calendarIds = new ArrayList<>();

            while (rs.next()) {
                title = rs.getString("title");
                type = rs.getString("type");
                calendarIds.add(rs.getInt("ID"));

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
                    CalendarPage calendarPage = new CalendarPage(null, calendarIds.get(row));
                    calendarPage.setVisible(true);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
