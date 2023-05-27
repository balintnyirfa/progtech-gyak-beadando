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

    public AddEventPage(JFrame parent){
        super(parent);
        setTitle("Új esemény létrehozása");
        setContentPane(createEvent);
        setMinimumSize(new Dimension(450, 500));
        setModal(true);
        setLocationRelativeTo(parent);

        eventAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }
}
