package Pages;

import Calendar.CalendarAbstract;
import classes.DeleteEventCommand;
import classes.RowListener;
import classes.TableRowClickListener;
import classes.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalendarPage extends  JDialog {
    final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "";
    private JButton addEvent;
    private JPanel calendarPage;
    private JTable eventsTable;
    private JLabel titleText;
    private JButton backButton;
    private JButton deleteEventButton;
    private JScrollPane dataPanel;
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

        int calendarId = calanderId;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            String sql = "SELECT ID, title, content, from_date, to_date FROM event WHERE calendar_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, calendarId);

            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            DefaultTableModel model = (DefaultTableModel) eventsTable.getModel();
            String[] colName = new String[]{"Cím", "Leírás", "Kezdeti dátum", "Vég dátum"};
            model.setColumnIdentifiers(colName);

            eventsTable.revalidate();
            eventsTable.repaint();


            String title, content;
            Timestamp from, to;
            int id;
            List<Event> events = new ArrayList<>();

            while (rs.next()) {
                Event event = new Event();
                id = rs.getInt("ID");
                title = rs.getString("title");
                content = rs.getString("content");
                from = rs.getTimestamp("from_date");
                to = rs.getTimestamp("to_date");
                Object[] row = {title, content, from, to};
                model.addRow(row);

                event.setID(id);
                event.setTitle(title);
                events.add(event);
            }

            stmt.close();
            conn.close();

            RowListener rl = new RowListener();
            eventsTable.addMouseListener(new TableRowClickListener(eventsTable, rl));
            deleteEventButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = rl.GetSelectedRow();
                    try {
                        DeleteEvent(events.get(row));
                        dispose();
                        CalendarPage c = new CalendarPage(null, calendarAbstract);
                        c.setVisible(true);
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null, "Nincs kiválasztva sor!");
                    }

                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void DeleteEvent(Event event){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM event WHERE ID = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, event.getID());

            preparedStatement.execute();

            DeleteEventCommand d = new DeleteEventCommand(calendarAbstract, event);
            d.ExecuteEvent();
            dataPanel.revalidate();
            eventsTable.revalidate();       //nem működik valamiért
            eventsTable.repaint();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
