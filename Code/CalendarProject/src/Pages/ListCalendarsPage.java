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

/*class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}


class ButtonEditor extends DefaultCellEditor {
    protected JButton button;

    private String label;

    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            //
            //
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
            // System.out.println(label + ": Ouch!");
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}*/

public class ListCalendarsPage extends JDialog {
    private JPanel ListCalendarsPanel;
    private JTable CalendarsTable;
    private JButton visszaButton;
    private JButton listItemsBtn;
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

        //CalendarsTable.getColumn("Title").setCellRenderer(new ButtonRenderer());
        //CalendarsTable.getColumn("Title").setCellEditor(
        //       new ButtonEditor(new JCheckBox()));

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

                } catch (Exception exception) {
                    //JDBCTutorialUtilities.printSQLException(e);
                }
            }
        });

        setVisible(true);
    }
}
