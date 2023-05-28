package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEventPage extends JDialog{
    private JTextField eventTitle;
    private JTextField eventContent;
    private JTextField eventFromDate;
    private JTextField eventEndDate;
    private JButton eventAdd;
    private JPanel createEvent;
    private JButton visszaButton;

    public AddEventPage(JFrame parent){
        super(parent);
        setTitle("Új esemény létrehozása");
        setContentPane(createEvent);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        eventAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        visszaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ListCalendarsPage lp = new ListCalendarsPage(null);
                lp.setVisible(true);
            }
        });
        setVisible(true);
    }
}
