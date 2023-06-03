package classes;

public class RowListener implements TableRowSelectionListener {
    int selectedrow = -1;
    @Override
    public void onRowSelected(int row) {
        selectedrow = row;
        GetSelectedRow();
    }
    public int GetSelectedRow(){
        return selectedrow;
    }
}
