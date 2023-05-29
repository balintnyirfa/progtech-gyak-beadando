package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarPage extends  JDialog {
    private JButton addEvent;
    private JPanel calendarPage;

    public CalendarPage(JFrame parent){
        super(parent);
        setTitle("Új esemény létrehozása");
        setContentPane(calendarPage);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddEventPage addEventPage = new AddEventPage(null);
                addEventPage.setVisible(true);
            }
        });

        setVisible(true);
    }
}
