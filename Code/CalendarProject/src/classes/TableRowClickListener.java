package classes;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableRowClickListener extends MouseAdapter {
    private JTable table;
    private TableRowSelectionListener tableRowSelectionListener;

    public TableRowClickListener(JTable table, TableRowSelectionListener tableRowSelectionListener) {
        this.table = table;
        this.tableRowSelectionListener = tableRowSelectionListener;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int row = table.rowAtPoint(event.getPoint());
        tableRowSelectionListener.onRowSelected(row);
    }
}
