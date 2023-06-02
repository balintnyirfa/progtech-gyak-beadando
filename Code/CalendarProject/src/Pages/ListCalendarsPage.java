package Pages;

import classes.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ListCalendarsPage extends JDialog {
    private JPanel ListCalendarsPanel;
    private JTable CalendarsTable;
    private JButton visszaButton;
    private JButton listItemsBtn;
    private JPanel CalendarsPanel;
    private User user;

    public ListCalendarsPage(JFrame parent){
        super(parent);
        setTitle("Naptáraim");
        setContentPane(ListCalendarsPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";


        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage h = new HomePage(null);
                h.setVisible(true);
            }
        });

        listItemsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    Statement stm = conn.createStatement();
                    String query = "SELECT title, type FROM `calendar` WHERE user_id = 4"; //A bejelentkezett user adati kellenenek inkább
                    //PreparedStatement preparedStatement = conn.prepareStatement(query);
                    //preparedStatement.setInt(1, id);
                    ResultSet rs = stm.executeQuery(query);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) CalendarsTable.getModel();

                    int cols = rsmd.getColumnCount();
                    String[] colName = new String[cols];
                    for (int i = 0; i < cols; i++) {
                        colName[i] = rsmd.getColumnName(i+1);
                    }
                    model.setColumnIdentifiers(colName);
                    String type, title;

                    while (rs.next()) {
                        title = rs.getString(2);
                        type = rs.getString(1);

                        Object[] row = { type, title};
                        model.addRow(row);
                    }
                    stm.close();
                    conn.close();
                }catch (Exception exception){
                    //JDBCTutorialUtilities.printSQLException(e);
                }
            }
        });

        setVisible(true);
    }
}
