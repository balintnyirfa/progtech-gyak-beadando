package Pages;

import classes.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ListCalendarsPage extends JDialog {
    private JPanel ListCalendarsPanel;
    private JTable CalendarsTable;
    private JPanel CalendarsPanel;

    private User user;

    public ListCalendarsPage(JFrame parent){
        super(parent);
        setTitle("Naptáraim");
        setContentPane(CalendarsPanel);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setForeground(Color.white);

        final String DB_URL = "jdbc:mysql://localhost/calendar?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM `calendar` WHERE `user_id = ` + user.ID"; //A bejelentkezett user adati kellenenek inkább
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) CalendarsTable.getModel();

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++) {
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);
            String title;
            while (rs.next()) {
                title = rs.getString(3); //Harmadik oszlopban van a title az adatbázisban
                //Gombokat kell itt megjeleníteni
                String[] row = { title };
                model.addRow(row);
            }
            stm.close();
            conn.close();
        }catch (Exception e){
            //JDBCTutorialUtilities.printSQLException(e);
        }


        setVisible(true);
    }
}
